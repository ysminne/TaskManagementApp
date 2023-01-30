package com.example.ict652;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ict652.fragments.FileFragment;
import com.example.ict652.fragments.PicturesFragment;
import com.example.ict652.fragments.TaskFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TaskFragment();
            case 1:
                return new PicturesFragment();
            case 2:
                return new FileFragment();
            default:
                return new TaskFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
