package com.jayan.jayashankar.notemaker;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.Explode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

public class
EditorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RichEditor editor;
    ImageButton activity_editor_bold,activity_editor_italics,activity_editor_underline,
            activity_editor_undo,activity_editor_redo,activity_editor_align_left,
            activity_editor_align_center,activity_editor_align_right,activity_editor_text_highlight;
    Spinner activity_editor_spinner;
    List headerTypeList = new ArrayList();
    private boolean isChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Toast.makeText(this, "Please bring focus to workspace before selecting any styles", Toast.LENGTH_LONG).show();
        editor = (RichEditor)findViewById(R.id.content_main_editor);

        final Checkers checkers = new Checkers();


        activity_editor_spinner = (Spinner)findViewById(R.id.activity_editor_spinner);
        activity_editor_bold = (ImageButton) findViewById(R.id.activity_editor_bold);
        activity_editor_italics = (ImageButton) findViewById(R.id.activity_editor_italics);
        activity_editor_underline = (ImageButton) findViewById(R.id.activity_editor_underline);
        activity_editor_undo = (ImageButton) findViewById(R.id.activity_editor_undo);
        activity_editor_redo = (ImageButton) findViewById(R.id.activity_editor_redo);
        activity_editor_align_left = (ImageButton) findViewById(R.id.activity_editor_align_left);
        activity_editor_align_center = (ImageButton) findViewById(R.id.activity_editor_align_center);
        activity_editor_align_right = (ImageButton) findViewById(R.id.activity_editor_align_right);
        activity_editor_text_highlight = (ImageButton)findViewById(R.id.activity_editor_text_highlight);


        if(!checkers.isLeftAlignSelected())
        {
            activity_editor_align_left.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_highlighted_background));
            checkers.setLeftAlignSelected(true);
        }
        else
        {
            activity_editor_align_left.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));
            checkers.setLeftAlignSelected(false);
        }

        for (int i=1;i<=6;i++)
        {
            headerTypeList.add("\t\tH"+i+"\t\t");
        }

        activity_editor_spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,headerTypeList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        activity_editor_spinner.setAdapter(arrayAdapter);

        FloatingActionButton activity_editor_save = (FloatingActionButton)findViewById(R.id.activity_editor_save);
        activity_editor_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivitySave.class);
                intent.putExtra("data",editor.getHtml());
                startActivity(intent);
            }
        });

        editor.setEditorHeight(200);
        editor.setEditorFontSize(18);

        editor.setPadding(10, 10, 10, 10);

        editor.setPlaceholder("Start typing ...");

        activity_editor_bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.setBold();

                if(!checkers.isBoldSelected())
                {
                    activity_editor_bold.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_highlighted_background));
                    checkers.setBoldSelected(true);
                }
                else
                {
                    activity_editor_bold.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));
                    checkers.setBoldSelected(false);
                }
            }
        });

        activity_editor_italics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.setItalic();

                if(!checkers.isItalicsSelected())
                {
                    activity_editor_italics.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_highlighted_background));
                    checkers.setItalicsSelected(true);
                }
                else
                {
                    activity_editor_italics.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));
                    checkers.setItalicsSelected(false);
                }
            }
        });

        activity_editor_underline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.setUnderline();

                if(!checkers.isUnderlineSelected())
                {
                    activity_editor_underline.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_highlighted_background));
                    checkers.setUnderlineSelected(true);
                }
                else
                {
                    activity_editor_underline.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));
                    checkers.setUnderlineSelected(false);
                }
            }
        });

        activity_editor_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.undo();
            }
        });

        activity_editor_redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.redo();
            }
        });

        activity_editor_align_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.setAlignLeft();

                if(!checkers.isLeftAlignSelected())
                {
                    activity_editor_align_left.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_highlighted_background));
                    activity_editor_align_center.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));
                    activity_editor_align_right.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));

                    checkers.setLeftAlignSelected(true);
                    checkers.setCenterALignSelected(false);
                    checkers.setRightAlignSelected(false);
                }
                else
                {
                    /*activity_editor_align_left.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));
                    checkers.setLeftAlignSelected(false);*/
                }
            }
        });

        activity_editor_align_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.setAlignCenter();

                if(!checkers.isCenterALignSelected())
                {
                    activity_editor_align_center.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_highlighted_background));
                    activity_editor_align_left.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));
                    activity_editor_align_right.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));

                    checkers.setCenterALignSelected(true);
                    checkers.setLeftAlignSelected(false);
                    checkers.setRightAlignSelected(false);
                }
                else
                {
                    activity_editor_align_center.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));
                    checkers.setCenterALignSelected(false);
                }
            }
        });

        activity_editor_align_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.setAlignRight();

                if(!checkers.isRightAlignSelected())
                {
                    activity_editor_align_right.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_highlighted_background));
                    activity_editor_align_left.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));
                    activity_editor_align_center.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));

                    checkers.setRightAlignSelected(true);
                    checkers.setLeftAlignSelected(false);
                    checkers.setCenterALignSelected(false);
                }
                else
                {
                    activity_editor_align_right.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));
                    checkers.setRightAlignSelected(false);
                }
            }
        });

        activity_editor_text_highlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isChanged)
                {
                    if(!checkers.isHighlightSelected())
                    {
                        isChanged = true;
                        checkers.setHighlightSelected(true);
                        activity_editor_text_highlight.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_highlighted_background));
                        editor.setTextBackgroundColor(Color.YELLOW);
                    }
                    else
                    {
                        isChanged = false;
                        checkers.setHighlightSelected(false);
                        editor.setTextBackgroundColor(Color.WHITE);
                        activity_editor_text_highlight.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_not_highlighted_background));
                    }
                }
                else
                {
                    isChanged = true;
                    checkers.setHighlightSelected(true);
                    activity_editor_text_highlight.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.button_highlighted_background));
                    editor.setTextBackgroundColor(Color.YELLOW);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        switch (item.trim())
        {
            case "H1":
                editor.setHeading(1);
                break;
            case "H2":
                editor.setHeading(2);
                break;
            case "H3":
                editor.setHeading(3);
                break;
            case "H4":

                editor.setHeading(4);
                break;
            case "H5":
                editor.setHeading(5);
                break;
            case "H6":
                editor.setHeading(6);
                break;
            default:

                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    class Checkers
    {
        private boolean boldSelected,italicsSelected,underlineSelected,leftAlignSelected,
        rightAlignSelected,centerALignSelected,highlightSelected;

        public boolean isHighlightSelected() {
            return highlightSelected;
        }

        public void setHighlightSelected(boolean highlightSelected) {
            this.highlightSelected = highlightSelected;
        }

        public boolean isCenterALignSelected() {
            return centerALignSelected;
        }

        public void setCenterALignSelected(boolean centerALignSelected) {
            this.centerALignSelected = centerALignSelected;
        }

        public boolean isRightAlignSelected() {
            return rightAlignSelected;
        }

        public void setRightAlignSelected(boolean rightAlignSelected) {
            this.rightAlignSelected = rightAlignSelected;
        }

        public boolean isLeftAlignSelected() {
            return leftAlignSelected;
        }

        public void setLeftAlignSelected(boolean leftAlignSelected) {
            this.leftAlignSelected = leftAlignSelected;
        }

        public boolean isUnderlineSelected() {
            return underlineSelected;
        }

        public void setUnderlineSelected(boolean underlineSelected) {
            this.underlineSelected = underlineSelected;
        }

        public boolean isItalicsSelected() {
            return italicsSelected;
        }

        public void setItalicsSelected(boolean italicsSelected) {
            this.italicsSelected = italicsSelected;
        }

        public boolean isBoldSelected() {
            return boldSelected;
        }

        public void setBoldSelected(boolean boldSelected) {
            this.boldSelected = boldSelected;
        }
    }
}
