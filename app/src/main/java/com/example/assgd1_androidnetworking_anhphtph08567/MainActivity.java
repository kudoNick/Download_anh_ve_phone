package com.example.assgd1_androidnetworking_anhphtph08567;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import static com.example.assgd1_androidnetworking_anhphtph08567.R.string.permission_contacts_rationale;

public class MainActivity extends AppCompatActivity {
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    LinearLayout mLayout;
    String URL = "https://www.flickr.com/services/rest/?method=flickr.favorites.getPublicList&api_key=a5020e01cb129c27e7dc2839ed37965e&user_id=190468342%40N04&extras=url_z%2Ctitle&per_page=50&page=&format=json&nojsoncallback=1";
    RecyclerView rcvPhotos;
    PhotosAdapter photosAdapter;
    List<Photos> photosList;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = findViewById(R.id.line1);

        rcvPhotos = findViewById(R.id.rcvPhotos);
        progress = findViewById(R.id.progress);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            operateContactWrapper();
        }

        progress.setVisibility(View.VISIBLE);
        photosList = new ArrayList<>();
        AndroidNetworking.get(URL).build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject list = response.getJSONObject("photos");
                            JSONArray photo = list.getJSONArray("photo");
                            for (int i = 0; i <photo.length() ; i++) {
                                Photos photos = new Photos(photo.getJSONObject(i));
                                photosList.add(photos);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        photosAdapter = new PhotosAdapter(photosList, MainActivity.this);
                        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                        rcvPhotos.setLayoutManager(layoutManager);
                        rcvPhotos.setHasFixedSize(true);
                        rcvPhotos.setAdapter(photosAdapter);
                        photosAdapter.notifyDataSetChanged();
                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, "bb", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // caaps quyen
    private void operateContactWrapper() {
        int READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int MANAGE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE);

        if (READ_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED || WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED || MANAGE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.MANAGE_EXTERNAL_STORAGE},REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }
    }
    public void ShowPhoto(View view) {
        progress.setVisibility(View.VISIBLE);
        photosList = new ArrayList<>();
        AndroidNetworking.get(URL).build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                           JSONObject list = response.getJSONObject("photos");
                            JSONArray photo = list.getJSONArray("photo");
                            for (int i = 0; i <photo.length() ; i++) {
                                Photos photos = new Photos(photo.getJSONObject(i));
                                photosList.add(photos);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        photosAdapter = new PhotosAdapter(photosList, MainActivity.this);
                        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                        rcvPhotos.setLayoutManager(layoutManager);
                        rcvPhotos.setHasFixedSize(true);
                        rcvPhotos.setAdapter(photosAdapter);
                        photosAdapter.notifyDataSetChanged();
                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, "bb", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}