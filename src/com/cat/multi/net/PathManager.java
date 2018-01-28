package com.cat.multi.net;

import com.sun.javafx.runtime.SystemProperties;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by cat on 2018/1/28.
 * root path
 */
public class PathManager {

    private PathManager() {
    }

    private static HashMap<String, String> url2destPath = new HashMap<>();

    static {
    }

    /**
     * 保证了路径不重名，以及下载过的url 不会被覆盖或者重复下载
     *
     * @param url
     * @return
     */
    public static String generatePathFromUrlFinal(String url) {
        if (url.endsWith(File.separator)) {
            url = url.substring(0, url.length() - 1);
        }
        String name = UUID.randomUUID() + url.substring(url.lastIndexOf(File.separator) + 1, url.lastIndexOf(".")) + ".mp4";
        String destPath = getRootDir() + File.separator + "raw/video" + File.separator + name; // 1. setData
        if (!url2destPath.keySet().contains(url)) {
            url2destPath.put(url, destPath);
        }
        return url2destPath.get(url);
    }


    public static String getRootDir() {
        String path = Class.class.getClass().getResource("/").getPath();
        String rootPath = new File(path).getParentFile().getParentFile().getParentFile().getPath();
        return rootPath;
    }


    public static String getRawDir() {
        String rootDir = getRootDir();
        return new File(rootDir, "/raw").getPath();
    }

    public static String generatePath(String name) {
        return getRootDir() + File.separator + "raw/video" + File.separator + name;
    }

    public static String generatePathFromUrl(String url) {
        if (url.endsWith(File.separator)) {
            url = url.substring(0, url.length() - 1);
        }
        String name = "video" + url.substring(url.lastIndexOf(File.separator) + 1, url.lastIndexOf(".")) + ".mp4";
        return getRootDir() + File.separator + "raw/video" + File.separator + name;
    }

    public static String generatePath() {
        return getRootDir() + File.separator + UUID.randomUUID();
    }
}
