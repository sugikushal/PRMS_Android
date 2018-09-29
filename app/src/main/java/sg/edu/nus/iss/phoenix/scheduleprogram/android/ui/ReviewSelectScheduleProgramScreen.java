package sg.edu.nus.iss.phoenix.scheduleprogram.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;

public class ReviewSelectScheduleProgramScreen extends AppCompatActivity {
    private ListView mListView;
    private ScheduleProgramAdapter mSPAdapter;
    private ProgramSlot selectedPS = null;
    private Button OkButton=null;
    private Button cancelButton=null;
    private String copyaction=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_select_schedule_program_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayList<ProgramSlot> programSlots = new ArrayList<ProgramSlot>();
        mSPAdapter = new ScheduleProgramAdapter(this, programSlots);

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

        OkButton=(Button)findViewById(R.id.selectButton);
        cancelButton=(Button)findViewById(R.id.cancelButton);
        copyaction=getIntent().getStringExtra("action");

        OkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getReviewSelectScheduledProgramController().selectProgramSlot(selectedPS,copyaction);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getMaintainScheduleController().reviewedSelectprogramslot();
            }
        });
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListView.setSelection(0);

        ControlFactory.getReviewSelectScheduledProgramController().onDisplay(this);
    }
    @Override
    public void onBackPressed() {
        ControlFactory.getMaintainScheduleController().reviewedSelectprogramslot();
    }
    public void showSchedulePrograms(List<ProgramSlot> programSlots) {
        mSPAdapter.clear();
        for (int i = 0; i < programSlots.size(); i++) {
            mSPAdapter.add(programSlots.get(i));
        }
    }

}
