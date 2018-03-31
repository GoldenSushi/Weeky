package com.week.busy.sad.my.weeky.Utilities;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.week.busy.sad.my.weeky.DataStorage.DataStore;
import com.week.busy.sad.my.weeky.R;

/**
 * Created by user on 24/03/2018.
 */

public class RecyclerVAdapter extends RecyclerView.Adapter<RecyclerVHolder> {


    private Context context;
    private String[] disciplineDataString;
    private String[] roomDataString;
    private final String DISCIPLINE_FILE_NAME;
    private final String ROOM_FILE_NAME;
    private final String[] HOURS_STRINGS;



    public RecyclerVAdapter (Context context,
                             String disciplineFileName,
                             String roomFileName,
                             String[] disciplineDataString,
                             String[] roomDataString,
                             String[] HOURS_STRINGS
    ) {
        this.disciplineDataString = disciplineDataString;
        this.roomDataString = roomDataString;
        this.DISCIPLINE_FILE_NAME = disciplineFileName;
        this.ROOM_FILE_NAME = roomFileName;
        this.context = context;
        this.HOURS_STRINGS = HOURS_STRINGS;
    }

    public Context getContext() {
        return context;
    }

    public String[] getDisciplineDataString() {
        return disciplineDataString;
    }

    public String[] getRoomDataString() {
        return roomDataString;
    }

    public String getDISCIPLINE_FILE_NAME() {
        return DISCIPLINE_FILE_NAME;
    }

    public String getROOM_FILE_NAME() {
        return ROOM_FILE_NAME;
    }

    @Override
    public RecyclerVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View newRow = inflater.inflate(R.layout.hours_row, parent, false);
        RecyclerVHolder newHolder = new RecyclerVHolder(
                newRow,
                getContext(),
                getDISCIPLINE_FILE_NAME(),
                getROOM_FILE_NAME(),
                getDisciplineDataString(),
                getRoomDataString()
        );

        newRow.setTag(newHolder);

        return newHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerVHolder holder, int position) {

        holder.getHours().setText(HOURS_STRINGS[position]);

        //sets the data retrieved for the data strings
        String[] discipline_data = holder.getDisciplineDataString();
        if (discipline_data[position] == null || discipline_data[position] == "") {
            holder.getDisciplineText().setText(" ");
        } else {
            holder.getDisciplineText().setText(discipline_data[position]);
        }

        String[] room_data = holder.getRoomDataString();
        if (room_data[position] == null || room_data[position] == "") {
            holder.getRoomText().setText(" ");
        } else {
            holder.getRoomText().setText(room_data[position]);
        }

        //when binding a view that was being edited, sets the editing invisible
        if (holder.isEditVisible()) {
            holder.onClickEditInvisible();
        }

        holder.setBackgroundColor();
    }

    @Override
    public int getItemCount() {
        return HOURS_STRINGS.length;
    }
}

