package tlu.edu.vn.ht63.btlon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmBreakActivity extends AppCompatActivity {
    private Button btnvekhoahoc, btndangxuat ;
    private MediaPlayer mediaPlayer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_alarm_break);

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound);
        btnvekhoahoc = findViewById(R.id.btnvekhoahoc);
        btndangxuat = findViewById(R.id.btndangxuat);

        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        btnvekhoahoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                Intent myIntent = new Intent(AlarmBreakActivity.this, HomePageActivity.class);
                Intent[] myIntents = {myIntent};
                startActivities(myIntents);
            }
        });

        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                Intent myIntent = new Intent(AlarmBreakActivity.this, LoginActivity.class);
                Intent[] myIntents = {myIntent};
                startActivities(myIntents);
            }
        });
    }
}
