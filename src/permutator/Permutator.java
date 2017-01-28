package permutator;

import java.util.Iterator;

public class Permutator implements Iterable<Object[]> {

	
	private Object[] original;
	private PermutatorIterator iterator;

	
	public Permutator(Object[] arr){
		this.original=arr.clone();	
	}

	@Override
	public Iterator<Object[]> iterator() {
		if(iterator==null){
			iterator = new PermutatorIteratorContainer();
		} else if(!iterator.hasNext()){
			iterator.reset();
		}
		return iterator;
	}
	
	
	public abstract class PermutatorIterator implements Iterator<Object[]>{
		 protected int swap, start;
		 protected Object[] copy;
		 
		 public void reset(){
			 swap=start;
		 }
		
		 
		 protected PermutatorIterator(Object[] original, int lower){
			 this.copy=original.clone();
			 swap=lower;
			 this.start=lower;
			
		 }

	    protected void swap(Object[] array, int a, int b){
	    	Object temp=array[a];
	    	array[a]=array[b];
	    	array[b]=temp;
	    }
		
	}
	public class PermutatorIteratorContainer extends PermutatorIterator {

		
		private PermutatorIterator rest;
		
		
		public PermutatorIteratorContainer(){
			this(original, 0);
			
		}
		
		private PermutatorIteratorContainer(Object[] copy, int start){
			super(copy, start);
			if(rest==null){
				//first recursion
				if(start<original.length-1){
					rest=new PermutatorIteratorContainer(copy, start+1);
				} else {
					rest=new PermutatorIteratorBase(copy);
				}
				
			
			}
		
		}
		
	
		@Override
		public boolean hasNext() {
			return rest.hasNext() || swap<copy.length-1;
		}

		@Override
		public Object[] next() {
	
			//we have generated all the permutations of lower+1, n, holding element at index 
			if(!rest.hasNext()){
				swap++;
				swap(copy, swap, start);
				
				rest=new PermutatorIteratorContainer(copy, start+1);
			} 
			return rest.next();
		
		}

		@Override
		public void reset() {
			super.reset();
			if(rest!=null){
				rest.reset();
			}
			
		}
		
	}
	
	
	public class PermutatorIteratorBase extends PermutatorIterator {

		
		public PermutatorIteratorBase(Object[] copy){
			super(copy, copy.length-1);
			
		}
		
		@Override
		public boolean hasNext() {
			return swap<copy.length;
		}

		@Override
		public Object[] next() {
	        swap++;
			return copy;
		}


		
	}
	

	

}
