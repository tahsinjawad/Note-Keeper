package com.darkray.noteskeeper.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.darkray.noteskeeper.R;
import com.darkray.noteskeeper.callback.NoteClickCallBack;
import com.darkray.noteskeeper.databinding.RowNotesBinding;
import com.darkray.noteskeeper.roomdb.Note;

import java.util.List;
import java.util.Random;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<? extends Note> notesList;
    private NoteClickCallBack callBack;
    private int[] colors;

    public NotesAdapter(NoteClickCallBack callBack) {
        this.callBack = callBack;
    }

    public void setNotesList(final List<? extends Note> notesList) {
        if (this.notesList == null) {
            this.notesList = notesList;
            notifyItemRangeInserted(0, notesList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return NotesAdapter.this.notesList.size();
                }

                @Override
                public int getNewListSize() {
                    return notesList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return NotesAdapter.this.notesList.get(oldItemPosition).id == (notesList.get(newItemPosition).id);
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Note newNote = notesList.get(newItemPosition);
                    Note oldNote = NotesAdapter.this.notesList.get(oldItemPosition);
                    return newNote.id == oldNote.id && newNote.title.equalsIgnoreCase(oldNote.title) && newNote.note.equalsIgnoreCase(oldNote.note);
                }
            });
            this.notesList = notesList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowNotesBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_notes,
                parent, false);
        binding.setCallBack(callBack);
        colors = parent.getContext().getResources().getIntArray(R.array.mdcolor_random);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.binding.setNote(notesList.get(position));
        int randomAndroidColor = colors[new Random().nextInt(colors.length)];
        holder.binding.ivDot.setColorFilter(randomAndroidColor, android.graphics.PorterDuff.Mode.SRC_IN);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return notesList == null ? 0 : notesList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private final RowNotesBinding binding;

        NoteViewHolder(RowNotesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
