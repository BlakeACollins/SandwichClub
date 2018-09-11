package com.udacity.sandwichclub;



// created by Blake Collins 5/14/2018

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {

            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        sandwich = JsonUtils.parseSandwichJson(json);


        if (sandwich == null) {
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView originTextView = findViewById(R.id.origin_tv);
        TextView alsoKnownAsTextView = findViewById(R.id.also_known_tv);
        TextView descriptionTextView = findViewById(R.id.description_tv);
        TextView ingredientsTextView = findViewById(R.id.ingredients_tv);

            if (sandwich.getPlaceOfOrigin().isEmpty()){
                originTextView.setText(R.string.detail_error_message);
            }else {
                originTextView.setText(sandwich.getPlaceOfOrigin());
            }
            if (sandwich.getAlsoKnownAs().isEmpty()){
                alsoKnownAsTextView.setText(R.string.detail_error_message);
            }else {
                alsoKnownAsTextView.setText(listModel(sandwich.getAlsoKnownAs()));
            }



            descriptionTextView.setText(sandwich.getDescription());

            ingredientsTextView.setText(listModel(sandwich.getIngredients()));

        }
        public StringBuilder listModel(List<String> list){
            StringBuilder stringBuilder= new StringBuilder();
            for (int i=0;i<list.size();i++){
                stringBuilder.append(list.get(i)).append("\n");
            }
            return stringBuilder;
        }







    }








