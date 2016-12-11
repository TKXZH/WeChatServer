package xvzh.test;

import java.net.URL;

public class test1 {
	public static void main(String args[]) {
		URL url = test1.class.getResource("/");
		System.out.println(url);
	}
}
