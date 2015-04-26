package com.abing.jokenorris.dialog;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abing.jokenorris.R;
import com.abing.jokenorris.helper.SharedPrefsHelper;
import com.abing.jokenorris.helper.StringHelper;
import com.gc.materialdesign.views.ButtonFlat;
import com.rengwuxian.materialedittext.MaterialEditText;

public class NameInputDialog extends DialogFragment implements View.OnClickListener{

    private static final String KEY_FIRST_NAME = "KEY_FIRST_NAME";
    private static final String KEY_LAST_NAME = "KEY_LAST_NAME";

    private ButtonFlat mButton;
    private MaterialEditText fNameEditText, lNameEditText;
    private OnNameDailogGoClickedListener mListener;
    private String firstName, lastName;

    public static NameInputDialog newInstance(){
        return new NameInputDialog();
    }

    public NameInputDialog(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_name, container, false);
        mButton = (ButtonFlat) v.findViewById(R.id.button_go);
        fNameEditText = (MaterialEditText) v.findViewById(R.id.et_first_name);
        lNameEditText = (MaterialEditText) v.findViewById(R.id.et_last_name);
        mButton.setOnClickListener(this);
        initTextFields();
        return v;
    }

    private void initTextFields(){
        String firstName, lastName;
        firstName = SharedPrefsHelper.readPreference(getActivity(), KEY_FIRST_NAME, null);
        lastName = SharedPrefsHelper.readPreference(getActivity(), KEY_LAST_NAME, null);
        if(firstName != null){
            fNameEditText.setText(firstName);
        }
        if(lastName != null){
            lNameEditText.setText(lastName);
        }
    }

    @Override
    public void onClick(View v) {
        firstName = StringHelper.removeSpecialChars(fNameEditText.getText().toString());
        lastName = StringHelper.removeSpecialChars(lNameEditText.getText().toString());
        SharedPrefsHelper.saveToPreference(getActivity(), KEY_FIRST_NAME, firstName);
        SharedPrefsHelper.saveToPreference(getActivity(), KEY_LAST_NAME, lastName);
        dismiss();
        mListener.onNameDialogGoClicked(firstName, lastName);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnNameDailogGoClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnNameDailogGoClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStart() {
        super.onStart();

        // change dialog width
        if (getDialog() != null) {

            int fullWidth = getDialog().getWindow().getAttributes().width;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                Display display = getActivity().getWindowManager()
                        .getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                fullWidth = size.x;
            } else {
                Display display = getActivity().getWindowManager()
                        .getDefaultDisplay();
                fullWidth = display.getWidth();
            }

            final int padding = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                            .getDisplayMetrics());

            int w = fullWidth - padding;
            int h = getDialog().getWindow().getAttributes().height;

            getDialog().getWindow().setLayout(w, h);
        }
    }

    public interface OnNameDailogGoClickedListener {
        public void onNameDialogGoClicked(String firstName, String lastName);
    }




}
