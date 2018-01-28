package com.cat.multi.net;

import com.cat.multi.sql.DaoManager;
import com.cat.multi.sql.UriBean;

import java.util.Set;

/**
 * Created by cat on 2018/1/29.
 */

public class TestDb {

    public static void main(String[] args) {

        String url = "http://101.44.1.123/mp4files/5141000006F9CC4B/video.95stc.me/9b/9b-1u9Fguj513xHN0MzuMAp-a.mp4";

        int insert1 = DaoManager.insert(url, PathManager.generatePathFromUrlFinal(url));
        int insert2 = DaoManager.insert(url, PathManager.generatePathFromUrlFinal(url));

        System.out.println("### -> " + insert1 + " , " + insert2);
//        DaoManager.show();
    }
}
