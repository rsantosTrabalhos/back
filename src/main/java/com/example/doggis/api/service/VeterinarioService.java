package com.example.doggis.api.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;

import com.example.doggis.api.model.Pessoa;
import com.example.doggis.api.model.Veterinario;
import com.example.doggis.api.model.VeterinarioTipoPet;

@Service
public class VeterinarioService {

	@PersistenceContext
	private EntityManager manager;
	
	public Veterinario salvar (Veterinario veterinario) {
		Session session = (Session) manager.getDelegate();
		session.doWork(new Work() {
			
			@Override
			public void execute(java.sql.Connection connection) throws SQLException {
				StringBuilder sql = new StringBuilder("call salvar_veterinario(?, ?, ?, ?, ?)");
				CallableStatement callableStatement = connection.prepareCall(sql.toString());
				callableStatement.setString(1, veterinario.getPessoa().getNome());
				callableStatement.setString(2, veterinario.getPessoa().getRg());
				callableStatement.setString(3, veterinario.getPessoa().getCpf());
				callableStatement.setString(4, veterinario.getRegistroConselho());
				callableStatement.setLong(5, 1);
				callableStatement.executeUpdate();
			}
		});
		
		return veterinario;
	}
	
	public Veterinario atualizar (Veterinario veterinario) {
		Session session = (Session) manager.getDelegate();
		session.doWork(new Work() {
			
			@Override
			public void execute(java.sql.Connection connection) throws SQLException {
				StringBuilder sql = new StringBuilder("call atualizar_veterinario(?, ?, ?, ?, ?, ?, ?)");
				CallableStatement callableStatement = connection.prepareCall(sql.toString());
				callableStatement.setLong(1, veterinario.getPessoa().getCodigo());
				callableStatement.setString(2, veterinario.getPessoa().getNome());
				callableStatement.setString(3, veterinario.getPessoa().getRg());
				callableStatement.setString(4, veterinario.getPessoa().getCpf());
				callableStatement.setLong(5, veterinario.getCodigo());
				callableStatement.setString(6, veterinario.getRegistroConselho());
				callableStatement.setLong(7, veterinario.getVeterinarioTipoPet().getCodigoTipoPet());
				callableStatement.executeUpdate();
			}
		});
		
		return veterinario;
	}
	
	public List<Veterinario> listar () {
		Session session = (Session) manager.getDelegate();
		List<Veterinario> veterinarios = session.doReturningWork(new ReturningWork<List<Veterinario>>() {
			@Override
			public List<Veterinario> execute(Connection connection) throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select 		v.id as id_veterinario, p.nome, p.cpf, p.rg, v.id_pessoa, v.registro_conselho, tp.id as id_tipo_pet, tp.descricao ").
					append("from 		veterinario v ").
					append("join 		pessoa p ").
					append("on 			v.id_pessoa = p.id ").
					append("join 		veterinario_tipo_pet vtp ").
					append("on 			v.id = vtp.id_veterinario ").
					append("join 		tipo_pet tp ").
					append("on 			vtp.id_tipo_pet = tp.id ");
				
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql.toString());
				List<Veterinario> veterinarios = null;
				while (resultSet.next()) {
					if(veterinarios == null) {
						veterinarios = new ArrayList<Veterinario>();
					}
					
					Pessoa pessoa = new Pessoa();
					pessoa.setCodigo(resultSet.getLong("id_pessoa"));
					pessoa.setNome(resultSet.getString("nome"));
					pessoa.setCpf(resultSet.getString("cpf"));
					pessoa.setRg(resultSet.getString("rg"));
					
					VeterinarioTipoPet veterinarioTipoPet = new VeterinarioTipoPet();
					veterinarioTipoPet.setCodigoTipoPet(resultSet.getLong("id_tipo_pet"));
					veterinarioTipoPet.setCodigoVeterinario(resultSet.getLong("id_veterinario"));
					
					Veterinario veterinario = new Veterinario();
					veterinario.setCodigo(resultSet.getLong("id_veterinario"));
					veterinario.setPessoa(pessoa);
					veterinario.setRegistroConselho(resultSet.getString("registro_conselho"));
					veterinario.setVeterinarioTipoPet(veterinarioTipoPet);
					
					veterinarios.add(veterinario);
				}
				
				
				return veterinarios;
			}
		});
		
		return veterinarios;
	}
	
}
