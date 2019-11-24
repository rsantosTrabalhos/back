package com.example.doggis.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.doggis.api.model.RelatorioAvaliacoesProfissionalPeriodo;
import com.example.doggis.api.model.RelatorioClienteServicosMes;
import com.example.doggis.api.model.RelatorioServicosProfissionalMes;
import com.example.doggis.api.model.Veterinario;
import com.example.doggis.api.service.RelatorioService;
import com.example.doggis.api.service.VeterinarioService;

@RestController
@RequestMapping("/doggis")
public class PessoaResource {
	
	@Autowired
	private VeterinarioService veterinarioService;
	
	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/veterinarios")
	public ResponseEntity<List<Veterinario>> listar() {
		List<Veterinario> veterinarios = veterinarioService.listar();
		
		return ResponseEntity.ok(veterinarios);
	}
	
	@PostMapping("/veterinario")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write') ")
	public ResponseEntity<Veterinario> criar(@RequestBody Veterinario veterinario, HttpServletResponse response) {
		Veterinario veterinarioSalvo = veterinarioService.salvar(veterinario);
		return ResponseEntity.status(HttpStatus.CREATED).body(veterinarioSalvo);
	}
	
	@PutMapping("/veterinario/{codigo}") 
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write') ")
	public ResponseEntity<Veterinario> atualizar(@PathVariable Long codigo, @RequestBody Veterinario veterinario) {
		Veterinario veterinarioAtualizada = veterinarioService.atualizar(veterinario);
		
		return ResponseEntity.ok(veterinarioAtualizada);
	}
	
	
	@GetMapping("/relatorio/1/{mes}/{ano}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read') ")
	public List<RelatorioServicosProfissionalMes> relatorioServicosProfissionalMes(@PathVariable int mes, int ano) {
		List<RelatorioServicosProfissionalMes> relatorio = relatorioService.relatorioServicosProfissionalMes(mes, ano);
		
		return relatorio;
	}
	
	@GetMapping("/relatorio/2/{de}/{ate}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read') ")
	public List<RelatorioAvaliacoesProfissionalPeriodo> relatorioAvaliacoesProfissionalPeriodo(@PathVariable String de, String ate) {
		List<RelatorioAvaliacoesProfissionalPeriodo> relatorio = relatorioService.relatorioAvaliacoesProfissionalPeriodo(de, ate);
		
		return relatorio;
	}
	
	@GetMapping("/relatorio/3/{mes}/{ano}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read') ")
	public List<RelatorioClienteServicosMes> relatorioClienteServicosMes(@PathVariable int mes, int ano) {
		List<RelatorioClienteServicosMes> relatorio = relatorioService.relatorioClienteServicosMes(mes, ano);
		
		return relatorio;
	}
	
}
