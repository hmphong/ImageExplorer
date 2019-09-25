package com.example.imageexplorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.example.imageexplorer.Adapter.ImgViewAdapter;
import com.example.imageexplorer.Model.ImgStorage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ImgVActivity extends AppCompatActivity {
    RecyclerView sdcard;
    ArrayList<ImgStorage> arrayList = new ArrayList<>();
    public static ArrayList<ImgStorage> imgStorages;
    ImgViewAdapter imgViewAdapter;
    String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_v);

        sdcard = findViewById(R.id.reImg);
        path = getIntent().getStringExtra("display")+"/";

        Log.e("oncreataccc", "" + path);
        getFile(new File(path));


        imgViewAdapter = new ImgViewAdapter(this,arrayList);

        sdcard.hasFixedSize();
        sdcard.setLayoutManager(new GridLayoutManager(this,3));
        sdcard.setAdapter(imgViewAdapter);
    }

    public void getFile(File dir) {

        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    getFile(listFile[i]);
                } else {
                    if (listFile[i].getName().endsWith(".png")
                            || listFile[i].getName().endsWith(".jpg")
                            || listFile[i].getName().endsWith(".jpeg")
                            || listFile[i].getName().endsWith(".gif")
                            || listFile[i].getName().endsWith(".bmp")) {
                        String temp = listFile[i].getPath();
                        //  String temp = file.getPath().substring(0, file.getPath().lastIndexOf('/'));
                        // String name = temp.substring(temp.lastIndexOf('/')).replace('/', ' ');
                        if (!arrayList.contains(temp)) {
                            File file = new File(temp);
                            Date lastModifiedDate = new Date(file.lastModified());
                            String format = "MM-dd-yyyy HH:mm:ss";
                            SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
                            String dt = formatter.format(new Date(String.valueOf(lastModifiedDate)));
                            ;
                            arrayList.add(new ImgStorage(temp, dt));

                        }

                    }
                }
            }
        }


    }





}
