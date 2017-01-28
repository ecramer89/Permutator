package permutator;

import java.util.Iterator;

public class Permutator implements Iterable<Object[]> {

	private Object[] original;
	private Object[] permutation;
	private PermutatorIterator iterator;

	
	public Permutator(Object[] arr){
		this.original=arr.clone();	
	}

	@Override
	public Iterator<Object[]> iterator() {
		permutation=original.clone();
		if(iterator==null){
			iterator = new OuterPermutatorIteratorContainer();
		} else if(!iterator.hasNext()){
			iterator.reset();
		}
		return iterator;
	}
	
	
	public abstract class PermutatorIterator implements Iterator<Object[]>{
		 protected int swap, start;
		 
		 public void reset(){
			 swap=start; 
		 }
		 

		 protected PermutatorIterator(int lower){
			 swap=lower;
			 this.start=lower;
			
		 }

	    protected void swap(int a, int b){
	    	Object temp=permutation[a];
	    	permutation[a]=permutation[b];
	    	permutation[b]=temp;
	    }
	    
	    protected abstract void swapBack();
		
	}
	
	
	
	public class PermutatorIteratorContainter extends PermutatorIterator{
		protected PermutatorIterator rest;

		protected PermutatorIteratorContainter(int lower) {
			super(lower);
			//first recursion
			if(start<permutation.length-1){
				rest=new PermutatorIteratorContainter(start+1);
			} else {
				rest=new PermutatorIteratorBase();
			}
		}

		@Override
		public boolean hasNext() {
			return rest.hasNext() || swap<permutation.length-1;
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
		
		
		@Override
		protected void swapBack(){
			rest.swapBack();
			swap(start, swap);
		}
		
	
		
		protected void advance(){
			//we have generated all the permutations of lower+1, n, holding element at index 
			if(!rest.hasNext()){
				rest.swapBack();
				swap++;
				swap(swap, start);
				rest.reset();
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
			super(permutation.length-1);
			
		}
		
		@Override
		public boolean hasNext() {
			return swap<permutation.length;
		}

		@Override
		public Object[] next() {
	        swap++;
			return permutation;
		}

		@Override
		protected void swapBack() {
			swap--;
	    	swap(swap, start);	
		}
		
		
	

		
	}
	

	

}
