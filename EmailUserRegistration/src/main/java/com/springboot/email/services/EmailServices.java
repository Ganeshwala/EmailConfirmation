package com.springboot.email.services;

public interface EmailServices {

	void sendEmailMessage(String name,String receiver,String toker);
	void sendEmailMessageWithFiles(String name,String receiver,String toker);
	void sendEmailMessageWithEmbeddedAttechments(String name,String receiver,String toker);
	void sendEmailMessageWithImages(String name,String receiver,String toker);
	void sendHtmlFormatEmail(String name,String receiver,String toker);
	void sendHtmlFormatEmailWithAttachments(String name,String receiver,String toker);
}
