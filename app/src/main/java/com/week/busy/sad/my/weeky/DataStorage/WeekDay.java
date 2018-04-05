package com.week.busy.sad.my.weeky.DataStorage;

public enum WeekDay {

    SUNDAY("sunday_data_discipline.myapp", "sunday_data_room.myapp"),
    MONDAY("monday_data_discipline.myapp", "monday_data_room.myapp"),
    TUESDAY("tuesday_data_discipline.myapp", "tuesday_data_room.myapp"),
    WEDNESDAY("wednesday_data_discipline.myapp", "wednesday_data_room.myapp"),
    THURSDAY("thursday_data_discipline.myapp", "thursday_data_room.myapp"),
    FRIDAY("friday_data_discipline.myapp", "friday_data_room.myapp"),
    SATURDAY("saturday_data_discipline.myapp", "saturday_data_room.myapp");

    private final String disciplineFileName;
    private final String roomFileName;

    WeekDay (String disciplineFileName, String roomFileName){
        this.disciplineFileName = disciplineFileName;
        this.roomFileName = roomFileName;
    }

    public String getDiscipline () {
        return this.disciplineFileName;
    }

    public String getRoom() {
        return this.roomFileName;
    }

}
