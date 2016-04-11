package com.grability.appstore.modules.apps.detail;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.grability.appstore.R;
import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.utils.IntentUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class AppDetailActivityFragment extends Fragment {
    @Bind(R.id.textViewAppName)
    TextView textViewAppName;
    @Bind(R.id.textViewPrice)
    TextView textViewPrice;
    @Bind(R.id.textViewArtist)
    TextView textViewArtist;

    private ApplicationEntry applicationEntry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadApplicationEntryObject();
    }

    private void loadApplicationEntryObject(){
        String applicationEntryString = getActivity().getIntent().getStringExtra(IntentUtil.KEY_DATA);
        applicationEntry = new Gson().fromJson(applicationEntryString, ApplicationEntry.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_app_detail, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        textViewAppName.setText(applicationEntry.getName().getLabel());
        textViewArtist.setText(applicationEntry.getArtist().getLabel());
        textViewPrice.setText(String.valueOf(applicationEntry.getPrice().getAttributes().getAmount()));
    }


}
