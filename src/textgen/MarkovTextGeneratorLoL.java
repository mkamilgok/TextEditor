package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
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
	
	private ListNode find(String word){
		for(ListNode each: wordList){
			if(each.getWord().equals(word))
				return each;
		}
		return null;
	}
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) {
		String[] words = sourceText.split(" ");
		if (words.length == 0) {
			return;
		}
		if (starter.equals(""))
			starter = words[0];
		
		if (words.length == 1) {
			ListNode wordNode = new ListNode(words[0]);
			wordNode = find(words[0]);
			if (wordNode == null) {
				wordNode = new ListNode(words[0]);
				wordList.add(wordNode);
			}
			wordNode.addNextWord(starter);
			return;
		}

		else {
			ListNode wordNode;
			String current = words[0];
			String next = words[1];

			for (int i = 1; i < words.length; i++) {
				wordNode = find(current);
				if (wordNode == null) {
					wordNode = new ListNode(current);
					wordList.add(wordNode);
				}
				
				wordNode.addNextWord(next);

				current = next;
				if (i != words.length - 1) {
					next = words[i + 1];
				}
			}
			
			wordNode = find(words[words.length - 1]);
			if (wordNode == null) {
				wordNode = new ListNode(current);
				wordList.add(wordNode);
			}
			wordNode.addNextWord(words[0]);
			
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    String text = "";
	    if(numWords == 0 || starter.equals("")) {
	    	return text;
	    }
	    
	    text = starter;
	    if(numWords == 1) {
	    	return text;
	    }
	    
	    ListNode wordNode;
	    String lastAdded = starter;
	    for(int i = 1; i < numWords; i++) {
	    	text += " ";
	    	wordNode = find(lastAdded);
	    	lastAdded = wordNode.getRandomNextWord(rnGenerator);
	    	text += lastAdded;
	    }
	    return text;
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
		wordList = new LinkedList<ListNode>();
		starter = "";
		train(sourceText);
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "hi there hi Leo hi Leo hi Leo hi Leo hi Leo hi Leo hi Leo";
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
		if(nextWords == null) {
			return null;
		}
		
		int length = nextWords.size();
		generator = new Random();
		int index = generator.nextInt(length);
	    return nextWords.get(index);
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


