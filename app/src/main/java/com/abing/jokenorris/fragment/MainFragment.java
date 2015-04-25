package com.abing.jokenorris.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abing.jokenorris.R;
import com.gc.materialdesign.views.ButtonRectangle;


public class MainFragment extends Fragment implements View.OnClickListener{

    private OnButtonClickedListener mListener;
    private ButtonRectangle randomJokeBtn, nameJokeBtn, categoryJokeBtn;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        randomJokeBtn = (ButtonRectangle) view.findViewById(R.id.button_joke_random);
        nameJokeBtn = (ButtonRectangle) view.findViewById(R.id.button_joke_name);
        categoryJokeBtn = (ButtonRectangle) view.findViewById(R.id.button_joke_category);
        addButtonListener();
        return view;
    }

    private void addButtonListener(){
        randomJokeBtn.setOnClickListener(this);
        nameJokeBtn.setOnClickListener(this);
        categoryJokeBtn.setOnClickListener(this);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnButtonClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnButtonClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if(mListener != null){
            switch(v.getId()){
                case R.id.button_joke_random:
                    mListener.onRandomJokeClicked();
                    break;
                case R.id.button_joke_name:
                    mListener.onNameJokeClicked();
                    break;
                case R.id.button_joke_category:
                    mListener.onCategoryJokeClicked();
                    break;
            }
        }
    }

    public interface OnButtonClickedListener {
        public void onRandomJokeClicked();
        public void onNameJokeClicked();
        public void onCategoryJokeClicked();
    }

}
