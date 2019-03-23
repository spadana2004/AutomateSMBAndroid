package com.swish.vtigerautomatecrm.Interface;

import android.content.Context;

import org.json.JSONObject;

import java.util.Map;

public interface VolleyInterface {

    //Making GET JSON Object request
    public void getJSONObjRequestVolley(VolleyCallback volleyCallback,String url);

    //Making GET JSON Array request
    public void getJSONArrayRequestVolley(String url);

    //Making GET  String request
    public void getStringPrequestVolley(String url);

    //Making POST request with parameters without Headers
    public void postJSONObejctWithParams(String url, Map<String,String> params);

    //Making POST JSON Object request With Headers and with out Params
    public void postJSONObjectWithHeadersAndParams(String url, final Map<String, String> requestbody);

    //Making GET  JSON Object request with  headers and with params
    public void getJSONObjectWithHeadAndParams(String url, final Map<String, String> requestbody);

    //Making a POST String request with headers and with params
    public void getStringRequestWithHeadersWithParams(String url, final Map<String, String> requestbody);

    //Making  POST String ,Headers and Params
    public void postStringRequestWithHeadersWithParams(String url, final Map<String, String> requestbody);

    //Making Multipart form request
    public void postJSOnRequestMutipart(String url, final Map<String, String> requestbody,VolleyCallback volleyCallback,int type);
}