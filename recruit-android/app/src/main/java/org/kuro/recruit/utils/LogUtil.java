package org.kuro.recruit.utils;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import org.kuro.recruit.BuildConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志打印、记录工具
 */
public class LogUtil {

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void i(String text) {
        if (BuildConfig.LOG_DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                Log.i(BuildConfig.LOG_TAG, text);
            }
        }
    }


    public static void e(String text) {
        if (BuildConfig.LOG_DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                Log.e(BuildConfig.LOG_TAG, text);
            }
        }
    }


    // 将日志保存为文件
    public static void saveToFile(String text) {
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            // 文件路径
            String fileRoot = Environment.getExternalStorageDirectory().getPath() + "/Recruit/";
            String fileName = "Recruit.log";
            // 存储格式：时间 + 内容
            String log = simpleDateFormat.format(new Date()) + " " + text + "\n";

            // 检查路径
            File fileGroup = new File(fileRoot);
            // 创建根布局
            if (!fileGroup.exists()) {
                fileGroup.mkdirs();
            }
            // 创建文件
            File fileChild = new File(fileRoot + fileName);
            if (!fileChild.exists()) {
                fileChild.createNewFile();
            }

            fileOutputStream = new FileOutputStream(fileName, true);

            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(fileOutputStream, Charset.forName("GBK"))
            );
            bufferedWriter.write(log);
        } catch (IOException e) {
            e.printStackTrace();
            e(e.toString());
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
