package sg.edu.nus.iss.phoenix.scheduleprogram.android.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;

public class ScheduleListScreen extends AppCompatActivity {
    private static final String TAG = ScheduleListScreen.class.getName();

    private ListView mListView;
    private ScheduleProgramAdapter mSPAdapter;
    private ProgramSlot selectedPS = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_program_list);

        ArrayList<ProgramSlot> programSlots = new ArrayList<ProgramSlot>();
        mSPAdapter = new ScheduleProgramAdapter(this, programSlots);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getMaintainScheduleController().selectCreateScheduleProgram();
            }
        });

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
        });
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
                    Log.v(TAG, "Viewing program slot: " + selectedPS.getProgramSlotName() + "...");
                    //ControlFactory.getProgramController().selectEditProgram(selectedPS);
                }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        ControlFactory.getProgramController().maintainedProgram();
    }
}
