package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * SplayTreeMap class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <K> key
 * @param <V> value
 */
public class SplayTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {
	
	/**
	 * SplayTreeMap constructor
	 */
	public SplayTreeMap() {
		super(null);
	}

	/**
	 * Constructor with compare
	 * @param compare comparator
	 */
	public SplayTreeMap(Comparator<K> compare) {
		super(compare);
	}

	private void splay(Position<Entry<K, V>> p) {
		while (!isRoot(p)) {
			Position<Entry<K, V>> parent = parent(p);
			Position<Entry<K, V>> grandParent = parent(parent);
			if (grandParent == null) {
				rotate(p);
			} else if ((parent == left(grandParent)) == (p == left(parent))) {
				rotate(parent);
				rotate(p);
			} else {
				rotate(p);
				rotate(p);
			}
		}
	}

	@Override
	protected void actionOnAccess(Position<Entry<K, V>> p) {
		// If p is a dummy/sentinel node, move to the parent
		if(isLeaf(p)) {
			p = parent(p);
		}
		if(p != null) {
			splay(p);
		}
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		splay(node);
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> p) {
		if(!isRoot(p)) {
			splay(parent(p));
		}
	}
}