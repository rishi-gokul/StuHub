package com.example.studentapp.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.studentapp.DB.DBHelper;
import com.example.studentapp.R;
import java.util.ArrayList;
import java.util.Locale;

public class VoiceFragment extends Fragment {
    private static final int SPEECH_REQUEST_CODE = 100;
    private TextView textViewNote;
    private Button btnRecord ;
    private DBHelper dbHelper;

    private ImageView back;
    private TextToSpeech textToSpeech;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voice, container, false);

        textViewNote = view.findViewById(R.id.textViewNote);
        btnRecord = view.findViewById(R.id.btnRecord);
        dbHelper = new DBHelper(requireContext());
        back = view.findViewById(R.id.back);

        // Initialize Text-to-Speech
        textToSpeech = new TextToSpeech(requireContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.getDefault());
            }
        });

        btnRecord.setOnClickListener(v -> startVoiceRecognition());
        back.setOnClickListener(v -> navigateToFragment(new DashboardFragment ()));


        return view;
    }

    private void navigateToFragment(Fragment fragment) {
        AppCompatActivity activity = (AppCompatActivity) requireContext();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void startVoiceRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && !result.isEmpty()) {
                String voiceText = result.get(0);
                textViewNote.setText(voiceText);
                dbHelper.insertVoiceNote(voiceText);
                Toast.makeText(requireContext(), "Voice Saved!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
