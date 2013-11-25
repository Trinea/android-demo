package cn.trinea.android.demo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import cn.trinea.android.common.entity.FailedReason;
import cn.trinea.android.common.service.impl.FileNameRuleImageUrl;
import cn.trinea.android.common.service.impl.ImageSDCardCache;
import cn.trinea.android.common.service.impl.ImageSDCardCache.OnImageSDCallbackListener;
import cn.trinea.android.common.service.impl.RemoveTypeLastUsedTimeFirst;
import cn.trinea.android.common.util.ListUtils;
import cn.trinea.android.common.view.SlideOnePageGallery;

/**
 * ImageSDCardCacheDemo
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-6-25
 */
public class ImageSDCardCacheDemo extends BaseActivity {

    public static final String TAG_CACHE = "image_sdcard_cache";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.slide_one_page_gallery_demo);

        Context context = getApplicationContext();
        initImageUrlList();
        IMAGE_SD_CACHE.initData(context, TAG_CACHE);
        IMAGE_SD_CACHE.setContext(context);

        SlideOnePageGallery imageGallery = (SlideOnePageGallery)findViewById(R.id.app_app_image_gallery);
        ImageAdapter adapter = new ImageAdapter(context);
        adapter.setImageUrlList(imageUrlList);
        imageGallery.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        IMAGE_SD_CACHE.saveDataToDb(this, TAG_CACHE);
        super.onDestroy();
    }

    /** icon cache **/
    public static final ImageSDCardCache IMAGE_SD_CACHE = new ImageSDCardCache();

    static {
        /** init icon cache **/
        OnImageSDCallbackListener imageCallBack = new OnImageSDCallbackListener() {

            private static final long serialVersionUID = 1L;

            /**
             * callback function after get image successfully, run on ui thread
             * 
             * @param imageUrl imageUrl
             * @param imagePath image path
             * @param view view need the image
             * @param isInCache whether already in cache or got realtime
             */
            @Override
            public void onGetSuccess(String imageUrl, String imagePath, View view, boolean isInCache) {
                ImageView imageView = (ImageView)view;

                // avoid oom caused by bitmap size exceeds VM budget
                BitmapFactory.Options option = new BitmapFactory.Options();
                option.inSampleSize = getImageScale(imagePath);
                Bitmap bm = BitmapFactory.decodeFile(imagePath, option);
                if (bm != null) {
                    // auto set height
                    LayoutParams imageParams = (LayoutParams)imageView.getLayoutParams();
                    imageParams.height = imageParams.width * bm.getHeight() / bm.getWidth();
                    imageView.setScaleType(ScaleType.FIT_XY);
                    imageView.setImageBitmap(bm);

                    // first time show with animation
                    if (!isInCache) {
                        imageView.startAnimation(getInAlphaAnimation(2000));
                    }
                }
            }

            /**
             * callback function before get image, run on ui thread
             * 
             * @param imageUrl imageUrl
             * @param view view need the image
             */
            @Override
            public void onPreGet(String imageUrl, View view) {
                // Log.e(TAG_CACHE, "pre get image");
            }

            /**
             * callback function after get image failed, run on ui thread
             * 
             * @param imageUrl imageUrl
             * @param imagePath image path
             * @param view view need the image
             * @param failedReason failed reason for get image
             */
            @Override
            public void onGetFailed(String imageUrl, String imagePath, View view, FailedReason failedReason) {
                Log.e(TAG_CACHE,
                      new StringBuilder(128).append("get image ").append(imageUrl).append(" error, failed type is: ")
                                            .append(failedReason.getFailedType()).append(", failed reason is: ")
                                            .append(failedReason.getCause().getMessage()).toString());
            }
        };
        IMAGE_SD_CACHE.setOnImageSDCallbackListener(imageCallBack);
        IMAGE_SD_CACHE.setCacheFullRemoveType(new RemoveTypeLastUsedTimeFirst<String>());
        IMAGE_SD_CACHE.setFileNameRule(new FileNameRuleImageUrl());

        IMAGE_SD_CACHE.setHttpReadTimeOut(10000);
        IMAGE_SD_CACHE.setOpenWaitingQueue(true);
        IMAGE_SD_CACHE.setValidTime(-1);
    }

    public static AlphaAnimation getInAlphaAnimation(long durationMillis) {
        AlphaAnimation inAlphaAnimation = new AlphaAnimation(0, 1);
        inAlphaAnimation.setDuration(durationMillis);
        return inAlphaAnimation;
    }

    /**
     * scale image to fixed height and weight
     * 
     * @param imagePath
     * @return
     */
    private static int getImageScale(String imagePath) {
        BitmapFactory.Options option = new BitmapFactory.Options();
        // set inJustDecodeBounds to true, allowing the caller to query the bitmap info without having to allocate the
        // memory for its pixels.
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, option);

        int scale = 1;
        while (option.outWidth / scale >= IMAGE_MAX_WIDTH || option.outHeight / scale >= IMAGE_MAX_HEIGHT) {
            scale *= 2;
        }
        return scale;
    }

    private static int   IMAGE_MAX_WIDTH  = 480;
    private static int   IMAGE_MAX_HEIGHT = 960;

    private List<String> imageUrlList;

    private void initImageUrlList() {
        imageUrlList = new ArrayList<String>();
        imageUrlList.add("http://farm8.staticflickr.com/7403/9146300103_03423db0cc.jpg");
        imageUrlList.add("http://farm4.staticflickr.com/3755/9148527824_6c156185ea.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7409/9148527822_36fa37d7ca_z.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7403/9146300103_03423db0cc.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7318/9148527808_e804baef0b.jpg");
        imageUrlList.add("http://farm3.staticflickr.com/2857/9148527928_3063544889.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7318/9146300275_5fe995d123.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7351/9148527976_8a4e75ae87.jpg");
        imageUrlList.add("http://farm4.staticflickr.com/3679/9146300263_5c2191232a_o.jpg");
        imageUrlList.add("http://farm3.staticflickr.com/2863/9148527892_31f9377351_o.jpg");
        imageUrlList.add("http://farm3.staticflickr.com/2888/9148527996_f05118d7de_o.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7310/9148528008_8e8f51997a.jpg");
        imageUrlList.add("http://farm3.staticflickr.com/2849/9148528108_dfcda19507.jpg");
        imageUrlList.add("http://farm4.staticflickr.com/3739/9148528022_e9bf03058f.jpg");
        imageUrlList.add("http://farm4.staticflickr.com/3696/9146300409_dfa9d7c603.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7288/9146300469_bd3420c75b_z.jpg");
    }

    private static class ImageAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        public List<String>    imageUrlList;

        public ImageAdapter(Context context){
            super();
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return ListUtils.isEmpty(imageUrlList) ? 0 : imageUrlList.size();
        }

        @Override
        public Object getItem(int position) {
            return ListUtils.isEmpty(imageUrlList) ? null : imageUrlList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.image_list_item, null);
                holder = new ViewHolder();
                holder.imageView = (ImageView)convertView.findViewById(R.id.image_list_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            // if not in cache, set default icon
            if (!IMAGE_SD_CACHE.get(imageUrlList.get(position), holder.imageView)) {
                holder.imageView.setImageResource(R.drawable.trinea);
                holder.imageView.setScaleType(ScaleType.CENTER);
            }
            return convertView;
        }

        public void setImageUrlList(List<String> imageUrlList) {
            this.imageUrlList = imageUrlList;
        }

        /**
         * ViewHolder
         * 
         * @author Trinea 2012-11-22
         */
        static class ViewHolder {

            ImageView imageView;
        }
    }
}
