package sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate;

/**
 * Created by SuganthiSugumar on 2018/9/21.
 */

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import sg.edu.nus.iss.phoenix.scheduleprogram.android.controller.MaintainScheduleController;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_SCHEDULE_PROGRAM;

public class DeleteScheduleDelegate extends AsyncTask<ProgramSlot, Void, Boolean> {
    // Tag for logging
    private static final String TAG = DeleteScheduleDelegate.class.getName();

    private final MaintainScheduleController scheduleController;

    public DeleteScheduleDelegate(MaintainScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    @Override
    protected Boolean doInBackground(ProgramSlot... params) {
        String date = null;
        String startTime = params[0].getStartTime();
        String time = startTime.split("\\+")[0];
        try {
            date = URLEncoder.encode(params[0].getDate(), "UTF-8");
            //startTime = URLEncoder.encode(params[0].getStartTime(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.v(TAG, e.getMessage());
            return new Boolean(false);
        }
        Uri builtUri = Uri.parse(PRMS_BASE_URL_SCHEDULE_PROGRAM).buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri,"delete").buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri, date).buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri,"startTime").buildUpon().build();
        builtUri = Uri.withAppendedPath(builtUri, time).buildUpon().build();
        Log.v(TAG, builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.v(TAG, e.getMessage());
            return new Boolean(false);
        }

        boolean success = false;
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("DELETE");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            httpURLConnection.setUseCaches (false);
            System.out.println(httpURLConnection.getResponseCode());
            Log.v(TAG, "Http DELETE response " + httpURLConnection.getResponseCode());
            success = true;
        } catch (IOException exception) {
            Log.v(TAG, exception.getMessage());
        } finally {
            if (httpURLConnection != null) httpURLConnection.disconnect();
        }
        return new Boolean(success);
    }

    /**
     *onPostExecute-method.This method is to execute schedule controller.
     * @param result ,result for whether schedule controller execute.
     */
    @Override
    protected void onPostExecute(Boolean result) {
        scheduleController.scheduleDeleted(result.booleanValue());
    }
}
