package com.bootcamp.android.service;

import com.bootcamp.android.domain.TodoItem;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class TodoItemServiceImpl implements TodoItemService {

    @Override
    public TodoItem saveOrUpdate(TodoItem todoItem) {
        todoItem.save();
        return todoItem;
    }

    public ArrayList<TodoItem> saveOrUpdateAllItem(ArrayList<TodoItem> items) {
        for (TodoItem item : items) {
            item.save();
        }
        return items;
    }

    @Override
    public List<TodoItem> readAllItems() {
        List<TodoItem> todoItems = SQLite.select().from(TodoItem.class).queryList();
        return todoItems;
    }

    public List<String> getListViewItems() {
        List<String> lvItems = new ArrayList<>();
        List<TodoItem> todoItems = readAllItems();
        if (todoItems != null && todoItems.size() > 0) {
            for (TodoItem item : todoItems) {
                lvItems.add(item.getName());
            }
        }
        return lvItems;
    }

    public void deleteItem(TodoItem item) {
        item.delete();
    }
}
