package vn.edu.fpt.eplinfo;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvSeason;
    private SearchView searchView;
    private StandingAdapter adapter;
    private List<StandingResponse.Table> fullTableList;
    private static final String API_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        tvSeason = findViewById(R.id.tvSeason);
        searchView = findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lấy mùa giải từ Intent
        String season = getIntent().getStringExtra("season");
        if (season == null) season = SeasonUtil.getCurrentSeason();
        tvSeason.setText("Mùa giải: " + SeasonUtil.getSeasonDisplay(season));

        // Thiết lập SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterTeams(newText);
                return true;
            }
        });

        // Gọi API để lấy bảng xếp hạng
        FootballApi api = ApiClient.getClient().create(FootballApi.class);
        api.getStandings(API_KEY, season).enqueue(new Callback<StandingResponse>() {
            @Override
            public void onResponse(Call<StandingResponse> call, Response<StandingResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getStandings() != null) {
                    fullTableList = response.body().getStandings().get(0).getTable();
                    adapter = new StandingAdapter(fullTableList);
                    recyclerView.setAdapter(adapter);
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

    // Lọc danh sách đội bóng dựa trên chuỗi tìm kiếm
    private void filterTeams(String query) {
        if (fullTableList == null) return;
        List<StandingResponse.Table> filteredList = new ArrayList<>();
        for (StandingResponse.Table table : fullTableList) {
            if (table.getTeam().getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(table);
            }
        }
        adapter.updateData(filteredList);
    }
}