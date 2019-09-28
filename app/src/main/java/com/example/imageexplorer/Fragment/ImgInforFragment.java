package com.example.imageexplorer.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imageexplorer.Adapter.FolderAdapter;
import com.example.imageexplorer.Adapter.ImgViewAdapter;
import com.example.imageexplorer.Model.ImgStorage;
import com.example.imageexplorer.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class ImgInforFragment extends Fragment {

    View view;
    RecyclerView reSdcard;
    ArrayList<ImgStorage> arrayList = new ArrayList<>();
    public static ArrayList<ImgStorage> imgStorages;
    ImgViewAdapter imgViewAdapter;


    public ImgInforFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_img_infor, container, false);
        reSdcard = view.findViewById(R.id.reSdcard);

        getFile(new File(FolderCardFragment.arrFolder.get(FolderAdapter.index)));
//        Log.e("oncreat", "" + arrayList.get(0));

        imgViewAdapter = new ImgViewAdapter(getContext(), arrayList);

        reSdcard.hasFixedSize();
        reSdcard.setLayoutManager(new GridLayoutManager(getContext(), 3));
        reSdcard.setAdapter(imgViewAdapter);
        return view;
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
