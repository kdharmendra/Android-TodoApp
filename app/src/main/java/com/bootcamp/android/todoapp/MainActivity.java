package com.bootcamp.android.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 200;

    ArrayList<String> todoItems;
    ArrayAdapter<String> aTodoAdapter;

    ListView lvItems;
    EditText etEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initArrayAdapter();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(aTodoAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);
        initListViewListener();
    }

    public void initArrayAdapter() {
        readItems();
        aTodoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
    }

    public void initListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                aTodoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iEditActivity = new Intent().setClass(MainActivity.this, EditItemActivity.class);
                iEditActivity.putExtra("selectedItem", todoItems.get(position));
                iEditActivity.putExtra("selectedItemIndex", position);
                startActivityForResult(iEditActivity, REQUEST_CODE);
            }
        });
    }

    public void onAddItem(View view) {
        String item = etEditText.getText().toString();
        aTodoAdapter.add(item);
        writeItems();
        etEditText.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            todoItems.set(data.getExtras().getInt("editedItemIndex"), data.getExtras().getString("editedItem"));
            aTodoAdapter.notifyDataSetChanged();
            writeItems();
        }
    }

    private void readItems() {
        File fileDir = getFilesDir();
        File todoAppDataFile = new File(fileDir, "todo_data.txt");
        try {
            todoItems = (ArrayList<String>) FileUtils.readLines(todoAppDataFile);
        } catch (IOException e) {
            todoItems = new ArrayList<>();
        }
    }

    private void writeItems() {
        File fileDir = getFilesDir();
        File todoAppDataFile = new File(fileDir, "todo_data.txt");
        try {
            FileUtils.writeLines(todoAppDataFile, todoItems);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
