package sg.edu.nus.iss.phoenix.scheduleprogram.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import sg.edu.nus.iss.phoenix.R;

public class ScheduleScreen extends AppCompatActivity {
    private static final String TAG = ScheduleScreen.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_program);
    }
}
