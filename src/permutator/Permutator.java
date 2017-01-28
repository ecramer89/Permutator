package permutator;

import java.util.Iterator;

public class Permutator<T> implements Iterable {

	
	private T[] original;

	
	public Permutator(T[] arr){
		this.original=arr.clone();	
	}

	@Override
	public Iterator iterator() {
		return new RecursivePermutatorIterator();
	}
	
	
	
	
	public abstract class PermutatorIterator<T> implements Iterator{
		 protected int swap, lower;
		 protected T[] copy;
		
		 
		 protected PermutatorIterator(T[] original, int lower){
			 this.copy=(T[]) original.clone();
			 swap=lower;
			 this.lower=lower;
			
		 }

	    protected void swap(T[] array, int a, int b){
	    	T temp=array[a];
	    	array[a]=array[b];
	    	array[b]=temp;
	    }
		
	}
	public class RecursivePermutatorIterator extends PermutatorIterator {

		
		private PermutatorIterator recursive;
		
		
		public RecursivePermutatorIterator(){
			super(original, 0);
			
		}
		
		private RecursivePermutatorIterator(T[] copy, int lower){
			
			super(copy, lower);
		
		}
		
	
		@Override
		public boolean hasNext() {
			return recursive==null || recursive.hasNext() || swap<copy.length-1;
		}

		@Override
		public T[] next() {
		
			if(recursive==null){
				//first recursion
				if(lower<original.length-1){
					recursive=new RecursivePermutatorIterator((T[]) copy, lower+1);
				} else {
					recursive=new BasePermutatorIterator((T[]) copy);
				}
			}
			
			//we have generated all the permutations of lower+1, n, holding element at index 
			if(!recursive.hasNext()){
				swap++;
				swap(copy, swap, lower);
				recursive=new RecursivePermutatorIterator((T[]) copy, lower+1);
			} 
			return (T[]) recursive.next();
		
		}
		
	}
	
	
	public class BasePermutatorIterator extends PermutatorIterator {

		
		public BasePermutatorIterator(T[] copy){
			super(copy, copy.length-1);
			
		}
		
		@Override
		public boolean hasNext() {
			return swap<copy.length;
		}

		@Override
		public T[] next() {
	        swap++;
			return (T[]) copy;
		}
		
	}
	

	

}
