package sg.edu.nus.iss.phoenix.scheduleprogram.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

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
    KeyListener mPSDateEditTextKeyListener = null;
    KeyListener mPSStartKeyListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_program);

        mRPNameEditText = (EditText) findViewById(R.id.pgslot_name_text_view);
        mPSDateEditText = (EditText) findViewById(R.id.pgslot_date_text_view);
        mPSDurationEditText = (EditText) findViewById(R.id.pgslot_duration_text_view);
        mPSStartTime = (EditText) findViewById(R.id.pgslot_starttime_text_view);

        mPSDateEditTextKeyListener = mPSDateEditText.getKeyListener();
        mPSStartKeyListener = mPSStartTime.getKeyListener();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ControlFactory.getMaintainScheduleController().onDisplayScheduleProgram(this);
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
                    ProgramSlot ps = new ProgramSlot(mRPNameEditText.getText().toString(),
                            mPSDurationEditText.getText().toString(),mPSDateEditText.getText().toString() ,mPSStartTime.getText().toString(),"presenter","producer");
                    ControlFactory.getMaintainScheduleController().selectCreateScheduleProgram(ps);
                }
                else { // Edited.
                    Log.v(TAG, "Saving program slot update " + psedit.getName() + "...");
                    psedit.setName(mRPNameEditText.getText().toString());
                    psedit.setDuration(mPSDurationEditText.getText().toString());
                    psedit.setDate(mPSDateEditText.getText().toString());
                    psedit.setStartTime(mPSStartTime.getText().toString());
                    psedit.setProducer("producer");
                    psedit.setPresenter("presenter");
                    //todo presenter producer
                    /*psedit.setPresenter();
                    psedit.setProducer();*/
                    ControlFactory.getMaintainScheduleController().selectUpdateScheduleProgram(psedit);
                }
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

    @Override
    public void onBackPressed() {
        Log.v(TAG, "Canceling creating/editing program slot...");
        ControlFactory.getMaintainScheduleController().selectCancelCreateEditSchedule();
    }

    public void createScheduleProgram() {
        this.psedit = null;
        mRPNameEditText.setText("", TextView.BufferType.EDITABLE);
        mPSDurationEditText.setText("", TextView.BufferType.EDITABLE);
        mPSDateEditText.setText("", TextView.BufferType.EDITABLE);
        mPSStartTime.setText("",TextView.BufferType.EDITABLE);
        //todo presenter producer
        mPSDateEditText.setKeyListener(mPSDateEditTextKeyListener);
        mPSStartTime.setKeyListener(mPSStartKeyListener);
    }

    public void editScheduleProgram(ProgramSlot psedit) {
        this.psedit = psedit;
        if (psedit != null) {
            mRPNameEditText.setText(psedit.getName(), TextView.BufferType.EDITABLE);
            mPSDurationEditText.setText(psedit.getDuration(), TextView.BufferType.EDITABLE);
            mPSDateEditText.setText(psedit.getDate(), TextView.BufferType.NORMAL);
            mPSStartTime.setText(psedit.getStartTime(), TextView.BufferType.NORMAL);
            //todo presenter producer
            mPSDateEditText.setKeyListener(null);
            mPSStartTime.setKeyListener(null);
        }
    }
}
