import java.io.*;
import java.util.*;


public class WordFrequency {

	private static String filename;
	private String[] wordSet;
	private String[][] results;
	
	public static void main(String[] args) throws FileNotFoundException{	
		WordFrequency wf = new WordFrequency();
		Collection<String> collection;
		wf.results = new String[4][3];
		wf.wordSet = new String[11];
		wf.addWords(wf.wordSet);
		
		filename = "Pride And Prejudice.txt";		
		
		collection = new HashSet<String>();
		wf.results[0][0] = "HashSet";
		System.out.print("The time for a HashSet is: ");
		wf.empiricalAnalysis(collection,0);

		
		collection = new TreeSet<String>();
		wf.results[1][0] = "TreeSet";
		System.out.print("The time for a TreeSet is: ");
		wf.empiricalAnalysis(collection,1);

		collection = new ArrayList<String>();
		wf.results[2][0] = "ArrayList";
		System.out.print("The time for a ArrayList is: ");
		wf.empiricalAnalysis(collection,2);

		collection = new LinkedList<String>();
		wf.results[3][0] = "LinkedList";
		System.out.print("The time for a LinkedList is: ");
		wf.empiricalAnalysis(collection,3);

		System.out.print(Arrays.deepToString(wf.results));
		wf.drawTable();
	}

	private void empiricalAnalysis(Collection<String> c, int setNum) throws FileNotFoundException {		
		readFile(c,setNum);
		askUser(c,setNum);
		//testTime(c, setNum);
		
	}
	
	private void readFile(Collection<String> c, int setNum) throws FileNotFoundException {
		double startTime = System.currentTimeMillis();
		
		Scanner reader = new Scanner(new File(filename));
		
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			
			Scanner lineReader = new Scanner(line);
			
			while(lineReader.hasNext()) {		
				String word = lineReader.next();
				cleanWord(word);
				c.add(word);
			}
			lineReader.close();
		}
		reader.close();
		double EndTime = System.currentTimeMillis();
		results[setNum][1] = "" + (EndTime - startTime);
		System.out.printf("%.2f miliseconds\n", EndTime - startTime);
		
	}
	
	private void askUser(Collection<String> c, int setNum) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("What word are you trying to find? ");
		String input = userInput.next();
		
		double startTime = System.nanoTime();

		if(c.contains(input.toLowerCase())) {
			System.out.println("It's in the file! ");
		}else {
			System.out.println("It's not in the file ");
		}
		
		double EndTime = System.nanoTime();
		double time = (EndTime - startTime) / 1000;
		results[setNum][2] = time + "";
		System.out.printf("It took %.2f, microseconds to find\n", time);
	}
	
	
	private String cleanWord(String word) {
		word = word.toLowerCase();
		word.replace("\\p{P}", "");
		return word;
	}
		
	private void testTime(Collection<String> c,int setNum) {
		
		
	}
	private void addWords(String[] array) {
		array[0] = "views";
		array[1] = "bewitching";
		array[2] = "together";
		array[3] = "books";
		array[4] = "living";
		array[5] = "talking";
		array[6] = "with";
		array[7] = "frankly";
		array[8] = "yelled";
		array[9] = "hello";
		array[10] = "questionable";
	}
	private String drawTable() {
		System.out.println("\nSummary of Results");
		for(int i= 0; i<37;i++) {
			System.out.print("-");
		}
		System.out.println("\n|           | Load File |  Contains |");
		
		for(int x = 0; x< 4; x++) {
			
		}
		
		return "";
	}

}



