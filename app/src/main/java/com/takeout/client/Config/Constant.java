package com.takeout.client.Config;

import android.os.Environment;

/**
 * Created by 鹏 on 2015/12/18.
 */
public class Constant {
    /**
     * 存储根目录
     */
    public static final String APP_ROOT_PATH = Environment.getExternalStorageDirectory().toString();
    /**
     * 图片缓存路径
     */
    public static final String PICTURE_ALBUM_PATH = APP_ROOT_PATH + "/JinyiweiDoctor/";
}
