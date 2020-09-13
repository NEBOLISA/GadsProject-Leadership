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

class SkilliqAdapter extends
        RecyclerView.Adapter<SkilliqAdapter.CustomViewHolder> {
    private List<SkilliqLeaders> skilliqlist;

    public SkilliqAdapter(List<SkilliqLeaders> skilliqlist){

        this.skilliqlist = skilliqlist;
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
    Context context;
    @Override

//Set the data//

    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textUser.setText(skilliqlist.get(position).getName());
        holder.tv_hour.setText(String.valueOf(skilliqlist.get(position).getScore() + " " + "learning hours," + " " + skilliqlist.get(position).getCountry()));
        Picasso.get().load(skilliqlist.get(position).getBadgeUrl()).into(holder.tv_badgeUrl);

    }

//Calculate the item count for the RecylerView//

    @Override
    public int getItemCount() {
        return skilliqlist.size();
    }
}
