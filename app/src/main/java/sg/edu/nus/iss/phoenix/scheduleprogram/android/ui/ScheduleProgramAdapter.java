package sg.edu.nus.iss.phoenix.scheduleprogram.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;

public class ScheduleProgramAdapter extends ArrayAdapter<ProgramSlot> {

    public ScheduleProgramAdapter(@NonNull Context context, ArrayList<ProgramSlot> programSlots) {
        super(context, 0, programSlots);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_schedule_program, parent, false);
        }
        ProgramSlot currentPS = getItem(position);

        EditText radioPMName = (EditText)listItemView.findViewById(R.id.pgslot_name_text_view);
        radioPMName.setText(currentPS.getName(), TextView.BufferType.NORMAL);
        radioPMName.setKeyListener(null); // This disables editing.

        EditText schedulePMDate = (EditText)listItemView.findViewById(R.id.pgslot_date_text_view);
        schedulePMDate.setText(currentPS.getDate(), TextView.BufferType.NORMAL);
        schedulePMDate.setKeyListener(null);

        EditText schedulePMDuration = (EditText)listItemView.findViewById(R.id.pgslot_duration_text_view);
        schedulePMDuration.setText(currentPS.getDuration(), TextView.BufferType.NORMAL);
        schedulePMDuration.setKeyListener(null);

        EditText schedulePMStartTime = (EditText)listItemView.findViewById(R.id.pgslot_starttime_text_view);
        schedulePMStartTime.setText(currentPS.getStartTime(), TextView.BufferType.NORMAL);
        schedulePMStartTime.setKeyListener(null);

        return listItemView;
    }
}
