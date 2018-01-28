package com.cat.multi.net;

import com.cat.multi.sql.DaoManager;
import com.cat.multi.sql.UriBean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by cat on 2018/1/28.
 */
public class Head {

    public static void main(String[] args) {


        try {
            DownLoadManager.showHeaders("http://www.52ppx.com/get_file/1/86afe4819cfd7a2efa20af11566ca4b3/2000/2105/2105.mp4/?rnd=1517126907969");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<UriBean> select = DaoManager.select();

        HashMap<String, Long> longHashMap = new HashMap<>();
        for (UriBean bean : select) {
            long length = 0;
            try {
                length = DownLoadManager.getRemoteLength(bean.getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(bean + " , " + length);
            longHashMap.put(bean.getUrl(), length);
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println("-------------------");
        for (String key : longHashMap.keySet()) {

            System.out.println(key + "\t:\t" + longHashMap.get(key));
        }

    }
}
