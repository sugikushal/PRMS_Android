package sg.edu.nus.iss.phoenix.scheduleprogram.android.ui;

/**
 * Created by GengHui on 2018/9/26.select_producer_button
 */


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.*;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Presenter;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;


public class ReviewSelectPresenterScreen extends AppCompatActivity {
    // Tag for logging
    private static final String TAG = ReviewSelectPresenterScreen.class.getName();

    private PresenterAdapter mRPAdapter;
    // private ArrayAdapter<String> adapter = null;
    private ListView mListView;
    private Presenter selectedRP = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_select_presenter);

        ArrayList<Presenter> presenters = new ArrayList<Presenter>();
        // ArrayList<String> radioProgramNames = new ArrayList<String>();
        // mRPAdapter = new ArrayAdapter<String>(this, R.layout.activity_review_select_program,
        //        R.id.maintain_program_name_text_view, radioProgramNames);
        mRPAdapter = new PresenterAdapter(this,presenters);

        mListView = (ListView) findViewById(R.id.review_select_pre_list);
        mListView.setAdapter(mRPAdapter);

        // Setup the item selection listener
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Log.v(TAG, "Radio program at position " + position + " selected.");
                Presenter rp =(Presenter)adapterView.getItemAtPosition(position);

                // Log.v(TAG, "Radio program name is " + rp.getRadioProgramName());
                selectedRP = rp;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // your stuff
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListView.setSelection(0);

        ControlFactory.getReviewSelectPresenterController().onDisplay(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_review_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "View" menu option
            case R.id.action_select:
                if (selectedRP == null) {
                    // Prompt for the selection of a radio program.
                    Toast.makeText(this, "Select a presenter first! Use arrow keys on emulator", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "There is no selected presenter.");
                }
                else {
                    Log.v(TAG, "Selected presenter: " + selectedRP.getPresenterId() + "...");
                    ControlFactory.getReviewSelectPresenterController().selectPresenter(selectedRP);
                    super.onBackPressed();
                }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void showPresenters(List<Presenter> presenters) {
        mRPAdapter.clear();
        for (int i = 0; i < presenters.size(); i++) {
            mRPAdapter.add(presenters.get(i));
        }
    }
}
