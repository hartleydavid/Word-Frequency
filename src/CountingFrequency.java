import java.io.*;
import java.util.*;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

public class CountingFrequency {

	private static String filename;
	private String[][] results;

	public static void main(String[] args) throws FileNotFoundException {
		CountingFrequency cf = new CountingFrequency();
		filename = "Pride And Prejudice.txt";
		Map<String, Integer> wordFreq;

		wordFreq = new HashMap<String, Integer>();
		cf.readFile(wordFreq);
		System.out.println("Unsorted Map " + wordFreq);
		wordFreq = cf.sortFrequency(wordFreq);
		System.out.println("Sorted Map " + wordFreq);
		cf.promptUser(wordFreq);
		cf.drawTable();

	}

	private void promptUser(Map<String, Integer> myMap) {
		Scanner user = new Scanner(System.in);
		System.out.println("\nWould you like to get all the words, or just a subset? (Input: All, Sub)");
		String input = user.next();
		user.close();
		if (input.equalsIgnoreCase("all")) {
			System.out.println("All the words: " + myMap);

		} else if (input.equalsIgnoreCase("sub")) {

			Iterator<String> itr = myMap.keySet().iterator();
			while (itr.hasNext()) {
				String word = itr.next();

				if (myMap.get(word) < 1000) {
					itr.remove();
				}
			}

			System.out.println("All words that appeared at least 1000 times: " + myMap);
		}

	}

	private Map<String, Integer> sortFrequency(Map<String, Integer> myMap) {

		Map<String, Integer> sorted = myMap.entrySet().stream().sorted(comparingByValue())
				.collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
		return sorted;

	}

	private void readFile(Map<String, Integer> map) throws FileNotFoundException {

		Scanner reader = new Scanner(new File(filename));

		while (reader.hasNextLine()) {
			String line = reader.nextLine();

			Scanner lineReader = new Scanner(line);

			while (lineReader.hasNext()) {
				String word = lineReader.next();
				if (word.contains("-")) {
					testKey(map, cleanWord(word.substring(0, word.indexOf("-"))));
					testKey(map, cleanWord(word.substring(word.indexOf("-"))));
				} else {
					word = cleanWord(word);
					testKey(map, word);

				}

			}
			lineReader.close();
		}
		reader.close();

	}

	private void testKey(Map<String, Integer> map, String word) {
		int n = 1;
		if (map.containsKey(word)) {
			n = map.get(word);
			map.put(word, ++n);

		} else {
			map.put(word, n);
		}

	}

	private void setUpTable() {
		results = new String[10][2];
		for (int index = 0; index < 10; index++) {
			int rank = index;
			if (index == 5) {				
				results[index][0] = "...";			
			} else if (index > 4) {			
				rank += 4197;
				results[index][0] = rank + "";			
			} else {			
				rank++;
				results[index][0] = rank  + "";	
			}
			
		}
		
		results[0][1] = "1656";	
		results[1][1] = "1597";	
		results[2][1] = "1388";	
		results[3][1] = "1374";	
		results[4][1] = "845";	
		results[5][1] = "...";	
		results[6][1] = "1" ;	
		results[7][1] = "1";	
		results[8][1] = "1";	
		results[9][1] = "1"	;
	}

	private void drawTable() {
		WordFrequency wf = new WordFrequency();
		System.out.println("Summary to Show Zipf's Law:");
		wf.drawLine(20);
		System.out.printf("|%6s|%11s|\n", "Rank", "Frequency");
		wf.drawLine(20);
		setUpTable();
		for(int x = 0; x< 10; x++) {
			System.out.printf("|%6s|%11s|\n",results[x][0],results[x][1]);
		}
		wf.drawLine(20);
	}

	private String cleanWord(String word) {
		word = word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		return word;
	}
}
