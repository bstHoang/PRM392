package vn.edu.fpt.eplinfo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.fpt.eplinfo.MatchAdapter;
import vn.edu.fpt.eplinfo.api.ApiClient;
import vn.edu.fpt.eplinfo.api.FootballApi;
import vn.edu.fpt.eplinfo.model.MatchResponse;

public class MatchScheduleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static final String API_KEY = "44626ee01940491ba6a1b76a97a44571";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_schedule);
        recyclerView = findViewById(R.id.recyclerViewMatches);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FootballApi api = ApiClient.getClient().create(FootballApi.class);
        api.getMatches(API_KEY).enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {
                if (response.isSuccessful()) {
                    recyclerView.setAdapter(new MatchAdapter(response.body().getMatches()));
                } else {
                    Log.e("API", "Response error: " + response.code());
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
}