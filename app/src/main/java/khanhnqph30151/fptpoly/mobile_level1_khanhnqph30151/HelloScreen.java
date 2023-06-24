package khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

public class HelloScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_screen);
        ImageView iv_banner = findViewById(R.id.iv_banner);

        CountDownTimer countDownTimer = new CountDownTimer(2000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent i = new Intent(HelloScreen.this, MainActivity.class);
                startActivity(i);
            }
        };

        iv_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HelloScreen.this, MainActivity.class);
                startActivity(i);
            }
        });

    }


}