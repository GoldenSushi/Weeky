package com.week.busy.sad.my.weeky.DataStorage;

import com.week.busy.sad.my.weeky.Utilities.RecyclerVHolder;

import java.util.ArrayList;
import java.util.List;

public final class Data {

    public final static int WEEK_DAYS = 7;
    public static final int HOURS_IN_DAY = 12;

    public static List<String[]> disciplineData = new ArrayList<String[]>();
    public static List<String[]> roomData = new ArrayList<String[]>();

    public static RecyclerVHolder holderUnderEdit;

}
