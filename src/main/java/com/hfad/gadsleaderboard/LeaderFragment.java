package com.hfad.gadsleaderboard;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderFragment extends Fragment {
    private RecyclerView myRecyclerView;
    private LeanersAdapter myAdapter;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view =inflater.inflate(R.layout.fragment_leader,container,false);
        myRecyclerView = (RecyclerView) view.findViewById(R.id.pizza_recycler);
        learnersData service = LearnerClient.getRetrofitInstance().create(learnersData.class);
        Call<List<LearningLeaders>> call = service.getAllUsers();

//Execute the request asynchronously//

        call.enqueue(new Callback<List<LearningLeaders>>() {
            @Override

//Handle a successful response//

            public void onResponse(Call<List<LearningLeaders>> call, Response<List<LearningLeaders>> response) {
                loadDataList(response.body());
            }

            @Override

//Handle execution failures//

            public void onFailure(Call<List<LearningLeaders>> call, Throwable throwable) {

//If the request fails, then display the following toast//

                Toast.makeText(getContext(), "Unable to load users", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    //Display the retrieved data as a list//
    private void loadDataList(List<LearningLeaders> usersList) {

//Get a reference to the RecyclerView//

        myAdapter = new LeanersAdapter(usersList);

//Use a LinearLayoutManager with default vertical orientation//

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(layoutManager);

//Set the Adapter to the RecyclerView//

        myRecyclerView.setAdapter(myAdapter);
    }


    }

