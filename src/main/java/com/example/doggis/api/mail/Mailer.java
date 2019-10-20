package com.example.doggis.api.mail;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.doggis.api.model.Agendamento;

/**
 * Classe de envio dos e-mails
 * @author Rodrigo
 *
 */
@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine trymeleaf;
	
	/**
	 * Configura o email e concretiza seu envio
	 * @param remetente
	 * @param destinatarios
	 * @param assunto
	 * @param mensagem
	 */
	public void enviarEmail(String remetente, String email, String assunto, String mensagem) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(remetente);
			helper.setTo(email);
			helper.setSubject(assunto);
			helper.setText(mensagem, true);
			
			mailSender.send(mimeMessage);
		} catch (MessagingException erro) {
			throw new RuntimeException("Problemas com o envio de e-mail.", erro);
		}
	}
	
	/**
	 * Processa o template html da mensagem e procede com o envio
	 * @param remetente
	 * @param destinatarios
	 * @param assunto
	 * @param template
	 * @param variaveis
	 */
	public void enviarEmail(String remetente, String email, String assunto, String template, Map<String, Object> variaveis) {
		Context context = new Context(new Locale("pt", "BR"));
		
		variaveis.entrySet().forEach(e -> context.setVariable(e.getKey(), e.getValue()));
		
		String mensagem = trymeleaf.process(template, context);
		
		this.enviarEmail(remetente, email, assunto, mensagem);
	}
	
	public void avisarAgendamento(Agendamento agendamento) {
		Map<String, Object> variaveis = new HashMap<String, Object>();
		variaveis.put("agendamento", agendamento);
		// this.enviarEmail("emailAqui@gmail.com", agendamento.getEmail(), "Aviso de Agendamento", "mail/agendamento", variaveis);
	}
}
