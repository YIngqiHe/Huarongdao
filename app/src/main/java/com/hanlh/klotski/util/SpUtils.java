package com.hanlh.klotski.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SPUtils save fakeWeiChat application data.
 */
public class SpUtils {

    public  static  final  String LEVEL ="level";



    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "share_data";
    private static SharedPreferences sharedPreferences;
    public static void initialize(Context ctx){
        sharedPreferences = ctx.getApplicationContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * save data
     * @param key key
     * @param value value
     */
    public static void put(String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit().putString(key,value);
        editor.apply();
    }


    /**
     * get data
     * @param key key
     * @return value
     */
    public static String get(String key){
        return sharedPreferences.getString(key,null);
    }
}
