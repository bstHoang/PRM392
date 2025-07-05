package vn.edu.fpt.premierleagueinformation;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.fpt.premierleagueinformation.adapter.StandingAdapter;
import vn.edu.fpt.premierleagueinformation.api.ApiClient;
import vn.edu.fpt.premierleagueinformation.api.FootballApi;
import vn.edu.fpt.premierleagueinformation.model.StandingResponse;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static final String API_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FootballApi api = ApiClient.getClient().create(FootballApi.class);
        api.getStandings(API_KEY).enqueue(new Callback<StandingResponse>() {
            @Override
            public void onResponse(Call<StandingResponse> call, Response<StandingResponse> response) {
                if (response.isSuccessful()) {
                    List<StandingResponse.TableItem> table = response.body()
                            .getStandings().get(0).getTable();
                    recyclerView.setAdapter(new StandingAdapter(table));
                } else {
                    Log.e("API", "Response error: " + response.code());
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
