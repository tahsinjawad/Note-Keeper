package com.darkray.noteskeeper.roomdb;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteDAO {

    @Insert
    void insert(Note... notes);

    @Update(onConflict = REPLACE)
    void update(Note... notes);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM note")
    List<Note> getAllNote();
}
