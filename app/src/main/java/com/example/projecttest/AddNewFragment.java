package com.example.projecttest;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.example.projecttest.DataBase.COL_AREA;
import static com.example.projecttest.DataBase.COL_GENDER;
import static com.example.projecttest.DataBase.COL_ID;
import static com.example.projecttest.DataBase.COL_IMAGE;
import static com.example.projecttest.DataBase.COL_NAME;
import static com.example.projecttest.DataBase.COL_PHONE;
import static com.example.projecttest.DataBase.COL_WORK;
import static com.example.projecttest.DataBase.DB_TABLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewFragment extends Fragment {


    ArrayList<ValuesModel> genderArrayList, areaArrayList, cateArrayList;
    ValuesModel genderModel, areaModel, cateModel;

    private static final int SELECT_PICTURE = 100;
    private Uri selectedImageUri;

    Spinner genderSpinner, areaSpinner, categorySpinner;
    Button submit, uploadImage;
    EditText mobileNo, name;
    ImageView imageView;
    TextInputLayout mobileNoTILayout, nameTILayout;

    SQLiteDatabase sqLiteDatabase;
    DataBase dataBase;
    ContentValues cv = new ContentValues();

    String userNameStr, phoneStr;
    String genderLableStr, genderLableIdStr;
    String areaLableStr, areaLableIdStr;
    String cateLableStr, cateLableIdStr;

    public AddNewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_add_new, container, false);

        dataBase = new DataBase(getActivity());
        sqLiteDatabase = dataBase.getWritableDatabase();

        genderSpinner = myview.findViewById(R.id.genderSpinner);
        areaSpinner = myview.findViewById(R.id.areaSpinner);
        categorySpinner = myview.findViewById(R.id.categorySpinner);

        submit = myview.findViewById(R.id.submit);
        uploadImage = myview.findViewById(R.id.uploadImage);
        imageView = myview.findViewById(R.id.imageView);
        mobileNo = myview.findViewById(R.id.mobileNo);
        name = myview.findViewById(R.id.name);
        mobileNoTILayout = myview.findViewById(R.id.mobileNoTILayout);
        nameTILayout = myview.findViewById(R.id.nameTILayout);

        genderArrayList = new ArrayList<ValuesModel>();
        for (int i = 0; i < 1; i++) {
            genderArrayList.add(new ValuesModel("Select Gender", "Select Gender"));
            genderArrayList.add(new ValuesModel("male", "Male"));
            genderArrayList.add(new ValuesModel("female", "Female"));
        }

        areaArrayList = new ArrayList<ValuesModel>();
        for (int i = 0; i < 1; i++) {
            areaArrayList.add(new ValuesModel("Select Area", "Select Area"));
            areaArrayList.add(new ValuesModel("hyderabad", "Hyderabad"));
            areaArrayList.add(new ValuesModel("secunderabad", "Secunderabad"));
            areaArrayList.add(new ValuesModel("l-b-nagar", "L B Nagar"));
            areaArrayList.add(new ValuesModel("dilsukhnagar", "Dilsukhnagar"));
            areaArrayList.add(new ValuesModel("ameerpet", "Ameerpet"));
            areaArrayList.add(new ValuesModel("kukatpally", "Kukatpally"));
        }

        cateArrayList = new ArrayList<ValuesModel>();
        for (int i = 0; i < 1; i++) {
            cateArrayList.add(new ValuesModel("Select Work", "Select Work"));
            cateArrayList.add(new ValuesModel("student", "Student"));
            cateArrayList.add(new ValuesModel("freelancer", "Freelancer"));
            cateArrayList.add(new ValuesModel("self-employed", "Self Employed"));
            cateArrayList.add(new ValuesModel("private-job", "Private Job"));
        }

        genderSpinner.setAdapter(setAdapterSpinner(genderArrayList));
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = genderSpinner.getSelectedItem().toString();
                int selectedPosition = genderSpinner.getSelectedItemPosition();

                if (selectedPosition != 0) {
                    genderLableIdStr = genderArrayList.get(selectedPosition).getLableId();
                    genderLableStr = genderArrayList.get(selectedPosition).getLable();
//                    Toast.makeText(getActivity(), genderLableStr + " " + genderLableIdStr, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        areaSpinner.setAdapter(setAdapterSpinner(areaArrayList));
        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = areaSpinner.getSelectedItem().toString();
                int selectedPosition = areaSpinner.getSelectedItemPosition();

                if (selectedPosition != 0) {
                    areaLableIdStr = areaArrayList.get(selectedPosition).getLableId();
                    areaLableStr = areaArrayList.get(selectedPosition).getLable();
//                    Toast.makeText(getActivity(), areaLableStr + " " + areaLableIdStr, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        categorySpinner.setAdapter(setAdapterSpinner(cateArrayList));
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = categorySpinner.getSelectedItem().toString();
                int selectedPosition = categorySpinner.getSelectedItemPosition();

                if (selectedPosition != 0) {
                    cateLableIdStr = cateArrayList.get(selectedPosition).getLableId();
                    cateLableStr = cateArrayList.get(selectedPosition).getLable();
//                    Toast.makeText(getActivity(), cateLableStr + " " + cateLableIdStr, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userNameStr = name.getText().toString();
                phoneStr = mobileNo.getText().toString();
                byte[] image = saveImageInDB();

                if (userNameStr.equalsIgnoreCase("") || userNameStr.isEmpty()) {
                    nameTILayout.setError("Please Enter Valid Name");
                } else if (phoneStr.equalsIgnoreCase("") || phoneStr.isEmpty() || phoneStr.length() != 10) {
                    nameTILayout.setError("Please Enter Valid Mobile No");
                } else {
                    addEntry(userNameStr, phoneStr, genderLableStr, areaLableStr, cateLableStr, image);
                    ShowDataFragment fragment = new ShowDataFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.MainAcLayout, fragment).addToBackStack(null).commit();
                }


            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });


        return myview;
    }

    public void addEntry(String name, String phone, String gender, String area, String work, byte[] image) throws SQLiteException {
        sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_PHONE, phone);
        cv.put(COL_GENDER, gender);
        cv.put(COL_AREA, area);
        cv.put(COL_WORK, work);
        image = saveImageInDB();
        cv.put(COL_IMAGE, image);

        long rowInserted = sqLiteDatabase.insert(DB_TABLE, null, cv);
        if (rowInserted != -1)
            Toast.makeText(getActivity(), "New row added, row id: " + rowInserted, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Something wrong", Toast.LENGTH_SHORT).show();
    }

    public ArrayAdapter setAdapterSpinner(ArrayList<ValuesModel> arrayList) {
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, arrayList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                    tv.setPadding(40, 0, 0, 0);
                } else {
                    tv.setTextColor(Color.BLACK);
                    tv.setPadding(40, 0, 0, 0);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapter;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    imageView.setImageURI(selectedImageUri);
                }
            }
        }
    }

    byte[] saveImageInDB() {
        try {
            InputStream iStream = getActivity().getContentResolver().openInputStream(selectedImageUri);
            return DbBitmapUtility.getBytes(iStream);
        } catch (IOException ioe) {
            Log.e("TAG", "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            return null;
        }
    }


}
