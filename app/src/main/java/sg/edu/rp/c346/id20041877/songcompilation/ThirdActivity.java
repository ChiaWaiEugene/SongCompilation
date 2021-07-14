package sg.edu.rp.c346.id20041877.songcompilation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    TextView etID;
    EditText etTitle, etSingers, etYear;
    RadioGroup rg;
    Button btnUpdate,btnDelete, btnCancel;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.editTitle);
        etSingers = findViewById(R.id.editSingers);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.rg);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        Intent intent = getIntent();
        data = (Song) intent.getSerializableExtra("data");
        System.out.println(data);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                if(etTitle.getText().toString().isEmpty() || etSingers.getText().toString().isEmpty() || etYear.getText().toString().isEmpty()){
                    Toast.makeText(ThirdActivity.this, "Missing input", Toast.LENGTH_SHORT).show();
                }
                else{
                    int selectedId = rg.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    int stars = Integer.parseInt(radioButton.getText().toString());

                    data.setTitle(etTitle.getText().toString());
                    data.setSingers(etSingers.getText().toString());
                    data.setYear(Integer.parseInt(etYear.getText().toString()));
                    data.setStars(stars);

                    System.out.println(data);
                    dbh.updateSong(data);
                    dbh.close();
                    Toast.makeText(ThirdActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteNote(data.get_id());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}