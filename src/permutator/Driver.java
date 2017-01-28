package permutator;

import java.util.Arrays;

public class Driver {

	public static void main(String[] args) {

		
		Permutator p = new Permutator(new String[]{"a","b","c","d","e","f"});
		for(Object obj : p){
			String[] order = (String[])obj;
			String[] asString=(String[])Arrays.stream(order).map(i->i.toString()).toArray(size->new String[size]);
			System.out.println(String.join(" ",asString));
		}
		

	}
	
	

}
