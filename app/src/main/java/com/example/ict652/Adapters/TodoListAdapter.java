package com.example.ict652.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ict652.R;
import com.example.ict652.interfaces.RecyclerViewClickListener;
import com.example.ict652.model.MemberModel;
import com.example.ict652.model.TodoModel;

import java.util.ArrayList;
import java.util.Random;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.MyViewHolder> {
    ArrayList<TodoModel> arrayList;
    Context context;
    final private RecyclerViewClickListener clickListener;
    public TodoListAdapter(Context context, ArrayList<TodoModel> arrayList, RecyclerViewClickListener clickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.todo_list_item, parent, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);

        int[] androidColors = view.getResources().getIntArray(R.array.androidcolors);
        int randomColors = androidColors[new Random().nextInt(androidColors.length)];

        myViewHolder.accordian_title.setBackgroundColor(randomColors);

        myViewHolder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myViewHolder.accordian_body.getVisibility() == View.VISIBLE) {
                    myViewHolder.accordian_body.setVisibility(View.GONE);
                } else {
                    myViewHolder.accordian_body.setVisibility(View.VISIBLE);
                }
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListAdapter.MyViewHolder holder, int position) {
        final String title = arrayList.get(position).getTitle();
        final String description = arrayList.get(position).getDescription();
        final String id = arrayList.get(position).getId();
        final String name = arrayList.get(position).getMemberModel().getName();
        final String role = arrayList.get(position).getMemberModel().getRole();

        holder.titleTv.setText(title);
        if(!description.equals("")) {
            holder.descriptionTv.setText(description);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView accordian_title;
        TextView titleTv, descriptionTv, name, role;
        RelativeLayout accordian_body;
        ImageView arrow, editBtn, deleteBtn, doneBtn, addMember;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = (TextView) itemView.findViewById(R.id.task_title);
            descriptionTv = (TextView) itemView.findViewById(R.id.task_description);
            accordian_title = (CardView) itemView.findViewById(R.id.accordian_title);
            accordian_body = (RelativeLayout) itemView.findViewById(R.id.accordian_body);
            arrow = (ImageView) itemView.findViewById(R.id.arrow);
            editBtn = (ImageView) itemView.findViewById(R.id.editBtn);
            deleteBtn = (ImageView) itemView.findViewById(R.id.deleteBtn);
            doneBtn = (ImageView) itemView.findViewById(R.id.doneBtn);
            addMember = (ImageView) itemView.findViewById(R.id.addMember);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListener.onLongItemClick(getAdapterPosition());
                    return true;
                }
            });

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onEditButtonClick(getAdapterPosition());
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDeleteButtonClick(getAdapterPosition());
                }
            });

            doneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDoneButtonClick(getAdapterPosition());
                }
            });

            addMember.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    clickListener.onAddMemberButtonClick(getAdapterPosition());
                }
            });
        }
    }
}
