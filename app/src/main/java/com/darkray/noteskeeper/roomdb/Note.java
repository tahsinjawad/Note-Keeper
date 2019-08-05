package com.darkray.noteskeeper.roomdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "Note")
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String dateOfCreated;

    public String title;

    public String note;
}
