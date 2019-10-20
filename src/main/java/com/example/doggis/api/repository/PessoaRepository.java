package com.example.doggis.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.doggis.api.model.Pessoa;
import com.example.doggis.api.repository.pessoa.PessoaRepositoryQuery;

/**
 * 
 * @author Rodrigo
 * Interface do modelo pessoa, extendemos do JpaRepository pra obter varias implementações prontas
 * devemos informar o modelo bem como o tipo de dado da primary key da tabela
 */
public interface PessoaRepository {

}
