package sg.edu.nus.iss.phoenix.scheduleprogram.android.ui;

/**
 * Created by GengHui on 2018/9/26.select_producer_button
 */


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Presenter;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Producer;


public class ReviewSelectProducerScreen extends AppCompatActivity {
    // Tag for logging
    private static final String TAG = ReviewSelectProducerScreen.class.getName();

    private ProducerAdapter mProAdapter;
    // private ArrayAdapter<String> adapter = null;
    private ListView mListView;
    private Producer selectedPro = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_select_producer);

        ArrayList< Producer> producers = new ArrayList<Producer>();
        // ArrayList<String> radioProgramNames = new ArrayList<String>();
        // mRPAdapter = new ArrayAdapter<String>(this, R.layout.activity_review_select_program,
        //        R.id.maintain_program_name_text_view, radioProgramNames);
        mProAdapter= new  ProducerAdapter(this, producers);

        mListView = (ListView) findViewById(R.id.review_select_pro_list);
        mListView.setAdapter(mProAdapter);

        // Setup the item selection listener
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Log.v(TAG, "Radio program at position " + position + " selected.");
                Producer pro =( Producer)adapterView.getItemAtPosition(position);

                // Log.v(TAG, "Radio program name is " + rp.getRadioProgramName());
                selectedPro = pro;
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

        ControlFactory.getReviewSelectProducerController().onDisplay(this);

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
                if (selectedPro == null) {
                    // Prompt for the selection of a radio program.
                    Toast.makeText(this, "Select a producer first! Use arrow keys on emulator", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "There is no selected producer.");
                }
                else {
                    Log.v(TAG, "Selected producer: " + selectedPro.getProducerId() + "...");
                    ControlFactory.getReviewSelectProducerController().selectProducer(selectedPro);
                    super.onBackPressed();
                }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void showProducers(List<Producer> producers) {
        mProAdapter.clear();
        for (int i = 0; i < producers.size(); i++) {
            mProAdapter.add(producers.get(i));
        }
    }
}
