package com.hfad.gadsleaderboard;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class SkillFragment extends Fragment {
    private RecyclerView myRecyclerView;
    private SkilliqAdapter myAdapter;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_skill, container, false);
        myRecyclerView = (RecyclerView) view.findViewById(R.id.pizza_recycler);
        SkilliqData service = LearnerClient.getRetrofitInstance().create(SkilliqData.class);
        Call<List<SkilliqLeaders>> call = service.getAllUsers();

//Execute the request asynchronously//

        call.enqueue(new Callback<List<SkilliqLeaders>>() {
            @Override

//Handle a successful response//

            public void onResponse(Call<List<SkilliqLeaders>> call, Response<List<SkilliqLeaders>> response) {
                loadDataList(response.body());
            }

            @Override

//Handle execution failures//

            public void onFailure(Call<List<SkilliqLeaders>> call, Throwable throwable) {

//If the request fails, then display the following toast//

                Toast.makeText(getContext(), "Unable to load users", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    private void loadDataList(List<SkilliqLeaders> usersList) {

//Get a reference to the RecyclerView//

        myAdapter = new SkilliqAdapter(usersList);

//Use a LinearLayoutManager with default vertical orientation//

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(layoutManager);

//Set the Adapter to the RecyclerView//

        myRecyclerView.setAdapter(myAdapter);
    }


}

