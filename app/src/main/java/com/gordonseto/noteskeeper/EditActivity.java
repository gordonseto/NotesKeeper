package com.gordonseto.noteskeeper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class EditActivity extends AppCompatActivity implements TextWatcher {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        EditText editText = (EditText)findViewById(R.id.editText);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if (noteId != -1){
            editText.setText(MainActivity.notes.get(noteId));
        }

        editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        MainActivity.notes.set(noteId, charSequence.toString());
        MainActivity.arrayAdapter.notifyDataSetChanged();

        if (MainActivity.set == null){
            MainActivity.set = new HashSet<String>();
        }
        MainActivity.set.clear();
        MainActivity.set.addAll(MainActivity.notes);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.gordonseto.noteskeeper", Context.MODE_PRIVATE);
        sharedPreferences.edit().putStringSet("notes", MainActivity.set).apply();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
