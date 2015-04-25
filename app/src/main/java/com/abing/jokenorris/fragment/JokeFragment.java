package com.abing.jokenorris.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abing.jokenorris.R;

public class JokeFragment extends Fragment {

    private static final String JOKE_ARG = "JOKE_ARG";

    private String joke;

    public static JokeFragment newInstance(String joke) {
        JokeFragment fragment = new JokeFragment();
        Bundle args = new Bundle();
        args.putString(JOKE_ARG, joke);
        fragment.setArguments(args);
        return fragment;
    }

    public JokeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            joke = getArguments().getString(JOKE_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_joke, container, false);
        ((TextView) v.findViewById(R.id.text_joke)).setText(joke);
        return v;
    }


}
