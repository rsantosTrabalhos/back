package com.example.doggis.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.doggis.api.config.properties.DoggisApiProperty;
/**
 * Classe de inicialização da api
 * @author Rodrigo
 * @EnableConfigurationProperties --> Informando classe de configuração
 */
@SpringBootApplication
@EnableConfigurationProperties(DoggisApiProperty.class)
public class DoggisApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoggisApiApplication.class, args);
	}
}
