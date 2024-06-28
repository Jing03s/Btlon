package tlu.edu.vn.ht63.btlon;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    private TextView textView;
    private boolean isHelloWorld = true;
    private CountDownTimer timerTask;
    private boolean isRunning = false;
    private long remainingTime = (long) (0.5 * 60 * 1000); // 50 minutes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start);
        textView = findViewById(R.id.textView);

        // Chuyển đổi chữ liên tục
        changeText();



        Button startButton = findViewById(R.id.button_start);
        Button stopButton = findViewById(R.id.button_stop);
        Button returnButton = findViewById(R.id.button_return);

        startButton.setOnClickListener(v -> startTimer());
        stopButton.setOnClickListener(v -> stopTimer());
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // return to course screen
                Intent i = new Intent(StartActivity.this, HomePageActivity.class);
                startActivity(i);
            }
        });

    }


    private void startTimer() {
        if (!isRunning) {
            timerTask = new CountDownTimer(remainingTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    remainingTime = millisUntilFinished;
                    updateTimerDisplay();
                }

                @Override
                public void onFinish() {
                    // Move to alarm start activity
                    Intent i = new Intent(StartActivity.this, AlarmStartActivity.class);
                    startActivity(i);
                }
            }.start();
            isRunning = true;
        }
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
    private void changeText() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isHelloWorld) {
                    textView.setText("Đừng để những kiến thức hiện có khiến chúng ta ngừng học hỏi. Thế giới vô cùng rộng lớn và học tập chưa bao giờ là đủ.");
                } else if (isHelloWorld == false && !isHelloWorld) {
                    textView.setText("Học tập không phải là dồn ép tất cả mọi thứ và đầu. Biết cách học vừa đủ, đó mới là người thông minh.");
                } else if (isHelloWorld == false && !isHelloWorld) {
                    textView.setText("Nếu hỏi một câu ngốc nghếch thì nó chỉ kéo dài vài phút. Còn không đặt câu hỏi, sự ngu ngốc sẽ kéo dài cả đời.");
                } else {
                    textView.setText("Học là phải luôn đi đôi với hành. Học lý thuyết phải tìm cách áp dụng vào thực tiễn, có vậy kiến thức mới có thể lưu lại thật lâu.");
                }

                isHelloWorld = !isHelloWorld;
                changeText();
            }
        }, 5000);
    }
}