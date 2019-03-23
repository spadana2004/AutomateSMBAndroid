package com.swish.vtigerautomatecrm.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.swish.vtigerautomatecrm.Constants.AppConstants;
import com.swish.vtigerautomatecrm.Interface.VolleyCallback;
import com.swish.vtigerautomatecrm.R;
import com.swish.vtigerautomatecrm.Utils.MD5;
import com.swish.vtigerautomatecrm.VolleyWebservice.NetworkOperations;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements VolleyCallback{
    EditText url,userName,accessKey;
    Button login;
    String result,crmAccessKey;
    NetworkOperations operations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url=(EditText)findViewById(R.id.url);
        userName=(EditText)findViewById(R.id.username);
        accessKey=(EditText)findViewById(R.id.accesskey);
        login=(Button)findViewById(R.id.login) ;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crmUrl=url.getText().toString();
                crmUrl=AppConstants.challange;
                String crmUserName=userName.getText().toString();
                crmAccessKey=accessKey.getText().toString();
                //Network call
                operations=NetworkOperations.getInstance();
                crmUrl=crmUrl+crmUserName;
                Log.e("Url",crmUrl);
                operations.getJSONObjRequestVolley(LoginActivity.this,crmUrl);
            }
        });
    }

    @Override
    public void onSuccess(String result) {
        this.result=result;
        String token;
        if(result!=null){
            Log.e("Mynew Dtat",result);
            try {
                JSONObject object = new JSONObject(result);
                String successString=object.getString("success");
                if(successString.equals("true")){
                    JSONObject objectResult =object.getJSONObject("result");
                    token=objectResult.getString("token");
                    Log.e("Mynew token",token);
                    String accesskey= MD5.getMd5(token+"u4uAssBnhzI964Us");
                    Log.e("Access key ",accesskey);
                    Map<String,String> dataMap=new HashMap<>();
                    dataMap.put("requestbody",accesskey);
                    String url=AppConstants.login;
                    operations.postJSOnRequestMutipart(url,dataMap,LoginActivity.this,1);
                }
            }
            catch (JSONException e){
                e.getStackTrace();
            }

        }
    }

    @Override
    public void onSuccessLogin(String result) {
        Log.e("Login response",result);
        try{
            JSONObject obj=new JSONObject(result);
            String status=obj.getString("success");
            if(status.equals("true")){
                JSONObject sessionObj=obj.getJSONObject("result");
                String ssid=sessionObj.getString("sessionName");
                Intent i=new Intent(LoginActivity.this,TestActivity.class);
                i.putExtra("ssid",ssid);
                startActivity(i);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
