package com.darkray.noteskeeper.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.darkray.noteskeeper.R;
import com.darkray.noteskeeper.callback.NoteClickCallBack;
import com.darkray.noteskeeper.databinding.ActivityMainBinding;
import com.darkray.noteskeeper.ui.adapter.NotesAdapter;
import com.darkray.noteskeeper.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    public static final String KEY = "NOTE_KEY";

    private ActivityMainBinding binding;
    private NotesAdapter adapter;
    private MainActivityViewModel viewModel;

    private NoteClickCallBack callBack = note -> startActivity(new Intent(getApplicationContext(), NoteActivity.class).putExtra(KEY, note));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setIsDataAvailable(true);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        initAdapter();
        observeViewModel();
        binding.fbAdd.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), NoteActivity.class)));

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getAllNote();
    }

    private void observeViewModel() {
        viewModel.getNoteListObservable().observe(this, notes -> {
            if (notes != null) {
                if (notes.size() > 0) {
                    binding.setIsDataAvailable(true);
                } else {
                    binding.setIsDataAvailable(false);
                }
                Log.d(TAG, "observeViewModel: " + notes.size());
                adapter.setNotesList(notes);
            }
        });
    }

    private void initAdapter() {
        adapter = new NotesAdapter(callBack);
        binding.rvMain.setAdapter(adapter);
    }

}
