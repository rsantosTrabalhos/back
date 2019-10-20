package com.example.doggis.api.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.stereotype.Service;

import com.example.doggis.api.model.RelatorioAvaliacoesProfissionalPeriodo;
import com.example.doggis.api.model.RelatorioClienteServicosMes;
import com.example.doggis.api.model.RelatorioServicosProfissionalMes;

@Service
public class RelatorioService {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<RelatorioServicosProfissionalMes> relatorioServicosProfissionalMes (int mes, int ano) {
		Session session = (Session) manager.getDelegate();
		List<RelatorioServicosProfissionalMes> relatorios = session.doReturningWork(new ReturningWork<List<RelatorioServicosProfissionalMes>>() {
			@Override
			public List<RelatorioServicosProfissionalMes> execute(Connection connection) throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select 		r.nome, ").
					append("			r.email, ").
					append("			r.data_realizacao, ").
					append("			r.nome as profissional ").
					append("from 		view_rel_servicos_por_profissional_mes r ").
					append("where 		r.mes = ? ").
					append("and 		r.ano = ? ").
					append("and 		r.is_realizado = 1 ");
				
				PreparedStatement statement = connection.prepareStatement(sql.toString());
				statement.setInt(1, mes);
				statement.setInt(2, ano);
				ResultSet resultSet = statement.executeQuery();
				List<RelatorioServicosProfissionalMes> relatorios = null;
				while (resultSet.next()) {
					if(relatorios == null) {
						relatorios = new ArrayList<RelatorioServicosProfissionalMes>();
					}
					
					RelatorioServicosProfissionalMes relatorio = new RelatorioServicosProfissionalMes();
					relatorio.setNome(resultSet.getString("nome"));
					relatorio.setEmail(resultSet.getString("email"));
					relatorio.setDataRealizacao(resultSet.getString("data_realizacao"));
					relatorio.setNomeProfissional(resultSet.getString("profissional"));
					
					relatorios.add(relatorio);
				}
				
				
				return relatorios;
			}
		});
		
		return relatorios;
	}
	
	public List<RelatorioAvaliacoesProfissionalPeriodo> relatorioAvaliacoesProfissionalPeriodo (String periodoInicial, String periodoFinal) {
		Session session = (Session) manager.getDelegate();
		List<RelatorioAvaliacoesProfissionalPeriodo> relatorios = session.doReturningWork(new ReturningWork<List<RelatorioAvaliacoesProfissionalPeriodo>>() {
			@Override
			public List<RelatorioAvaliacoesProfissionalPeriodo> execute(Connection connection) throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select 		r.nome, ").
					append("			r.data_realizacao, ").
					append("			r.nome as profissional, ").
					append("			avaliacao ").
					append("from 		view_rel_avaliacoes_profissional_periodo r ").
					append("where 		date(r.data_realizacao) between ? and ? ").
					append("and 		r.is_realizado = 1 ");
				
				PreparedStatement statement = connection.prepareStatement(sql.toString());
				statement.setString(1, periodoInicial);
				statement.setString(2, periodoFinal);
				ResultSet resultSet = statement.executeQuery();
				List<RelatorioAvaliacoesProfissionalPeriodo> relatorios = null;
				while (resultSet.next()) {
					if(relatorios == null) {
						relatorios = new ArrayList<RelatorioAvaliacoesProfissionalPeriodo>();
					}
					
					RelatorioAvaliacoesProfissionalPeriodo relatorio = new RelatorioAvaliacoesProfissionalPeriodo();
					relatorio.setNome(resultSet.getString("nome"));
					relatorio.setDataRealizacao(resultSet.getString("data_realizacao"));
					relatorio.setNomeProfissional(resultSet.getString("profissional"));
					relatorio.setAvaliacao(resultSet.getInt("avaliacao"));
					
					relatorios.add(relatorio);
				}
				
				
				return relatorios;
			}
		});
		
		return relatorios;
	}
	
	public List<RelatorioClienteServicosMes> relatorioClienteServicosMes (int mes, int ano) {
		Session session = (Session) manager.getDelegate();
		List<RelatorioClienteServicosMes> relatorios = session.doReturningWork(new ReturningWork<List<RelatorioClienteServicosMes>>() {
			@Override
			public List<RelatorioClienteServicosMes> execute(Connection connection) throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select 		r.nome, ").
					append("			r.data_realizacao, ").
					append("			r.patazReceber, ").
					append("			ifnull(r.total_pataz, '0') as total_pataz ").
					append("from 		view_rel_cli_servicos_mes r ").
					append("where 		r.mes = ? ").
					append("and 		r.ano = ? ").
					append("and 		r.is_realizado = 1 ");
				
				PreparedStatement statement = connection.prepareStatement(sql.toString());
				statement.setInt(1, mes);
				statement.setInt(2, ano);
				ResultSet resultSet = statement.executeQuery();
				List<RelatorioClienteServicosMes> relatorios = null;
				while (resultSet.next()) {
					if(relatorios == null) {
						relatorios = new ArrayList<RelatorioClienteServicosMes>();
					}
					
					RelatorioClienteServicosMes relatorio = new RelatorioClienteServicosMes();
					relatorio.setNome(resultSet.getString("nome"));
					relatorio.setDataRealizacao(resultSet.getString("data_realizacao"));
					relatorio.setPatazReceber(resultSet.getInt("patazReceber"));
					relatorio.setTotalPataz(resultSet.getInt("total_pataz"));
					
					relatorios.add(relatorio);
				}
				
				
				return relatorios;
			}
		});
		
		return relatorios;
	}
}
