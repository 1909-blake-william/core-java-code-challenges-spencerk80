package com.revature.eval.java.core;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.LongStream;

//Custom Lambda expression to test whether input number is prime
//Interface is basically a custom type declaration
interface PrimeTest {
	
	boolean testIfPrime(long number);
	
}

public class EvaluationService {
	
	//Define the lambda itself
	private PrimeTest primeNumber = (long number) -> number > 1 && LongStream.range(2L, number).noneMatch(index -> number % index == 0);
	
	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] 	reversed = string.toCharArray();
		char	temp;
		int		j = string.length() - 1;
		
		for(int i = 0; i < j; i++) {
			
			temp = reversed[i];
			reversed[i] = reversed[j];
			reversed[j] = temp;
			j--;
			
		}
		
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		
		StringTokenizer tokens 	= new StringTokenizer(phrase, " -");
		StringBuilder	sb		= new StringBuilder("");
		
		while(tokens.hasMoreElements())
			
			sb.append(tokens.nextToken().toUpperCase().charAt(0));
		
		return sb.toString();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			
			if(this.sideOne == this.sideTwo && this.sideOne == this.sideThree)
				
				return true;
			
			return false;
			
		}

		public boolean isIsosceles() {
			
			if(this.sideOne == this.sideTwo || this.sideOne == this.sideThree || this.sideTwo == this.sideThree)
				
				return true;
			
			return false;
			
		}

		public boolean isScalene() {
			
			if(!isIsosceles())
				
				return true;
			
			return false;
			
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		
		String 	onePoint 	= "A|E|I|O|U|L|N|R|S|T",
				threePoint	= "B|C|M|P",
				fourPoint	= "F|H|V|W|Y",
				fivePoint	= "K",
				eightPoint	= "X",
				tenPoint	= "Q|Z",
				letters[]	= string.toUpperCase().split("");
		int		total 		= 0;
		
		for(String letter : letters) {
			
			total += letter.matches(onePoint) 	? 1  : 0;
			total += letter.matches(threePoint) ? 3  : 0;
			total += letter.matches(fourPoint) 	? 4  : 0;
			total += letter.matches(fivePoint) 	? 5  : 0;
			total += letter.matches(eightPoint) ? 8  : 0;
			total += letter.matches(tenPoint)	? 10 : 0;
			
		}
		
		return total;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		
		StringBuilder	sb			= new StringBuilder("");
		String			zeroToNine	= "[0-9]",
						twoToNine	= "[2-9]";
		String[]		raw			= string.split("");
		List<String>	digits		= new ArrayList<>();
		
		//remove the punctuation
		for(String character : raw)
		
			if(character.matches(zeroToNine))
				
				digits.add(character);
		
		//If the country code 1 is left in
		if(digits.size() == 11)
			
			//If the country code wasn't 1
			if(!digits.get(0).matches(twoToNine))
				
				throw new IllegalArgumentException("Provided country code isn't 1");
		
			else if (!digits.get(1).matches(twoToNine) && !digits.get(4).matches(twoToNine))
				
				throw new IllegalArgumentException("Provided number does not match acceptable specifications");
		
			else
				
				//Rejoin all strings and return
				raw = Arrays.copyOf(digits.toArray(), digits.toArray().length, String[].class);
		
		else if(digits.size() == 10)
			
			if(!digits.get(0).matches(twoToNine) && !digits.get(3).matches(twoToNine))
				
				throw new IllegalArgumentException("Provided number does not match acceptable specifications");
		
			else
				
				//Rejoin all strings and return
				raw = Arrays.copyOf(digits.toArray(), digits.toArray().length, String[].class);
		
		else
			
			throw new IllegalArgumentException("Number has a bad number of digits");
		

		//Build the string for return and return it
		for(String s : raw)
			
			sb.append(s);
		
		return sb.toString();
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		
		StringTokenizer		words	= new StringTokenizer(string, " ,\n");
		Map<String,Integer>	counts	= new HashMap<>();
		String				key;
		
		while(words.hasMoreElements()) {
			
			key = words.nextToken();
			
			if(counts.containsKey(key))
				
				counts.replace(key, counts.get(key) + 1);
			
			else
				
				counts.put(key, 1);
			
		}
		
		return counts;
		
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable<T>> {
		private List<T> sortedList;

		public int indexOf(T t) {
			
			int		lower = 0, mid = sortedList.size() / 2, upper = sortedList.size() - 1;
			
			do {
				
				if(sortedList.get(mid).compareTo(t) < 0) {
					
					lower = mid;
					mid = (upper - lower) / 2 + lower + 1;
					
				} else if(sortedList.get(mid).compareTo(t) > 0) {
					
					upper = mid;
					mid = (upper - lower) / 2 + lower - 1;
					
				} else
					
					return mid;
				
			} while(lower < mid && mid < upper);
			
			if(sortedList.get(mid).compareTo(t) == 0) //Last check for the beginning of the list 
				
				return mid;
			
			return -1;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		
			StringTokenizer	words	= new StringTokenizer(string);
			StringBuilder	sb		= new StringBuilder("");
			String			word,
							con		= "B|C|D|F|G|H|J|K|L|M|N|P|Q|R|S|T|V|W|X|Y|Z";
			int				numCon	= 0;
			
			while(words.hasMoreElements()) {
				
				word = words.nextToken();
				numCon = 0;

				//Count the con cluster at beginning of word
				for(int i = 0; i < word.length(); i++)
					
					if(!(word.charAt(i) + "").toUpperCase().matches(con))
						
						break;
				
					else {
						
						//If the tricky q+u exists, move u with q
						if((word.charAt(i) + "").equalsIgnoreCase("Q"))
								
								if((word.charAt(i + 1) + "").equalsIgnoreCase("U"))
									
									numCon += 2;
								
								else //A word with a Q, but not a u. Ex: Qatar
									
									numCon++;
						
						else //Not a Q
							
							numCon++;
						
					}

				//If begins with cons
				if(numCon > 0) {
					
					sb.append(word.substring(numCon));
					sb.append(word.substring(0, numCon));
					sb.append("ay ");
					
				}
				
				//Begins with a vowel
				else {
					
					sb.append(word);
					sb.append("ay ");
					
				}
				
			}
		
		return sb.toString().trim();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		
		String[]	digits 	= String.valueOf(input).split("");
		int			total	= 0;
		
		for(String digit : digits)
			
			total += Math.pow(Integer.parseInt(digit), digits.length);
		
		if(total == input)
			
			return true;
		
		return false;
		
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		
		List<Long>	primeFactors	= new ArrayList<>();
		long		prime			= 2;
		
		while(l > 1 && prime <= l) {
			
			if(l % prime == 0) {
				
				primeFactors.add(prime);
				l /= prime;
				prime = 2;
				
			} else {
			
				prime++;
				while(!primeNumber.testIfPrime(prime)) //Keep iterating until next prime
					
					prime++; 
				
			}
			
		}
		
		return primeFactors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			
			StringBuffer	sb		= new StringBuffer("");
			char[]			letters	= string.toCharArray();
			String			albet	= "abcdefghijklmnopqrstuvwxyz",
							cypher;
			
			//Make the cypher set
			cypher = albet.substring(key) + albet.substring(0, key);
			
			//Shift each letter
			for(int i = 0; i < letters.length; i++) 
				
				if((letters[i] & 64) == 0) //if not a letter
					
					continue;
				
				else if(letters[i] < 97) //if Uppercase
					
					letters[i] = (char) (cypher.charAt(albet.indexOf(letters[i] | 32)) & ~32);
			
				else //if lowercase
					
					letters[i] = cypher.charAt(albet.indexOf(letters[i]));
	
			//Rebuild the string
			for(char c : letters)
				
				sb.append(c);
			
			return sb.toString();
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		
		int prime = 2, count = 1;
		
		if(i < 1) throw new IllegalArgumentException();
		
		while(count < i) 
			
			//See lambda expression written at the top of the class
			if(primeNumber.testIfPrime(++prime))
				
				count++;
		
		return prime;
		
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			
			StringBuffer	sb		= new StringBuffer("");
			char[]			letters	= string.toLowerCase().toCharArray();
			String			albet	= "abcdefghijklmnopqrstuvwxyz",
							cypher	= sb.append(albet).reverse().toString();
			int				count	= 1;
			
			sb.delete(0, sb.length());
			
			//Shift each letter
			for(int i = 0; i < letters.length; i++) 
				
				if((letters[i] & 64) == 0) //if not a letter
					
					if(letters[i] > 48 && letters[i] < 57)// if a number
						
						continue; //Keep number
			
					else
						
						letters[i] = 0; //Throw away not letter or number
			
				else //if letter
					
					letters[i] = cypher.charAt(albet.indexOf(letters[i]));
	
			//Rebuild the string
			for(char c : letters)
				
				if(count == 5) //If time to put in a space
				
					if(c != 0) { //If it's a letter or number
				
						sb.append(c + " ");
						count = 1;
			
					} else continue; //Discard if not a number or letter
					
				else //No space needed
					
					if(c != 0) { //If a letter or a number
						
						sb.append(c);
						count++;
			
					} else continue; //Discard if not a number or a letter

			return sb.toString().trim();
			
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			
			StringBuffer	sb		= new StringBuffer("");
			char[]			letters	= string.toCharArray();
			String			albet	= "abcdefghijklmnopqrstuvwxyz",
							cypher	= sb.append(albet).reverse().toString();
			
			sb.delete(0, sb.length());
			
			//Remove spaces
			for(char c : letters)
				
				if(c == 32) //if space
					
					continue;
			
				else
					
					sb.append(c);
			
			//Get the string without spaces
			letters = sb.toString().toCharArray();
			sb.delete(0, sb.length());
			
			//Decode
			for(char c : letters)
				
				if((c & 64) == 0) //If not a letter
					
					sb.append(c);
			
				else //Is a letter
				
					sb.append(albet.charAt(cypher.indexOf(c)));
			
			return sb.toString();
			
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
	
		String[]		chars	= string.split("");
		StringBuilder	sb		= new StringBuilder("");
		int				total	= 0;
		
		//strip all the hyphens out
		for(String c : chars)
			
			if(c.contentEquals("-")) continue;
			else sb.append(c);
		
		string = sb.toString();
		
		if(string.length() != 10) return false;
		
		for(int i = 0; i < string.length(); i++)
			
			try {
				
				if(string.substring(i, i+1).equalsIgnoreCase("x"))
					
					total += 10 * (10 - i);
				
				else
				
					total += Integer.parseInt(string.substring(i, i+1)) * (10 - i);
				
			} catch(NumberFormatException nfe) {
				
				return false;
				
			}
		
		return total % 11 == 0;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		
		Set<Character>	letterSet	= new HashSet<>();
		char[]			letters		= string.toLowerCase().toCharArray();
		
		for(char c : letters)
			
			if((c & 64) > 0) //If is letter
					
				letterSet.add(c);
		
		return letterSet.size() == 26;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		
		//Tried. Learned that time in Java is the most annoying thing ever
	
		return null;
		
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		
		Set<Integer>	multiples	= new HashSet<>();
		int 			total		= 0;
		
		for(int j = 1; j < i; j++)
			
			for(int k : set)
				
				if(j % k == 0)
					
					multiples.add(j);
		
		for(int m : multiples)
			
			total += m;
		
		return total;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
	
		String			numberRex	= "[0-9]";
		StringBuilder	sb			= new StringBuilder("");
		int[]			nums;
		int				sum			= 0;
		
		//Check if input is valid
		if(string.length() <= 1) return false;
		
		for(String c : string.split(""))
			
			if(!c.matches(numberRex))
				
				if(c.matches(" "))
					
					continue;
		
				else
					
					return false;
		
			else
				//Store the numbers without spaces
				sb.append(c);
		
		//Set up for conversion
		string = sb.toString();
		nums = new int[string.length()];
		
		//Convert from string to int
		for(int i = 0; i < string.length(); i++)
			
			nums[i] = Integer.parseInt(string.charAt(i) + "");
		
		//Now we can finally do the fun stuff
		//Double every 2nd digit starting at the right
		for(int i = nums.length - 2; i > 0; i -= 2)
			
			nums[i] = nums[i] * 2 % 9;
		
		System.out.println(Arrays.toString(nums));
		
		//Sum all the digits
		for(int i : nums)
			
			sum += i;
		
		System.out.println(sum);
		
		return sum % 10 == 0;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		
		int			rOprnd	= 0,
					lOprnd	= 0;
		boolean		onRight = false;
		
		string = string.substring(0, string.indexOf('?'));
		
		for(String s : string.split(" "))
			
			if(!onRight)
			
				if(strIsNum(s)) {
			
					lOprnd = Integer.parseInt(s);
					onRight = true;
					
				}
		
				else continue;

			else
		
					if(strIsNum(s))
						
						rOprnd = Integer.parseInt(s);
		
		if(string.contains("plus"))
			
			return lOprnd + rOprnd;
		
		else if(string.contains("minus"))
			
			return lOprnd - rOprnd;
		
		else if(string.contains("multiplied"))
			
			return lOprnd * rOprnd;
		
		else if(string.contains("divided"))
			
			return lOprnd / rOprnd;
		
		else
			
			return 0;

	}
	
	private boolean strIsNum(String s) {
		
		try {
			
			Integer.parseInt(s);
			return true;
			
		} catch(Exception e) {
			
			return false;
			
		}
	}

}
