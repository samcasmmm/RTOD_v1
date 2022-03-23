package com.example.objectdetect_v1.RTOD_Len;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.objectdetect_v1.MainActivity;
import com.example.objectdetect_v1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainRLenActivity extends AppCompatActivity {
    private ImageView captureIV;
    Button snapBtn, getSearchResultsBtn;
    private RecyclerView resultRv;
    private SearchRVAdapter searchRVAdapter;
    private ArrayList<SearchRVModal> searchRVModalArrayList;
    int REQUEST_CODE = 1;
    private ProgressBar progressBar;
    private Bitmap imageBit;
//    private Object FirebaseVisionImageLabeler;
    String title,link,displayed_link,snippet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_r_len);
        captureIV = findViewById(R.id.image);
        snapBtn = findViewById(R.id.idBtnSnap);
        getSearchResultsBtn = findViewById(R.id.idBtnResult);
        resultRv = findViewById(R.id.idRVSearchResults);
        progressBar = findViewById(R.id.progressBar);

        searchRVModalArrayList = new ArrayList<>();
        searchRVAdapter = new SearchRVAdapter(this,searchRVModalArrayList);
        resultRv.setLayoutManager(new LinearLayoutManager(MainRLenActivity.this, LinearLayoutManager.HORIZONTAL,false));
        resultRv.setAdapter(searchRVAdapter);


        snapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchRVModalArrayList.clear();
                searchRVAdapter.notifyDataSetChanged();
                takePictureIntent();

            }
        });


        getSearchResultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchRVModalArrayList.clear();
                searchRVAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.VISIBLE);
                getResults();
            }
        });

    }

    private void getResults(){
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBit);
        FirebaseVisionImageLabeler labeler = FirebaseVision.getInstance().getOnDeviceImageLabeler();

        labeler.processImage(image).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
            @Override
            public void onSuccess(List<FirebaseVisionImageLabel> firebaseVisionImageLabels) {
                String searchQuery = firebaseVisionImageLabels.get(0).getText();
                getSearchResults(searchQuery);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainRLenActivity.this, "Fail to Detect Image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && requestCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBit = (Bitmap) extras.get("data");
            captureIV.setImageBitmap(imageBit);

        }
    }

    private void getSearchResults(String searchQuery){

        String url = "https://serpapi.com/search.json?engine=google&q="+searchQuery+"&api_key=0044b2846e0da6712e4ad71c68ce78b046b705c8c02ac0462270c9949bcb29f3";
        RequestQueue queue = Volley.newRequestQueue(MainRLenActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray organicArray = response.getJSONArray("organic_results");
                    for (int i = 0; i<organicArray.length();i++){
                        JSONObject organicObj = organicArray.getJSONObject(i);
                        if(organicObj.has("title")){
                            title =  organicObj.getString("title");
                        }

                        if (organicObj.has("link")){
                            link = organicObj.getString("link");
                        }

                        if (organicObj.has("displayed_link")){
                            displayed_link = organicObj.getString("displayed_link");
                        }
                        if (organicObj.has("snippet")){
                           snippet = organicObj.getString("snippet");
                        }
                        searchRVModalArrayList.add(new SearchRVModal(title,link,displayed_link,snippet));
                    }
                    searchRVAdapter.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainRLenActivity.this, "No Results Found", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);

    }

    private void takePictureIntent() {

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (i.resolveActivity(getPackageManager()) != null){
            startActivityForResult(i,REQUEST_CODE);
            startActivityIfNeeded(i,REQUEST_CODE);
        }

    }
}