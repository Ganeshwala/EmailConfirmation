package com.springboot.email.services.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.springboot.email.helper.EmailHelper;
import com.springboot.email.services.EmailServices;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImp implements EmailServices{

	
	private final String localHost = "http://localhost:8080";
	@Value("${spring.mail.username}")
	private String senderEmail;
	private final JavaMailSender emailSender;
	
	private final String User_Verification = "User Account Verification";
	
	@Override
	@Async
	public void sendEmailMessage(String name, String receiver, String toker) {
		// TODO Auto-generated method stub
		try {
			SimpleMailMessage emailMessage = new SimpleMailMessage();
			emailMessage.setSubject(User_Verification);
			emailMessage.setFrom(senderEmail);
			emailMessage.setTo(receiver);
			emailMessage.setText(EmailHelper.getEmailMessageBody(name, localHost, toker));
			emailSender.send(emailMessage);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@Async
	public void sendEmailMessageWithFiles(String name, String receiver, String toker) {
		try {
			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
			helper.setPriority(1);
			helper.setSubject(User_Verification);
			helper.setFrom(senderEmail);
			helper.setTo(receiver);
			helper.setText(EmailHelper.getEmailMessageBody(name, localHost, toker));
			
			// add Attachments
			FileSystemResource fort = new FileSystemResource(new File("/Users/ganesh/Downloads/IMG_0779.jpeg"));
			helper.addAttachment(fort.getFilename(), fort);
			
			emailSender.send(mimeMessage);
		} catch (MailException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@Async
	public void sendEmailMessageWithEmbeddedAttechments(String name, String receiver, String toker) {
		try {
			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
			helper.setPriority(1);
			helper.setSubject(User_Verification);
			helper.setFrom(senderEmail);
			helper.setTo(receiver);
			helper.setText(EmailHelper.getEmailMessageBody(name, localHost, toker));
			
			// add Attachments
			FileSystemResource fort = new FileSystemResource(new File("/Users/ganesh/Downloads/IMG_0779.jpeg"));
			helper.addInline("<"+fort.getFilename()+">", fort);
			
			emailSender.send(mimeMessage);
		} catch (MailException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	@Async
	public void sendEmailMessageWithImages(String name, String receiver, String toker) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Async
	public void sendHtmlFormatEmail(String name, String receiver, String toker) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Async
	public void sendHtmlFormatEmailWithAttachments(String name, String receiver, String toker) {
		// TODO Auto-generated method stub
		
	}

}
