package com.takeout.client.utils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.takeout.client.R;

/**
 * ImageLoader圖片加載屬性工具類
 */
public class ImageLoaderConfigFactory {

    private DisplayImageOptions options;
    private static ImageLoaderConfigFactory factory;

    private ImageLoaderConfigFactory(){

    }

    public static ImageLoaderConfigFactory getInstance() {
        if(factory == null) {
            synchronized (ImageLoaderConfigFactory.class) {
                if(factory == null) {
                    factory = new ImageLoaderConfigFactory();
                }
            }
        }
        return factory;
    }

    public DisplayImageOptions getCollectImg() {
        options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .showImageOnLoading(R.mipmap.collect_bg)
                .showImageForEmptyUri(R.mipmap.collect_bg)
                .showImageOnFail(R.mipmap.collect_bg)
                .cacheInMemory(true)
                .build();
        return options;
    }

    public DisplayImageOptions getShopIcon() {
        options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .showImageOnLoading(R.mipmap.collect_icon)
                .showImageForEmptyUri(R.mipmap.collect_icon)
                .showImageOnFail(R.mipmap.collect_icon)
                .cacheInMemory(true)
                .build();
        return options;
    }

}
