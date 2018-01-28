package com.cat.multi.sql;

import com.cat.multi.net.NetPath;
import com.cat.multi.net.PathManager;

import java.util.Set;

/**
 * Created by cat on 2018/1/28.
 * java sqlite3
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        DaoManager.createTable();

        DaoManager.insert(NetPath.urlPath1, PathManager.generatePathFromUrlFinal(NetPath.urlPath1));
        DaoManager.insert(NetPath.urlPath2, PathManager.generatePathFromUrlFinal(NetPath.urlPath2));
        Set<UriBean> set = DaoManager.select();
        for (UriBean bean : set) {
            System.out.println(bean);
            DaoManager.delete(bean.getUrl());
        }

        Thread.sleep(300);
        System.err.println("--------###-------");

        set = DaoManager.select();
        for (UriBean bean : set) {
            System.out.println(bean);
//            DaoManager.delete(bean.getUrl());
        }

        DaoManager.dropTable();

        Thread.sleep(600);
        System.err.println("-XXX-------###-------");

        set = DaoManager.select();
        for (UriBean bean : set) {
            System.out.println(bean);
//            DaoManager.delete(bean.getUrl());
        }

    }
}
