package vn.edu.fpt.eplinfo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import vn.edu.fpt.eplinfo.util.SeasonUtil;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Lấy mùa giải hiện tại và mùa trước
        String currentSeason = SeasonUtil.getCurrentSeason();
        String previousSeason = SeasonUtil.getPreviousSeason();

        Button btnStandingsCurrent = findViewById(R.id.btnStandingsCurrent);
        Button btnMatchesCurrent = findViewById(R.id.btnMatchesCurrent);
        Button btnStandingsPrevious = findViewById(R.id.btnStandingsPrevious);
        Button btnMatchesPrevious = findViewById(R.id.btnMatchesPrevious);

        // Cập nhật văn bản nút với mùa giải động
        btnStandingsCurrent.setText("BXH");
        btnMatchesCurrent.setText("Lịch Thi Đấu");
        btnStandingsPrevious.setText("BXH");
        btnMatchesPrevious.setText("Lịch Thi Đấu");

        // Bảng xếp hạng mùa hiện tại
        btnStandingsCurrent.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.putExtra("season", currentSeason);
            startActivity(intent);
        });

        // Lịch thi đấu mùa hiện tại
        btnMatchesCurrent.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MatchScheduleActivity.class);
            intent.putExtra("season", currentSeason);
            startActivity(intent);
        });

        // Bảng xếp hạng mùa trước
        btnStandingsPrevious.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.putExtra("season", previousSeason);
            startActivity(intent);
        });

        // Lịch thi đấu mùa trước
        btnMatchesPrevious.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MatchScheduleActivity.class);
            intent.putExtra("season", previousSeason);
            startActivity(intent);
        });
    }
}