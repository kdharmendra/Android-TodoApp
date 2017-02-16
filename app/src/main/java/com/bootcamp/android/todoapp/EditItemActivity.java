package com.bootcamp.android.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText etMultilineText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        etMultilineText = (EditText) findViewById(R.id.etMultilineText);
        etMultilineText.setText(getIntent().getStringExtra("selectedItem"));
    }

    public void onSubmit(View view) {
        Intent result = new Intent();
        result.putExtra("editedItem", etMultilineText.getText().toString());
        result.putExtra("editedItemIndex", getIntent().getIntExtra("selectedItemIndex", 0));
        result.putExtra("code", 200);
        setResult(RESULT_OK, result);
        this.finish();
    }
}
