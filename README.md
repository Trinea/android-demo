![Trinea](http://farm8.staticflickr.com/7426/9456847893_053161c7a4_o.png)android-demo
-------------
> **关于我，欢迎关注**  
> 微博：<a title="Android 技术及移动互联网分享" href="http://weibo.com/trinea?s=6cm7D0" target="_blank">Trinea</a>&nbsp;&nbsp;&nbsp;&nbsp;主页：<a title="关注于 Android、Java、性能优化、开源项目" href="http://www.trinea.cn/" target="_blank">trinea.cn</a>&nbsp;&nbsp;&nbsp;&nbsp;邮箱：<a title="欢迎邮件与我交流" href="mailto:trinea.cn@gmail.com" target="_blank">trinea.cn#gmail.com</a>&nbsp;&nbsp;&nbsp;&nbsp;QQ：<a title="欢迎 Q 我" href="http://wpa.qq.com/msgrd?v=3&amp;uin=717763774&amp;site=qq&amp;menu=yes" target="_blank">717763774</a>  

**依赖**：<a title="包含缓存 个性化View 工具类库" href="https://github.com/Trinea/android-common" target="_blank">trinea-android-common</a>&nbsp;&nbsp;&nbsp;&nbsp;<a title="自动滚动 循环轮播的ViewPager" href="https://github.com/Trinea/android-auto-scroll-view-pager" target="_blank">android-auto-scroll-view-pager</a>&nbsp;&nbsp;&nbsp;&nbsp;<a title="ViewPager Indicator" href="https://github.com/JakeWharton/Android-ViewPagerIndicator" target="_blank">viewpager-indicator</a>。  

**包括**：[网络缓存Demo](http://www.trinea.cn/android/android-http-cache)，[图片缓存Demo](http://www.trinea.cn/android/android-imagecache/)，[图片SD卡缓存Demo](http://www.trinea.cn/android/android-imagesdcardcache/)，[下拉刷新及滚动到底部加载更多listview Demo](http://www.trinea.cn/android/dropdown-to-refresh-and-bottom-load-more-listview/)，[自动滚动轮播循环ViewPager Demo](http://www.trinea.cn/android/auto-scroll-view-pager/)，[Android系统下载管理DownloadManager功能介绍及使用示例](http://www.trinea.cn/android/android-downloadmanager/)，[ViewPager Multi Page](http://www.trinea.cn/android/viewpager-multi-fragment-effect/)，[滚动到底部或顶部响应的ScrollView使用](http://www.trinea.cn/android/on-bottom-load-more-scrollview/)，[Gallery滑动一页效果 Demo](http://www.trinea.cn/android/gallery-scroll-one-page/)，[SearchView Demo](http://www.trinea.cn/android/android-searchview-and-search-tips-impl/)，[ViewPager、Fragment使用](http://www.cnblogs.com/trinea/archive/2012/11/23/2771273.html)，[Service Demo](http://www.cnblogs.com/trinea/archive/2012/11/08/2699856.html)，[BroadcastReceiver Demo](http://www.cnblogs.com/trinea/archive/2012/11/09/2763182.html)。  

####示例APK下载:  
<a href="https://play.google.com/store/apps/details?id=cn.trinea.android.demo" target="_blank" title="从Google Play下载"><img src="http://www.android.com/images/brand/get_it_on_play_logo_small.png" title="从Google Play下载"/></a>
    <a href="http://as.baidu.com/a/item?docid=5499464" target="_blank" title="从Baidu手机助手下载"><img src="http://farm3.staticflickr.com/2826/11928623406_b9e8d39bd7_o.png" title="从Baidu手机助手下载"/></a>
    <a href="http://zhushou.360.cn/detail/index/soft_id/994107" target="_blank" title="从360手机助手下载"><img src="http://farm4.staticflickr.com/3775/11983355756_f8548f4c17_o.png" title="从360手机助手下载"/></a>
    <a href="http://app.xiaomi.com/detail/54761" target="_blank" title="从小米应用商店下载"><img src="http://farm8.staticflickr.com/7380/11982503045_b0538df5f5_o.png" title="从小米应用商店下载"/></a>
    <a href="http://trinea.github.com/apk/trinea-android-demo.apk" target="_blank" title="二维码扫描下载"><img src="https://farm3.staticflickr.com/2930/14017948972_bafb6df1b5_o.png" title="二维码扫描下载"/></a>
    <a href="http://trinea.github.com/apk/trinea-android-demo.apk" target="_blank" title="点击下载到本地">本地下载</a>  
    
  
#####1.  网络缓存  
使用示例：[Android网络缓存](http://www.trinea.cn/android/android-http-cache)  
适用：网络获取内容不大的应用，尤其是api接口数据，如新浪微博、twitter的timeline、微信公众账号发送的内容等等。  
效果图：  
![HttpCache](http://farm3.staticflickr.com/2843/12566457534_2cfa4297a1_o.jpg)  
  
#####2. 图片缓存
使用示例：[图片缓存的使用](http://www.trinea.cn/android/android-imagecache/)  
适用：获取图片较多且图片使用频繁的应用，包含二级缓存，如新浪微博、twitter、微信头像、美丽说、蘑菇街、花瓣、淘宝等等。  
效果图：  
![ImageCahe](http://farm4.staticflickr.com/3710/9312163125_81f1c1997b_o.jpg)
  

#####3. 图片SD卡缓存
使用示例：[图片SD卡缓存的使用](http://www.trinea.cn/android/android-imagesdcardcache/)  
适用：应用中获取图片较多且图片较大的情况。需要二级缓存及ListView或GridView图片加载推荐使用上面的[ImageCache](http://www.trinea.cn/android/android-imagecache/)。  
效果图：  
![ImageSDCardCache](http://farm3.staticflickr.com/2834/9314949798_ea69bdb5e8_o.jpg)
  
#####4. 下拉刷新及滚动到底部加载更多的Listview Demo
使用示例：[下拉刷新及滚动到底部加载更多listview Demo](http://www.trinea.cn/android/dropdown-to-refresh-and-bottom-load-more-listview/)  
实现原理: [http://trinea.iteye.com/blog/1562281](http://trinea.iteye.com/blog/1562281)。  
效果图：  
![DropDownListView](http://farm8.staticflickr.com/7376/9312162951_74b597ebaa_o.jpg)
  
#####5. 自动滚动轮播循环的ViewPager Demo
使用示例：[Android自动滚动 轮播循环的ViewPager](http://www.trinea.cn/android/auto-scroll-view-pager/)。  
效果图：  
![AutoScrollViewPagerDemo](http://farm3.staticflickr.com/2843/12805132475_e595664a81_o.gif)  
  
#####6. Android系统下载管理DownloadManager Demo
使用示例：[Android系统下载管理DownloadManager功能介绍及使用示例](http://www.trinea.cn/android/android-downloadmanager/)  
功能扩展：[Android下载管理DownloadManager功能扩展和bug修改](http://www.trinea.cn/android/android-downloadmanager-pro/)。  
效果图：  
![downloadManagerDemo](http://www.trinea.cn/wp-content/uploads/2013/05/downloadDemo2.gif)  
  
#####7. viewpager实现画廊效果Demo
使用示例：[ViewPager Multi Page](http://www.trinea.cn/android/viewpager-multi-fragment-effect/)。  
效果图：  
![viewpager multi page demo](http://farm8.staticflickr.com/7330/9321381014_4e5408a445_b.jpg)  
  
#####8. 滑动到底部或顶部响应的ScrollView Demo
使用及实现原理: [滚动到底部或顶部响应的ScrollView使用](http://www.trinea.cn/android/on-bottom-load-more-scrollview/)。  
效果图：  
![ScrollView](http://farm4.staticflickr.com/3669/9459686814_1a523ceeb6_o.jpg)
  
#####9. Gallery滑动一页效果 Demo
使用示例：[Gallery One Page](http://www.trinea.cn/android/gallery-scroll-one-page/)。  
效果图：  
![gallery one page demo](http://farm8.staticflickr.com/7330/9321381014_fb404e2430_o.jpg)   
  
#####10. SearchView Demo
使用示例：[SearchView Demo](http://www.trinea.cn/android/android-searchview-and-search-tips-impl/)。  
效果图：  
![SearchView Demo](http://www.trinea.cn/wp-content/uploads/2013/04/SearchView.jpg)   
  
#####11. ViewPager、Fragment Demo
使用示例：[ViewPager、Fragment使用](http://www.cnblogs.com/trinea/archive/2012/11/23/2771273.html)。  
效果图：  
![viewpager demo](http://pic002.cnblogs.com/images/2012/392321/2012112319384979.jpg)  
  
#####12. Service Demo
使用示例：[Service Demo](http://www.cnblogs.com/trinea/archive/2012/11/08/2699856.html)   
  
#####13. BroadcastReceiver Demo
使用示例：[BroadcastReceiver Demo](http://www.cnblogs.com/trinea/archive/2012/11/09/2763182.html)   


