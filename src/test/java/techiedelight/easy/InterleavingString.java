package techiedelight.easy;/*

Find all interleavings of given strings that can be formed from all the characters of the first and second string where the order of characters is preserved.

Input: X = "ABC", Y = "ACB"
Output: {"ACBABC", "AABCBC", "ACABCB", "ABCACB", "AACBBC", "ABACCB", "ACABBC", "ABACBC", "AACBCB", "AABCCB"}

Input: X = "", Y = ""
Output: {}

*/

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class InterleavingString
{
	public static Set<String> findInterleavings(String X, String Y)
	{
		Set<String> set = new HashSet<>();
		if (X.length() > 0 || Y.length() > 0) {
			addChar(set, X, Y, "", 0, 0);
		}
		return set;
	}

	static void addChar(Set<String> set, String X, String Y, String tmp, int ix, int iy) {
		if (tmp.length() == X.length() + Y.length()) {
			set.add(tmp);
		} else {
			if (ix < X.length()) {
				addChar(set, X, Y, tmp + X.charAt(ix), ix + 1, iy);
			}
			if (iy < Y.length()) {
				addChar(set, X, Y, tmp + Y.charAt(iy), ix, iy + 1);
			}
		}
	}

	@Test
	void tet() {
		System.out.println(findInterleavings("ABC", "XYZ"));
	}
}
