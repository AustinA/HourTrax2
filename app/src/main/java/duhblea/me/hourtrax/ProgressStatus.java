package duhblea.me.hourtrax;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;


public class ProgressStatus extends Fragment {

    private static MainActivity mActivity;
    private ObjectAnimator progressAnimator;
    private ProgressBar progressBar;
    private View view;
    private Biweek theBiweek;

    public static ProgressStatus newInstance(MainActivity activity) {
        mActivity = activity;
        ProgressStatus fragment = new ProgressStatus();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_progress_status, container, false);
        theBiweek = mActivity.getBiweek();

        initializeProgressBar();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public ProgressStatus() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initializeProgressBar() {
        progressBar = (ProgressBar)view.findViewById(R.id.hoursProgress);
        progressBar.setMax(80);

        progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 80);
        progressAnimator.setDuration(1000);
    }

}
