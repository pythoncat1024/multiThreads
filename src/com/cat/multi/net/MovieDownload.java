package com.cat.multi.net;

import java.io.File;
import java.io.IOException;

/**
 * Created by cat on 2018/1/29.
 */
public class MovieDownload {
    public static void main(String[] args) {
        System.setProperty("https.protocols", "TLSv1");

        {
            String url = "http://123.249.12.242:812/109o_jrOCr/move/201801/6951_3Qs81.mp4";
            url="http://123.249.12.242:812/109o_jrOCr/move/201606/Chinese_Beautiful_Model.mp4";
            DownLoadHelper.taskFinal(url, "张慧敏");
        }

    }
}
