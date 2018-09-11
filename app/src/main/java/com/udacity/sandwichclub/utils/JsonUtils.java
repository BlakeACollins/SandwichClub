package com.udacity.sandwichclub.utils;


//Created by Blake Collins
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static Sandwich parseSandwichJson(String sandwichJson) {

        try {
            JSONObject baseJsonObject = new JSONObject(sandwichJson);

            JSONObject name = baseJsonObject.optJSONObject("name");

            JSONArray alsoKnownAs = (JSONArray) name.get("alsoKnownAs");

            String mainName = name.optString("mainName");

            String placeOfOrigin = baseJsonObject.optString("placeOfOrigin");

            String description = baseJsonObject.optString("description");

            String image = baseJsonObject.optString("image");

            ArrayList<String> alsoKnownAsList = new ArrayList<>();

            List<String> ingredientsList = new ArrayList<>();
            JSONArray ingredientsArray = baseJsonObject.getJSONArray("ingredients");
            int countIngredientsArray = ingredientsArray.length();

            for (int k = 0; k < alsoKnownAs.length(); k++) {
                alsoKnownAsList.add(alsoKnownAs.getString(k));
            }

            for (int i = 0; i < countIngredientsArray; i++) {
                String ingredient = ingredientsArray.getString(i);
                ingredientsList.add(ingredient);
            }

            return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);


        } catch (JSONException e) {
            e.printStackTrace();

        }

        return null;
    }
}




