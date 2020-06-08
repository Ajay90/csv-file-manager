import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BufferedReaderCSVFile {

	// BufferedReader in java.io
	// https://www.baeldung.com/java-csv-file-array#buff-reader

	private static final String COMMA_DELIMITER = ";";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// starting time
		long start = System.currentTimeMillis();

//		for (int i = 0; i < 100; i++)
			CallFunction();

		// ending time
		long end = System.currentTimeMillis();
		System.out.println("Takes " + (end - start) + "ms");
	}

	private static void CallFunction() throws FileNotFoundException, IOException {
		// start of function
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "\\resources\\duplicate_mapping.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(COMMA_DELIMITER);
				records.add(Arrays.asList(values));
			}
		}
		List<String> record = getRecordFromList(records);
		System.out.println(record.toString());
		// end of function

	}

	private static List<String> getRecordFromList(List<List<String>> records) {

		boolean foundArtifact = false;
		for (List<String> record : records) {
			for (int j = 0; j < record.size(); j++) {
				foundArtifact = record.get(j).contains("fasterxml-jackson-schema-maven-plugin");
				if (foundArtifact) {
					System.out.println("Found the artifact name " + record.get(j));
					break;
				}
			}
			if (foundArtifact) {
				return record;
			}
		}
		return null;
	}

}
