package com.client.vpman.alertdialogpr;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.client.vpman.alertdialogpr.Adapter.VideoAdatper;
import com.client.vpman.alertdialogpr.Model.VideoModel;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerviewLayoutManager;
    ArrayList<VideoModel> arrayListVideos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES)
        {
            setTheme(R.style.AppTheme);
        }
        else
        {
            setTheme(R.style.LightTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar=findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.action_menu);

        recyclerView=findViewById(R.id.recyclerViewVideo);
        recyclerviewLayoutManager=new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(recyclerviewLayoutManager);
        arrayListVideos=new ArrayList<>();
        fetchVideoFromGallery();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.mySwitch);
        menuItem.setActionView(R.layout.use_switch);
        final Switch sw=(Switch)menu.findItem(R.id.mySwitch).getActionView().findViewById(R.id.action_switch);

        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES)
        {
            sw.setChecked(true);
        }

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    setTheme(R.style.AppTheme);
                   /* Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                    startActivity(intent);
                    finish();*/
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    setTheme(R.style.LightTheme);
                    /*Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                    startActivity(intent);
                    finish();*/
                }
            }
        });
        return true;


    }

    private void fetchVideoFromGallery()
    {
        Uri uri;
        Cursor cursor;
        int column_index_data,column_index_folder,column_id,thum;
        String absolutepathImage=null;
        uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String projection[]={MediaStore.MediaColumns.DATA,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME
                ,MediaStore.Video.Media._ID,
                MediaStore.Video.Thumbnails.DATA};
        String orderBy= MediaStore.Images.Media.DATE_TAKEN;
        cursor=Main2Activity.this.getContentResolver()
                .query(uri,projection,null,null,orderBy+" DESC");
        column_index_data=cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
      //  column_index_folder=cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
       // column_id=cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
        thum=cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);
        while (cursor.moveToNext())
        {
            absolutepathImage=cursor.getString(column_index_data);
            VideoModel videoModel=new VideoModel();
            videoModel.setBoolean_selected(false);
            videoModel.setStr_path(absolutepathImage);
            videoModel.setStr_thumb(cursor.getString(thum));
            arrayListVideos.add(videoModel);
        }
        VideoAdatper videoAdatper=new VideoAdatper(Main2Activity.this,arrayListVideos,Main2Activity.this);
        recyclerView.setAdapter(videoAdatper);
    }
}
