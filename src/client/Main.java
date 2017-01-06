package client;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		Set<Integer> integers = new HashSet<>();
		integers.add(12);
		integers.add(11);
		integers.add(13);
		
		Set<Integer> integers2 = new TreeSet<>();
		integers2.add(12);
		integers2.add(11);
		integers2.add(13);
		
		System.out.println(integers.equals(integers2));
	}

}