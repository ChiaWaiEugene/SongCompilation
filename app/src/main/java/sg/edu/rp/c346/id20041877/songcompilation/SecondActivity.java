package sg.edu.rp.c346.id20041877.songcompilation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class SecondActivity extends AppCompatActivity {

    Button btnTop;
    ListView lv;
    ArrayList<Song> songsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnTop = findViewById(R.id.btnTop);
        lv = findViewById(R.id.listView);

        songsArray = new ArrayList<Song>();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songsArray);
        lv.setAdapter(adapter);

        DBHelper dbh = new DBHelper(SecondActivity.this);
        songsArray.addAll(dbh.getAllSongs());
        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Song data = songsArray.get(position);
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("Song", data);
                startActivity(i);
            }
        });

        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);

                songsArray.clear();
                songsArray.addAll(dbh.getAllSongs());
                adapter.notifyDataSetChanged();
            }
        });

    }
}