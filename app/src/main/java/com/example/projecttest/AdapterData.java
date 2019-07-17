package com.example.projecttest;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class AdapterData extends BaseAdapter {

    String TAG = "AdapterData";

    private ArrayList<ShowDataModel> arrayList;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterData(Context context, ArrayList<ShowDataModel> arrayList) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.arrayList =arrayList;
    }


    @Override
    public int getCount() {
        if (arrayList.size() == 0) {
            return 0;
        } else {
            return arrayList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder resultHolder;
        if (convertView == null) {
            resultHolder = new AdapterData.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.dataview_layout, parent, false);

            resultHolder.dataUserName = convertView.findViewById(R.id.dataUserName);
            resultHolder.dataGender = convertView.findViewById(R.id.dataGender);
            resultHolder.dataArea = convertView.findViewById(R.id.dataArea);
            resultHolder.dataWork = convertView.findViewById(R.id.dataWork);
            resultHolder.dataImage = convertView.findViewById(R.id.dataImage);

            convertView.setTag(resultHolder);
        } else {
            resultHolder = (AdapterData.ViewHolder) convertView.getTag();
        }

        resultHolder.dataUserName.setText(arrayList.get(position).getName());
        resultHolder.dataGender.setText(arrayList.get(position).getGender());
        resultHolder.dataArea.setText(arrayList.get(position).getArea());
        resultHolder.dataWork.setText(arrayList.get(position).getWork());

        byte[] image = arrayList.get(position).getImage();
        Bitmap imageBitmap = DbBitmapUtility.getImage(image);
        if (imageBitmap != null) {
            resultHolder.dataImage.setImageBitmap(imageBitmap);
        } else {
            resultHolder.dataImage.setBackgroundResource(R.color.colorAccent);
        }

//        try {
//            if (jsonArray.length() > 0) {
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    ShowDataModel dataModel = new ShowDataModel();
//                    Gson gson = new Gson();
//
//                    String jsonObject = jsonArray.getJSONObject(i).toString();
//                    Log.d(TAG, "jsonObject " + jsonObject);
//                    dataModel = gson.fromJson(jsonObject, ShowDataModel.class);
//                    arrayList.add(dataModel);
//
//                    resultHolder.dataUserName.setText(arrayList.get(position).getName());
//                    resultHolder.dataGender.setText(arrayList.get(position).getGender());
//                    resultHolder.dataArea.setText(arrayList.get(position).getArea());
//                    resultHolder.dataWork.setText(arrayList.get(position).getWork());
//
//                    byte[] image = arrayList.get(position).getImage();
//                    Bitmap imageBitmap = DbBitmapUtility.getImage(image);
//                    if (imageBitmap != null) {
//                        resultHolder.dataImage.setImageBitmap(imageBitmap);
//                    } else {
//                        resultHolder.dataImage.setBackgroundResource(R.color.colorAccent);
//                    }
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return convertView;
    }

    static class ViewHolder {
        TextView dataUserName, dataGender, dataArea, dataWork;
        ImageView dataImage;
        RelativeLayout notificationLayout;
    }
}
