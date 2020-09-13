package com.hfad.gadsleaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface learnersData {
    @GET("/api/hours")
    Call<List<LearningLeaders>> getAllUsers();
}
