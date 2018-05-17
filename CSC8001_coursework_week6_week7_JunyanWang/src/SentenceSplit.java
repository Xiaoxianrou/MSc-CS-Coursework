import java.util.*;
import java.io.*;

/**
 * The SentenceSplit class read input.txt file and out put sorted words in output.file.
 *
 * @author Junyan Wang
 * @version 1.0
 */
public class SentenceSplit {

	/**
	 * constructor for objects of class SentenceSplit
	 */
	public SentenceSplit() {
	}

	/**
	 * main method. 
	 *
	 * @param  
	 */
	public static void main(String args[]) throws IOException {
		SentenceSplit newSentenceSplit = new SentenceSplit();

		FileReader fileReader = new FileReader("src/input.txt");
		BufferedReader bufferReader = new BufferedReader(fileReader);
		String fileString = bufferReader.readLine();

		FileWriter fileWriter = new FileWriter("src/output.txt");

		// handle words
		LinkedHashMap<String, Integer> result = newSentenceSplit.splitToWords(fileString);

		// write words and counts in output.txt
		for (Map.Entry<String, Integer> entry : result.entrySet()) {
			fileWriter.write("The word: " + entry.getKey() + "  times  " + entry.getValue() + "\r\n");
		}
		fileWriter.close();
	}

	/**
	 * split sentence to linked hash map type data.
	 *  
	 * @param  sentence  input.txt content 
	 * @return  sorted words and its count number
	 */
	public LinkedHashMap<String, Integer> splitToWords(String sentence) {
		ArrayList<String> wordList = new ArrayList<String>();
		LinkedHashMap<String, Integer> resCountWord = new LinkedHashMap<String, Integer>();
		String[] wordListOrigin = sentence.split(" ");

		// remove the punctuation mark and lower case the word
		for (int i = 0; i < wordListOrigin.length; i++) {
			String res = wordListOrigin[i];
			res = res.replaceAll("\\p{P}", "");
			if (!res.matches("[0-9]+")) {
				res = res.toLowerCase();
				wordList.add(res);
			}
		}

		// sort the word list 
		Collections.sort(wordList);

		// store and count the word in a linked HashMap
		for (int i = 0; i < wordList.size(); i++) {
			if (resCountWord.containsKey(wordList.get(i))) {
				resCountWord.put(wordList.get(i), resCountWord.get(wordList.get(i)) + 1);
			} else {
				resCountWord.put(wordList.get(i), 1);
			}
		}

		return resCountWord;
	}

}
