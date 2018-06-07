package com.jayan.jayashankar.notemaker;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

public class ActivityHome extends AppCompatActivity {


    private static final int READ_EXTERNAL_MEMORY = 1;
    private static final int WRITE_EXTERNAL_MEMORY = 2;
    String pathToFolder = Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/storage/emulated/0/NotesMaker";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        permissions();
        // inside your activity (if you did not enable transitions in your theme)

        /*File myDir = new File(pathToFolder);
        if(!myDir.exists())
        {

            myDir.mkdir();
        }
        else
        {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                // Check if we're running on Android 5.0 or higher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Apply activity transition
                } else {
                    // Swap without transition
                }
                startActivity(new Intent(getApplicationContext(),EditorActivity.class), ActivityOptions.makeSceneTransitionAnimation(ActivityHome.this).toBundle());
            }
        });

    }

    private void permissions()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this)
                        .setMessage("You need to provide permission to read phone state")
                        .setPositiveButton("Ok",null);
                builder.show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},READ_EXTERNAL_MEMORY);
            }

        }


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this)
                        .setMessage("You need to provide permission to save files")
                        .setPositiveButton("Ok",null);
                builder.show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_EXTERNAL_MEMORY);
            }

        }
    }
}
