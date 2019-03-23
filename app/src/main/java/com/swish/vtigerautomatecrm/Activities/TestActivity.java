package com.swish.vtigerautomatecrm.Activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.swish.vtigerautomatecrm.Constants.AppConstants;
import com.swish.vtigerautomatecrm.Interface.VolleyCallback;
import com.swish.vtigerautomatecrm.R;
import com.swish.vtigerautomatecrm.VolleyWebservice.NetworkOperations;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class TestActivity extends AppCompatActivity implements VolleyCallback {
    Button listTypes,describeoprn,create,retrieve,update,delete;
    String ssid;
    int state;
    String typeString;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        ssid=getIntent().getStringExtra("ssid");
        Log.e("Session id",ssid);
        listTypes=(Button)findViewById(R.id.listtypes);
        describeoprn=(Button)findViewById(R.id.describeoprn);
        describeoprn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkOperations obr1=NetworkOperations.getInstance();
                obr1.getJSONObjRequestVolley(TestActivity.this,AppConstants.describeOperationsPart1+ssid+AppConstants.describeOperationsPart2+typeString);
                state=2;
            }
        });
        listTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkOperations obr1=NetworkOperations.getInstance();
                obr1.getJSONObjRequestVolley(TestActivity.this,AppConstants.getList+ssid);
                state=1;
            }
        });
        create=(Button)findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkOperations obr1=NetworkOperations.getInstance();
                HashMap<String,String> map=new HashMap<>();
                map.put("ssid",ssid);
                obr1.postJSOnRequestMutipart(AppConstants.baseUrl+AppConstants.webserviceExtention,map,TestActivity.this,2);
                state=3;
            }
        });
        retrieve=(Button)findViewById(R.id.retrieve);
        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Retrieve Link",""+AppConstants.baseUrl+AppConstants.webserviceExtention+AppConstants.retrieve+ssid+"&id=12x6");
                NetworkOperations obr1=NetworkOperations.getInstance();
                obr1.getJSONObjRequestVolley(TestActivity.this,AppConstants.baseUrl+AppConstants.webserviceExtention+AppConstants.retrieve+ssid+"&id=12x10");
                state=4;
            }
        });
        update=(Button)findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkOperations obr1=NetworkOperations.getInstance();
                HashMap<String,String> map=new HashMap<>();
                map.put("ssid",ssid);
                obr1.postJSOnRequestMutipart(AppConstants.baseUrl+AppConstants.webserviceExtention,map,TestActivity.this,3);
                state=5;
            }
        });
        delete=(Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkOperations obr1=NetworkOperations.getInstance();
                HashMap<String,String> map=new HashMap<>();
                map.put("ssid",ssid);
                obr1.postJSOnRequestMutipart(AppConstants.baseUrl+AppConstants.webserviceExtention,map,TestActivity.this,4);
                state=6;
            }
        });
    }

    @Override
    public void onSuccess(String result) {

        if(state==1){
            try {
                Log.e("GET LIST Response ",result);
                JSONObject object = new JSONObject(result);
                JSONObject resultObj=object.getJSONObject("result");
                JSONArray array=resultObj.getJSONArray("types");
                typeString=array.getString(10);
                Log.e("TypeString",typeString);
            }
            catch (Exception e){

            }
        }
        if(state==2){
            try {
                Log.e("Describe Operation ",result);
            }
            catch (Exception e){

            }
        }
        if(state==3){
            try {
                Log.e("Create Operation ",result);
            }
            catch (Exception e){

            }
        }
        if(state==4){
            try {
                Log.e("Retrieve Operation ",result);
            }
            catch (Exception e){

            }
        }
        if(state==5){
            try {
                Log.e("Update Operation ",result);
            }
            catch (Exception e){

            }
        }
        state=0;
    }

    @Override
    public void onSuccessLogin(String result) {

    }
}
