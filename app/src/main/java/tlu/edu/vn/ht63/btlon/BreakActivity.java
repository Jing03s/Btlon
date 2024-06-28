package tlu.edu.vn.ht63.btlon;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BreakActivity extends AppCompatActivity {
    private CountDownTimer timerTask;
    private boolean isRunning = false;
    private long remainingTime = (long) (0.5 * 60 * 1000); // 50 minutes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_break);

        Button startButton = findViewById(R.id.button_start);
        Button stopButton = findViewById(R.id.button_stop);
        Button returnButton = findViewById(R.id.button_return);

        startButton.setOnClickListener(v -> startBreak());
        stopButton.setOnClickListener(v -> stopTimer());
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // return to course screen
                Intent i = new Intent(BreakActivity.this, HomePageActivity.class);
                startActivity(i);
            }
        });
    }

    private void startBreak() {
        timerTask = new CountDownTimer(remainingTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = millisUntilFinished;
                updateTimerDisplay();
            }

            @Override
            public void onFinish() {
                // Break finished, reset timer
                stopTimer();
                // Move to alarm break activity
                Intent i = new Intent(BreakActivity.this, AlarmBreakActivity.class);
                startActivity(i);
            }
        }.start();
    }

    private void stopTimer() {
        if (timerTask != null) {
            timerTask.cancel();
        }
        isRunning = false;
        updateTimerDisplay();
    }

    private void updateTimerDisplay() {
        int minutes = (int) (remainingTime / 1000) / 60;
        int seconds = (int) (remainingTime / 1000) % 60;
        TextView timerText = findViewById(R.id.text_timer);
        timerText.setText(String.format("%02d:%02d", minutes, seconds));
    }
}