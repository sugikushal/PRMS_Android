package sg.edu.nus.iss.phoenix.scheduleprogram.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;

public class ScheduleScreen extends AppCompatActivity {
    private static final String TAG = ScheduleScreen.class.getName();
    private ProgramSlot psedit = null;
    private EditText mRPNameEditText;
    private EditText mPSDateEditText;
    private EditText mPSDurationEditText;
    private EditText mPSStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_program);

        mRPNameEditText = (EditText) findViewById(R.id.pgslot_name_text_view);
        mPSDateEditText = (EditText) findViewById(R.id.pgslot_date_text_view);
        mPSDurationEditText = (EditText) findViewById(R.id.pgslot_duration_text_view);
        mPSStartTime = (EditText) findViewById(R.id.pgslot_starttime_text_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    /**
     * This method is called after invalidateOptionsMenu(), so that the
     * menu can be updated (some menu items can be hidden or made visible).
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new program slot, hide the "Delete" menu item.
        if (psedit == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save radio program.
               if (psedit == null) { // Newly created.
                    Log.v(TAG, "Saving program slot " + mRPNameEditText.getText().toString() + "...");
                    Date date = formatDate(mPSDurationEditText.getText().toString());

                    ProgramSlot ps = new ProgramSlot(mRPNameEditText.getText().toString(),
                            mPSDurationEditText.getText().toString(),mPSDateEditText.getText().toString() ,mPSStartTime.getText().toString(),"presenter","producer");
                    ControlFactory.getMaintainScheduleController().selectCreateScheduleProgram(ps);
                }
                /*else { // Edited.
                    Log.v(TAG, "Saving radio program " + psedit.getName() + "...");
                    psedit.;
                    psedit.setRadioProgramDuration(mDurationEditText.getText().toString());
                    ControlFactory.getProgramController().selectUpdateProgram(rp2edit);
                }*/
                return true;
            // Respond to a click on the "Delete" menu option
           /* case R.id.action_delete:
                Log.v(TAG, "Deleting program slot " + psedit.getProgramName() + "...");
                ControlFactory.getMaintainScheduleController().selectDeleteProgram(psedit);
                return true;*/
            // Respond to a click on the "Cancel" menu option
            case R.id.action_cancel:
                Log.v(TAG, "Canceling creating/editing program slot...");
                ControlFactory.getMaintainScheduleController().selectCancelCreateEditSchedule();
                return true;
        }

        return true;
    }

    public  Date formatDate(String dateInString) {
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
        Date date = null;
        try {
            java.util.Date utilDate =  dateFormatter.parse(dateInString);
            date = new Date(utilDate.getTime());
        }catch(ParseException ex){
            Log.v(TAG,"Date parsing exception");
        }
        return date;
    }

    @Override
    public void onBackPressed() {
        Log.v(TAG, "Canceling creating/editing program slot...");
        ControlFactory.getMaintainScheduleController().selectCancelCreateEditSchedule();
    }
}
