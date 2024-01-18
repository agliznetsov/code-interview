package techiedelight.medium;
/*

Given a string, find the maximum length contiguous substring of it that is also a palindrome.

Input: "bananas"
Output: "anana"

Input: "abdcbcdbdcbbc"
Output: "bdcbcdb"

The longest palindromic substring is not guaranteed to be unique. If multiple longest palindromic substring exists, the solution should return the one which appear first in the string.

Input: "abracadabra"
Output: "aca"
Explanation: There is no palindromic substring in a string "abracadabra" with a length greater than 3. There are two palindromic substrings with length 3, namely, "aca" and "ada". The solution returns "aca" as it appears before "ada" in the string.

Input: "dcabc"
Output: "d"

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LongestPalindromicSubstring {
	// Expand in both directions of `low` and `high` to find maximum length palindrome
	public static String expand(String str, int low, int high)
	{
		// expand in both directions
		while (low >= 0 && high < str.length() &&
				(str.charAt(low) == str.charAt(high))) {
			low--;
			high++;
		}

		// return palindromic substring
		return str.substring(low + 1, high);
	}

	// Function to find the longest palindromic substring in `O(n²)` time
	// and `O(1)` space
	public static String longestPalindromicSubstring(String str)
	{
		// base case
		if (str == null || str.length() == 0) {
			return str;
		}

		// `max_str` stores the maximum length palindromic substring
		// found so far

		String max_str = "", curr_str;

		// `max_length` stores the maximum length of palindromic
		// substring found so far

		int max_length = 0, curr_length;

		// consider every character of the given string as a midpoint and expand
		// in both directions to find maximum length palindrome

		for (int i = 0; i < str.length(); i++)
		{
			// find the longest odd length palindrome with `str[i]` as a midpoint

			curr_str = expand(str, i, i);
			curr_length = curr_str.length();


			// update maximum length palindromic substring if odd length
			// palindrome has a greater length

			if (curr_length > max_length)
			{
				max_length = curr_length;
				max_str = curr_str;
			}

			// Find the longest even length palindrome with str[i] and
			// str[i+1] as midpoints. Note that an even length palindrome
			// has two midpoints.

			curr_str = expand(str, i, i + 1);
			curr_length = curr_str.length();

			// update maximum length palindromic substring if even length
			// palindrome has a greater length

			if (curr_length > max_length)
			{
				max_length = curr_length;
				max_str = curr_str;
			}
		}

		return max_str;
	}

    @Test
    void test() {
        assertEquals("aca", longestPalindromicSubstring("abracadabra"));
    }

	@Test
	void test2() {
		assertEquals("a", longestPalindromicSubstring("a"));
	}

	@Test
	void test3() {
		assertEquals("BAAB", longestPalindromicSubstring("CDCBAAB"));
	}
}
