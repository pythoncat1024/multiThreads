package com.cat.multi.oldtcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 *  ServerSocket¥´“ªæ‰ª∞ ‰≥ˆ‘⁄∑˛ŒÒ∆˜øÿ÷∆Ã®
 */
public class ClientDemo {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		// -1¥¥Ω®øÕªß∂ÀSocket∂‘œÛ
		Socket s = new Socket("127.0.0.1", 12306);
		// -2∑‚◊∞π‹µ¿¡˜
		byte[] buf = "Hello,TCP I am coming".getBytes();
		// int len = 0;
		OutputStream os = s.getOutputStream();
		os.write(buf);
		// -3-- Õ∑≈◊ ‘¥
//		s.close();
	}
}
