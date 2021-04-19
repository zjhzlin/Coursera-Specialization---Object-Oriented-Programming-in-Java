package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Lynn ZHANG
 * 2021-04-19 08:15 - 09:23 addWord, size, isWord
 * next up: predict completion
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		word = word.toLowerCase();
		//System.out.println("Word is " + word);
		TrieNode currNode = root;
		boolean flag = false;
		for (int i = 0; i < word.length(); i++) {
			// check whether each character is in the trie 
			//printNode(currNode);
			char currChar = word.charAt(i);		
			Set<Character> charSets = currNode.getValidNextCharacters(); 		//all the valid next characters
			if(charSets.contains(currChar)) {   // the character is in the set, look for next node
				currNode = currNode.getChild(currChar);
				if(i == word.length()-1  && !currNode.endsWord()) {
					currNode.setEndsWord(true);
					size += 1;
					
				}
			}
			else { 		// the current char is not, then add new node 
				flag = true;
				if(i == word.length()-1) {
					currNode = currNode.insert(currChar);   // next node is the correct word
					currNode.setEndsWord(true);
					size += 1;
				}
				else {
					currNode = currNode.insert(currChar);
				}
			}
		}		
		//System.out.println("size is " + size);
		//System.out.println("Tree looks like:");
		//printTree();
		return flag;
		
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if (s.equals("")) {
			return false;
		}
		s = s.toLowerCase();
		TrieNode currNode = root;
		for(int i = 0; i < s.length(); i++) {
			char currChar = s.charAt(i);		
			Set<Character> charSets = currNode.getValidNextCharacters(); 
			if (charSets.contains(currChar)) {
				currNode = currNode.getChild(currChar);
			}
			else {
				return false;
			}
		}
		return true;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
		 List<String> predict = new LinkedList<String>();  	 
    	 prefix = prefix.toLowerCase();
		 TrieNode currNode = root;
		 boolean flag = false;
		 if (prefix.equals("")) {
			 flag = true;
		 }
    	 for (int i = 0; i < prefix.length(); i++) {
    		 char currChar = prefix.charAt(i);		
    		 Set<Character> charSets = currNode.getValidNextCharacters(); 
 			 if (charSets.contains(currChar)) {
 				currNode = currNode.getChild(currChar);
 				flag = true;
 			 }
 			 else {
 				 flag = false;
 			 }
    	 }
    	 if(!flag || numCompletions == 0) {	// if no such stem found, return empty list
    		 return predict;
    	 }
    	 // if stem found
    	 // use breath first search - queue
    	 Queue<TrieNode> q = new LinkedList<TrieNode>();
    	 q.add(currNode);
    	 while(!q.isEmpty() && numCompletions > 0) {
    		 TrieNode curr = q.remove();
    		 if(curr!=null) {
    			 // check whether curr is valid word. 
    			 if(curr.endsWord()) {
    				 predict.add(curr.getText());
    				 numCompletions -= 1;
    			 }
    			 // add all of its next level child 
    			 Set<Character> charSets = curr.getValidNextCharacters();
    			 for (char currChar: charSets) {
        			 TrieNode nextNode = curr.getChild(currChar);
        			 q.add(nextNode);
        		 }
    			 
    		 }
    	 }
 		//System.out.println("size is " + predict.size());
    	 
        return predict;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}