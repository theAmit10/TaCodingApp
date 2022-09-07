package com.example.tacoding.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tacoding.Model.CodingPlatformModel;
import com.example.tacoding.R;
import java.util.ArrayList;

public class CodingPlatformAdapter extends RecyclerView.Adapter<CodingPlatformAdapter.viewHolder> {

    ArrayList<CodingPlatformModel> list;
    Context context;
    ArrayList<String> selectedPlatformSet = new ArrayList<>();


    public CodingPlatformAdapter(ArrayList<CodingPlatformModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.coding_platform_story_design, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CodingPlatformModel codingPlatformModel = list.get(position);
        holder.codingPlatformName.setText(codingPlatformModel.getCodingPlatformName());

        final boolean[] check = {true};
        if(check[0]){
            holder.codingPlatformImage.setImageResource(codingPlatformModel.getCodingPlatformImage());
        }else {

        }

        holder.codingPlatformImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selectedPlatformSet.contains(holder.codingPlatformName.getText().toString())) {

                    selectedPlatformSet.add(holder.codingPlatformName.getText().toString());
                    holder.codingPlatformImage.setImageResource(R.drawable.pogo_ic_checked_tick_svgrepo_com);
                    Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();
                    check[0] = false;


                    updateSelectedList();

                } else {
                    selectedPlatformSet.remove(holder.codingPlatformName.getText().toString());
                    selectedPlatformSet.remove(holder.codingPlatformName.getText().toString());
                    holder.codingPlatformImage.setImageResource(codingPlatformModel.getCodingPlatformImage());
                    Toast.makeText(context, "Disselected", Toast.LENGTH_SHORT).show();
                    updateSelectedList();
                }

            }
        });


        if (selectedPlatformSet.size() > 0) {
            for (int i = 0; i < selectedPlatformSet.size(); i++) {
                System.out.println("YOYOYOYG : " + selectedPlatformSet);

            }
        }


    }

    public ArrayList<String> getArrayListSELECTED() {
        return selectedPlatformSet;
    }


    public void updateSelectedList() {
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView codingPlatformImage;
        TextView codingPlatformName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            codingPlatformImage = itemView.findViewById(R.id.codingPlatformImage);
            codingPlatformName = itemView.findViewById(R.id.codingPlatformName);
//            checkedPlatform = itemView.findViewById(R.id.checkedPlatform);
        }
    }
}
