package com.swish.vtigerautomatecrm.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.swish.vtigerautomatecrm.Activities.HomeActivity;
import com.swish.vtigerautomatecrm.Activities.LoginActivity;
import com.swish.vtigerautomatecrm.Constants.AppConstants;
import com.swish.vtigerautomatecrm.Interface.VolleyCallback;
import com.swish.vtigerautomatecrm.PreferenceManager.ContextSharedPreferenceManager;
import com.swish.vtigerautomatecrm.R;
import com.swish.vtigerautomatecrm.Utils.DrawerDataModel;
import com.swish.vtigerautomatecrm.VolleyWebservice.NetworkOperations;

import java.util.HashMap;

public class DrawerItemCustomAdapter extends ArrayAdapter<DrawerDataModel> {

    Context mContext;
    int layoutResourceId;
    DrawerDataModel data[] = null;
    SharedPreferences drawPrefs;
    VolleyCallback volleyCallback;
    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DrawerDataModel[] data,VolleyCallback volleyCallback) {
        super(mContext, layoutResourceId, data);
        ContextSharedPreferenceManager prefManager = ContextSharedPreferenceManager.getInstance();
        drawPrefs= prefManager.getContextSharedPrefs(mContext);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
        this.volleyCallback=volleyCallback;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);
        final DrawerDataModel folder = data[position];
        textViewName.setText(folder.name);
        textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,folder.name+" Clecked",Toast.LENGTH_SHORT).show();
                NetworkOperations obr1=NetworkOperations.getInstance();
                HashMap<String,String> map=new HashMap<>();
                String ssid=drawPrefs.getString("SSID", null);
                map.put("ssid",ssid);
                map.put("module",folder.name);
                obr1.postJSOnRequestMutipart(AppConstants.baseUrl+AppConstants.webserviceExtention,map,volleyCallback,"HomeOne");
                String type=folder.name;
                obr1.getJSONObjRequestVolley(volleyCallback,AppConstants.describeOperationsPart1+ssid+AppConstants.describeOperationsPart2+type,"HomeTwo");
            }
        });
        return listItem;
    }
}