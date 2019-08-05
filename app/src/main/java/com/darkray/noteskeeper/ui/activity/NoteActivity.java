package com.darkray.noteskeeper.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.darkray.noteskeeper.R;
import com.darkray.noteskeeper.databinding.ActivityNoteBinding;
import com.darkray.noteskeeper.roomdb.Note;
import com.darkray.noteskeeper.viewmodel.NoteViewModel;

import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    private ActivityNoteBinding binding;
    private NoteViewModel viewModel;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note);
        viewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        note = (Note) getIntent().getSerializableExtra(MainActivity.KEY);
        binding.setNote(note);

        binding.layoutToolbar.ivBack.setOnClickListener(view -> onBackPressed());
        binding.layoutToolbar.tvBackInfo.setOnClickListener(view -> onBackPressed());

        binding.layoutToolbar.save.setOnClickListener(view -> {

            if (binding.etNote.getText().toString().trim().length() == 0) {
                binding.etNote.setError("Note Can't be empty");
            } else {
                if (note != null) {
                    letDoSomeMagic(Type.UPDATE);
                } else {
                    letDoSomeMagic(Type.SAVE);
                }
            }

        });

        binding.layoutToolbar.delete.setOnClickListener(view -> {
            if (note != null) {
                viewModel.deleteNote(note);
                onBackPressed();
            }
        });


        binding.etNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() > 0) {
                    binding.layoutToolbar.save.setVisibility(View.VISIBLE);
                } else {
                    binding.layoutToolbar.save.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    private void letDoSomeMagic(Type type) {
        Note note = new Note();
        note.dateOfCreated = (String) android.text.format.DateFormat.format("d MMM", new Date());
        note.title = binding.etTitle.getText().toString();
        note.note = binding.etNote.getText().toString();
        if (type.equals(Type.SAVE)) {
            viewModel.saveNote(note);
        } else {
            note.id = this.note.id;
            viewModel.updateNote(note);
        }
        onBackPressed();
    }


    enum Type {
        SAVE,
        UPDATE
    }
}
