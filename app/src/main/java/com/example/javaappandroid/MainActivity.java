package com.example.javaappandroid;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean canClearAll;
    private boolean worked;
    private ActivityResultLauncher activityResultLauncher;
    private BracesProcess bracesProcess = new BracesProcess();
    private TextView ProcessTxt;
    private TextView resultTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        Button stBtn = findViewById(R.id.firstButton);
        Button ndBtn = findViewById(R.id.secondButton);
        Button ProcessBtn = findViewById(R.id.ProcessBtn);

        CheckBox clearAllCheckB = findViewById(R.id.clearAllCheckB);

        stBtn.setOnClickListener(this);
        ndBtn.setOnClickListener(this);
        ProcessBtn.setOnClickListener(this);

        clearAllCheckB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "checked", Toast.LENGTH_SHORT).show();
                    canClearAll = true;
                } else canClearAll = false;
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();

            ProcessTxt = findViewById(R.id.txtFile);
            resultTxt = findViewById(R.id.resultTxt);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    try {
                        String toProcess = readTextFromUri(uri);
                        ProcessTxt.setText(toProcess);
                        resultTxt.setText(bracesProcess.processBrackets(toProcess));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Do
                }
            }
        });



    }


    private String readTextFromUri(Uri uri) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Toast.makeText(MainActivity.this, "Text Reading...", Toast.LENGTH_SHORT).show();
        System.out.println("Reading file");
        try (InputStream inputStream = getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            // The result data contains a URI for the document or directory that
            // the user selected.
            Uri fileUri = null;
            if (resultData != null) {
                fileUri = resultData.getData();
                // Perform operations on the document using its URI.
                try {
                    System.out.println("Will read file");
                    String toBeCorrectedTXT = readTextFromUri(fileUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    public void goToMainMenu(View view) {

        setContentView(R.layout.activity_main);

    }


    public void exploreFiles(View view) {


        Toast.makeText(this, "image BTN", Toast.LENGTH_SHORT).show();

        System.out.println("will open file");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");

        if (intent.resolveActivity(getPackageManager()) != null) {
            activityResultLauncher.launch(intent);
        } else {
            Toast.makeText(MainActivity.this, "There is no app that support this action", Toast.LENGTH_SHORT).show();

        }


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

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (edt1stName.getText().length() + edtName.getText().length() <= 12) {
                    lengthBar.setProgress(edt1stName.getText().length() + edtName.getText().length());
                }
            }
        });
        thread.start();

        switch (view.getId()) {
            case R.id.firstButton:

                txtView.setText("First Name: " + edt1stName.getText().toString());


                nameTxtView.setText("Name: " + edtName.getText().toString());

                if (edt1stName.getText().length() + edtName.getText().length() <= 12) {

                    emailTxtView.setText("Suggested Email: " + edt1stName.getText().toString() + edtName.getText().toString() + edtDomain.getText().toString());
                    Toast.makeText(this, "Email suggested", Toast.LENGTH_SHORT).show();
                    worked = true;

                } else Toast.makeText(this, "Email too long", Toast.LENGTH_SHORT).show();
                break;
            case R.id.secondButton:
                if (canClearAll) {
                    edt1stName.setText("");
                    edtName.setText("");
                    edtDomain.setText("");
                    txtView.setText("First Name: ");
                    nameTxtView.setText("Name: ");
                    emailTxtView.setText("Suggested Email: ");
                    Toast.makeText(this, "All Cleared", Toast.LENGTH_SHORT).show();

                } else {
                    txtView.setText("First Name: ");
                    nameTxtView.setText("Name: ");
                    emailTxtView.setText("Suggested Email: ");
                    Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show();
                }
                worked = false;
                break;

            case R.id.ProcessBtn:
                setContentView(R.layout.activity_second);

                break;

            default:
                Toast.makeText(this, "Press one of the two Buttons", Toast.LENGTH_SHORT).show();
                break;
        }
        emailTxtView.setTextIsSelectable(worked);

        }



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


