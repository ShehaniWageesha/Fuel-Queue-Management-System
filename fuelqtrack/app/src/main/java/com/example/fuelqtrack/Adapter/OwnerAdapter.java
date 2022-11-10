package com.example.fuelqtrack.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelqtrack.Models.StationModel;
import com.example.fuelqtrack.R;
import com.example.fuelqtrack.UpdateStation;
import com.example.fuelqtrack.ViewQueueActivity;

import java.util.List;

public class OwnerAdapter extends RecyclerView.Adapter<OwnerAdapter.ownerAdapter>{

    List<StationModel> stationCAt;
    Context context;
    public OwnerAdapter(Context context, List<StationModel> stationCAt) {
        this.context = context;
        this.stationCAt = stationCAt;
    }

    public void setFuelData(List<StationModel> stationCAt) {
        this.stationCAt = stationCAt;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ownerAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new OwnerAdapter.ownerAdapter(LayoutInflater.from(context).inflate(R.layout.stationcard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ownerAdapter holder, int position) {
        StationModel stationModel= stationCAt.get(position);

        String fuelStationName = stationModel.getStationName();
        String stationAddress = stationModel.getStationLocation();

        holder.fuelStationName.setText(fuelStationName);
        holder.stationAddress.setText(stationAddress);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateStation.class);
                intent.putExtra("_id",stationModel.get_id());
                intent.putExtra("stationName", stationModel.getStationName());
                intent.putExtra("stationLocation", stationModel.getStationLocation());
                intent.putExtra("petrol", stationModel.getPetrol());
                intent.putExtra("diesel", stationModel.getDiesel());
                intent.putExtra("parivalTime", stationModel.getParivalTime());
                intent.putExtra("darivalTime", stationModel.getDarivalTime());
                intent.putExtra("pliters", stationModel.getPliters());
                intent.putExtra("dliters", stationModel.getDliters());
                intent.putExtra("pfinishTime", stationModel.getPfinishTime());
                intent.putExtra("dfinishTime", stationModel.getDfinishTime());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationCAt.size();
    }

    public class ownerAdapter extends RecyclerView.ViewHolder {
        TextView fuelStationName, stationAddress;
        Button view;
        public ownerAdapter(@NonNull View itemView) {
            super(itemView);
            fuelStationName = itemView.findViewById(R.id.textView9);
            stationAddress = itemView.findViewById(R.id.textView36);
            view = itemView.findViewById(R.id.view);
        }
    }
}
