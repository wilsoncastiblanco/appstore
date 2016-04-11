package com.grability.appstore.modules.apps.detail;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.grability.appstore.R;
import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.utils.IntentUtil;
import com.grability.appstore.utils.StringUtil;
import com.grability.appstore.utils.ViewUtils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class AppDetailActivityFragment extends Fragment {
    @Bind(R.id.textViewCategoryName)
    TextView textViewCategoryName;
    @Bind(R.id.textViewCategory)
    TextView textViewCategory;
    @Bind(R.id.frameLayoutCategory)
    FrameLayout frameLayoutCategory;
    @Bind(R.id.textViewAppName)
    TextView textViewAppName;
    @Bind(R.id.textViewArtist)
    TextView textViewArtist;
    @Bind(R.id.textViewPrice)
    TextView textViewPrice;
    @Bind(R.id.imageViewApp)
    ImageView imageViewApp;
    @Bind(R.id.textViewSummary)
    TextView textViewSummary;
    @Bind(R.id.textViewRights)
    TextView textViewRights;
    @Bind(R.id.textViewReleaseDate)
    TextView textViewReleaseDate;
    @Bind(R.id.textViewLink)
    TextView textViewLink;
    @Bind(R.id.textViewArtistRights)
    TextView textViewArtistRights;

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
        applicationName();
        applicationSummary();
        applicationCategory();
        applicationRights();
    }

    private void applicationRights() {
        textViewRights.setText(applicationEntry.getRights().getLabel());
        textViewReleaseDate.setText(applicationEntry.getReleaseDate().getAttributes().getLabel());

        String text = String.format(getString(R.string.application_detail_link),applicationEntry.getLink().getAttributes().getHref() );
        textViewLink.setMovementMethod(LinkMovementMethod.getInstance());
        textViewLink.setAutoLinkMask(Linkify.WEB_URLS);
        textViewLink.setLinksClickable(true);
        textViewLink.setText(Html.fromHtml(text));


        textViewArtistRights.setText(applicationEntry.getArtist().getLabel());
    }

    private void applicationCategory() {
        textViewCategoryName.setText(applicationEntry.getCategory().getAttributes().getLabel());
        textViewCategory.setText(applicationEntry.getCategory().getAttributes().getLabel().substring(0, 1).toUpperCase());
        frameLayoutCategory.setBackgroundColor(ViewUtils.getRandomArgbColor());
    }

    private void applicationSummary() {
        textViewSummary.setText(applicationEntry.getSummary().getLabel());
    }

    private void applicationName() {
        String appImageUrl = applicationEntry.getBiggestAppImage(applicationEntry.getImagesList());
        Picasso.with(getActivity().getApplicationContext()).load(appImageUrl).into(imageViewApp);
        textViewAppName.setText(applicationEntry.getTitle().getLabel());
        textViewArtist.setText(applicationEntry.getArtist().getLabel());
        textViewPrice.setText(StringUtil.getInCurrencyFormat(String.valueOf(applicationEntry.getPrice().getAttributes().getAmount()), applicationEntry.getPrice().getAttributes().getCurrency()));
    }


}
