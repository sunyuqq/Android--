package com.example.novelapp.book;

import androidx.fragment.app.Fragment;

public class ShelfActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ShelfFragment();
    }

}
