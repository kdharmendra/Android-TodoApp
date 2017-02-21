package com.bootcamp.android.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bootcamp.android.domain.TodoItem;

import java.util.ArrayList;

public class TodoItemAdapter extends ArrayAdapter<TodoItem> {

    public TodoItemAdapter(Context context, ArrayList<TodoItem> todoItems) {
        super(context, 0, todoItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_item, parent, false);
        }

        TextView tvItemName = (TextView) convertView.findViewById(R.id.itemName);
        tvItemName.setText(item.getName());

        return convertView;
    }
}
