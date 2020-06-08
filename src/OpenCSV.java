import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;

public class OpenCSV {
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
		try (CSVReader csvReader = new CSVReader(
				new FileReader(System.getProperty("user.dir") + "\\resources\\duplicate_mapping.csv"))) {
			String[] values = null;
			String[] foundRecord = null;
			String componentName = "fasterxml-jackson-schema-maven-plugin";
			while ((values = csvReader.readNext()) != null) {
				if (containsRecord(values, componentName) == true) {
					foundRecord = values;
					break;
				}
			}
			System.out.println("Record fields are");
			System.out.println(foundRecord);
			for (String field : foundRecord)
				System.out.println(field);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// end of function

	}

	private static boolean containsRecord(String[] records, String componentName) {
		for (String record : records) {
			if (record.contains(componentName))
				return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
