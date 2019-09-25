package com.example.imageexplorer.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.imageexplorer.Adapter.FolderAdapter;
import com.example.imageexplorer.Adapter.ImgViewAdapter;
import com.example.imageexplorer.Model.ImgStorage;
import com.example.imageexplorer.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;


public class FolderCardFragment extends Fragment {
    View view;
    ListView sdcard;
    ArrayList<String> arrayList = new ArrayList<>();
    public static ArrayList<String> arrFolder = new ArrayList<>();
    FolderAdapter folderAdapter;

    public FolderCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_folder_card, container, false);
        sdcard = view.findViewById(R.id.lvListFol);
        getFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()));
        Log.e("aasdsadsa", ""+arrayList.size());


        for (String a : arrayList) {
            boolean isFound = false;
            for (String e : arrFolder) {
                String tempA = a.substring(0, a.lastIndexOf('/'));
                String   nameA = tempA.substring(tempA.lastIndexOf('/')).replace('/', ' ').trim();

                String tempB  = e.substring(0, e.lastIndexOf('/'));
                String  nameB = tempB.substring(tempB.lastIndexOf('/')).replace('/', ' ').trim();

                Log.e("aasdsadsa", nameB);
                if (nameA.equalsIgnoreCase(nameB) || (e.equals(a))) {
                    isFound = true;
                    break;
                }
            }

            if (!isFound) arrFolder.add(a);
        }
//        for (int i = 0; i < arrayList.size(); i++) {
//
//        }
        Collections.sort(arrFolder, new Comparator<String>() {
            @Override
            public int compare(String t1, String t2) {
                String name1 = t1.substring(t1.lastIndexOf('/')).replace('/', ' ').trim();
                String name2 = t2.substring(t2.lastIndexOf('/')).replace('/', ' ').trim();

                return name2.compareToIgnoreCase(name1);
            }
        });
        folderAdapter = new FolderAdapter(getContext(),arrFolder);
        sdcard.setAdapter(folderAdapter);

        return view;
    }

    public ArrayList<String> getFile(File dir) {

        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i =0; i <listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    getFile(listFile[i]);
                } else {
                    if (listFile[i].getName().endsWith(".png")
                            || listFile[i].getName().endsWith(".jpg")
                            || listFile[i].getName().endsWith(".jpeg")
                            || listFile[i].getName().endsWith(".gif")
                            || listFile[i].getName().endsWith(".bmp")) {
                        String  path = listFile[i].getPath();

//
                        String tempB = path.substring(0, path.lastIndexOf('/'));
                        String nameB = tempB.substring(tempB.lastIndexOf('/')).replace('/', ' ').trim();

                        if (!arrayList.contains(path))
                            arrayList.add(tempB);
                        Log.e("folder", "file2332 " + tempB);
                    }
                }
            }
        }

        return arrayList;

    }

}
