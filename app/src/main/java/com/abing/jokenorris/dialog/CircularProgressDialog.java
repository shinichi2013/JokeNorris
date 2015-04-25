package com.abing.jokenorris.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abing.jokenorris.R;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

/**
 * Created by Catingub on 4/25/2015.
 */
public class CircularProgressDialog extends DialogFragment {

    private CircleProgressBar mProgressBar;

    public static CircularProgressDialog newInstance(){
        return new CircularProgressDialog();
    }

    public CircularProgressDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_circular_progress, container, false);
        mProgressBar = (CircleProgressBar) v.findViewById(R.id.progressBar);
        mProgressBar.setColorSchemeResources(R.color.primaryColor, R.color.primaryColorDark, R.color.accentColor);
        mProgressBar.setVisibility(View.INVISIBLE);
        setCancelable(false);
        return v;
    }

}
