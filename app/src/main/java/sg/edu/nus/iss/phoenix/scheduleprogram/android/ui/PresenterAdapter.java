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
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Presenter;

/**
 * Created by Administrator on 2018/9/27.
 */

public class PresenterAdapter  extends ArrayAdapter<Presenter> {

    public PresenterAdapter(@NonNull Context context, ArrayList<Presenter> presenters) {
        super(context, 0,presenters);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_presenter, parent, false);
        }
        //    Word currentWord = getItem(position);
        Presenter currentRP = getItem(position);

        EditText PresenterName = (EditText)listItemView.findViewById(R.id.pre_name);
        PresenterName.setText(currentRP.getPresenterId(), TextView.BufferType.NORMAL);
        PresenterName.setKeyListener(null); // This disables editing.



        return listItemView;
    }
}
