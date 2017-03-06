package xvzh.servlets;

import xvzh.wechat.util.AccessTokenManager;

public class test {
	
	public static void main(String args[]) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			String tokenString = AccessTokenManager.getAccessToken();
			System.out.println(tokenString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
