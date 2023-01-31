package com.example.ict652.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ict652.R;
import com.example.ict652.interfaces.RecyclerViewClickListener;
import com.example.ict652.model.MemberModel;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MyViewHolder> {
    ArrayList<MemberModel> arrayList;
    Context context;
    //    final private RecyclerViewClickListener clickListener;
    public MemberAdapter(ArrayList<MemberModel> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
//        this.clickListener = clickListener;
    }
    @NonNull
    @Override
    public MemberAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.member_list_item,
                        parent, false);

        final MemberAdapter.MyViewHolder myViewHolder = new MemberAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.MyViewHolder holder, int position) {
        final String name = arrayList.get(position).getName();
        final String role = arrayList.get(position).getRole();
        holder.name_view.setText(name);
        holder.role_view.setText(role);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_view;
        TextView role_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_view = itemView.findViewById(R.id.member_name_view);
            role_view = itemView.findViewById(R.id.member_role_view);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    clickListener.onItemClick(getAdapterPosition());
//                }
//            });
//
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    clickListener.onLongItemClick(getAdapterPosition());
//                    return true;
//                }
//            });


        }
    }
}
