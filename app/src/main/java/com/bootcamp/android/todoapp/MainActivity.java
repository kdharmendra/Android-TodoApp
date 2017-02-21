package com.bootcamp.android.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.bootcamp.android.domain.TodoItem;
import com.bootcamp.android.service.TodoItemService;
import com.bootcamp.android.service.TodoItemServiceImpl;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 200;

    ArrayList<TodoItem> todoItems;
    TodoItemAdapter aTodoAdapter;
    TodoItemService todoItemService;

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
        if (todoItemService == null) todoItemService = new TodoItemServiceImpl();
        readItems();
        aTodoAdapter = new TodoItemAdapter(this, todoItems);
    }

    public void initListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TodoItem selectedItem = todoItems.get(position);
                todoItems.remove(selectedItem);
                aTodoAdapter.notifyDataSetChanged();
                todoItemService.deleteItem(selectedItem);
                return true;
            }
        });

        try {
            lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent iEditActivity = new Intent().setClass(MainActivity.this, EditItemActivity.class);
                    TodoItem selectedItem = (TodoItem) todoItems.get(position);
                    iEditActivity.putExtra("selectedItem", selectedItem);
                    iEditActivity.putExtra("selectedItemIndex", position);
                    startActivityForResult(iEditActivity, REQUEST_CODE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddItem(View view) {
        String itemName = etEditText.getText().toString();
        TodoItem item = new TodoItem(itemName);
        aTodoAdapter.add(item);
        writeItems();
        etEditText.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            todoItems.set(data.getExtras().getInt("editedItemIndex"), (TodoItem) data.getParcelableExtra("editedItem"));
            aTodoAdapter.notifyDataSetChanged();
            writeItems();
        }
    }

    private void readItems() {
        todoItems = (ArrayList) todoItemService.readAllItems();
    }

    private void writeItems() {
        todoItemService.saveOrUpdateAllItem(todoItems);
    }
}
