package com.swish.vtigerautomatecrm.VolleyWebservice;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.swish.vtigerautomatecrm.ApplicationPackage.AppApplication;
import com.swish.vtigerautomatecrm.Constants.AppConstants;
import com.swish.vtigerautomatecrm.Interface.VolleyCallback;
import com.swish.vtigerautomatecrm.Interface.VolleyInterface;
import com.swish.vtigerautomatecrm.Utils.VolleyMultipartRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.e;

public class NetworkOperations implements VolleyInterface {

    private static NetworkOperations operations;

    private NetworkOperations(){

    }
    public static NetworkOperations getInstance(){

        if(operations==null){
            operations=new NetworkOperations();
            return operations;
        }
        else{
            return operations;
        }
    }

    //Making GET request with json object request
    @Override
    public void getJSONObjRequestVolley(final VolleyCallback volleyCallback,String url) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Response",response.toString());
                        volleyCallback.onSuccess(response.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Data Response",error.toString());

            }
        });
        AppApplication.getInstance().addToRequestQueue(jsonObjReq, AppConstants.NETWORKTAG);
    }

    //Making GET request with json array request
    @Override
    public void getJSONArrayRequestVolley(String url) {
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        AppApplication.getInstance().addToRequestQueue(req, AppConstants.NETWORKTAG);

    }

    //Making GET request with a String request
    @Override
    public void getStringPrequestVolley(String url) {
        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppApplication.getInstance().addToRequestQueue(strReq,AppConstants.NETWORKTAG);

    }

    //Making POST request with parameters without Headers
    @Override
    public void postJSONObejctWithParams(String url, final Map<String,String> requestbody) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = requestbody;
                return params;
            }
        };

    }

    //Making POST request With Headers with out Params
    @Override
    public void postJSONObjectWithHeadersAndParams(String url, final Map<String, String> requestbody) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(requestbody),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(AppConstants.NETWORKTAG, response.toString());


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }

        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppApplication.getInstance().addToRequestQueue(jsonObjReq,AppConstants.NETWORKTAG);
    }


    //JSONbject Request with POST and Params
    public void getJSONObjectWithHeadAndParams(String url, final Map<String, String> requestbody) {

        try {
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(requestbody),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {


                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {


                }
            }) {


                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    return params;
                }
            };

            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(20000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            AppApplication.getInstance().addToRequestQueue(jsonObjReq, AppConstants.NETWORKTAG);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    //Making GET request with With headers and with params
    public void getStringRequestWithHeadersWithParams(String url, final Map<String, String> requestbody) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error-->", error.toString());
                //asyncTaskCompleteListener.onError(servicecode, error, null);
                if (error instanceof NoConnectionError) {

                }

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if(!requestbody.isEmpty()){
                    Log.e("requestbody-->", requestbody.toString());
                    return requestbody;
                }
                else {
                    return null;
                }
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppApplication.getInstance().addToRequestQueue(stringRequest,AppConstants.NETWORKTAG);
    }

    //Making POST request with With headers and with params
    public void postStringRequestWithHeadersWithParams(final String url, final Map<String, String> requestbody) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error-->", error.toString());
                if (error instanceof NoConnectionError) {

                }

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Content-Type", "application/json");
                return params;
            }

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                if(!requestbody.isEmpty()){
                    Log.e("requestbody-->", requestbody.toString());
                    return requestbody;
                }
                else{
                    return null;
                }

            }


        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppApplication.getInstance().addToRequestQueue(stringRequest,AppConstants.NETWORKTAG);
    }

    public void postJSOnRequestMutipart(String url, final Map<String, String> requestbody, final VolleyCallback volleyCallback, final int type){
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {

                String resultResponse = new String(response.data);
                Log.e("Response",resultResponse);
                volleyCallback.onSuccessLogin(resultResponse);
                // parse success output
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                if(type==1) {
                    params.put("operation", "login");
                    params.put("username", "admin");
                    params.put("accessKey", requestbody.get("requestbody"));
                }
                if(type==2) {
                    try {

                        JSONObject object = new JSONObject("{\"lastname\":\"admin\",\"assigned_user_id\":\"19x1\"}");
                        Log.e("Test",object.toString());
                        params.put("operation", "create");
                        params.put("sessionName", requestbody.get("ssid"));
                        params.put("element", "{\"lastname\":\"admin\",\"assigned_user_id\":\"19x1\"}");
                        params.put("elementType", "Contacts");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                if(type==3) {
                    try {
                        params.put("operation", "update");
                        params.put("sessionName", requestbody.get("ssid"));
                        params.put("element", "{\"id\":\"12x10\",\"firstname\":\"admin\",\"lastname\":\"admin\",\"assigned_user_id\":\"19x1\"}");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                if(type==4) {
                    try {
                        params.put("operation", "delete");
                        params.put("sessionName", requestbody.get("ssid"));
                        params.put("id", "12x10");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();


                return params;
            }
        };

        AppApplication.getInstance().addToRequestQueue(multipartRequest);
    }


}
