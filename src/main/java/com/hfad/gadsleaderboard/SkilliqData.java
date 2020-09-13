package com.hfad.gadsleaderboard;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface SkilliqData {
    @GET("/api/skilliq")
    Call<List<SkilliqLeaders>> getAllUsers();
}
