package permutator;

import java.util.Arrays;

public class Driver {
	static Permutator p;
	public static void main(String[] args) {

		
		p = new Permutator(new String[]{"a","b","c", "d"});
		iterateOverAll();
		System.out.println("next");
		iterateOverAll();
		

	}
	
	
	private static void iterateOverAll(){
		for(Object[] obj : p){
			String[] order = (String[])obj;
			String[] asString=(String[])Arrays.stream(order).map(i->i.toString()).toArray(size->new String[size]);
			System.out.println(String.join(" ",asString));
		}
	}
	
	

}
