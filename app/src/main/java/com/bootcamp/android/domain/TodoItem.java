package com.bootcamp.android.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.bootcamp.android.database.TodoDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


@Table(database = TodoDatabase.class)
public class TodoItem extends BaseModel implements Parcelable {
    @Column
    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private String name;

    public TodoItem(){};

    public TodoItem(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public static final Parcelable.Creator<TodoItem> CREATOR = new Parcelable.Creator<TodoItem>(){
        public TodoItem createFromParcel(Parcel parcel) {
            return new TodoItem(parcel);
        }

        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };

    public TodoItem(Parcel parcel) {
        id = parcel.readInt();
        name = parcel.readString();
    }
}
