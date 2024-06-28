package tlu.edu.vn.ht63.btlon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmStartActivity extends AppCompatActivity {
    private Button btnnghigiuagio, button;
    private MediaPlayer mediaPlayer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_alarm_start);

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound);
        btnnghigiuagio = findViewById(R.id.btnnghigiuagio);
        button = findViewById(R.id.button);

        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        btnnghigiuagio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                Intent myIntent = new Intent(AlarmStartActivity.this, BreakActivity.class);
                Intent[] myIntents = {myIntent};
                startActivities(myIntents);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                Intent myIntent = new Intent(AlarmStartActivity.this, StartActivity.class);
                Intent[] myIntents = {myIntent};
                startActivities(myIntents);
            }
        });
    }
}
