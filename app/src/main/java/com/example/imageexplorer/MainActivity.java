package com.example.imageexplorer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.imageexplorer.Adapter.ViewPagerAdapter;
import com.example.imageexplorer.Fragment.FolderCardFragment;
import com.example.imageexplorer.Fragment.ImgViewFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewPager);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        } else {

            addTabs(viewPager);
            ( (TabLayout)findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);
        }
    }

    public void addTabs(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new ImgViewFragment(),"Tất cả");
        adapter.add(new FolderCardFragment(),"Thư mục");
        viewPager.setAdapter(adapter);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permisson granted", Toast.LENGTH_SHORT).show();
                        addTabs(viewPager);
                        ( (TabLayout)findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);
                    }
                } else {
                    Toast.makeText(this, "No permisson granted", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
            }
        }
    }

}
