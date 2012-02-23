import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {

	public static void main(String[] args) {
		String fileName = "saves/save1.txt";
		API api = new API();
		try {
			new Loader(fileName, api).parse();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		} catch (BadFileException e) {
			System.out.println("Invalid file format: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O exception loading file: " + fileName);
		}
	}
}