package sg.edu.nus.iss.phoenix.scheduleprogram.android.delegate;

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

import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.controller.MaintainScheduleController;
import sg.edu.nus.iss.phoenix.scheduleprogram.android.controller.ReviewSelectScheduledProgramController;
import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_SCHEDULE_PROGRAM;

public class RetrieveScheduleDelegate extends AsyncTask<String, Void, String> {
    private static final String TAG = RetrieveScheduleDelegate.class.getName();

    private MaintainScheduleController maintainScheduleController = null;
    private ReviewSelectScheduledProgramController reviewSelectScheduledProgramController = null;

    public RetrieveScheduleDelegate(MaintainScheduleController maintainScheduleController) {
        this.reviewSelectScheduledProgramController = null;
        this.maintainScheduleController = maintainScheduleController;
    }

    public RetrieveScheduleDelegate(ReviewSelectScheduledProgramController reviewSelectScheduledProgramController) {
        this.maintainScheduleController = null;
        this.reviewSelectScheduledProgramController = reviewSelectScheduledProgramController;
    }

    @Override
    protected String doInBackground(String... params) {
        Uri builtUri1 = Uri.parse(PRMS_BASE_URL_SCHEDULE_PROGRAM).buildUpon().build();
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
        List<ProgramSlot> programSlots = new ArrayList<>();

        if (result != null && !result.equals("")) {
            try {
                JSONObject reader = new JSONObject(result);
                JSONArray psArray = reader.getJSONArray("pgSlots");

                for (int i = 0; i < psArray.length(); i++) {
                    JSONObject psJson = psArray.getJSONObject(i);
                    String name = psJson.getString("name");
                    String duration  = psJson.getString("duration");
                    String date = psJson.getString("date");
                    String startTime = psJson.getString("startTime");
                    String presenter = psJson.getString("presenter");
                    String producer = psJson.getString("producer");

                    programSlots.add(new ProgramSlot(name,duration,date,startTime,presenter,producer));
                }
            } catch (JSONException e) {
                Log.v(TAG, e.getMessage());
            }
        } else {
            Log.v(TAG, "JSON response error.");
        }

        if (maintainScheduleController != null)
            maintainScheduleController.scheduleRetrieved(programSlots);
        /*else if (reviewSelectScheduledProgramController != null)
            reviewSelectScheduledProgramController.scheduleRetrieved(programSlots);*/
    }
}
