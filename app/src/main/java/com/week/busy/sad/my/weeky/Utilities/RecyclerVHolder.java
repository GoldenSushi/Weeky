package com.week.busy.sad.my.weeky.Utilities;


import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.week.busy.sad.my.weeky.DataStorage.Data;
import com.week.busy.sad.my.weeky.DataStorage.DataStore;
import com.week.busy.sad.my.weeky.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerVHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.hour_text) TextView hours;

    @BindViews({ R.id.discipline_text, R.id.classroom_text })
    List<TextView> textViews;

    @BindViews({ R.id.insert_text_discipline, R.id.insert_text_classroom })
    List<EditText> editTexts;

    @BindViews({ R.id.edit_button, R.id.delete_button })
    List<Button> buttons;

    @BindViews({ R.id.edit_frame, R.id.delete_frame })
    List<ConstraintLayout> frames;

    private String[] disciplineDataString;
    private String[] roomDataString;

    private final Context context;
    private final String DISCIPLINE_FILE_NAME;
    private final String ROOM_FILE_NAME;
    private final Resources res;



    //<Constructor **********************************************************************************************************************************************************
    public RecyclerVHolder(final View itemView,
                           final Context context,
                           String disciplineFileName,
                           String roomFileName,
                           String[] disciplineDataString,
                           String[] roomDataString) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        //context
        this.context = context;

        //resources
        this.res = context.getResources();

        //data strings
        this.disciplineDataString = disciplineDataString;
        this.roomDataString = roomDataString;

        //file names
        this.DISCIPLINE_FILE_NAME = disciplineFileName;
        this.ROOM_FILE_NAME = roomFileName;


        //<Setting Click Handlers
        //checks if the view being clicked is the one being edited. if it isn't, sets the edit screen invisible
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnotherUnderEdit(v)){
                    unbindUnderEdit(v);
                } else {
                    //sets color highlight when editing begins
                    setEditingColor(v);
                    //sets visibility on and recovers the text so user can edit
                    onClickEditVisible();
                    recoverEditText();
                    bindUnderEdit(v);
                }
            }
        });
        //Setting Click Handlers/>

    }
    //Constructor/>******************************************************************************************************************************************************************



    //<Getters and Setters **********************************************************************************************************************************************************
    //<Getters
    public TextView getHours() {
        return hours;
    }
    public TextView getDisciplineText() {
        return textViews.get(0);
    }
    public TextView getRoomText() {
        return textViews.get(1);
    }
    public EditText getEditTextDiscipline() {
        return editTexts.get(0);
    }
    public EditText getEditTextRoom() {
        return editTexts.get(1);
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
    //Getters and Setters/> **********************************************************************************************************************************************************



    //<Click Handlers code **********************************************************************************************************************************************************
    @OnClick({ R.id.edit_button, R.id.delete_button })
    public void onClickButtons (Button button) {

        switch (button.getId()) {
            case R.id.edit_button : {
                onClickEdit();
                //set visibility off
                onClickEditInvisible();
                //this hides the keyboard after pressing the button
                turnOffKeyboard(itemView);
                //changes item's background color if text is entered
                setBackgroundColor();
            } break;
            case R.id.delete_button : {
                onClickDelete();
                onClickEditInvisible();
                //changes item's background color if text is entered
                setBackgroundColor();
            } break;
        }
    }

    //creates visibility actions to use on visibility functions
    ButterKnife.Action<View> INVISIBLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            view.setVisibility(View.INVISIBLE);
        }
    };
    ButterKnife.Action<View> VISIBLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            view.setVisibility(View.VISIBLE);
        }
    };

    //sets to visible the edit interface
    public void onClickEditVisible() {
        ButterKnife.apply(textViews, INVISIBLE);
        ButterKnife.apply(editTexts, VISIBLE);
        ButterKnife.apply(frames, VISIBLE);
        ButterKnife.apply(buttons, VISIBLE);
    }

    //sets to invisible the edit interface
    public void onClickEditInvisible() {
        ButterKnife.apply(textViews, VISIBLE);
        ButterKnife.apply(editTexts, INVISIBLE);
        ButterKnife.apply(frames, INVISIBLE);
        ButterKnife.apply(buttons, INVISIBLE);
    }

    //refreshes data strings and calls the text storage function
    public void onClickEdit() {
        int viewPosition = getAdapterPosition();

        String insertedTextDiscipline = getEditTextDiscipline().getText().toString();
        String insertedTextRoom = getEditTextRoom().getText().toString();

        if (!insertedTextDiscipline.isEmpty()) {
            getDisciplineText().setText(insertedTextDiscipline);
            getDisciplineDataString()[viewPosition] = insertedTextDiscipline;
            DataStore.store(getContext(), getDISCIPLINE_FILE_NAME(), getDisciplineDataString());
        }
        if (!insertedTextRoom.isEmpty()) {
            getRoomText().setText(insertedTextRoom);
            getRoomDataString()[viewPosition] = insertedTextRoom;
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
    //Click Handlers code/> **********************************************************************************************************************************************************



    //<Helpers **********************************************************************************************************************************************************
    //checks if there is another item being edited
    public boolean isAnotherUnderEdit(View v) {
        if (Data.holderUnderEdit != null && !Data.holderUnderEdit.equals(v.getTag())) {
            return true;
        }
        return false;
    }

    //stores the holder being edited in the data store
    public void bindUnderEdit (View v) {
        Data.holderUnderEdit = (RecyclerVHolder) v.getTag();
    }

    //resets reference if there was another item being added
    public void unbindUnderEdit(View v) {
        Data.holderUnderEdit.onClickEditInvisible();
        Data.holderUnderEdit.setBackgroundColor();
        Data.holderUnderEdit = null;
    }

    //turns keyboard off after clicking the edit or delete button
    public void turnOffKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    //checks if item is being edited. returns true if it is, and false if it isn't
    public boolean isEditVisible() {
        return frames.get(0).getVisibility() == View.VISIBLE;
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

    private void setEditingColor (View v) {
        v.setBackgroundColor(res.getColor(R.color.colorPrimary));
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
    //Helpers/> **********************************************************************************************************************************************************

}
