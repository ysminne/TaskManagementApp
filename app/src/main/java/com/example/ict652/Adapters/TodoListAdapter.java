package com.example.ict652.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ict652.R;
import com.example.ict652.interfaces.RecyclerViewClickListener;
import com.example.ict652.model.TodoModel;

import java.util.ArrayList;
import java.util.Random;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.MyViewHolder> {
    ArrayList<TodoModel> arrayList;
    Context context;
    final private RecyclerViewClickListener clickListener;
    private RecyclerView.RecycledViewPool
            viewPool
            = new RecyclerView
            .RecycledViewPool();
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
                    myViewHolder.childRecyclerView.setVisibility(View.GONE);
                } else {
                    myViewHolder.accordian_body.setVisibility(View.VISIBLE);
                    myViewHolder.childRecyclerView.setVisibility(View.VISIBLE);

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
        TodoModel parentItem
                = arrayList.get(position);

        // For the created instance,
        // get the title and set it
        // as the text for the TextView
//        parentViewHolder
//                .ParentItemTitle
//                .setText(parentItem.getParentItemTitle());

        // Create a layout manager
        // to assign a layout
        // to the RecyclerView.

        // Here we have assigned the layout
        // as LinearLayout with vertical orientation
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(
                holder
                        .childRecyclerView
                        .getContext(),
                LinearLayoutManager.VERTICAL,
                false);

        // Since this is a nested layout, so
        // to define how many child items
        // should be prefetched when the
        // child RecyclerView is nested
        // inside the parent RecyclerView,
        // we use the following method
        layoutManager
                .setInitialPrefetchItemCount(
                        parentItem
                                .getMemberModelArrayList()
                                .size());

        // Create an instance of the child
        // item view adapter and set its
        // adapter, layout manager and RecyclerViewPool
        MemberAdapter childItemAdapter
                = new MemberAdapter(
                parentItem
                        .getMemberModelArrayList());
        holder
                .childRecyclerView
                .setLayoutManager(layoutManager);
        holder
                .childRecyclerView
                .setAdapter(childItemAdapter);
        holder
                .childRecyclerView
                .setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView accordian_title;
        TextView titleTv, descriptionTv, name, role;
        RelativeLayout accordian_body;
        ImageView arrow;
        ImageView editBtn;
        ImageView deleteBtn;
        ImageView doneBtn;
        ImageView addMember;
        private RecyclerView childRecyclerView;

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
            addMember =  itemView.findViewById(R.id.addMember);
            childRecyclerView = itemView.findViewById(R.id.recycler_view_member);

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
