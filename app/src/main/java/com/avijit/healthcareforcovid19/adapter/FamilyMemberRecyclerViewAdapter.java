package com.avijit.healthcareforcovid19.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.healthcareforcovid19.R;

import java.util.List;


public class FamilyMemberRecyclerViewAdapter extends RecyclerView.Adapter<FamilyMemberRecyclerViewAdapter.ViewHolder>{
    List<String> names,ages,relations;

    public FamilyMemberRecyclerViewAdapter(List<String> names, List<String> ages, List<String> relations) {
        this.names = names;
        this.ages = ages;
        this.relations = relations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_family_member,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTextView.setText("Name: "+names.get(position));
        holder.ageTextView.setText("Age: "+ages.get(position));
        holder.relationTextView.setText("Relation: "+relations.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView,ageTextView,relationTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            ageTextView = itemView.findViewById(R.id.age_text_view);
            relationTextView = itemView.findViewById(R.id.relation_text_view);
        }
    }
}
