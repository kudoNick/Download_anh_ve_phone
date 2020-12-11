package com.example.assgd1_androidnetworking_anhphtph08567;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity2 extends AppCompatActivity {
    File file;
    ImageView imageView;
    static String photo;
    static  String dirPath,fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.imgPhotos);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String id = intent.getStringExtra("id");
         photo = intent.getStringExtra("photo");
        Picasso.get().load(photo).into(imageView);
        registerForContextMenu(imageView);

        dirPath = Environment.getExternalStorageDirectory() + "/Image ";
        fileName = id + ".jpg";
        file = new File(dirPath, fileName);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dowload:
                AndroidNetworking.download(photo,dirPath,fileName)
                        .build()
                        .startDownload(new DownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                Toast.makeText(MainActivity2.this, "Đã lưu ảnh", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(ANError anError) {
                                System.out.println("TTT: " + anError);
                                Toast.makeText(MainActivity2.this, "Luu anh that bat", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
        return super.onContextItemSelected(item);
    }


}