/*
package com.example.calculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CustomDialogFragment extends DialogFragment {

    File history_file = new File("src/main/java/com/example/calculator/history_file.txt");
    FileReader reader = new FileReader(history_file);
    int get_message;
    char[] buff = new char[10];

    public CustomDialogFragment() throws FileNotFoundException {
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        while (true) {
            try {
                if (!reader.ready()) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                get_message = reader.read(buff);
            } catch (IOException e) {
                e.printStackTrace();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            return builder
                    .setTitle("Диалоговое окно")
                    .setMessage(get_message)
                    .setPositiveButton("OK", null)
                    .setNegativeButton("Отмена", null)
                    .create();

        }
        return null;
    }
}*/
