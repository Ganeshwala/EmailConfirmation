package com.springboot.email.helper;

public class EmailHelper {

	public static String getEmailMessageBody(String name,String host,String token) {
		return "Hello " + name
				+ ", \n\n Your Account has been created. Please clink below link to verify your account. \n\n"
				+ getVerificationLink(host,token)+"\n\n Please Support Me..... :)"
						+ "\n\n and message me back once you get it.......";
	}

	public static String  getVerificationLink(String host, String token) {
		return host+"/api/users?token="+token;
	}
}
