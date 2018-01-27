package com.cat.multi.net;

import java.io.File;
import java.util.UUID;

/**
 * Created by cat on 2018/1/28.
 * root path
 */
public class PathManager {

    private PathManager() {

    }

    public static String getRootDir() {

        String path = Class.class.getClass().getResource("/").getPath();
        String rootPath = new File(path).getParentFile().getParentFile().getParentFile().getPath();
        return rootPath;
    }

    public static String generatePath(String name) {
        return getRootDir() + File.separator + "raw/video" + File.separator + name;
    }

    public static String generatePath() {
        return getRootDir() + File.separator + UUID.randomUUID();
    }
}
