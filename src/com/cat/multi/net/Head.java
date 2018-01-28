package com.cat.multi.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by cat on 2018/1/28.
 */
public class Head {

    public static void main(String[] args) throws IOException {


        DownLoadManager.showHeaders("http://www.52ppx.com/get_file/1/86afe4819cfd7a2efa20af11566ca4b3/2000/2105/2105.mp4/?rnd=1517126907969");
    }
}
