package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileUtils {
	/**
	 * Given a text file, reads it and returns the list of clean tokens of this
	 * file.
	 */
	public static List<String> readAllTokens(File file)
			throws IOException {
		List<String> tokens = new LinkedList<>();
		try (BufferedReader reader = new BufferedReader(
				new FileReader(file));) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				
				//split every line by whitespaces
				String[] split = line.split("\\s+");
				for (String token : split) {
					//convert the token to lowercase and trim trailing spaces and punctuation marks
					token = token.replaceFirst("^\\p{Punct}*", "")
							.replaceFirst("\\p{Punct}*$", "")
							.toLowerCase().trim();
					if (token.length() > 0) {
						//add non-empty tokens to the list
						tokens.add(token);
					}
				}
			}
		}
		return tokens;
	}
}
