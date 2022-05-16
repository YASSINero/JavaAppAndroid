package com.example.javaappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ImageFormat;
import android.media.Image;
import android.media.ImageReader;
import android.os.storage.StorageManager;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.MediaColumns;
import android.os.Bundle;
import java.lang.Object;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean canClearAll;
    private boolean worked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button stBtn = findViewById(R.id.firstButton);
        Button ndBtn = findViewById(R.id.secondButton);
        ImageButton imgBtn = findViewById(R.id.imageBtn);

        CheckBox clearAllCheckB = findViewById(R.id.clearAllCheckB);

        stBtn.setOnClickListener(this);
        ndBtn.setOnClickListener(this);
        imgBtn.setOnClickListener(this);

        clearAllCheckB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    Toast.makeText(MainActivity.this, "checked", Toast.LENGTH_SHORT).show();
                        canClearAll = true;
                } else canClearAll = false;
            }
        });

    }



    @Override
    public void onClick(View view) {

        TextView txtView = findViewById(R.id.textView1stName);
        EditText edt1stName = findViewById(R.id.editTxtFirstName);

        TextView nameTxtView = findViewById(R.id.textViewName);
        EditText edtName = findViewById(R.id.editTxtName);

        TextView emailTxtView = findViewById(R.id.textViewEmail);
        EditText edtDomain = findViewById(R.id.editTxtDomain);

        ProgressBar lengthBar = findViewById(R.id.lengthBar);

        Thread thread = new Thread(new Runnable()
        {
          @Override
          public void run()
            {
              while (edt1stName.getText().length() + edtName.getText().length() <= 12) {
                  lengthBar.setProgress(edt1stName.getText().length() + edtName.getText().length());
              }
            }
        });
        thread.start();

        switch(view.getId())
        {
            case R.id.firstButton:

                txtView.setText("First Name: " + edt1stName.getText().toString());


                nameTxtView.setText("Name: " + edtName.getText().toString());

                if(edt1stName.getText().length() + edtName.getText().length() <= 12) {

                    emailTxtView.setText("Suggested Email: " + edt1stName.getText().toString() + edtName.getText().toString() + edtDomain.getText().toString());
                    Toast.makeText(this, "Email suggested", Toast.LENGTH_SHORT).show();
                    worked = true;

                }else Toast.makeText(this, "Email too long", Toast.LENGTH_SHORT).show();
                break;
            case R.id.secondButton:
                if(canClearAll)
                {
                    edt1stName.setText("");
                    edtName.setText("");
                    edtDomain.setText("");
                    txtView.setText("First Name: ");
                    nameTxtView.setText("Name: ");
                    emailTxtView.setText("Suggested Email: ");
                    Toast.makeText(this, "All Cleared", Toast.LENGTH_SHORT).show();

                }else {
                    txtView.setText("First Name: ");
                    nameTxtView.setText("Name: ");
                    emailTxtView.setText("Suggested Email: ");
                    Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show();
                }
                worked = false;
                break;
            case R.id.imageBtn:
Toast.makeText(this, "image BTN", Toast.LENGTH_SHORT).show();

                break;

            default:
                Toast.makeText(this, "Press one of the two Buttons", Toast.LENGTH_SHORT).show();
                break;
        }
        if(worked){
            emailTxtView.setTextIsSelectable(true);
        }else emailTxtView.setTextIsSelectable(false);

    }



    /*
    public void btnClickable(View view)
    {
        TextView txtView = findViewById(R.id.textView1stName);
        EditText edt1stName = findViewById(R.id.editTxtFirstName);
        txtView.setText("First Name: " + edt1stName.getText().toString());

        TextView nameTxtView = findViewById(R.id.textViewName);
        EditText edtName = findViewById(R.id.editTxtName);
        nameTxtView.setText("Name: " + edtName.getText().toString());

        TextView emailTxtView = findViewById(R.id.textViewEmail);
        EditText edtDomain = findViewById(R.id.editTxtDomain);
        emailTxtView.setText("Suggested Email: " + edt1stName.getText().toString() + edtName.getText().toString() + edtDomain.getText().toString());
    }

    public void sdBtnClickable(View view)
    {
        TextView txtView = findViewById(R.id.textView1stName);
        TextView nameTxtView = findViewById(R.id.textViewName);
        TextView emailTxtView = findViewById(R.id.textViewEmail);

        txtView.setText("First Name: ");
        nameTxtView.setText("Name: ");
        emailTxtView.setText("Suggested Email: ");
    }
*/

}
