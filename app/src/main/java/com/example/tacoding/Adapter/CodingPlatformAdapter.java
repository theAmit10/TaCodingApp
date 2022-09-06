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
import androidx.room.Room;

import com.example.tacoding.Model.CodingPlatformModel;
import com.example.tacoding.R;
import com.example.tacoding.SDatabase.Word;
import com.example.tacoding.SDatabase.WordRoomDatabase;
import com.example.tacoding.SDatabase.WordRoomDatabase_Impl;
import com.example.tacoding.SDatabase.WordViewModel;
//import com.example.tacoding.taDatabase.SelectedContestList;

import java.util.ArrayList;

public class CodingPlatformAdapter extends RecyclerView.Adapter<CodingPlatformAdapter.viewHolder> {

    ArrayList<CodingPlatformModel> list;
    Context context;
    ArrayList<String> selectedPlatformSet = new ArrayList<>();

    private WordViewModel mWVM;


//    ArrayList<SelectedContestList> mSelectedPlatformList = new ArrayList<SelectedContestList>();


    public CodingPlatformAdapter(ArrayList<CodingPlatformModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.coding_platform_story_design, parent, false);
        mWVM = new ViewModelProvider((ViewModelStoreOwner) this.context).get(WordViewModel.class);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CodingPlatformModel codingPlatformModel = list.get(position);
//        SelectedContestList selectedContestList = mSelectedPlatformList.get(position);
//        holder.codingPlatformImage.setImageResource(codingPlatformModel.getCodingPlatformImage());
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
//                    SelectedContestList sc = new SelectedContestList(holder.codingPlatformName.getText().toString());
                    holder.codingPlatformImage.setImageResource(R.drawable.pogo_ic_checked_tick_svgrepo_com);
                    Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();
                    check[0] = false;
                    Word word = new Word(holder.codingPlatformName.getText().toString());
                    mWVM.insert(word);

                    System.out.println("AMIT"+ mWVM.getAllWords().toString());

                    updateSelectedList();

                } else {
                    selectedPlatformSet.remove(holder.codingPlatformName.getText().toString());
                    selectedPlatformSet.remove(holder.codingPlatformName.getText().toString());
                    holder.codingPlatformImage.setImageResource(codingPlatformModel.getCodingPlatformImage());
                    Toast.makeText(context, "Disselected", Toast.LENGTH_SHORT).show();
                    updateSelectedList();
                }
//                if (selectedPlatformSet.size() > 0) {
//
//                    for (int i = 0; i < selectedPlatformSet.size(); i++) {
//                        System.out.println("YOYOYOY : " + selectedPlatformSet);
//
//                    }
//                }
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

//        selectedPlatformSet.addAll(selectedPlatformSet);

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
