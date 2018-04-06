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

import com.week.busy.sad.my.weeky.DataStorage.Data;
import com.week.busy.sad.my.weeky.R;

import com.week.busy.sad.my.weeky.DataStorage.DataStore;
import com.week.busy.sad.my.weeky.Utilities.RecyclerVAdapter;

import static com.week.busy.sad.my.weeky.DataStorage.WeekDay.*;

public class DayFragments extends Fragment implements DataManager {

    private String DISCIPLINE_FILE_NAME;
    private String ROOM_FILE_NAME;
    private String[] DATA_STRING_DISCIPLINE;
    private String[] DATA_STRING_CLASSROOM;

    private int pageNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //initializes the data arrays inside Data list
        initializeData(pageNumber);


        View currentView = inflater.inflate(R.layout.fragments_layout, container, false);
        Resources res = getResources();
        String[] hour_strings = res.getStringArray(R.array.hours);



        //<recyclerView
        //Receives the context, the file names and the data
        RecyclerView recycler = (RecyclerView) currentView.findViewById(R.id.fragments_recyclerview);

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

    public String getDISCIPLINE_FILE_NAME() {
        return DISCIPLINE_FILE_NAME;
    }

    public String getROOM_FILE_NAME() {
        return ROOM_FILE_NAME;
    }

    //Getters>

    //<Setters
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setDISCIPLINE_FILE_NAME(String DISCIPLINE_FILE_NAME) {
        this.DISCIPLINE_FILE_NAME = DISCIPLINE_FILE_NAME;
    }

    public void setROOM_FILE_NAME(String ROOM_FILE_NAME) {
        this.ROOM_FILE_NAME = ROOM_FILE_NAME;
    }

    public void setDATA_STRING_DISCIPLINE(String[] DATA_STRING_DISCIPLINE) {
        this.DATA_STRING_DISCIPLINE = DATA_STRING_DISCIPLINE;
    }

    public void setDATA_STRING_CLASSROOM(String[] DATA_STRING_CLASSROOM) {
        this.DATA_STRING_CLASSROOM = DATA_STRING_CLASSROOM;
    }
    //Setters>

    //***this function will be used to construct this object because the fragments
    //constructors cannot be modified, or the manager wont work.
    //so this function is used instead to save the fragment number (pageNumber)
    //inside this object on creation.***
    public static DayFragments createFragment(int position) {
        DayFragments fragment = new DayFragments();
        fragment.setPageNumber(position);

        //sets file names
        switch (position) {

            case 0 : fragment.setDISCIPLINE_FILE_NAME(SUNDAY.getDiscipline());
                    fragment.setROOM_FILE_NAME(SUNDAY.getRoom());
                return  fragment;

            case 1 : fragment.setDISCIPLINE_FILE_NAME(MONDAY.getDiscipline());
                    fragment.setROOM_FILE_NAME(MONDAY.getRoom());
                return  fragment;

            case 2 : fragment.setDISCIPLINE_FILE_NAME(TUESDAY.getDiscipline());
                    fragment.setROOM_FILE_NAME(TUESDAY.getRoom());
                return  fragment;

            case 3 : fragment.setDISCIPLINE_FILE_NAME(WEDNESDAY.getDiscipline());
                    fragment.setROOM_FILE_NAME(WEDNESDAY.getRoom());
                return  fragment;

            case 4 : fragment.setDISCIPLINE_FILE_NAME(THURSDAY.getDiscipline());
                    fragment.setROOM_FILE_NAME(THURSDAY.getRoom());
                return  fragment;

            case 5 : fragment.setDISCIPLINE_FILE_NAME(FRIDAY.getDiscipline());
                    fragment.setROOM_FILE_NAME(FRIDAY.getRoom());
                return  fragment;

            case 6 : fragment.setDISCIPLINE_FILE_NAME(SATURDAY.getDiscipline());
                    fragment.setROOM_FILE_NAME(SATURDAY.getRoom());
                return  fragment;

            default : return null;

        }
    }

    @Override
    public void initializeData(int position) {

        String[] newDisciplineData = new String[Data.HOURS_IN_DAY];
        String[] newRoomData = new String[Data.HOURS_IN_DAY];

        //checks if there is a file with the data and if it is accessible, if not fills arrays with spaces to avoid null exceptions
        if (DataStore.restore(getActivity(), getDISCIPLINE_FILE_NAME(), getROOM_FILE_NAME(), newDisciplineData, newRoomData)) {
            //if true arrays will already have been filled with data
        } else {
            DataStore.fillEmpty(newDisciplineData, newRoomData);
        }

        //sets references of this data to this object
        setDATA_STRING_DISCIPLINE(newDisciplineData);
        setDATA_STRING_CLASSROOM(newRoomData);

        //adds strings to lists in Data
        Data.disciplineData.add(position, newDisciplineData);
        Data.roomData.add(position, newRoomData);


    }

}


