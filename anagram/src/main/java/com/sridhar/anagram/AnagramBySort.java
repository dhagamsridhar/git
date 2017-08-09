package com.sridhar.anagram;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

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
public class AnagramBySort {
	
    public static void main( String[] args ) {
        AnagramBySort util = new AnagramBySort();
        System.out.println(util.isAnagram("Google", "Gog"));
        System.out.println(util.isAnagram("Google", "Goo"));
        System.out.println(util.isAnagram("Google", "Got"));
    }
    
    /**
     * Method to check the given source string contains the searchString.
     * 
     * @param source any string.
     * @param searchString string to be serached in the source.
     * @return true if source contains searchString else false.
     */
    public boolean isAnagram(String source, String searchString) {
    	
    	boolean returnValue = false;
    	for (int wordSize=0; 
    			(!returnValue && wordSize < source.length()-searchString.length()); 
    			wordSize++) {
    		returnValue = searchInString(
    				source.substring(wordSize, (wordSize + searchString.length())), 
    				searchString);
    	}
    	return returnValue;
    }
    
    /**
     * Method to take two strings, 
     * convert the strings to arrays, sort the arrays and compare the sorted arrays.
     * 
     * @param string1
     * @param string2
     * @return
     */
    private boolean searchInString(String string1, String string2) {
    	char[] charArray1 = string1.replaceAll("\\s", "").toCharArray();
    	char[] charArray2 = string2.replaceAll("\\s", "").toCharArray();
    	Arrays.sort(charArray1);
    	Arrays.sort(charArray2);
    	
    	return Arrays.equals(charArray1, charArray2);
    }
    
    
    
    @Test
    public void testWithGog() {
    	AnagramBySort util = new AnagramBySort();
    	assertFalse(util.isAnagram("Goolge", "Gog"));
    }

    @Test
    public void testWithGoo() {
    	AnagramBySort util = new AnagramBySort();
    	assertTrue(util.isAnagram("Goolge", "Goo"));
    }
    
    @Test
    public void testWithGot() {
    	AnagramBySort util = new AnagramBySort();
    	assertFalse(util.isAnagram("Goolge", "Got"));
    }
}
