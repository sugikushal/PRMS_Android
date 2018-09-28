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
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Presenter;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.Producer;

/**
 * Created by Administrator on 2018/9/27.
 */

public class ProducerAdapter extends ArrayAdapter<Producer> {

    public ProducerAdapter(@NonNull Context context, ArrayList<Producer> producers) {
        super(context, 0,producers);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_producer, parent, false);
        }
        //    Word currentWord = getItem(position);
        Producer currentPro = getItem(position);

        EditText ProducerName = (EditText)listItemView.findViewById(R.id.pro_name);
        ProducerName.setText(currentPro.getProducerId(), TextView.BufferType.NORMAL);
        ProducerName.setKeyListener(null); // This disables editing.



        return listItemView;
    }
}
