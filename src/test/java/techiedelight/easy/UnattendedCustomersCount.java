package techiedelight.easy;
/*

Given an integer representing the capacity of a cyber cafe and a sequence representing entry/exit logs of customers, find the total number of customers who could not get any computer.

The first occurrence in the given sequence indicates the arrival, and the second occurrence indicates the departure for a customer. A customer cannot be serviced when the cafe capacity is full (when all computers are allocated).

Input:

sequence = "ABCDDCEFFEBGAG"
capacity = 3

Output: 2

Explanation: Customers 'D' and 'F' left unattended


Input:

sequence = "ABCDDCBEFFEGAG"
capacity = 2

Output: 3

Explanation: Customers 'C', 'D', and 'F' left unattended

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class UnattendedCustomersCount
{
	public static int findUnattendedCustomers(String sequence, int capacity)
	{
		Map<Character, Integer> clients = new HashMap<>();
		int cap = capacity;
		int unattended = 0;
		for(char ch : sequence.toCharArray()) {
			Integer computer = clients.get(ch);
			if (computer == null) {
				// new client
				if (cap > 0) {
					cap--;
					computer = 1;
				} else {
					computer = 0;
					unattended++;
				}
				clients.put(ch, computer);
			} else {
				clients.remove(ch);
				cap += computer;
			}
		}
		return unattended;
	}

	@Test
	void test1() {
		assertEquals(2, findUnattendedCustomers("ABCDDCEFFEBGAG", 3));
	}
}
