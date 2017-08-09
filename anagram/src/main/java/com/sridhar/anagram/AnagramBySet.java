package com.sridhar.anagram;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Anagram.
 * This is a interview test program to solve the problem given below.
 * 
 *  —————————————————————————————————————————— 
 *	Problem: Given two strings S and T, find out whether any anagram of T is a Substring of S.
 *
 *  Definition 
 *  Anagram: Any permutation of string - eg. "leg,gel,egl,elg" are all anagrams 
 *  Substring: Continuous sequence of characters of a string  
 *  Goo is a substring of Google but Gog is not. 
 *
 *  Example: 
 *  A = Google, B = Gog Answer: False 
 *  A= Google, B = Goo Answer: True 
 *  A=Google, B = Got Answer: False  
 *  ——————————————————
 *
 */
public class AnagramBySet {
	
    public static void main( String[] args ) {
        AnagramBySet util = new AnagramBySet();
        System.out.println(util.isAnagram("Google", "Gog"));
        System.out.println(util.isAnagram("Google", "Goo"));
        System.out.println(util.isAnagram("Google", "Got"));
    }
    
    /**
     * Method to check the given source string contains the searchString.
     * 
     * @param source any string.
     * @param searchString string to be searched in the source.
     * @return true if source contains searchString else false.
     */
    public boolean isAnagram(String source, String searchString) {
    	return hasSearchWord(source, getAnagramSet(searchString));
    }
    
    private boolean hasSearchWord(String source, Set<String> anagramSet) {
    	System.out.println(anagramSet);
    	for (String word : anagramSet) {
    		if (searchWithContains(source, word)) return true;
    	}
    	return false;
    }
    
    /**
     * Recursive method to take a word and return all possible anagram set.
     * From the given word, move the position of each char to make new word and add
     * all the words to a set and return.
     * 
     * @param word
     * @return set of anagram words
     */
	private Set<String> getAnagramSet(String word) {
		Set<String> resultSet = new HashSet<String>();
		int wordLen = word.length();
		if (wordLen == 1) { // should not be 1, added just to be safe.
			resultSet.add(word);
		} else if (wordLen == 2) { // if 2 then only 2 possible results 01 or 10.
			resultSet.add(word);
			resultSet.add(new StringBuilder().append(word.charAt(1))
					.append(word.charAt(0)).toString());			
		} else if (wordLen > 2) { // if > 2 then hold one char and call the same method with other char.'s.
			for (int wordIndex = 0; wordIndex < wordLen; wordIndex++) {
				char holdChar = word.charAt(wordIndex);
				// remove the hold char from the given string.
				String forAnagram = word.substring(0, wordIndex) 
						+ word.substring(wordIndex + 1);
				// call the same method without hold char.
				for (String returnSet : getAnagramSet(forAnagram)) {
					//with the hold char add the result to the set.
					resultSet.add(new StringBuilder().append(holdChar)
							.append(returnSet).toString());
				}
			}
		}
		return resultSet;
	}
    
    private boolean searchWithContains(String source, String searchString) {
    	// can implement ignore case if required.
    	return source.contains(searchString) ? true : false;
    }
    
    @Test
    public void testWithGog() {
    	AnagramBySet util = new AnagramBySet();
    	assertFalse(util.isAnagram("Goolge", "Gog"));
    }

    @Test
    public void testWithGoo() {
    	AnagramBySet util = new AnagramBySet();
    	assertTrue(util.isAnagram("Goolge", "Goo"));
    }
    
    @Test
    public void testWithGot() {
    	AnagramBySet util = new AnagramBySet();
    	assertFalse(util.isAnagram("Goolge", "Got"));
    }
}
