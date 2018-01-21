package com.cat.multi.oldtcp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * ServerSocket¥´“ªæ‰ª∞ ‰≥ˆ‘⁄∑˛ŒÒ∆˜øÿ÷∆Ã®
 */
public class ServerDemo {
	public static void main(String[] args) throws IOException {
		//-1¥¥Ω®∑˛ŒÒ∂ÀSocket∂‘œÛ
		ServerSocket ss = new ServerSocket(12306);
		//-2º‡Ã˝øÕªß∂ÀSocket∂‘œÛ
		Socket s = ss.accept();
		//-3∑‚◊∞π‹µ¿¡˜
		BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
		byte[] buf = new byte[1024*100];
		int len = 0;
		while((len=bis.read(buf))!=-1) {
			System.out.println(new String(buf,0,len));
		}
		//-4
//		s.close();
	}
}
