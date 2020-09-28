package com.avijit.healthcareforcovid19.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.healthcareforcovid19.MainActivity;
import com.avijit.healthcareforcovid19.PlasmaDonorsActivity;
import com.avijit.healthcareforcovid19.R;
import com.avijit.healthcareforcovid19.model.Donor;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Avijit Acharjee on 9/28/2020 at 10:44 AM.
 * Email: avijitach@gmail.com.
 */
public class PlasmaDonorRecyclerViewAdapter extends RecyclerView.Adapter<PlasmaDonorRecyclerViewAdapter.ViewHolder> {
    private List<Donor> donorList;
    private Context context;
    public PlasmaDonorRecyclerViewAdapter(List<Donor> donorList, Context context){
        this.donorList = donorList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plasma_donor,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTextView.setText(donorList.get(position).getName());
        holder.bloodGroupTextView.setText(donorList.get(position).getBloodGroup());
        holder.phoneTextView.setText(donorList.get(position).getPhone());
        holder.phoneTextView.setOnClickListener(v->{
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            callIntent.setData(Uri.parse("tel:"+donorList.get(position).getPhone()));

            if (ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Permission is not granted to call", Toast.LENGTH_SHORT).show();
                return;
            }
            context.startActivity(callIntent);
        });
    }

    @Override
    public int getItemCount() {
        return donorList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView,bloodGroupTextView,phoneTextView;
        ViewHolder(View itemView){
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            bloodGroupTextView = itemView.findViewById(R.id.blood_group_text_view);
            phoneTextView = itemView.findViewById(R.id.phone_text_view);
        }
    }
}
