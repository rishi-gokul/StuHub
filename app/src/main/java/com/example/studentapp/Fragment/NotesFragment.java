package com.example.studentapp.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.studentapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NotesFragment extends Fragment {
    private EditText editTextNote;
    private Button btnSave;
    private ImageView back;
    private ListView listViewNotes;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> notesList;
    private SharedPreferences sharedPreferences;

    public NotesFragment() {
        super( R.layout.fragment_notes);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextNote = view.findViewById(R.id.editTextNote);
        btnSave = view.findViewById(R.id.btnSave);
        back = view.findViewById(R.id.back);
        listViewNotes = view.findViewById(R.id.listViewNotes);
        sharedPreferences = requireActivity().getSharedPreferences("NotesApp", Context.MODE_PRIVATE);
        notesList = loadNotes();
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, notesList);
        listViewNotes.setAdapter(adapter);
        back.setOnClickListener(v -> navigateToFragment (new List_Fragment ()));

        btnSave.setOnClickListener(v -> {
            String note = editTextNote.getText().toString().trim();
            if (!note.isEmpty()) {
                notesList.add(note);
                adapter.notifyDataSetChanged();
                saveNotes(notesList);
                editTextNote.setText(""); // Clear input field
            }
        });


        listViewNotes.setOnItemLongClickListener((parent, view1, position, id) -> {
            notesList.remove(position);
            adapter.notifyDataSetChanged();
            saveNotes(notesList);
            return true;
        });
    }

    private void navigateToFragment(List_Fragment Fragment) {
        AppCompatActivity activity = (AppCompatActivity) requireContext();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container , Fragment)
                .addToBackStack(null)
                .commit();

    }

    private ArrayList<String> loadNotes() {
        Set<String> notesSet = sharedPreferences.getStringSet("notes", new HashSet<>());
        return new ArrayList<>(notesSet);
    }

    private void saveNotes(ArrayList<String> notes) {
        Set<String> notesSet = new HashSet<>(notes);
        sharedPreferences.edit().putStringSet("notes", notesSet).apply();
    }
}
