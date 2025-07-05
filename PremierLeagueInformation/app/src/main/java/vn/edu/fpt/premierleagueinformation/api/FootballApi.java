package vn.edu.fpt.premierleagueinformation.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import vn.edu.fpt.premierleagueinformation.model.StandingResponse;

public interface FootballApi {
    @GET("competitions/PL/standings")
    Call<StandingResponse> getStandings(@Header("X-Auth-Token") String token);
}
