package com.bootcamp.android.service;

import com.bootcamp.android.domain.TodoItem;

import java.util.ArrayList;
import java.util.List;


public interface TodoItemService {
    public TodoItem saveOrUpdate(TodoItem item);
    public List<TodoItem> readAllItems();
    public List<String> getListViewItems();
    public ArrayList<TodoItem> saveOrUpdateAllItem(ArrayList<TodoItem> items);
    public void deleteItem(TodoItem item);
}
