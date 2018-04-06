package com.week.busy.sad.my.weeky.DataStorage;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

//This class stores the information about fragments. holders access them to update their text file
//It also provides the functions needed to created the text files that will store the data

public final class DataStore {

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

    public static boolean restore(Context context,
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
            return true;

        } catch (FileNotFoundException e) {
            e.getStackTrace();
            return false;
        }
    }

    //fills the array with spaces
    public static void fillEmpty(String[] disciplineData, String[] roomData) {
        for (int i = 0; i < Data.HOURS_IN_DAY; i++) {
            disciplineData[i] = " ";
            roomData[i] = " ";
        }
    }

    //creates string (index1;data1;index2; ... ) that will be used to write the text file
    public static String createTextString(String[] list) {
        String text = "";

        for (int i = 0; i < Data.HOURS_IN_DAY; i++) {
            text += i + ";";
            text += list[i] + ";";
        }

        return text;
    }
}



