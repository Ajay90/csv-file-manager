import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFileScanner {
	private static final String COMMA_DELIMITER = ";";

	public static void main(String[] args) {
		// starting time
		long start = System.currentTimeMillis();

		for (int i = 0; i < 100; i++)
			CallFunction();

		// ending time
		long end = System.currentTimeMillis();
		System.out.println("Takes " + (end - start) + "ms");
	}

	private static void CallFunction() {
		List<List<String>> records = new ArrayList<>();
		try (Scanner scanner = new Scanner(
				new File(System.getProperty("user.dir") + "\\resources\\duplicate_mapping.csv"));) {
			while (scanner.hasNextLine()) {
				records.add(getRecordFromLine(scanner.nextLine()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> record = getRecordFromList(records);
		System.out.println(record.toString());
		// end of function
	}

	private static List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return values;
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
