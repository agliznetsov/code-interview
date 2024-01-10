package techiedelight.easy;/*

Given two strings, determine whether they are anagrams.

Any word that exactly reproduces the letters in another order is an anagram. In other words, X and Y are anagrams if by rearranging the letters of X, we can get Y using all the original letters of X exactly once.

Input: X = "silent", Y = "listen"
Output: true

Input: X = "incest", Y = "insect"
Output: true

*/

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class CheckAnagram
{
	public static boolean isAnagram(String X, String Y)
	{
		if (X.length() != Y.length()) {
			return false;
		}

		Map<Character, Integer> freq = new HashMap<>();
		for (char c: X.toCharArray()) {
			freq.put(c, freq.getOrDefault(c, 0) + 1);
		}

		for (char c: Y.toCharArray()) {
			Integer count = freq.get(c);
			if (count == null) {
				return false;
			} else {
				count--;
			}
			if (count < 0) {
				return false;
			}
			freq.put(c, count);
		}

		return true;
	}

	@Test
	void test() {
		assertTrue(isAnagram("silent", "listen"));
	}
}
