package com.grability.appstore.modules.categories;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grability.appstore.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CategoriesActivityFragment extends Fragment {

    public CategoriesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }
}
