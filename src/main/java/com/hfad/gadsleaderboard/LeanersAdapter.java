package com.hfad.gadsleaderboard;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class LeanersAdapter extends
            RecyclerView.Adapter<LeanersAdapter.CustomViewHolder> {
    private List<LearningLeaders> dataList;

    public LeanersAdapter(List<LearningLeaders> dataList){

        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

//Get a reference to the Views in our layout//

        public final View myView;

        TextView textUser,tv_hour;
        ImageView tv_badgeUrl;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            textUser = myView.findViewById(R.id.tv_name);
            tv_hour=myView.findViewById(R.id.tv_hour);
            tv_badgeUrl=myView.findViewById(R.id.tv_badgeUrl);


        }
    }

    @Override

//Construct a RecyclerView.ViewHolder//

    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.learners_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override

//Set the data//

    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textUser.setText(dataList.get(position).getName());
        holder.tv_hour.setText(String.valueOf(dataList.get(position).getHours()+ " " + "learning hours," + " " + dataList.get(position).getCountry()));
        Picasso.get().load(dataList.get(position).getBadgeUrl()).into(holder.tv_badgeUrl);

    }

//Calculate the item count for the RecylerView//

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
