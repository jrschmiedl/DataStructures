package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * QuickSorter class that uses the QuickSort algorithm
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> generic data
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** First Element Selector */
	public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
	/** Last Element Selector */
	public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
	/** Middle Element Selector */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    /** Random Element Selector */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
    /** Selector that is being used */
    private PivotSelector selector;
    
    /**
     * PivotSelector interface
     * @author jrschmie Jacob Schmiedl
     *
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * @param low - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
    
    /**
     * Constructor for QuickSorter with a comparator
     * @param comparator comparator that compares the data
     * @param selector selects the pivot
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }
    
    /**
     * Constructor for QuickSorter with a comparator
     * @param comparator comparator that compares the data
     */
	public QuickSorter(Comparator<E> comparator) {
		this(comparator, null);
	}    
    
	/**
	 * Constructor for QuickSorter with a selector
	 * @param selector selects the pivot
	 */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }
    
    /**
     * Constructor for QuickSorter without a comparator
     */
    public QuickSorter() {
        this(null, null);
    }
    
    /**
     * Setter for selector
     * @param selector selects the pivot
     */
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            selector = new RandomElementSelector();
        }
        this.selector = selector;
    }

	@Override
	public void sort(E[] data) {
		int low = 0;
		int high = data.length - 1;
		quickSort(data, low, high);
	}
    
	/**
	 * Quick Sort method
	 * @param data that is unsorted
	 * @param low in the array
	 * @param high in the array
	 */
	private void quickSort(E[] data, int low, int high) {
		if (low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}
	
	/**
	 * Partition method
	 * @param data that is unsorted
	 * @param low in the array
	 * @param high in the array
	 * @return index found by partitionHelper
	 */
	private int partition(E[] data, int low, int high) {
		int pivotIndex = selector.selectPivot(low, high);
		swap(data, pivotIndex, high);
		return partitionHelper(data, low, high);
	}
	
	/**
	 * Swap method
	 * @param data that is unsorted
	 * @param index of the element being swapped
	 * @param high in the array
	 */
	private void swap(E[] data, int index, int high) {
		E temp = data[index];
		data[index] = data[high];
		data[high] = temp;
	}
	
	private int partitionHelper(E[] data, int low, int high) {
    	E pivot = data[high];
    	int index = low;
    	for (int i = low; i < high; i++) {
    		if (super.compare(data[i], pivot) <= 0) {
    			swap(data, index, i);
    			index++;
    		}
    	}
    	swap(data, index, high);
    	return index;
    }
	
	/**
	 * FirstElementSelector class
	 * @author jrschmie Jacob Schmiedl
	 *
	 */
	static class FirstElementSelector implements PivotSelector {
		
		/**
		 * Constructor for FirstElementSelector
		 */
		public FirstElementSelector() {
			// skip
		}
		
		/**
		 * SelectPivot method
		 * 
		 * @param low in the array
		 * @param high in the array
		 * 
		 * @return low in the array
		 */
		public int selectPivot(int low, int high) {
			return low;
		}
	}
	
	/**
	 * LastElementSelector class
	 * @author jrschmie Jacob Schmiedl
	 *
	 */
	static class LastElementSelector implements PivotSelector {
		
		/**
		 * Constructor for LastElementSelector
		 */
		public LastElementSelector() {
			// skip
		}

		/**
		 * SelectPivot method
		 * 
		 * @param low in the array
		 * @param high in the array
		 * 
		 * @return high in the array
		 */
		public int selectPivot(int low, int high) {
			return high;
		}
	}
	
	/**
	 * MiddleElementSelector class
	 * @author jrschmie Jacob Schmiedl
	 *
	 */
	static class MiddleElementSelector implements PivotSelector {
		
		/**
		 * Constructor for MiddleElementSelector
		 */
		public MiddleElementSelector() {
			// skip
		}
		
		/**
		 * SelectPivot method
		 * 
		 * @param low in the array
		 * @param high in the array
		 * 
		 * @return middle in the array
		 */
		public int selectPivot(int low, int high) {
			int middle = (high - low) / 2 + low;
			return middle;
		}
	}
	
	/**
	 * RandomElementSelector class
	 * @author jrschmie Jacob Schmiedl
	 *
	 */
	static class RandomElementSelector implements PivotSelector {
		
		/**
		 * Constructor for RandomElementSelector
		 */
		public RandomElementSelector() {
			// skip
		}
		
		/**
		 * SelectPivot method
		 * 
		 * @param low in the array
		 * @param high in the array
		 * 
		 * @return random in the array
		 */
		public int selectPivot(int low, int high) {
			int random = low + (int)(Math.random() * ((high - low) + 1));
			return random;
		}
	}
}