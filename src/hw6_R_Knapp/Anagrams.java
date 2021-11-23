package hw6_R_Knapp;
import java.util.*;
import java.io.*;

public class Anagrams {

	//Data fields 
	final Integer [] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };
	Map<Character, Integer>letterTable; 
	Map <Long, ArrayList<String>>anagramTable; 
	
	//Constructor for class Anagrams
	public Anagrams() { 
		letterTable = new HashMap<>(); 
		anagramTable = new HashMap<>(); 
		
		buildLetterTable(); 
	}
	
	//Method that builds a hash table letterTable with keys made of letters in the alphabet and the prime numbers in the data fields above
	private void buildLetterTable() {
		int i; 
		Character[] lettersInAlphabet = {
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	    
		for (i = 0; i < 26; i++) {
	    letterTable.put(lettersInAlphabet[i], primes[i]);
	}
}
	
	// Method that computes the hash code of the string s and adds this word to the hash table anagramTable
	private void addWord (String s) { 
		if (anagramTable.containsKey(myHashCode(s))) { 
			anagramTable.get(myHashCode(s)).add(s); 
		
		} else { 
			ArrayList<String> hashArray = new ArrayList<>(); 
			
			hashArray.add(s); 
			anagramTable.put(myHashCode(s), hashArray); 
		}
	}
	
	//Method that returns the hash code given a string s
	private long myHashCode (String s) { 
		int i; 
		long hResult = 1; 
		
		for (i = 0; i < s.length(); ++i)  
			hResult = hResult * letterTable.get(s.charAt(i)); 
		
		return hResult; 
		
	}
	
	//Method that receives the name of a text file and builds the hash table anagramTable
	public void processFile (String s) throws IOException { 
		FileInputStream fstream = new FileInputStream(s); 
		BufferedReader br = new BufferedReader (new InputStreamReader (fstream)); 
		String strLine; 
		while ((strLine = br.readLine()) != null) { 
			this.addWord(strLine);
		}
		br.close(); 
	}
	
	//Method that returns the entries in the anagramTable that have the largest number of anagrams
	private ArrayList <Map.Entry<Long, ArrayList<String>>> getMaxEntries() { 
		int maxEntry = 0;
		
		ArrayList <Map.Entry<Long,ArrayList<String>>> hashArray = new ArrayList<>(); 
		
		for (Map.Entry<Long,ArrayList< String>> strang: anagramTable.entrySet()) { 
			
			if (strang.getValue().size() > maxEntry) { 
				hashArray.clear(); 
				
				maxEntry = strang.getValue().size(); 
				hashArray.add(strang); 
			}
			else if (strang.getValue().size() == maxEntry)
				hashArray.add(strang); 
		}
		
		return hashArray; 
	}
	
	// Testing method in main will read the strings in the file, put them in a hash table, and then iterate the hash table showing words with the largest number of anagrams
	public static void main(String[] args) {
        Anagrams a = new Anagrams ();
        final long startTime = System.nanoTime();
        try {
        	
            a.processFile("words_alpha.txt");
            
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
        
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime/1000000000);
        
        int anagramSize = maxEntries.get(0).getValue().size(); //data field to help calculate the size of the anagram list
        long anagramKey = maxEntries.get(0).getKey(); // data field to help print out the key 
        
        System.out.println("Elapsed Time: " + seconds); // prints out the elapsed time
        System.out.println("Key of max anagrams: " + anagramKey); // prints out key
        System.out.println("List of max anagrams: " + maxEntries.get(0).getValue()); //prints out list
        System.out.println("Length of list of max anagrams: " + anagramSize);// prints out length of list
    }
}


// Ryan Knapp
// Homework Assignment 6: Hashing - Create list of anagrams using hashing
// Date: 5/3/2021













