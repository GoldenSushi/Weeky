package com.week.busy.sad.my.weeky.Utilities;


import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.week.busy.sad.my.weeky.DataStorage.DataStore;
import com.week.busy.sad.my.weeky.R;

public class RecyclerVHolder extends RecyclerView.ViewHolder {


    private TextView hours;
    private TextView disciplineText;
    private TextView roomText;

    private EditText editTextDiscipline;
    private EditText editTextRoom;

    private ConstraintLayout editFrame;
    private Button editButton;
    private ConstraintLayout deleteFrame;
    private Button deleteButton;

    private String[] disciplineDataString;
    private String[] roomDataString;

    private final Context context;
    private final String DISCIPLINE_FILE_NAME;
    private final String ROOM_FILE_NAME;
    private final Resources res;


    public RecyclerVHolder(final View itemView,
                           final Context context,
                           String disciplineFileName,
                           String roomFileName,
                           String[] disciplineDataString,
                           String[] roomDataString) {
        super(itemView);

        //text
        this.hours = (TextView) itemView.findViewById(R.id.hour_text);
        this.disciplineText = (TextView) itemView.findViewById(R.id.discipline_text);
        this.roomText = (TextView) itemView.findViewById(R.id.classroom_text);

        //text input
        this.editTextDiscipline = (EditText) itemView.findViewById(R.id.insert_text_discipline);
        this.editTextRoom = (EditText) itemView.findViewById(R.id.insert_text_classroom);

        //buttons
        this.editFrame = (ConstraintLayout) itemView.findViewById(R.id.edit_frame);
        this.editButton = (Button) itemView.findViewById(R.id.edit_button);
        this.deleteFrame = (ConstraintLayout) itemView.findViewById(R.id.delete_frame);
        this.deleteButton = (Button) itemView.findViewById(R.id.delete_button);

        //data strings
        this.disciplineDataString = disciplineDataString;
        this.roomDataString = roomDataString;

        //file names
        this.DISCIPLINE_FILE_NAME = disciplineFileName;
        this.ROOM_FILE_NAME = roomFileName;

        //context
        this.context = context;

        //resources
        this.res = context.getResources();


        //<Setting Click Handlers
        itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

                //sets color highlight when editing begins
                v.setBackgroundColor(res.getColor(R.color.colorPrimary));

                //sets visibility on and recovers the text so user can edit
                onClickEditVisible();
                recoverEditText();

                //checks if the is another item being edited
                //if there is, closes the edition view
                if (DataStore.holderUnderEdit != null && !DataStore.holderUnderEdit.equals(v.getTag())) {
                    DataStore.holderUnderEdit.onClickEditInvisible();
                    DataStore.holderUnderEdit.setBackgroundColor();
                }

                //stores the holder being edited in the data store
                DataStore.holderUnderEdit = (RecyclerVHolder) v.getTag();

                return false;
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onButtonClick();
                onClickEditInvisible();

                //this hides the keyboard after pressing the button
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                //changes item's background color if text is entered
                setBackgroundColor();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDelete();
                onClickEditInvisible();

                //changes item's background color if text is entered
                setBackgroundColor();
            }
        });

        //checks if the view being clicked is the one being edited. if it isn't, sets the edit screen invisible
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataStore.holderUnderEdit != null && !v.getTag().equals(DataStore.holderUnderEdit)) {
                    DataStore.holderUnderEdit.onClickEditInvisible();

                    DataStore.holderUnderEdit.setBackgroundColor();
                }
            }
        });
        //Setting Click Handlers/>

    }


    //<Getters
    public TextView getHours() {
        return hours;
    }

    public TextView getDisciplineText() {
        return disciplineText;
    }

    public TextView getRoomText() {
        return roomText;
    }

    public EditText getEditTextDiscipline() {
        return editTextDiscipline;
    }
    public EditText getEditTextRoom() {
        return editTextRoom;
    }

    public ConstraintLayout getEditFrame() {
        return editFrame;
    }

    public Button getEditButton() {
        return editButton;
    }

    public ConstraintLayout getDeleteFrame() {
        return deleteFrame;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public String[] getDisciplineDataString() {
        return disciplineDataString;
    }

    public String[] getRoomDataString() {
        return roomDataString;
    }

    public Context getContext() {
        return context;
    }

    public String getDISCIPLINE_FILE_NAME() {
        return DISCIPLINE_FILE_NAME;
    }

    public String getROOM_FILE_NAME() {
        return ROOM_FILE_NAME;
    }
    //Getters/>


    //<Setters
    private void setDisciplineDataString(String[] dataString) {
        this.disciplineDataString = dataString;
    }

    private void setRoomDataString(String[] roomDataString) {
        this.roomDataString = roomDataString;
    }

    public void setEditTextDiscipline(EditText editTextDiscipline) {
        this.editTextDiscipline = editTextDiscipline;
    }

    public void setEditTextRoom(EditText editTextRoom) {
        this.editTextRoom = editTextRoom;
    }

    //Setters/>


    //<Click Handlers code

    //sets to visible the edit interface
    public void onClickEditVisible() {

        //text
        getDisciplineText().setVisibility(View.INVISIBLE);
        getRoomText().setVisibility(View.INVISIBLE);
        //buttons
        getEditTextDiscipline().setVisibility(View.VISIBLE);
        getEditTextRoom().setVisibility(View.VISIBLE);
        getEditButton().setVisibility(View.VISIBLE);
        getEditFrame().setVisibility(View.VISIBLE);
        getDeleteButton().setVisibility(View.VISIBLE);
        getDeleteFrame().setVisibility(View.VISIBLE);

    }

    //sets to invisible the edit interface
    public void onClickEditInvisible() {

        //text
        getDisciplineText().setVisibility(View.VISIBLE);
        getRoomText().setVisibility(View.VISIBLE);
        //buttons
        getEditTextDiscipline().setVisibility(View.INVISIBLE);
        getEditTextRoom().setVisibility(View.INVISIBLE);
        getEditButton().setVisibility(View.INVISIBLE);
        getEditFrame().setVisibility(View.INVISIBLE);
        getDeleteButton().setVisibility(View.INVISIBLE);
        getDeleteFrame().setVisibility(View.INVISIBLE);

        //clears the text from the input view
        getEditTextDiscipline().getText().clear();
        getEditTextRoom().getText().clear();

    }

    //refreshes data strings and calls the text storage function
    public void onButtonClick() {
        int viewPosition = getAdapterPosition();

        String insertedTextDiscipline = getEditTextDiscipline().getText().toString();
        String insertedTextRoom = getEditTextRoom().getText().toString();

        if (!insertedTextDiscipline.isEmpty()) {
            getDisciplineText().setText(insertedTextDiscipline);
            getDisciplineDataString()[viewPosition] = insertedTextDiscipline;
            setDisciplineDataString(DataStore.createFileString(getDisciplineDataString()));
            DataStore.store(getContext(), getDISCIPLINE_FILE_NAME(), getDisciplineDataString());
        }
        if (!insertedTextRoom.isEmpty()) {
            getRoomText().setText(insertedTextRoom);
            getRoomDataString()[viewPosition] = insertedTextRoom;
            setRoomDataString(DataStore.createFileString(getRoomDataString()));
            DataStore.store(getContext(), getROOM_FILE_NAME(), getRoomDataString());
        }

    }

    public void onClickDelete () {

        getDisciplineText().setText(" ");
        getRoomText().setText(" ");
        getDisciplineDataString()[getAdapterPosition()] = " ";
        getRoomDataString()[getAdapterPosition()] = " ";
        DataStore.store(getContext(), getDISCIPLINE_FILE_NAME(), getDisciplineDataString());
        DataStore.store(getContext(), getROOM_FILE_NAME(), getRoomDataString());

    }
    //Click Handlers code/>

    //checks if item is being edited. returns true if it is, and false if it isn't
    public boolean isEditVisible() {
        return editFrame.getVisibility() == View.VISIBLE;
    }

    //checks if text is null or whitespace
    public boolean isDisciplineTextEmpty() {
        String text = getDisciplineText().getText().toString();
        if (text.isEmpty()) return true;

        if (text.trim().length() > 0) {
            return false;
        }

        return true;
    }

    //checks if text is null or whitespace
    public boolean isRoomTextEmpty() {
        String text = getRoomText().getText().toString();
        if (text.isEmpty()) return true;

        if (text.trim().length() > 0) {
            return false;
        }

        return true;
    }

    //sets background color if text not empty
    public void setBackgroundColor() {
        if (!isDisciplineTextEmpty() || !isRoomTextEmpty()) {
            itemView.setBackgroundColor(res.getColor(R.color.text_background));
        } else {
            itemView.setBackgroundColor(res.getColor(android.R.color.white));
        }
    }

    //recovers the text from the view and put it in EditText
    //so the user won't need to write it again in case he/she wants to edit
    public void recoverEditText() {

        if (!isDisciplineTextEmpty()) {
            getEditTextDiscipline().setText(getDisciplineText().getText().toString());
        }
        if (!isRoomTextEmpty()) {
            getEditTextRoom().setText(getRoomText().getText().toString());
        }
    }

}

