package cn.trinea.android.demo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import cn.trinea.android.common.view.SlideOnePageGallery;
import cn.trinea.android.demo.adapter.ImageListAdapter;

/**
 * SlideOnePageGalleryDemo
 * 
 * @author Trinea 2013-5-9
 */
public class SlideOnePageGalleryDemo extends BaseActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.slide_one_page_gallery_demo);

        context = getApplicationContext();
        SlideOnePageGallery imageGallery = (SlideOnePageGallery)findViewById(R.id.app_app_image_gallery);
        imageGallery.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        imageGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ImageListAdapter adapter = new ImageListAdapter(getApplicationContext());
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(R.drawable.image1);
        idList.add(R.drawable.image2);
        idList.add(R.drawable.image3);
        adapter.setImageList(idList);
        imageGallery.setAdapter(adapter);
    }
}
