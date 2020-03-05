package br.com.diego.util;

import java.io.PrintWriter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;

import br.com.diego.model.Cidade;


public class WriteCsvToResponse {

	private static final Logger LOGGER = LoggerFactory.getLogger(WriteCsvToResponse.class);

	public static void writeUsers(PrintWriter writer, List<Cidade> cidade) {

		try {

			ColumnPositionMappingStrategy<Cidade> mapStrategy = new ColumnPositionMappingStrategy<>();

			mapStrategy.setType(Cidade.class);

			String[] columns = new String[] { "codigo", "nomeCidade" };
			mapStrategy.setColumnMapping(columns);

			StatefulBeanToCsv<Cidade> btcsv = new StatefulBeanToCsvBuilder<Cidade>(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withMappingStrategy(mapStrategy).withSeparator(',')
					.build();

			btcsv.write(cidade);

		} catch (CsvException ex) {

			LOGGER.error("Error mapping Bean to CSV", ex);
		}
	}

	public static void writeUser(PrintWriter writer, Cidade cidade) {

		try {

			ColumnPositionMappingStrategy<Cidade> mapStrategy = new ColumnPositionMappingStrategy<>();

			mapStrategy.setType(Cidade.class);

			String[] columns = new String[] { "codigo", "nomeCidade" };
			mapStrategy.setColumnMapping(columns);

			StatefulBeanToCsv<Cidade> btcsv = new StatefulBeanToCsvBuilder<Cidade>(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withMappingStrategy(mapStrategy).withSeparator(',')
					.build();

			btcsv.write(cidade);

		} catch (CsvException ex) {

			LOGGER.error("Error mapping Bean to CSV", ex);
		}
	}
}