package sg.edu.rp.c346.id20041877.songcompilation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    RadioGroup rgroup;
    Button btnInsert,btnShowList;
    RadioButton rb1, rb2, rb3, rb4, rb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etSong);
        etSingers = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgroup = findViewById(R.id.rg);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                boolean isChecked = true;
                int stars = 0;

                if(rb1.isChecked() && !rb2.isChecked() && !rb3.isChecked() && !rb4.isChecked() && !rb5.isChecked()){
                   isChecked = true;
                   //stars = 1;
                }
                else if(!rb1.isChecked() && rb2.isChecked() && !rb3.isChecked() && !rb4.isChecked() && !rb5.isChecked()) {
                    isChecked = true;
                    //stars = 2;
                }
                else if(!rb1.isChecked() && !rb2.isChecked() && rb3.isChecked() && !rb4.isChecked() && !rb5.isChecked()) {
                    isChecked = true;
                    //stars = 3;
                }
                else if(!rb1.isChecked() && !rb2.isChecked() && !rb3.isChecked() && rb4.isChecked() && !rb5.isChecked()) {
                    isChecked = true;
                    //stars = 4;
                }
                else if(!rb1.isChecked() && !rb2.isChecked() && !rb3.isChecked() && !rb4.isChecked() && rb5.isChecked()) {
                    isChecked = true;
                    //stars = 5;
                }
                else {
                    isChecked = false;
                }


                if(etTitle.getText().toString().isEmpty() || etSingers.getText().toString().isEmpty() || etYear.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Missing input", Toast.LENGTH_SHORT).show();
                }
                else {
                    String title = etTitle.getText().toString();
                    String singers = etSingers.getText().toString();
                    int year = Integer.parseInt(etYear.getText().toString());
                    int selectedId = rgroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    stars = Integer.parseInt(radioButton.getText().toString());

                    dbh.insertSong(title, singers, year, stars);
                    Toast.makeText(MainActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

    }
}