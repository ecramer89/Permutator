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
			iterator = new OuterPermutatorIteratorContainer();
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
		 
		 protected void updateData(Object[] data){
			 copy=data.clone();
		 }
		
		 
		 protected PermutatorIterator(Object[] original, int lower){
			 this.copy=original.clone();
			 swap=lower;
			 this.start=lower;
			
		 }

	    protected void swap(int a, int b){
	    	Object temp=copy[a];
	    	copy[a]=copy[b];
	    	copy[b]=temp;
	    }
		
	}
	
	
	
	public class PermutatorIteratorContainter extends PermutatorIterator{
		protected PermutatorIterator rest;

		protected PermutatorIteratorContainter(Object[] original, int lower) {
			super(original, lower);
			//first recursion
			if(start<original.length-1){
				rest=new PermutatorIteratorContainter(copy, start+1);
			} else {
				rest=new PermutatorIteratorBase(copy);
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
				swap(swap, start);
				
				rest=new PermutatorIteratorContainter(copy, start+1);
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
	
	
	public class OuterPermutatorIteratorContainer extends PermutatorIteratorContainter{

		protected OuterPermutatorIteratorContainer() {
			super(original, 0);
		
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
