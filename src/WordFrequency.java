import java.util.*;
import java.io.*;


public class WordFrequency {
	private static int lineCount, wordCount;
	public static void main(String[] args) throws FileNotFoundException{
		WordFrequency wf = new WordFrequency();
		lineCount = 0;
		wordCount = 0;
		wf.readFile("Pride and Prejudice.txt");
	}

	private void readFile(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(filename));
		
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			lineCount++;
			System.out.println("Num of lines are " + lineCount);

			Scanner lineReader = new Scanner(line);
			while(lineReader.hasNext()) {
				
				String word = lineReader.next();
				wordCount++;

			}
			
		}
		System.out.println("Num of lines are " + lineCount);
		System.out.println("Num of words are " + wordCount);
		
	}
}
