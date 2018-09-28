package sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate;

/**
 * Created by GengHui on 2018/9/26.
 */

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sg.edu.nus.iss.phoenix.scheduleprogram.android.controller.ReviewSelectPresenterController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.controller.ReviewSelectProducerController;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Presenter;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Producer;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_PRESENTER;
import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_PRODUCER;


public class RetrieveProducerDelegate extends AsyncTask<String, Void, String> {
    // Tag for logging
    private static final String TAG = RetrieveProducerDelegate.class.getName();
    private ReviewSelectProducerController reviewSelectProducerController = null;
    public RetrieveProducerDelegate(ReviewSelectProducerController reviewSelectProducerController) {

        this.reviewSelectProducerController = reviewSelectProducerController;
    }

    @Override
    protected String doInBackground(String... params) {
        Uri builtUri1 = Uri.parse(PRMS_BASE_URL_PRODUCER).buildUpon().build();
        Uri builtUri = Uri.withAppendedPath(builtUri1, params[0]).buildUpon().build();
        Log.v(TAG, builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.v(TAG, e.getMessage());
            return e.getMessage();
        }

        String jsonResp = null;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()) jsonResp = scanner.next();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
        }

        return jsonResp;
    }

    @Override
    protected void onPostExecute(String result) {

        List<Producer> producers =new ArrayList<>();
        if (result != null && !result.equals("")) {
            try {
                JSONObject reader = new JSONObject(result);
                JSONArray rpArray = reader.getJSONArray("userList");

                for (int i = 0; i < rpArray.length(); i++) {
                    JSONObject rpJson = rpArray.getJSONObject(i);

                    String name = rpJson.getString("id");

                    producers.add(new Producer(name));

                }
            } catch (JSONException e) {
                Log.v(TAG, e.getMessage());
            }
        } else {
            Log.v(TAG, "JSON response error.");
        }

        if (reviewSelectProducerController != null)
            reviewSelectProducerController.producerRetrieved(producers);


    }
}

