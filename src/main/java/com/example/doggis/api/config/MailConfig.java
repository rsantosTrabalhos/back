package com.example.doggis.api.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.example.doggis.api.config.properties.DoggisApiProperty;

@Configuration
public class MailConfig {
	
	@Autowired
	private DoggisApiProperty property;
	
	@Bean
	public JavaMailSender javaMailSender() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.connectiontimeout", 10000);
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setJavaMailProperties(props);
		mailSender.setHost(property.getMail().getHost());
		mailSender.setPort(property.getMail().getPort());
		mailSender.setUsername(property.getMail().getNomeUsuario());
		mailSender.setPassword(property.getMail().getSenha());
		
		return mailSender;
	}
}
