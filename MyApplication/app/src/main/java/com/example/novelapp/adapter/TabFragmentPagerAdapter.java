package com.example.novelapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class TabFragmentPagerAdapter extends FragmentStateAdapter {
    private List<Fragment> mlist;

    public TabFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> mlist) {
        super(fragmentActivity);
        this.mlist=mlist;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // return your fragment that corresponds to this 'position'
        return mlist.get(position);
    }


    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    public void addFragment(Fragment fragment) {
        mlist.add(fragment);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}