package BaseCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadAndWriteFile {

	public static String readCarInputFile(int i) throws IOException {
		String content = Files.readString(
				Paths.get("C:\\Users\\KR12\\Desktop\\APITesting\\SR2-TECH-TEST\\src\\Resources\\car_input.txt"));
		Pattern pattern = Pattern.compile("[A-Z]*[0-9]+\\\\ [A-Z]*");

		Matcher matcher = pattern.matcher(null);
		ArrayList<String> cars = new ArrayList<String>();

		while (matcher.find()) {
			cars.add(matcher.group());

		}
		return cars.get(i);

	}

}
