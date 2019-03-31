package com.swish.vtigerautomatecrm.Activities;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.swish.vtigerautomatecrm.Adapters.DrawerItemCustomAdapter;
import com.swish.vtigerautomatecrm.Interface.VolleyCallback;
import com.swish.vtigerautomatecrm.R;
import com.swish.vtigerautomatecrm.Utils.DrawerDataModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements VolleyCallback{
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    DrawerDataModel[] drawerDataModels;
    String response;
    TextView moduleName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Get the list
        String menuData=getIntent().getStringExtra("MenuData");
        String[] menuArray=null;
        moduleName=(TextView)findViewById(R.id.module_name);
        try {
            JSONObject listObject = new JSONObject(menuData);
            JSONObject resultObject=listObject.getJSONObject("result");
            JSONArray listArray=resultObject.getJSONArray("types");
            menuArray=new String[listArray.length()];
            drawerDataModels=new DrawerDataModel[listArray.length()];
            for(int i=0;i<listArray.length();i++){
                menuArray[i]=listArray.get(i).toString();
                drawerDataModels[i]=new DrawerDataModel( menuArray[i].toString());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        mNavigationDrawerItemTitles= menuArray;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        setupToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.drawer_list_view_item_row, drawerDataModels,HomeActivity.this);
        mDrawerList.setAdapter(adapter);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

    }
    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }
    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onSuccess(String result, String type) {
        if(type.equals("HomeOne")){
            this.response=result;
            try{
                Log.e("Home1 resp",result);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        else if(type.equals("HomeTwo")){
            try {
                LinearLayout myLayout = (LinearLayout) findViewById(R.id.content_view);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT,    LinearLayout.LayoutParams.WRAP_CONTENT);
                TextView[] pairs;
                EditText[] editTexts;
                JSONObject mainObkject = new JSONObject(result);
                JSONObject resultObject=mainObkject.getJSONObject("result");
                //Lable
                String lable=resultObject.getString("label");
                moduleName.setText(lable);
                JSONArray feildsArray=resultObject.getJSONArray("fields");
                pairs=new TextView[feildsArray.length()];
                editTexts=new EditText[feildsArray.length()];
                for(int i=0;i<feildsArray.length();i++){
                    JSONObject feildObject=feildsArray.getJSONObject(i);
                    String name=feildObject.get("label").toString()+" :";
                    //Views
                    pairs[i] = new TextView(this);
                    editTexts[i]=new EditText(this);
                    editTexts[i].setPadding(2,2,2,8);
                    pairs[i].setTextSize(15);
                    pairs[i].setTypeface(null, Typeface.BOLD);
                    pairs[i].setLayoutParams(lp);
                    pairs[i].setId(i);
                    pairs[i].setPadding(2,8,2,2);
                    pairs[i].setText(name);
                    myLayout.addView(pairs[i]);
                    myLayout.addView(editTexts[i]);
                }

            }
            catch (Exception e){

            }

        }
    }

    @Override
    public void onSuccessLogin(String result, String type) {

    }
}