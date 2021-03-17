package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * AVLTreeMap Class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <K> key
 * @param <V> value
 */
public class AVLTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {
	
	/**
	 * AVLTreeMap Constructor
	 */
	public AVLTreeMap() {
		super(null);
	}

	/**
	 * AVLTreeMap Constructor with compare
	 * @param compare comparator
	 */
	public AVLTreeMap(Comparator<K> compare) {
		super(compare);
	}

    // Helper method to trace upward from position p checking to make
    // sure each node on the path is balanced. If a rebalance is necessary,
    // trigger a trinode resturcturing
	private void rebalance(Position<Entry<K, V>> p) {
		int oldHeight;
		int newHeight;
		do {
			oldHeight = getProperty(p);
			if (!isBalanced(p)) {
				Position<Entry<K, V>> child = tallerChild(p);
				Position<Entry<K, V>> grandChild = tallerChild(child);
				p = restructure(grandChild);
				recomputeHeight(left(p));
				recomputeHeight(right(p));
			}
			recomputeHeight(p);
			newHeight = getProperty(p);
			p = parent(p);
		} while (oldHeight != newHeight && p != null);
	}
	
    // Returns the child of p that has the greater height
    // If both children have the same height, use the child that 
    // matches the parent's orientation	
	private Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
		if (getProperty(left(p)) > getProperty(right(p))) {
			return left(p);
		}
		if (getProperty(left(p)) < getProperty(right(p))) {
			return right(p);
		}
		if (isRoot(p)) {
			return left(p);
		}
		if (p == left(parent(p))) {
			return left(p);
		} else {
			return right(p);
		}
	}	

	private boolean isBalanced(Position<Entry<K, V>> p) {
		return Math.abs(getProperty(left(p)) - getProperty(right(p))) <= 1;
	}
	
	private void recomputeHeight(Position<Entry<K, V>> p) {
		int h = 1 + Math.max(getProperty(left(p)), getProperty(right(p)));
		setProperty(p, h);
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		rebalance(node);
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> node) {
		if(!isRoot(node))
		{
			rebalance(parent(node));
		}
	}
}
