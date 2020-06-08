import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.google.common.collect.Lists;

public class ApachePOICSVFile {
	private static final String CSV_FILE_PATH = System.getProperty("user.dir") + "\\resources\\duplicate_mapping.csv";

	public static void main(String[] args) {
		// starting time
		long start = System.currentTimeMillis();

//		for (int i = 0; i < 100; i++)
			CallFunction();

		// ending time
		long end = System.currentTimeMillis();
		System.out.println("Takes " + (end - start) + "ms");
	}

	private static void CallFunction() {
		CSVRecord csvRecord = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			csvRecord = getRecordFromCSVParser(csvParser);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<String> actualIterator = csvRecord.iterator();
		List<String> record = Lists.newArrayList(actualIterator);

		System.out.println(record.toString());

		// end of function
	}

	private static CSVRecord getRecordFromCSVParser(CSVParser csvParser) {
		boolean foundArtifact = false;
		for (CSVRecord csvRecord : csvParser) {
			for (int j = 0; j < csvRecord.size(); j++) {
				foundArtifact = csvRecord.get(j).contains("fasterxml-jackson-schema-maven-plugin");
				if (foundArtifact) {
					System.out.println("Found the artifact name " + csvRecord.get(j));
					break;
				}
			}
			if (foundArtifact) {
				return csvRecord;
			}
		}
		return null;
	}

}
