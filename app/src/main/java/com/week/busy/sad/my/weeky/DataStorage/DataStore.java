package com.week.busy.sad.my.weeky.DataStorage;

import android.content.Context;

import com.week.busy.sad.my.weeky.Utilities.RecyclerVHolder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by user on 24/03/2018.
 */

//This class stores the information of the fragments. holders access them to get update their text file/
//It also provides the functions needed to created the text files that will store the data

public class DataStore {

    private static final int ARRAY_SIZE = 12;
    public static RecyclerVHolder holderUnderEdit;

    private static String[] discipline_list_monday = new String[ARRAY_SIZE];
    private static String[] room_list_monday = new String[ARRAY_SIZE];

    private static String[] discipline_list_tuesday = new String[ARRAY_SIZE];
    private static String[] room_list_tuesday = new String[ARRAY_SIZE];

    private static String[] discipline_list_wednesday = new String[ARRAY_SIZE];
    private static String[] room_list_wednesday = new String[ARRAY_SIZE];

    private static String[] discipline_list_thursday = new String[ARRAY_SIZE];
    private static String[] room_list_thursday = new String[ARRAY_SIZE];

    private static String[] discipline_list_friday = new String[ARRAY_SIZE];
    private static String[] room_list_friday = new String[ARRAY_SIZE];

    private static String[] discipline_list_saturday = new String[ARRAY_SIZE];
    private static String[] room_list_saturday = new String[ARRAY_SIZE];

    private static String[] discipline_list_sunday = new String[ARRAY_SIZE];
    private static String[] room_list_sunday = new String[ARRAY_SIZE];


    //<Getters
    public static String[] getDiscipline_list_monday() {
        return discipline_list_monday;
    }

    public static String[] getRoom_list_monday() {
        return room_list_monday;
    }

    public static String[] getDiscipline_list_tuesday() {
        return discipline_list_tuesday;
    }

    public static String[] getRoom_list_tuesday() {
        return room_list_tuesday;
    }

    public static String[] getDiscipline_list_wednesday() {
        return discipline_list_wednesday;
    }

    public static String[] getRoom_list_wednesday() {
        return room_list_wednesday;
    }

    public static String[] getDiscipline_list_thursday() {
        return discipline_list_thursday;
    }

    public static String[] getRoom_list_thursday() {
        return room_list_thursday;
    }

    public static String[] getDiscipline_list_friday() {
        return discipline_list_friday;
    }

    public static String[] getRoom_list_friday() {
        return room_list_friday;
    }

    public static String[] getDiscipline_list_saturday() {
        return discipline_list_saturday;
    }

    public static String[] getRoom_list_saturday() {
        return room_list_saturday;
    }

    public static String[] getDiscipline_list_sunday() {
        return discipline_list_sunday;
    }

    public static String[] getRoom_list_sunday() {
        return room_list_sunday;
    }
    //Getters/>


    //<Setters
    public static void setDiscipline_list_monday(String[] discipline_list_monday) {
        DataStore.discipline_list_monday = discipline_list_monday;
    }

    public static void setRoom_list_monday(String[] room_list_monday) {
        DataStore.room_list_monday = room_list_monday;
    }
    //Setters/>


    public static void store(Context context, String fileName, String[] list) {

       String text = createTextString(list);

        FileOutputStream storage = null;
        try {
            storage = context.openFileOutput(fileName, Context.MODE_PRIVATE);

            storage.write(text.getBytes());

        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            try {
                if (storage != null) {
                    storage.close();
                }
            } catch (IOException e2) {
                e2.getStackTrace();
            }
        }
    }

    public static void restore(Context context,
                               String disciplineFileName,
                               String roomFileName,
                               String[] data_discipline,
                               String[] data_room) {

        FileInputStream restoringDiscipline;
        FileInputStream restoringRoom;

        try {
            restoringDiscipline = context.openFileInput(disciplineFileName);
            restoringRoom = context.openFileInput(roomFileName);

            Scanner sc1 = new Scanner(restoringDiscipline);
            sc1.useDelimiter(";");
            Scanner sc2 = new Scanner(restoringRoom);
            sc2.useDelimiter(";");

            //gets integer value(index) and then the string value
            int i;
            while (sc1.hasNext()) {
                i = Integer.valueOf(sc1.next());
                data_discipline[i] = sc1.next();
            }
            while (sc2.hasNext()) {
                i = Integer.valueOf(sc2.next());
                data_room[i] = sc2.next();
            }

            sc1.close();
            sc2.close();


        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    //takes the data array and transforms it into a single string so it can be stored in the text file
    private static String createTextString (String[] list) {
        String text = "";
        for (int i = 0; i < ARRAY_SIZE; i++) {
            text += i + ";";
            text += list[i] + ";";
        }
        return text;
    }

    //fills all the array so there is no null pointer inside it
    public static String[] createFileString (String[] dataString) {
        for (int i = 0; i < dataString.length; i++) {
            if (dataString[i] == null) {
                dataString[i] = " ";
            }
        }
        return dataString;
    }
}
