package com.bootcamp.android.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.bootcamp.android.domain.TodoItem;

public class EditItemActivity extends AppCompatActivity {

    EditText etMultilineText;
    TodoItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        etMultilineText = (EditText) findViewById(R.id.etMultilineText);
        item = getIntent().getParcelableExtra("selectedItem");
        etMultilineText.setText(item.getName());
    }

    public void onSubmit(View view) {
        Intent result = new Intent();
        item.setName(etMultilineText.getText().toString());
        result.putExtra("editedItem", item);
        result.putExtra("editedItemIndex", getIntent().getIntExtra("selectedItemIndex", 0));
        result.putExtra("code", 200);
        setResult(RESULT_OK, result);
        this.finish();
    }
}
