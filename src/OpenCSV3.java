import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVReader;

public class OpenCSV3 {

	private static final String SAMPLE_CSV_FILE_PATH = System.getProperty("user.dir")
			+ "\\resources\\duplicate_mapping.csv";

	public static void main(String[] args) {
		try (Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
				CSVReader csvReader = new CSVReader(reader);) {
			// Reading Records One by One in a String array
			String[] nextRecord;
			while ((nextRecord = csvReader.readNext()) != null) {
				System.out.println(nextRecord);
				System.out.println("Name : " + nextRecord[0]);
				System.out.println("Email : " + nextRecord[1]);
				System.out.println("Phone : " + nextRecord[2]);
				System.out.println("Country : " + nextRecord[3]);
				System.out.println("==========================");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
