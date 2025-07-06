package vn.edu.fpt.eplinfo;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MatchDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        // Ánh xạ các TextView từ layout
        TextView tvHomeTeam = findViewById(R.id.tvHomeTeamDetail);
        TextView tvAwayTeam = findViewById(R.id.tvAwayTeamDetail);
        TextView tvScore = findViewById(R.id.tvScoreDetail);
        TextView tvDate = findViewById(R.id.tvDateDetail);
        TextView tvStatus = findViewById(R.id.tvStatusDetail);

        // Lấy dữ liệu từ Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tvHomeTeam.setText(extras.getString("homeTeam", "N/A"));
            tvAwayTeam.setText(extras.getString("awayTeam", "N/A"));
            tvScore.setText(extras.getString("score", "-"));
            tvDate.setText(extras.getString("date", "N/A"));
            tvStatus.setText(extras.getString("status", "N/A"));
        }
    }
}