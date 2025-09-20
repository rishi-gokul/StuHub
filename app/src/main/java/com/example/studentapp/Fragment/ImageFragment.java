package com.example.studentapp.Fragment;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.studentapp.DB.DBHelper;
import com.example.studentapp.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageFragment extends Fragment {
    private ImageView imageView;
    private ImageView back;
    private Button btnChooseImage, btnSaveImage;
    private Uri imageUri;
    private DBHelper databaseHelper;
    private byte[] imageBytes;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_image, container, false);

        imageView = view.findViewById(R.id.imageView);
        back = view.findViewById(R.id.back);
        btnChooseImage = view.findViewById(R.id.btnChooseImage);
        btnSaveImage = view.findViewById(R.id.btnSaveImage);

        // Initialize SQLite Database Helper
        databaseHelper = new DBHelper(requireContext());

        btnChooseImage.setOnClickListener(v -> selectImage());
        back.setOnClickListener(v -> navigateToFragment(new List_Fragment ()));
        btnSaveImage.setOnClickListener(v -> {
            if (imageBytes != null) {
                saveImageToDatabase(imageBytes);
            } else {
                Toast.makeText(requireContext(), "No image selected!", Toast.LENGTH_SHORT).show();
            }
        });
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

    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    imageView.setImageURI(imageUri);
                    imageBytes = convertImageToByteArray(imageUri);
                }
            }
    );

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }

    private byte[] convertImageToByteArray(Uri uri) {
        try {
            ContentResolver contentResolver = requireActivity().getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(uri);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveImageToDatabase(byte[] imageData) {
        databaseHelper.insertImage(imageData);
        Toast.makeText(requireContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
    }


}
