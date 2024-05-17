package com.springboot.email.helper;

public class EmailHelper {

	public static String getEmailMessageBody(String name,String host,String token) {
		return "Hello " + name
				+ ", \n\n Your Account has been created. Please clink below link to verify your account. \n\n"
				+ getVerificationLink(host,token)+"\n\n Please Support Me..... :)";
	}

	private static String  getVerificationLink(String host, String token) {
		return host+"/api/users?token="+token;
	}
}
