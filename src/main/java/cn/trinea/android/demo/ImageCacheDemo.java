package cn.trinea.android.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import cn.trinea.android.common.entity.FailedReason;
import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.service.impl.ImageMemoryCache.OnImageCallbackListener;
import cn.trinea.android.common.service.impl.RemoveTypeLastUsedTimeFirst;

/**
 * ImageCacheDemo
 * 
 * @author <a href="http://www.trinea.cn/android/android-imagecache/" target="_blank">Trinea</a> 2013-6-25
 */
public class ImageCacheDemo extends BaseActivity {

    /** column number **/
    public static final int    COLUMNS                  = 2;
    /** imageView default height **/
    public static final int    IMAGEVIEW_DEFAULT_HEIGHT = 400;
    public static final String TAG_CACHE                = "image_cache";
    /** cache folder path which be used when saving images **/
    public static final String DEFAULT_CACHE_FOLDER     = new StringBuilder()
                                                                .append(Environment.getExternalStorageDirectory()
                                                                        .getAbsolutePath()).append(File.separator)
                                                                .append("Trinea").append(File.separator)
                                                                .append("AndroidDemo").append(File.separator)
                                                                .append("ImageCache").toString();
    private RelativeLayout     parentLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.image_cache_demo);

        parentLayout = (RelativeLayout)findViewById(R.id.image_cache_parent_layout);
        initImageUrlList();
        IMAGE_CACHE.initData(this, TAG_CACHE);
        IMAGE_CACHE.setContext(context);
        IMAGE_CACHE.setCacheFolder(DEFAULT_CACHE_FOLDER);
        // intelligent compress image
        // IMAGE_CACHE.setCompressListener(new CompressListener() {
        //
        // @Override
        // public int getCompressSize(String imagePath) {
        // if (FileUtils.isFileExist(imagePath)) {
        // long fileSize = FileUtils.getFileSize(imagePath) / 1000;
        // /**
        // * if image bigger than 100k, compress to 1/(n + 1) width and 1/(n + 1) height, n is fileSize / 100k
        // **/
        // if (fileSize > 100) {
        // return (int)(fileSize / 100) + 1;
        // }
        // }
        // return 1;
        // }
        // });

        int count = 0, viewId = 0x7F24FFF0;
        int verticalSpacing, horizontalSpacing;
        verticalSpacing = horizontalSpacing = getResources().getDimensionPixelSize(R.dimen.dp_4);
        Display display = getWindowManager().getDefaultDisplay();
        int imageWidth = (display.getWidth() - (COLUMNS + 1) * horizontalSpacing) / COLUMNS;
        for (String imageUrl : imageUrlList) {
            ImageView imageView = new ImageView(context);
            imageView.setId(++viewId);
            imageView.setScaleType(ScaleType.CENTER);
            imageView.setBackgroundResource(R.drawable.image_border);
            parentLayout.addView(imageView);

            // set imageView layout params
            LayoutParams layoutParams = (RelativeLayout.LayoutParams)imageView.getLayoutParams();
            layoutParams.width = imageWidth;
            layoutParams.topMargin = verticalSpacing;
            layoutParams.rightMargin = horizontalSpacing;
            int column = count % COLUMNS;
            int row = count / COLUMNS;
            if (row > 0) {
                layoutParams.addRule(RelativeLayout.BELOW, viewId - COLUMNS);
            }
            if (column > 0) {
                layoutParams.addRule(RelativeLayout.RIGHT_OF, viewId - 1);
            }
            layoutParams.height = IMAGEVIEW_DEFAULT_HEIGHT;

            // get image
            IMAGE_CACHE.get(imageUrl, imageView);
            count++;
        }
    }

    @Override
    protected void onDestroy() {
        IMAGE_CACHE.saveDataToDb(this, TAG_CACHE);
        super.onDestroy();
    }

    /**
     * if you just want load image for ImageView, just like this, no need to setOnImageCallbackListener and so on like
     * <code>static</code> below
     */
    // public static final ImageCache IMAGE_CACHE = CacheManager.getImageCache();

    /** icon cache **/
    public static final ImageCache IMAGE_CACHE = new ImageCache(128, 512);

    static {
        /** init icon cache **/
        OnImageCallbackListener imageCallBack = new OnImageCallbackListener() {

            /**
             * callback function after get image successfully, run on ui thread
             * 
             * @param imageUrl imageUrl
             * @param loadedImage bitmap
             * @param view view need the image
             * @param isInCache whether already in cache or got realtime
             */
            @Override
            public void onGetSuccess(String imageUrl, Bitmap loadedImage, View view, boolean isInCache) {
                if (view != null && loadedImage != null) {
                    ImageView imageView = (ImageView)view;
                    imageView.setImageBitmap(loadedImage);
                    // first time show with animation
                    if (!isInCache) {
                        imageView.startAnimation(getInAlphaAnimation(2000));
                    }

                    // auto set height accroding to rate between height and weight
                    LayoutParams imageParams = (LayoutParams)imageView.getLayoutParams();
                    imageParams.height = imageParams.width * loadedImage.getHeight() / loadedImage.getWidth();
                    imageView.setScaleType(ScaleType.FIT_XY);
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
             * @param loadedImage bitmap
             * @param view view need the image
             * @param failedReason failed reason for get image
             */
            @Override
            public void onGetFailed(String imageUrl, Bitmap loadedImage, View view, FailedReason failedReason) {
                Log.e(TAG_CACHE,
                        new StringBuilder(128).append("get image ").append(imageUrl).append(" error, failed type is: ")
                                .append(failedReason.getFailedType()).append(", failed reason is: ")
                                .append(failedReason.getCause().getMessage()).toString());
            }

            @Override
            public void onGetNotInCache(String imageUrl, View view) {
                if (view != null && view instanceof ImageView) {
                    ((ImageView)view).setImageResource(R.drawable.trinea);
                }
            }
        };
        IMAGE_CACHE.setOnImageCallbackListener(imageCallBack);
        IMAGE_CACHE.setCacheFullRemoveType(new RemoveTypeLastUsedTimeFirst<Bitmap>());

        IMAGE_CACHE.setHttpReadTimeOut(10000);
        IMAGE_CACHE.setOpenWaitingQueue(true);
        IMAGE_CACHE.setValidTime(-1);
        /**
         * close connection, default is connect keep-alive to reuse connection. if image is from different server, you
         * can set this
         */
        // IMAGE_CACHE.setRequestProperty("Connection", "false");
    }

    public static AlphaAnimation getInAlphaAnimation(long durationMillis) {
        AlphaAnimation inAlphaAnimation = new AlphaAnimation(0, 1);
        inAlphaAnimation.setDuration(durationMillis);
        return inAlphaAnimation;
    }

    private List<String> imageUrlList;

    private void initImageUrlList() {
        imageUrlList = new ArrayList<String>();
        imageUrlList.add("http://farm8.staticflickr.com/7409/9148527822_36fa37d7ca_z.jpg");
        imageUrlList.add("http://farm4.staticflickr.com/3755/9148527824_6c156185ea.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7318/9148527808_e804baef0b.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7318/9146300275_5fe995d123.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7288/9146300469_bd3420c75b_z.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7351/9148527976_8a4e75ae87.jpg");
        imageUrlList.add("http://farm3.staticflickr.com/2888/9148527996_f05118d7de_o.jpg");
        imageUrlList.add("http://farm3.staticflickr.com/2863/9148527892_31f9377351_o.jpg");
        imageUrlList.add("http://farm8.staticflickr.com/7310/9148528008_8e8f51997a.jpg");
        imageUrlList.add("http://farm3.staticflickr.com/2849/9148528108_dfcda19507.jpg");
        imageUrlList.add("http://farm4.staticflickr.com/3739/9148528022_e9bf03058f.jpg");
    }
}
