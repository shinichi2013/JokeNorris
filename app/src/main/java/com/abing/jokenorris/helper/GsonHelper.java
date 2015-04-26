package com.abing.jokenorris.helper;

import android.text.Html;

import com.abing.jokenorris.entity.JokeResult;
import com.google.gson.Gson;

public class GsonHelper {
    public static String parseResponse(String response){
        JokeResult joke =  new Gson().fromJson(response, JokeResult.class);
        return Html.fromHtml(joke.getValue().getJoke()).toString();
    }
}
