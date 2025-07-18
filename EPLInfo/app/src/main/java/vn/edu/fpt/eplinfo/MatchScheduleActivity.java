package vn.edu.fpt.eplinfo;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.fpt.eplinfo.adapter.MatchAdapter;
import vn.edu.fpt.eplinfo.api.ApiClient;
import vn.edu.fpt.eplinfo.api.FootballApi;
import vn.edu.fpt.eplinfo.model.MatchResponse;
import vn.edu.fpt.eplinfo.util.SeasonUtil;

import java.util.ArrayList;
import java.util.List;

public class MatchScheduleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvSeason;
    private SearchView searchView;
    private MatchAdapter adapter;
    private List<MatchResponse.Match> fullMatchList;
    private static final String API_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_schedule);
        recyclerView = findViewById(R.id.recyclerViewMatches);
        tvSeason = findViewById(R.id.tvSeason);
        searchView = findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Thêm dòng kẻ ngang giữa các mục trong RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Thiết lập SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterMatches(newText);
                return true;
            }
        });

        // Lấy mùa giải từ Intent
        String season = getIntent().getStringExtra("season");
        if (season == null) season = SeasonUtil.getCurrentSeason();
        tvSeason.setText("Mùa giải: " + SeasonUtil.getSeasonDisplay(season));

        // Gọi API để lấy danh sách trận đấu
        FootballApi api = ApiClient.getClient().create(FootballApi.class);
        api.getMatches(API_KEY, season).enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    fullMatchList = response.body().getMatches();
                    adapter = new MatchAdapter(fullMatchList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("API", "Response error: " + response.code() + ", Message: " + response.message());
                    try {
                        Log.e("API", "Error body: " + response.errorBody().string());
                    } catch (Exception e) {
                        Log.e("API", "Failed to parse error body: " + e.getMessage());
                    }
                    Toast.makeText(MatchScheduleActivity.this, "API lỗi: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MatchResponse> call, Throwable t) {
                Log.e("API", "Failed: " + t.getMessage());
                Toast.makeText(MatchScheduleActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Lọc danh sách trận đấu dựa trên chuỗi tìm kiếm
    private void filterMatches(String query) {
        if (fullMatchList == null) return;
        List<MatchResponse.Match> filteredList = new ArrayList<>();
        for (MatchResponse.Match match : fullMatchList) {
            if (match.getHomeTeam().getName().toLowerCase().contains(query.toLowerCase()) ||
                    match.getAwayTeam().getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(match);
            }
        }
        adapter.updateData(filteredList);
    }
}