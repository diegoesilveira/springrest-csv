package br.com.diego.resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.model.Cidade;
import br.com.diego.services.CidadeService;
import br.com.diego.util.WriteCsvToResponse;

@RestController
public class CidadeResource {

	@Autowired
	private CidadeService service;

	@RequestMapping(value = "/cidades", produces = "text/csv")
	public void findAll(HttpServletResponse response) throws IOException {
		List<Cidade> cidade = (List<Cidade>) service.findAll();
		
		WriteCsvToResponse.writeUsers(response.getWriter(), cidade);
	}

	@RequestMapping(value = "/cidades/{cidadeId}", produces = "text/csv")
	public void findByCidade(@PathVariable Long cidadeId, HttpServletResponse response) throws IOException {

		Cidade cidade = service.findByID(cidadeId);
		WriteCsvToResponse.writeUser(response.getWriter(), cidade);
	}

}
