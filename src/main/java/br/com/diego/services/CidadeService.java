package br.com.diego.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.model.Cidade;
import br.com.diego.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public Cidade findByID(Long codigo) {
		Cidade obj = repository.findById(codigo).orElse(null);
		return obj;
	}

	public void deleteByID(Long codigo) {
		repository.deleteById(codigo);
	}

	public Cidade insert(Cidade cidade) {
		return repository.save(cidade);
	}

	public Cidade update(Cidade cidade) {
		return repository.saveAndFlush(cidade);
	}

	public List<Cidade> findAll() {
		return repository.findAll();
	}
}
