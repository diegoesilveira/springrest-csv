package br.com.diego.resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.diego.model.Usuario;
import br.com.diego.services.UsuarioService;


@RestController
public class UploadResource {
	
	private UsuarioService service;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/upload")
	public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

		// validate file
		if (file.isEmpty()) {
			model.addAttribute("message", "Please select a CSV file to upload.");
			model.addAttribute("status", false);
		} else {

			// parse CSV file to create a list of `User` objects
			try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

				// create csv bean reader
				CsvToBean<Usuario> csvToBean = new CsvToBeanBuilder(reader).withType(Usuario.class)
						.withIgnoreLeadingWhiteSpace(true).build();

				// convert `CsvToBean` object to list of users
				List<Usuario> users = csvToBean.parse();

				// TODO: save users in DB?
				//users.forEach(System.out::println);
								
//				for (Usuario usuario : users) {
//					service.insert(usuario);
//				}

				// save users list on model
				model.addAttribute("users", users);
				model.addAttribute("status", true);
				
			} catch (Exception ex) {
				model.addAttribute("message", "An error occurred while processing the CSV file.");
				model.addAttribute("status", false);
			}
		}

		return "file-upload-status";
	}

}
