package com.example.javaappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean canClearAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button stBtn = findViewById(R.id.firstButton);
        Button ndBtn = findViewById(R.id.secondButton);
        CheckBox clearAllCheckB = findViewById(R.id.clearAllCheckB);
        stBtn.setOnClickListener(this);
        ndBtn.setOnClickListener(this);
        clearAllCheckB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    Toast.makeText(MainActivity.this, "checked", Toast.LENGTH_SHORT);
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


        switch(view.getId())
        {
            case R.id.firstButton:

                txtView.setText("First Name: " + edt1stName.getText().toString());


                nameTxtView.setText("Name: " + edtName.getText().toString());

                if(edt1stName.getText().toString().length() + edtName.getText().toString().length() <= 12) {
                    emailTxtView.setText("Suggested Email: " + edt1stName.getText().toString() + edtName.getText().toString() + edtDomain.getText().toString());
                    Toast.makeText(this, "Email suggested", Toast.LENGTH_SHORT).show();
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
                break;
            default:
                Toast.makeText(this, "Press one of the two Buttons", Toast.LENGTH_SHORT).show();
                break;
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

}