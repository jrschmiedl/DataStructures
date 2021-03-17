package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * RedBlackTreeMap
 * @author jrschmie Jacob Schmiedl
 *
 * @param <K> key
 * @param <V> value
 */
public class RedBlackTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {
	
	/**
	 * Constructor for RedBlackTreemap
	 */
	public RedBlackTreeMap() {
		super(null);
	}
	
	/**
	 * Constructor with compare
	 * @param compare comparator
	 */
	public RedBlackTreeMap(Comparator<K> compare) {
		super(compare);
	}

    // Helper method to abstract the idea of black
	private boolean isBlack(Position<Entry<K, V>> p) {
		return getProperty(p) == 0;
	}

    // Helper method to abstract the idea of red
	private boolean isRed(Position<Entry<K, V>> p) {
		return getProperty(p) == 1;
	}

    // Set the color for a node to be black
	private void makeBlack(Position<Entry<K, V>> p) {
		setProperty(p, 0);
	}

    // Set the color for a node to be red
	private void makeRed(Position<Entry<K, V>> p) {
		setProperty(p, 1);
	}

	private void resolveRed(Position<Entry<K, V>> node) {
		Position<Entry<K, V>> parent = parent(node);
		if (isRed(parent)) {
			Position<Entry<K, V>> uncle = sibling(parent);
			if (isBlack(uncle)) {
				Position<Entry<K, V>> middle = restructure(node);
				makeBlack(middle);
				makeRed(left(middle));
				makeRed(right(middle));
			} else {
				makeBlack(parent);
				makeBlack(uncle);
				Position<Entry<K, V>> grand = parent(parent);
				if (!isRoot(grand)) {
					makeRed(grand);
					resolveRed(grand);
				}
			}
		}
	}

	private void remedyDoubleBlack(Position<Entry<K, V>> p) {
		Position<Entry<K, V>> parent = parent(p);
		Position<Entry<K, V>> sibling = sibling(p);
		if (isBlack(sibling(p))) {
			if (isRed(left(sibling)) || isRed(right(sibling))) {
				Position<Entry<K, V>> temp;
				if (isRed(left(sibling))) {
					temp = left(sibling);
				} else {
					temp = right(sibling);
				}
				Position<Entry<K, V>> middle = restructure(temp);
				if (isRed(parent)) {
					makeRed(middle);
				} else {
					makeBlack(middle);
				}
				makeBlack(left(middle));
				makeBlack(right(middle));
			} else {
				makeRed(sibling);
				if (isRed(parent)) {
					makeBlack(parent);
				} else if (!isRoot(parent)) {
					remedyDoubleBlack(parent);
				}
			}
		} else {
			rotate(sibling);
			makeBlack(sibling);
			makeRed(parent);
			remedyDoubleBlack(p);
		}
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> p) {
		if (!isRoot(p)) {
			makeRed(p);
			resolveRed(p);
		}
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> p) {
		if (isRed(p)) {
			makeBlack(p);
		} else if (!isRoot(p)) {
			Position<Entry<K, V>> sib = sibling(p);
			if (isInternal(sib) && (isBlack(sib) || isInternal(left(sib)))) {
				remedyDoubleBlack(p);
			}
		}
	}
}
