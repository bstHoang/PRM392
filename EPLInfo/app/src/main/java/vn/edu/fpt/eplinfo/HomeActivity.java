package vn.edu.fpt.eplinfo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnStandings = findViewById(R.id.btnStandings);
        Button btnMatches = findViewById(R.id.btnMatches);

        btnStandings.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        btnMatches.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MatchScheduleActivity.class);
            startActivity(intent);
        });
    }
}