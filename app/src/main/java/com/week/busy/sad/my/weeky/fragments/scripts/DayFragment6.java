package com.week.busy.sad.my.weeky.fragments.scripts;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.week.busy.sad.my.weeky.R;

import com.week.busy.sad.my.weeky.DataStorage.DataStore;
import com.week.busy.sad.my.weeky.Utilities.RecyclerVAdapter;

public class DayFragment6 extends Fragment {

    private static final String DISCIPLINE_FILE_NAME = "saturday_data_discipline.myapp";
    private static final String ROOM_FILE_NAME = "saturday_data_room.myapp";
    private static final String[] DATA_STRING_DISCIPLINE = DataStore.getDiscipline_list_saturday();
    private static final String[] DATA_STRING_CLASSROOM = DataStore.getRoom_list_saturday();

    private int pageNumber;


    private final int layout = R.layout.layout_day_fragment6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        DataStore.restore(
                getContext(),
                DISCIPLINE_FILE_NAME,
                ROOM_FILE_NAME,
                DATA_STRING_DISCIPLINE,
                DATA_STRING_CLASSROOM
        );
        //sets data lists based on data restored from the restore function, or fills the null list if there was nothing stored
        DataStore.setDiscipline_list_monday(DataStore.createFileString(DATA_STRING_DISCIPLINE));
        DataStore.setRoom_list_monday(DataStore.createFileString(DATA_STRING_CLASSROOM));

        View currentView = inflater.inflate(layout, container, false);
        Resources res = getResources();
        String[] hour_strings = res.getStringArray(R.array.hours);


        //<recyclerView
        //Receives the context, the file names and the data
        RecyclerView recycler = (RecyclerView) currentView.findViewById(R.id.recycler_v_fragment6);

        RecyclerView.LayoutManager viewManager = new LinearLayoutManager(getActivity());
        RecyclerVAdapter recyclerAdapter = new RecyclerVAdapter(
                getContext(),
                DISCIPLINE_FILE_NAME,
                ROOM_FILE_NAME,
                DATA_STRING_DISCIPLINE,
                DATA_STRING_CLASSROOM,
                hour_strings
        );

        recycler.setLayoutManager(viewManager);
        recycler.setAdapter(recyclerAdapter);
        //recyclerView />


        return currentView;
    }

    //<Getters
    public int getPageNumber() {
        return pageNumber;
    }
    //Getters>

    //<Setters
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    //Setters

    //***this function will be used to construct this object because the fragments
    //constructors cannot be modified, or the manager wont work.
    //so this function is used instead to save the fragment number (pageNumber)
    //inside this object on creation.***
    public static DayFragment6 createFragment(int position) {
        DayFragment6 fragment = new DayFragment6();
        fragment.setPageNumber(position);
        return  fragment;
    }
}