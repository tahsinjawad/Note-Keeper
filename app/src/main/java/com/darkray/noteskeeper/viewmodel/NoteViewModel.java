package com.darkray.noteskeeper.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.darkray.noteskeeper.roomdb.AppDatabase;
import com.darkray.noteskeeper.roomdb.Note;
import com.darkray.noteskeeper.roomdb.NoteDAO;

public class NoteViewModel extends AndroidViewModel {

    private NoteDAO noteDAO;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteDAO = AppDatabase.getDatabaseBuilder(application.getApplicationContext()).getNoteDAO();
    }

    public void saveNote(Note note) {
        noteDAO.insert(note);
    }

    public void updateNote(Note note) {
        noteDAO.update(note);
    }

    public void deleteNote(Note note) {
        noteDAO.delete(note);
    }
}
