package com.abing.jokenorris.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.abing.jokenorris.R;
import com.abing.jokenorris.dialog.CircularProgressDialog;
import com.abing.jokenorris.dialog.NameInputDialog;
import com.abing.jokenorris.fragment.MainFragment;
import com.abing.jokenorris.helper.GsonHelper;
import com.abing.jokenorris.helper.SharedPrefsHelper;
import com.abing.jokenorris.helper.URLHelper;
import com.abing.jokenorris.volley.VolleySingleton;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


public class MainActivity extends ActionBarActivity
        implements MainFragment.OnButtonClickedListener,
        NameInputDialog.OnNameDailogGoClickedListener{

    private static final String REQUEST_TAG = "REQUEST_TAG";
    private static final String NAME_DIALOG_TAG = "NAME_DIALOG_TAG";
    private static final String PROGRESS_DIALOG_TAG = "PROGRESS_DIALOG_TAG";

    public static final String KEY_JOKE = "JOKE_KEY";
    public static final String KEY_CATEGORY = "KEY_CATEGORY";

    private RequestQueue mRequestQueue;
    private StringRequest mRequest;

    private Toolbar mToolbar;
    private CircularProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initRequestObject();
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    private void initRequestObject() {
        mRequestQueue = VolleySingleton.getsInstance().getRequestQueue();
        mRequest = new StringRequest(Request.Method.GET, "",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(mProgressDialog != null){
                    mProgressDialog.dismiss();
                }
                startNextActivity(GsonHelper.parseResponse(response));
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                if(mProgressDialog != null){
                    mProgressDialog.dismiss();
                }
                Toast.makeText(MainActivity.this, "Error has occured. Please check internet connection and try again.", Toast.LENGTH_SHORT).show();
            }
        });
        mRequest.setTag(REQUEST_TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop () {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    public void onRandomJokeClicked() {
        showProgressDialog();
        mRequest.setRedirectUrl(URLHelper.getRandomJokeURL());
        mRequestQueue.add(mRequest);
    }

    @Override
    public void onNameJokeClicked() {
        showDialog(NAME_DIALOG_TAG, NameInputDialog.newInstance());
    }

    @Override
    public void onCategoryJokeClicked() {
        int lastPosition = Integer.valueOf(SharedPrefsHelper.readPreference(this, KEY_CATEGORY, "0"));
        new MaterialDialog.Builder(this)
                .items(R.array.category_joke)
                .itemsCallbackSingleChoice(lastPosition, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int position, CharSequence category) {
                        saveCategoryPosition(position);
                        dialog.dismiss();
                        showProgressDialog();
                        mRequest.setRedirectUrl(URLHelper.getCategoryJokeURL(category.toString().toLowerCase()));
                        mRequestQueue.add(mRequest);
                        return true;
                    }
                })
                .positiveText(R.string.label_go)
                .show();
    }

    @Override
    public void onNameDialogGoClicked(String firstName, String lastName) {
        showProgressDialog();
        mRequest.setRedirectUrl(URLHelper.getNameJokeURL(firstName, lastName));
        mRequestQueue.add(mRequest);
    }

    private void showProgressDialog() {
        mProgressDialog = CircularProgressDialog.newInstance();
        showDialog(PROGRESS_DIALOG_TAG, mProgressDialog);
    }

    private void showDialog(String tag, DialogFragment newFragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(tag);
        if (prev != null){
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        newFragment.show(ft, tag);
    }

    private void startNextActivity(String joke){
        startActivity(new Intent(this, JokeActivity.class).putExtra(KEY_JOKE, joke));
    }

    private void saveCategoryPosition(int position){
        SharedPrefsHelper.saveToPreference(this, KEY_CATEGORY, String.valueOf(position));
    }
}