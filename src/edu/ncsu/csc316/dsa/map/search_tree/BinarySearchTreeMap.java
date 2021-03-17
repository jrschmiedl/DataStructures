package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractSortedMap;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.tree.BinaryTree;
import edu.ncsu.csc316.dsa.tree.LinkedBinaryTree;

/**
 * BinarySearchTreeMap
 * @author jrschmie Jacob Schmiedl
 *
 * @param <K> key
 * @param <V> value
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V>
        implements BinaryTree<Entry<K, V>> {

    /** Tree */
    private BalanceableBinaryTree<K, V> tree;

    /**
     * BinarySearchTreeMap constructor
     */
    public BinarySearchTreeMap() {
        this(null);
    }
    
    /**
     * Constructor with comparator
     * @param compare comparator
     */
    public BinarySearchTreeMap(Comparator<K> compare) {
        super(compare);
        tree = new BalanceableBinaryTree<K, V>();
        tree.addRoot(null);
    }

    @Override
    public int size() {
        // Our search trees will all use dummy/sentinel leaf nodes,
        // so the actual number of elements in the tree will be (size-1)/2      
        return (tree.size() - 1) / 2;
    }

    /**
     * ExpandLeaf method
     * @param p position
     * @param entry entry
     */
    private void expandLeaf(Position<Entry<K, V>> p, Entry<K, V> entry) {
        // initially, p is a dummy/sentinel node,
        // so replace the null entry with the new actual entry      
        tree.set(p, entry);
        // Then add new dummy/sentinel children     
        tree.addLeft(p, null);
        tree.addRight(p, null);
    }

    /**
     * Lookup method
     * @param p Position
     * @param key K
     * @return position
     */
    private Position<Entry<K, V>> lookUp(Position<Entry<K, V>> p, K key) {
        // If we have reached a dummy/sentinel node (a leaf), return that sentinel node
        if (isLeaf(p)) {
            return p;
        }
        int comp = compare(key, p.getElement().getKey());
        if (comp == 0) {
            // Return the position that contains the entry with the key         
            return p;
        } else if (comp < 0) {
            return lookUp(left(p), key);
        } else {
            return lookUp(right(p), key);
        }
    }

    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(tree.root(), key);
        // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use        
        actionOnAccess(p);
        if (isLeaf(p)) {
            return null;
        }
        return p.getElement().getValue();
    }

    @Override
    public V put(K key, V value) {
        // Create the new map entry     
        Entry<K, V> newEntry = new MapEntry<K, V>(key, value);
        // Get the last node visited when looking for the key       
        Position<Entry<K, V>> p = lookUp(root(), key);
        // If the last node visited is a dummy/sentinel node        
        if (isLeaf(p)) {
            expandLeaf(p, newEntry);
            // actionOnInsert is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnInsert(p);
            return null;
        } else {
            V original = p.getElement().getValue();
            set(p, newEntry);
            // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnAccess(p);
            return original;
        }
    }

    @Override
    public V remove(K key) {
        // Get the last node visited when looking for the key       
        Position<Entry<K, V>> p = lookUp(root(), key);
        // If p is a dummy/sentinel node        
        if (isLeaf(p)) {
            // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnAccess(p);
            return null;
        } else {
            V original = p.getElement().getValue();
            // If the node has two children (that are not dummy/sentinel nodes)         
            if (isInternal(left(p)) && isInternal(right(p))) {
                // Replace with the inorder successor               
                Position<Entry<K, V>> replacement = treeMin(right(p));
                set(p, replacement.getElement());
                // Move p to the replacement node in the right subtree              
                p = replacement;
            }
            // Get the dummy/sentinel node (in case the node has an actual entry as a child)...         
            Position<Entry<K, V>> leaf = (isLeaf(left(p)) ? left(p) : right(p));
            // ... then get its sibling (will be another sentinel or an actual entry node)          
            Position<Entry<K, V>> sib = sibling(leaf);
            // Remove the leaf NODE (this is your binary tree remove method)            
            remove(leaf);
            // Remove the NODE (this is your binary tree remove method)
            // which will "promote" the sib node to replace p           
            remove(p);
            // actionOnDelete is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnDelete(sib);
            return original;
        }
    }

    /**
     * Treemin method
     * @param node Position entry
     * @return position entry
     */
    private Position<Entry<K, V>> treeMin(Position<Entry<K, V>> node) {
        Position<Entry<K, V>> current = node;
        while (isInternal(current)) {
            current = left(current);
        }
        return parent(current);
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>(size());
        for (Position<Entry<K, V>> n : tree.inOrder()) {
            set.addLast(n.getElement());
        }
        return set;
    }

    @Override
    public String toString() {
        return tree.toString();
    }

    /**
     * actionOnAccess method
     * @param node position entry
     */
    protected void actionOnAccess(Position<Entry<K, V>> node) {
        // Do nothing for BST
    }

    /**
     * actionOnInsert method
     * @param node position entry
     */
    protected void actionOnInsert(Position<Entry<K, V>> node) {
        // Do nothing for BST
    }

    /**
     * actionOnDelete method
     * @param node position entry
     */
    protected void actionOnDelete(Position<Entry<K, V>> node) {
        // Do nothing for BST
    }
    
    /**
     * BlanaceableBinaryTree class
     * @author jrschmie Jacob Schmiedl
     *
     * @param <K> key
     * @param <V> value
     */
    protected static class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {

        /**
         * Relink
         * @param parent node entry
         * @param child node entry
         * @param makeLeftChild bool
         */
        private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild) {
            child.setParent(parent);
            if (makeLeftChild) {
            	parent.setLeft(child);
            } else {
            	parent.setRight(child);
            }
        }

        /**
         * Rotate
         * @param p position entry
         */
        public void rotate(Position<Entry<K, V>> p) {
            Node<Entry<K, V>> x = validate(p);
            Node<Entry<K, V>> y = x.getParent();
            Node<Entry<K, V>> z = y.getParent();
            if (z == null) {
            	setRoot(x);
            	x.setParent(null);
            } else {
            	if (y == z.getLeft()) {
            		relink(z, x, true);
            	} else {
            		relink(z, x, false);
            	}
            }
            if (x == y.getLeft()) {
            	relink(y, x.getRight(), true);
            	relink(x, y, false);
            } else {
            	relink(y, x.getLeft(), false);
            	relink(x, y, true);
            }
        }

        /**
         * Restructure method
         * @param x position entry
         * @return node position entry
         */
        public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        	Node<Entry<K, V>> node = validate(x);
        	Node<Entry<K, V>> parent = node.getParent();
        	Node<Entry<K, V>> grandParent = parent.getParent();
        	if ((node == parent.getRight()) == (parent == grandParent.getRight())) {
        		rotate(parent);
        		return parent;
        	} else {
        		rotate(node);
        		rotate(node);
        		return node;
        	}
        }

        @Override
        protected Node<Entry<K, V>> createNode(Entry<K, V> element, Node<Entry<K, V>> parent, Node<Entry<K, V>> left,
                Node<Entry<K, V>> right) {
            BSTNode<Entry<K, V>> newNode = new BSTNode<Entry<K, V>>(element);
            newNode.setParent(parent);
            newNode.setLeft(left);
            newNode.setRight(right);
            newNode.setProperty(0);
            return newNode;
        }

        /**
         * BSTNode class
         * @author jrschmie Jacob Schmiedl
         *
         * @param <E> data
         */
        protected static class BSTNode<E> extends Node<E> {
        	/** Property */
            private int property;
            
            /**
             * BSTNode constructor
             * @param element E
             */
            public BSTNode(E element) {
                super(element);
                setProperty(0);
            }
            
            /**
             * Setter for Property
             * @param height of property
             */
            public void setProperty(int height) {
                this.property = height;
            }
            
            /**
             * Getter for Property
             * @return property
             */
            public int getProperty() {
                return property;
            }
        }
        
        /**
         * Getter for Property
         * @param p position
         * @return property 
         */
        public int getProperty(Position<Entry<K, V>> p) {
            if (p == null) {
                return 0;
            }
            BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) p;
            return node.getProperty();
        }
        
        /**
         * Setter for Property
         * @param p position 
         * @param value int
         */
        public void setProperty(Position<Entry<K, V>> p, int value) {
            BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) (p);
            node.setProperty(value);
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // All the methods below delegate to the BalanceableBinaryTree class, which extends 
    // your linked binary tree implementation
    /////////////////////////////////////////////////////////////////////////////

    @Override
    public Position<Entry<K, V>> root() {
        return tree.root();
    }

    @Override
    public Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
        return tree.parent(p);
    }

    @Override
    public Iterable<Position<Entry<K, V>>> children(Position<Entry<K, V>> p) {
        return tree.children(p);
    }

    @Override
    public int numChildren(Position<Entry<K, V>> p) {
        return tree.numChildren(p);
    }

    @Override
    public boolean isInternal(Position<Entry<K, V>> p) {
        return tree.isInternal(p);
    }
    
    /**
     * Setter
     * @param p position
     * @param entry entry
     * @return entry
     */
    public Entry<K, V> set(Position<Entry<K, V>> p, Entry<K, V> entry) {
        return tree.set(p, entry);
    }

    @Override
    public boolean isLeaf(Position<Entry<K, V>> p) {
        return tree.isLeaf(p);
    }

    @Override
    public boolean isRoot(Position<Entry<K, V>> p) {
        return tree.isRoot(p);
    }

    @Override
    public Iterable<Position<Entry<K, V>>> preOrder() {
        return tree.preOrder();
    }

    @Override
    public Iterable<Position<Entry<K, V>>> postOrder() {
        return tree.postOrder();
    }

    @Override
    public Iterable<Position<Entry<K, V>>> levelOrder() {
        return tree.levelOrder();
    }

    @Override
    public Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
        return tree.left(p);
    }
    
    /**
     * Remove method
     * @param p position
     * @return entry
     */
    protected Entry<K, V> remove(Position<Entry<K, V>> p) {
        return tree.remove(p);
    }

    @Override
    public Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
        return tree.right(p);
    }

    @Override
    public Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
        return tree.sibling(p);
    }

    @Override
    public Iterable<Position<Entry<K, V>>> inOrder() {
        return tree.inOrder();
    }
    
    /**
     * Rotate method
     * @param p position
     */
    protected void rotate(Position<Entry<K, V>> p) {
        tree.rotate(p);
    }

    /**
     * Restructure method
     * @param x position entry
     * @return tree node
     */
    protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        return tree.restructure(x);
    }

    /**
     * Getter for Property
     * @param p position
     * @return property
     */
    public int getProperty(Position<Entry<K, V>> p) {
        return tree.getProperty(p);
    }
    
    /**
     * Setter for Property
     * @param p position
     * @param value value
     */
    public void setProperty(Position<Entry<K, V>> p, int value) {
        tree.setProperty(p, value);
    }
}