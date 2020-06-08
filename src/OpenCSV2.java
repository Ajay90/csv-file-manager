import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class OpenCSV2 {

	public static void main(String[] args) {
		CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build();
		OpenCSV2 openCSV2 = new OpenCSV2();
		String readAllExample = openCSV2.readAllExample(parser);
		String oneByOneExample = openCSV2.oneByOneExample(parser);
	}

	public String readAllExample(CSVParser csvParser) {
		Reader reader = null;
		String returnStr = null;
		try {
			reader = Files.newBufferedReader(
					Paths.get(System.getProperty("user.dir") + "\\resources\\duplicate_mapping.csv"));
			returnStr = this.readAll(reader, csvParser).toString();
		} catch (IOException | NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return returnStr;
	}

	public List<String[]> readAll(Reader reader, CSVParser csvParser) {
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(csvParser).build();
		List<String[]> list = new ArrayList<>();
		try {
			list = csvReader.readAll();
			reader.close();
			csvReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public String oneByOneExample(CSVParser csvParser) {
		Reader reader = null;
		String returnStr = null;
		try {
			reader = Files.newBufferedReader(
					Paths.get(System.getProperty("user.dir") + "\\resources\\duplicate_mapping.csv"));
			returnStr = this.oneByOne(reader, csvParser).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnStr;
	}

	public List<String[]> oneByOne(Reader reader, CSVParser csvParser) {
		List<String[]> list = new ArrayList<>();
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(csvParser).build();
		String[] line;
		try {
			while ((line = csvReader.readNext()) != null) {
				System.out.println(csvReader.readNext().toString());
				list.add(line);
			}
			reader.close();
			csvReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
