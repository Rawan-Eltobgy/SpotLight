package com.example.eltobgy.spotlight.utlis;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eltobgy.spotlight.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Eltobgy on 02-Jul-18.
 */


public class Connector {

    //using Volley
    private String responseString = "";
    private Uri uri;
    private Context mContext;
    StringRequest stringRequest;

    public Connector() {
        stringRequest = null;
    }


    public ArrayList<News> parsingJSON(String response) {


        ArrayList<News> mNews = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                String title = jo.getString("title");
                String description = jo.getString("description");
                mNews.add(new News(title, description));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();


        }
        return mNews;}

    public static String createDefaultUrl(String countryCode) {
        Uri.Builder builder = Uri.parse(Constants.mBaseUrl).buildUpon()
                .appendQueryParameter(Constants.COUNTRY_PARAM, countryCode)
                .appendQueryParameter(Constants.API_KEY_PARAM, Constants.API_KEY);

        return builder.toString();

    }
    public static String createTechnologyUrl(String countryCode) {
        Uri.Builder builder = Uri.parse(Constants.mBaseUrl).buildUpon()
                .appendQueryParameter(Constants.CATEGORY_PARAM, Constants.NEWS_CATEGORY_TECHNOLOGY)
                .appendQueryParameter(Constants.COUNTRY_PARAM, countryCode)
                .appendQueryParameter(Constants.API_KEY_PARAM, Constants.API_KEY);

        return builder.toString();

    }
    public static String createSportsUrl(String countryCode) {
        Uri.Builder builder = Uri.parse(Constants.mBaseUrl).buildUpon()
                .appendQueryParameter(Constants.CATEGORY_PARAM, Constants.NEWS_CATEGORY_SPORTS)
                .appendQueryParameter(Constants.COUNTRY_PARAM, countryCode)
                .appendQueryParameter(Constants.API_KEY_PARAM, Constants.API_KEY);

        return builder.toString();

    }

    public static String createPoliticsUrl(String countryCode) {
        Uri.Builder builder = Uri.parse(Constants.mBaseUrl).buildUpon()
                .appendQueryParameter(Constants.CATEGORY_PARAM, Constants.NEWS_CATEGORY_POLITICS)
                .appendQueryParameter(Constants.COUNTRY_PARAM, countryCode)
                .appendQueryParameter(Constants.API_KEY_PARAM, Constants.API_KEY);

        return builder.toString();

    }



    public String sendRequest(Context context) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, uri.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseString = response.toString();
                Helper.showLog("Connection" , responseString);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseString = error.toString();
                Helper.showLog("Connection" , responseString);
            }
        }
        );
        Volley.newRequestQueue(context).add(jsonObjectRequest);

        return responseString;
    }



    /**
     * Check whether the device is offline or online
     *
     * @return connectivity status
     */
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
