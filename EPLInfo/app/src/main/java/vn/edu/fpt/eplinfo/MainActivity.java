package vn.edu.fpt.eplinfo;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.fpt.eplinfo.adapter.StandingAdapter;
import vn.edu.fpt.eplinfo.api.ApiClient;
import vn.edu.fpt.eplinfo.api.FootballApi;
import vn.edu.fpt.eplinfo.model.StandingResponse;
import vn.edu.fpt.eplinfo.util.SeasonUtil;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvSeason;
    private static final String API_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        tvSeason = findViewById(R.id.tvSeason);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lấy mùa giải từ Intent
        String season = getIntent().getStringExtra("season");
        if (season == null) season = SeasonUtil.getCurrentSeason(); // Mặc định là mùa hiện tại
        tvSeason.setText("Mùa giải: " + SeasonUtil.getSeasonDisplay(season));

        FootballApi api = ApiClient.getClient().create(FootballApi.class);
        api.getStandings(API_KEY, season).enqueue(new Callback<StandingResponse>() {
            @Override
            public void onResponse(Call<StandingResponse> call, Response<StandingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recyclerView.setAdapter(new StandingAdapter(response.body().getStandings().get(0).getTable()));
                } else {
                    Log.e("API", "Response error: " + response.code() + ", Message: " + response.message());
                    try {
                        Log.e("API", "Error body: " + response.errorBody().string());
                    } catch (Exception e) {
                        Log.e("API", "Failed to parse error body: " + e.getMessage());
                    }
                    Toast.makeText(MainActivity.this, "API lỗi: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StandingResponse> call, Throwable t) {
                Log.e("API", "Failed: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
            }
        });
    }
}