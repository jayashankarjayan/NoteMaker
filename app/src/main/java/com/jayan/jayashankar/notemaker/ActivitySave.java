package com.jayan.jayashankar.notemaker;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ActivitySave extends AppCompatActivity {

    TextInputEditText save_file_name;
    String content = "";
    String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/NotesMaker";
    TextInputEditText save_file_topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        Intent intent = getIntent();
        content = intent.getStringExtra("data");
        save_file_name = (TextInputEditText)findViewById(R.id.save_file_name);
        save_file_topic = (TextInputEditText)findViewById(R.id.save_file_topic);

        FloatingActionButton save_fab = (FloatingActionButton)findViewById(R.id.save_fab);

        save_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String filename = save_file_name.getText().toString();
                saveTextFileToStorage(filename);
                saveHTMLFileToStorage(filename);
            }
        });

    }

    private void saveHTMLFileToStorage(String fileName) {

        try
        {
            createDirectory();
            OutputStream fOut = null;
            File file = new File(fullPath, fileName+".html");
            if(file.exists())
                file.delete();
            file.createNewFile();
            fOut = new FileOutputStream(file);

            fOut.write(content.getBytes());

            fOut.flush();
            fOut.close();

            Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
            ActivitySave.this.finish();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "File not saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveTextFileToStorage(String fileName) {

        try
        {
            createDirectory();
            OutputStream fOut = null;
            File file = new File(fullPath, fileName+".txt");
            if(file.exists())
                file.delete();
            file.createNewFile();
            fOut = new FileOutputStream(file);

            fOut.write(content.getBytes());

            fOut.flush();
            fOut.close();

            Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
            ActivitySave.this.finish();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "File not saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void createDirectory() {
        File dir = new File(fullPath+"/"+save_file_topic.getText().toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
