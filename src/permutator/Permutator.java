package permutator;

import java.util.Iterator;

public class Permutator implements Iterable<Object[]> {

	
	private Object[] data;
	private PermutatorIterator iterator;

	
	public Permutator(Object[] arr){
		this.data=arr.clone();	
	}

	@Override
	public Iterator<Object[]> iterator() {
		if(iterator==null){
			iterator = new OuterPermutatorIteratorContainer();
		} else if(!iterator.hasNext()){
			iterator.reset();
		}
		return iterator;
	}
	
	
	public abstract class PermutatorIterator implements Iterator<Object[]>{
		 protected int swap, start;
		 //protected Object[] copy;
		 
		 public void reset(){
			 swap=start;
			 
		 }
		 
		
		 
		 protected PermutatorIterator(int lower){
			 swap=lower;
			 this.start=lower;
			
		 }

	    protected void swap(int a, int b){
	    	Object temp=data[a];
	    	data[a]=data[b];
	    	data[b]=temp;
	    }
		
	}
	
	
	
	public class PermutatorIteratorContainter extends PermutatorIterator{
		protected PermutatorIterator rest;

		protected PermutatorIteratorContainter(int lower) {
			super(lower);
			//first recursion
			if(start<data.length-1){
				rest=new PermutatorIteratorContainter(start+1);
			} else {
				rest=new PermutatorIteratorBase();
			}
		}

		@Override
		public boolean hasNext() {
			return rest.hasNext() || swap<data.length-1;
		}

		@Override
		public Object[] next() {
	
			//we have generated all the permutations of lower+1, n, holding element at index 
			advance(); 
			return returnNext();
		
		}
		
		protected Object[] returnNext(){
			return rest.next();
		}
		
		protected void advance(){
			//we have generated all the permutations of lower+1, n, holding element at index 
			if(!rest.hasNext()){
				swap++;
				swap(swap, start);
				
				rest=new PermutatorIteratorContainter(start+1);
			} 
		}
		
		
		
		@Override
		public void reset() {
			super.reset();
			if(rest!=null){
				rest.reset();
			}
			
		}
		
	}
	
	
	public class OuterPermutatorIteratorContainer extends PermutatorIteratorContainter{

		protected OuterPermutatorIteratorContainer() {
			super(0);
		
		}

		@Override
		protected Object[] returnNext(){
			return rest.next().clone();
		}
		
	}
	
	
	
	public class PermutatorIteratorBase extends PermutatorIterator {

		
		public PermutatorIteratorBase(){
			super(data.length-1);
			
		}
		
		@Override
		public boolean hasNext() {
			return swap<data.length;
		}

		@Override
		public Object[] next() {
	        swap++;
			return data;
		}


		
	}
	

	

}
