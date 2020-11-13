import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductApp {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		// listagem de produtos
		List<Product> list = new ArrayList<>();

		String fileName = "\\summary.csv";
		String path = "D:\\ESTUDO\\EstudoJava\\JavaCompleto2020\\ReadCsvFiles\\csvFiles\\itemCsv.csv";
		File sourceFile = new File(path);

		String sourceFolder = sourceFile.getParent();
		String targetFile = sourceFolder + fileName;

		// ReadFile
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			// Read file line
			String itemCsv = br.readLine();

			// while line exsits
			while (itemCsv != null) {
				// storage lines/fields in array
				String[] fields = itemCsv.split(",");
				// product name
				String name = fields[0];
				// product price
				double price = Double.parseDouble(fields[1]);
				// product quantity
				int quantity = Integer.parseInt(fields[2]);

				// add products to list
				list.add(new Product(name, price, quantity));

				// ???
				itemCsv = br.readLine();

			}

			// write file
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {

				for (Product item : list) {
					bw.write(item.getName() + ", " + String.format("%.2f", item.total()));
					bw.newLine();
				}

				System.out.println(targetFile + "CREATED");

			} catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}

	}// main

	private static String setDir(String path) {
		try {
			StringBuffer sb = new StringBuffer(path);
			if (sb.charAt(sb.length() - 1) != File.pathSeparatorChar) {
				sb.append(File.separatorChar);
			}
			path = sb.toString();
			File f = new File(path);
			if (!f.exists()) {
				f.mkdir();
			}
		} catch (Exception e) {
			System.out.println("ERROR" + e.getMessage());
		}
		return path;
	}

}
// class
