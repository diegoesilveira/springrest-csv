package br.com.diego.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.model.Usuario;
import br.com.diego.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario findByID(Long codigo) {
		Usuario obj = repository.findById(codigo).orElse(null);
		return obj;
	}
	
	public void deleteByID(Long codigo) {
		repository.deleteById(codigo);
	}
	
	public Usuario insert(Usuario usuario) {
		return repository.save(usuario);
	}
	
	public List<Usuario> insertAll(List<Usuario> usuario) {
		return repository.saveAll(usuario);
	}
	
	public Usuario update(Usuario usuario) {
		return repository.saveAndFlush(usuario);
	}
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	

}
