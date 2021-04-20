package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 * Lynn Zhang 2021-4-17 10:40 - 11:20
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	/** helper method: check whether prevWord is in the wordList
	 * return:
	 * the index of that word in the wordList
	 * -1 if not found
	 */
	
	private int isInList(String prevWord, List<ListNode> list) {
		for (int i = 0; i < list.size(); i++) {
			ListNode ln = list.get(i);
			if(prevWord.equals(ln.getWord())) {
				return i;
			}
		}			
		return -1;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		if (!starter.equals("")) {
			// trained. 
			// break
		}
		else {
			String[] source = sourceText.split("[ ]+");
			starter = source[0];
			String prevWord = starter;
			// for each word w in the source starting at the second word
			for (int i = 1; i < source.length; i++) {
			// check whether prevWord is in the wordList
				String w = source[i];
				int idx = isInList(prevWord, wordList);   //index in the list
				if(idx != -1) {
					// if in the list, add w as a nextWord to the prevWord node
					ListNode ln = wordList.get(idx);
					ln.addNextWord(w);
				}
				else {
					// else, add a node in the wordList with prevWord as the node's word
					// add w as a nextWord to the prevWord node
					ListNode lnNew = new ListNode(prevWord);
					lnNew.addNextWord(w);	
					wordList.add(lnNew);
				}
				
			// set prevWord as w
				prevWord = w;
			}
			// add starter to be a next word for the last word in the source.
			// add last word 
			int idx = isInList(prevWord, wordList);   //index in the list
			if(idx != -1) {
				// if in the list, add w as a nextWord to the prevWord node
				ListNode ln = wordList.get(idx);
				ln.addNextWord(starter);
			}
			else {
				// else, add a node in the wordList with prevWord as the node's word
				// add w as a nextWord to the prevWord node
				ListNode lnNew = new ListNode(prevWord);
				lnNew.addNextWord(starter);	
				wordList.add(lnNew);
			}
		}
	
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		String currWord = starter;
		if (currWord.equals("") || numWords <= 0) {
			return "";
		}
		String output = "";
		output = output + currWord + " ";
		
		while (numWords > 1) {
			int idx = isInList(currWord, wordList);
			ListNode ln = wordList.get(idx);
			String w = ln.getRandomNextWord(rnGenerator);
			output = output + w + " ";
			currWord = w;
			numWords -= 1;
			
		}
		
		return output;
		
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		starter = "";
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int randomValue = generator.nextInt(nextWords.size());
		String randomNext = nextWords.get(randomValue);
	    return randomNext;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


