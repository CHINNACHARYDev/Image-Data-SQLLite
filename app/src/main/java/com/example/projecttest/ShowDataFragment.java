package com.example.projecttest;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.projecttest.DataBase.DB_TABLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowDataFragment extends Fragment {

    public final static String TAG = "ShowDataFragment";

    SQLiteDatabase sqLiteDatabase;
    DataBase dataBase;

    ArrayList<ShowDataModel> arrayList = new ArrayList<>();
    AdapterData dataAdapter;

    ListView listviewData;

    public ShowDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_show_data, container, false);

        dataBase = new DataBase(getActivity());
        sqLiteDatabase = dataBase.getWritableDatabase();

        listviewData = myview.findViewById(R.id.listviewData);

//        JSONArray jsonArray = getResults();
//        Log.d(TAG, "jsonArray " + jsonArray);
        ArrayList<ShowDataModel> arrayList = getAllDataDB();
        Log.d(TAG, "arrayList " + arrayList);
        dataAdapter = new AdapterData(getActivity(), arrayList);
        listviewData.setAdapter(dataAdapter);

        return myview;
    }

//    private void loadToDosFromDatabase() {
////        String query = "SELECT * FROM ToDotasks WHERE todotype='" + "Pending'";
////        Cursor cursor = SQLdb.rawQuery(query, null);
//
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//
//                arrayList.add(new ShowDataModel(
//                        cursor.getInt(0),
//                        cursor.getString(1),
//                        cursor.getString(2),
//                        cursor.getString(3),
//                        cursor.getString(4),
//                        cursor.getString(5),
//                        cursor.getString(6)
//                ));
//            } while (cursor.moveToNext());
//            //passing the databasemanager instance this time to the toDoAdapter
//            JSONArray jsonArray = new JSONArray();
//            dataAdapter = new AdapterData(getActivity(), dataBase, jsonArray);
//            listviewData.setAdapter(dataAdapter);
//        }
//    }


    public JSONArray getResults() {

//        Cursor cursor = dataBase.getAllData();
        Cursor cursor = dataBase.getAllData();

        JSONArray resultSet = new JSONArray();
        JSONObject returnObj = new JSONObject();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();
            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        if (cursor.getString(i) != null && cursor.getBlob(i) != null) {
                            Log.d(TAG, "CursorGetString(i) " + cursor.getString(i));
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } else {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    } catch (Exception e) {
                        Log.d(TAG, "Error Exception " + e.getMessage());
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d(TAG, resultSet.toString());
        return resultSet;
    }

    public ArrayList<ShowDataModel> getAllDataDB() {
        ArrayList<ShowDataModel> arrayList = new ArrayList<ShowDataModel>();
// Select All Query
        String query = "SELECT * FROM " + DB_TABLE;
        SQLiteDatabase db = dataBase.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
// looping through all rows and adding to list
        if (cursor != null && cursor.getCount() > 0){
            if (cursor.moveToFirst()) {
                do {
                    ShowDataModel contact = new ShowDataModel();
                    contact.setId(cursor.getInt(0));
                    contact.setName(cursor.getString(1));
                    contact.setPhone(cursor.getString(2));
                    contact.setGender(cursor.getString(3));
                    contact.setArea(cursor.getString(4));
                    contact.setWork(cursor.getString(5));
                    contact.setImage(cursor.getBlob(6));
// Adding contact to list
                    arrayList.add(contact);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
// close inserting data from database
        db.close();
// return contact list
        return arrayList;
    }


}
