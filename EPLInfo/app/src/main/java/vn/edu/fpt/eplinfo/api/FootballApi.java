package vn.edu.fpt.eplinfo.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import vn.edu.fpt.eplinfo.model.MatchResponse;
import vn.edu.fpt.eplinfo.model.StandingResponse;

public interface FootballApi {
    @GET("competitions/PL/standings")
    Call<StandingResponse> getStandings(@Header("X-Auth-Token") String token);

    @GET("competitions/PL/matches")
    Call<MatchResponse> getMatches(@Header("X-Auth-Token") String token);
}