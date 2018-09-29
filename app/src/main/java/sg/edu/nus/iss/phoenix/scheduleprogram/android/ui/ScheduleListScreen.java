package sg.edu.nus.iss.phoenix.scheduleprogram.android.ui;

/**
 * Created by SuganthiSugumar on 2018/9/21.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;

public class ScheduleListScreen extends AppCompatActivity {
    private static final String TAG = ScheduleListScreen.class.getName();

    private ListView mListView;
    private ScheduleProgramAdapter mSPAdapter;
    private ProgramSlot selectedPS = null;
    private Button CreateBtn=null;
    private Button CopyBtn=null;
    private Button DeleteBtn=null;
    private Button BrowseBtn=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_program_list);



        // Setup FAB to open EditorActivity
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getMaintainScheduleController().selectCreateScheduleProgram();
            }
        });*/

        CreateBtn =(Button) findViewById(R.id.CreateProgramSlotButton);
        BrowseBtn =(Button) findViewById(R.id.BrowsePSbutton);

        CreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getMaintainScheduleController().selectCreateScheduleProgram();
            }
        });
        BrowseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getReviewSelectScheduledProgramController().startUseCase();
            }
        });

/*
        mListView = (ListView) findViewById(R.id.schedule_pm_list);
        mListView.setAdapter(mSPAdapter);

        // Setup the item selection listener
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Log.v(TAG, "Program Slot at position " + position + " selected.");
                ProgramSlot ps = (ProgramSlot) adapterView.getItemAtPosition(position);
                // Log.v(TAG, "Program Slot name is " + ps.getProgramSlotName());
                selectedPS = ps;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // your stuff
            }
        });*/
    }

  /*  @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListView.setSelection(0);

        ControlFactory.getMaintainScheduleController().onDisplayProgramSlotList(this);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "View" menu option
            case R.id.action_view:
                if (selectedPS == null) {
                    // Prompt for the selection of a radio program.
                    Toast.makeText(this, "Select a program slot first! Use arrow keys on emulator", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "There is no selected program slot.");
                }
                else {
                    Log.v(TAG, "Viewing program slot: " + selectedPS.getName() + "...");
                    ControlFactory.getMaintainScheduleController().selectEditScheduleProgram(selectedPS);
                }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        ControlFactory.getMaintainScheduleController().maintainedSchedule();
    }


}
