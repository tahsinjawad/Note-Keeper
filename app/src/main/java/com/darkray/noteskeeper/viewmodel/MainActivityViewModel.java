package com.darkray.noteskeeper.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.darkray.noteskeeper.roomdb.AppDatabase;
import com.darkray.noteskeeper.roomdb.Note;
import com.darkray.noteskeeper.roomdb.NoteDAO;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private final LiveData<List<Note>> noteListObservable;
    private MutableLiveData<List<Note>> data = new MutableLiveData<>();

    private NoteDAO noteDAO;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        noteDAO = AppDatabase.getDatabaseBuilder(application.getApplicationContext()).getNoteDAO();
        noteListObservable = getAllNote();
    }


    public LiveData<List<Note>> getAllNote() {
        data.setValue(noteDAO.getAllNote());
        return data;
    }

    public LiveData<List<Note>> getNoteListObservable() {
        return noteListObservable;
    }

}
