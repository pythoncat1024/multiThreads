package com.cat.multi.net;

import java.io.IOException;

/**
 * Created by cat on 2018/1/27.
 * https://201712mp4.89soso.com/20171205/9/1/xml/91_1b99a5d6c21a43d3ee8706786d4a9a19.mp4
 */
public class Main {

    public static void main(String[] args) {


        System.out.println(PathManager.generatePathFromUrl("http://media66.avtb02.com/media/videos/mp4/23142.mp4?st=oWYfxTq4X3MkZu8WiGOe2Q&e=1517128198"));


        DownLoadHelper.taskSimple("http://tui.zxc123.info/20180123/42.mp4");

        DownLoadHelper.taskSimple("http://tui.zxc123.info/20180123/18.mp4");

        DownLoadHelper.taskSimple("http://www.99ff6.com/get_file/3/78de13f4ca8f0846a9d1bdc6f597d4f9/83000/83350/83350.mp4/");

        DownLoadHelper.taskSimple("http://media66.avtb02.com/media/videos/mp4/22865.mp4?st=tlnoMNTcM5Dn_YRcTtE5mQ&e=1517133182");


        DownLoadHelper.taskSimple("http://tui.zxc123.info/20180123/28.mp4");

        DownLoadHelper.taskSimple("http://www.52ppx.com/get_file/1/86afe4819cfd7a2efa20af11566ca4b3/2000/2105/2105.mp4/?rnd=1517126907969");


//        #####################################################

        DownLoadHelper.taskFinal("http://seku.tv/get_file/1/8755d0736679305a7623b6711661b6230b6edba4d3/0/785/785_360p.mp4/?rnd=1517127031823");

        DownLoadHelper.taskFinal("http://www.52ppx.com/get_file/1/86afe4819cfd7a2efa20af11566ca4b3/2000/2105/2105.mp4/?rnd=1517126907969");

        DownLoadHelper.taskFinal("http://www.52ppx.com/get_file/1/8f10a38ab00465b0196e5140a7680337/1000/1413/1413.mp4/?rnd=1517128313390");

        DownLoadHelper.taskFinal("http://101.44.1.126/mp4files/A040000006F7257C/video.95stc.me/b2/b2-nNqkZnL0I6aeYzZn2khk-a.mp4");

        DownLoadHelper.taskFinal("http://www.yaoshe6.com/get_file/1/c0ad0ad21e697c57ff15feb0e3689ca9/10000/10253/10253.mp4/?rnd=1517128607888");

        DownLoadHelper.taskFinal("http://www.77fcw.com/get_file/1/1095ed6374940de54a4537b98086b94c4a49e5458a/0/403/403.mp4/");
    }
}
