package com.cat.multi.net;

import com.cat.multi.sql.DaoManager;
import com.cat.multi.sql.UriBean;

import java.io.File;
import java.util.Set;

/**
 * Created by cat on 2018/1/29.
 */

public class TestDb {

    public static void main(String[] args) {


//        DaoManager.show();

        String key = "http://www.52ppx.com/get_file/1/8f10a38ab00465b0196e5140a7680337/1000/1413/1413.mp4/?rnd=1517128313390";
        int delete = DaoManager.delete(key);

        if (delete > 0) {
            new File(DaoManager.select(key)).delete();
        }
        int delete2 = DaoManager.delete("http://www.51ppx.com/get_file/1/97f362d10918454fd7cc39adb2df67f8/1000/1017/1017.mp4/?rnd=1517152632324");
        System.out.println("del==" + delete + " , " + delete2);

    }
}
