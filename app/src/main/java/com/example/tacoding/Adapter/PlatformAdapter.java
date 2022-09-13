package com.example.tacoding.Adapter;

import static com.example.tacoding.Fragment.ContestFragment.map;
import static com.example.tacoding.Fragment.ContestFragment.platformViewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tacoding.Fragment.ContestFragment;
import com.example.tacoding.R;
import com.example.tacoding.tadatabase.Platform;
import com.example.tacoding.tadatabase.PlatformName;
import com.example.tacoding.tadatabase.PlatformViewModel;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class PlatformAdapter extends RecyclerView.Adapter<PlatformAdapter.PlatformViewHolder> {

    ArrayList<Platform> allPlatform = new ArrayList<>();
    ArrayList<PlatformName> allPlatformName = new ArrayList<>();
    Context context;


    IPlatformRVAdapter listener;
    int i = 0;
    boolean flag = true;


    public PlatformAdapter(Context context, IPlatformRVAdapter listener) {
        this.context = context;
        this.listener = listener;

    }

    @NonNull
    @Override
    public PlatformViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PlatformViewHolder viewHolder = new PlatformViewHolder(LayoutInflater.from(context).inflate(R.layout.coding_platform_story_design, parent, false));

        viewHolder.codingPlatformImage.setOnClickListener(allPlatforms -> {
            listener.onITemClick(allPlatform.get(viewHolder.getAdapterPosition()));
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlatformViewHolder holder, int position) {

        Platform currentPlatform = allPlatform.get(position);

        platformViewModel.getmAllPlatformName().observeForever(platformNames -> {



            while (i < platformNames.size()) {

                if (platformNames.get(i).getNplatformName().toLowerCase(Locale.ROOT).equalsIgnoreCase(currentPlatform.getPlatformName().toLowerCase(Locale.ROOT))) {
                    System.out.println("FIRST IF " + platformNames.get(i).getNplatformName() + " AND " + currentPlatform.getPlatformName());
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        String k = entry.getKey();
                        Integer v = entry.getValue();

                        if (platformNames.get(i).getNplatformName().toLowerCase(Locale.ROOT).equalsIgnoreCase(k)) {
                            System.out.println("SECOND IF " + platformNames.get(i).getNplatformName() + " AND KEY " + k);
                            if (currentPlatform.getPlatformName().toLowerCase(Locale.ROOT).equalsIgnoreCase(platformNames.get(i).getNplatformName().toLowerCase(Locale.ROOT))) {
                                System.out.println("PLATFORM ADAPTER " + "key: " + k + ", value: " + v);
                                holder.codingPlatformImage.setImageResource(v);
//                                i++;
                                flag = false;
                                break;
                            }
                        }
                    }

                    i++;
                    break;
                } else {

                    if (flag) {
                        System.out.println("ELSE " + platformNames.get(i).getNplatformName() + " AND " + currentPlatform.getPlatformName());
                        holder.codingPlatformImage.setImageResource(currentPlatform.getPlatformImage());
                        break;
                    }
                    i++;
                    flag = true;
                    break;

                }

            }


        });


        holder.codingPlatformName.setText(currentPlatform.getPlatformName());


    }

    public void updateList(ArrayList<Platform> newList) {
        allPlatform.clear();
        allPlatform.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allPlatform.size();
    }

    public class PlatformViewHolder extends RecyclerView.ViewHolder {

        ImageView codingPlatformImage;
        TextView codingPlatformName;

        public PlatformViewHolder(@NonNull View itemView) {
            super(itemView);

            codingPlatformName = itemView.findViewById(R.id.codingPlatformName);
            codingPlatformImage = itemView.findViewById(R.id.codingPlatformImage);
        }
    }
}

