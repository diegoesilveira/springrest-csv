package br.com.diego.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import br.com.diego.model.Usuario;
import br.com.diego.services.UsuarioService;


@Controller
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	 @GetMapping("/export")
	    public void exportCSV(HttpServletResponse response) throws Exception {

	        //set file name and content type
	        String filename = "usuarios.csv";

	        response.setContentType("text/csv");
	        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
	                "attachment; filename=\"" + filename + "\"");

	        //create a csv writer
	        StatefulBeanToCsv<Usuario> writer = new StatefulBeanToCsvBuilder<Usuario>(response.getWriter())
	                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
	                .withOrderedResults(false)
	                .build();

	        //write all users to csv file
	        writer.write(service.findAll());
					
	    }
}
