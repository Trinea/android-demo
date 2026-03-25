package cn.trinea.android.demo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import cn.trinea.android.common.view.SlideOnePageGallery;
import cn.trinea.android.demo.adapter.ImageListAdapter;

/**
 * SlideOnePageGalleryDemo
 * 
 * @author <a href="http://www.trinea.cn/android/gallery-scroll-one-page/" target="_blank">Trinea</a> 2013-5-9
 */
public class SlideOnePageGalleryDemo extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.slide_one_page_gallery_demo);

        SlideOnePageGallery imageGallery = (SlideOnePageGallery)findViewById(R.id.app_app_image_gallery);
        imageGallery.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}
        });

        imageGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ImageListAdapter adapter = new ImageListAdapter(context);
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(R.drawable.image1);
        idList.add(R.drawable.image2);
        idList.add(R.drawable.image3);
        adapter.setImageList(idList);
        imageGallery.setAdapter(adapter);
    }
}
