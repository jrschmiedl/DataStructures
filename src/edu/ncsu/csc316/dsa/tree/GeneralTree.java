package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;

/**
 * General Tree class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> data
 */
public class GeneralTree<E> extends AbstractTree<E> implements GeneralTreeCollection<E> {
    /** Node root */
    private Node<E> root;
    
    /** Size */
    private int size;
    
    /**
     * General Tree Constructor
     */
    public GeneralTree() {
        root = null;
        size = 0;
    }
    
    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        return validate(p).getParent();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        Node<E> node = validate(p);
        List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
        for(Position<Node<E>> n : node.getChildren().positions())
        {
            ret.addLast(n.getElement());
        }
        return ret;
    }

    @Override
    public int numChildren(Position<E> p) {
        Node<E> node = validate(p);
        return node.getChildren().size();
    }
    
    @Override
    public Position<E> addRoot(E value) {
        if (root != null) {
            throw new IllegalArgumentException("Tree already has a root");
        }
        this.root = createNode(value);
        size = 1;
        return root;
    }   

    @Override
    public Position<E> addChild(Position<E> p, E value) {
        Node<E> node = validate(p);
        Node<E> newNode = createNode(value);
        node.getChildren().addLast(newNode);
        newNode.setParent(node);
        size++;
        return newNode;
    }

    @Override
    public E set(Position<E> p, E value) {
        Node<E> node = validate(p);
        E original = node.getElement();
        node.setElement(value);
        return original;
    }
    
    /**
     * Create Node method
     * @param element E
     * @return node
     */
    public Node<E> createNode(E element) {
        return new Node<E>(element);
    }
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterable<Position<E>> preOrder()
    {
        // You can use any list data structure here that supports
        // O(1) addLast
        List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
	/**
	 * PreOrderHelper method
	 * @param p position
	 * @param traversal list
	 */
    private void preOrderHelper(Position<E> p, List<Position<E>> traversal) {
        traversal.addLast(p);
        for(Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
    } 

	@Override
	public Iterable<Position<E>> postOrder() {
		List<Position<E>> list = new SinglyLinkedList<Position<E>>();
		if (!isEmpty()) {
			postOrderHelper(root(), list);
		}
		return list;
	}
	
	/**
	 * PostOrderHelper method
	 * @param p position
	 * @param list list
	 */
	private void postOrderHelper(Position<E> p, List<Position<E>> list) {
        for(Position<E> c : children(p)) {
            postOrderHelper(c, list);
        }
        list.addLast(p);
	}

	@Override
	public Iterable<Position<E>> levelOrder() {
		List<Position<E>> list = new SinglyLinkedList<Position<E>>();
		ArrayBasedQueue<Position<E>> queue = new ArrayBasedQueue<Position<E>>();
		Position<E> pRoot = root();
		if (pRoot == null) {
			return list;
		}
		queue.enqueue(pRoot);
		while (!queue.isEmpty()) {
			Position<E> newP = queue.dequeue();
			list.addLast(newP);
			for (Position<E> child : children(newP)) {
				queue.enqueue(child);
			}
		}
		return list;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator(preOrder().iterator());
	}

	@Override
	public E remove(Position<E> p) {
		// We will only support removal of a node that only has 1 child
	    if (numChildren(p) > 1) {
	        throw new IllegalArgumentException("The node has more than 1 child.");
	    }
	    // Handle special case if the node being removed is the root
	    if (isRoot(p)) {
	        E original = p.getElement();
	        if (numChildren(p) == 0) {
	            this.root = null;
	        } else {
	            Node<E> replacement = validate(p).getChildren().first().getElement();
	            replacement.setParent(null);
	            this.root = replacement;
	        }
	        size--;
	        return original;
	    }
	    // Handle the case where the node being removed is NOT the root
	    Node<E> node = validate(p);
	    Node<E> parent = validate(parent(p));
	    // Create an iterator over the parent node's children positions
	    Iterator<Position<Node<E>>> it = parent.getChildren().positions().iterator();
	    while (it.hasNext()) {
	        // Find the position of the node to be removed
	        Position<Node<E>> current = it.next();
	        if (current.getElement() == node) {
	            if (numChildren(p) == 1) {
	                // If the node being removed has 1 child, replace it
	                // in the parent's list of children
	                Node<E> replacement = node.getChildren().first().getElement();
	                replacement.setParent(parent);
	                parent.getChildren().set(current, replacement);
	            } else {
	                // If the node being removed has 0 children, remove it
	                parent.getChildren().remove(current);
	            }
	        }
	    }
	    size--;
	    return node.getElement();
	}
	
	/**
	 * Node class
	 * @author jrschmie Jacob Schmiedl
	 *
	 * @param <E> data
	 */
	private static class Node<E> extends AbstractNode<E> {
		/** Parent Node */
        private Node<E> parent;
        
        /** Children */
        private PositionalList<Node<E>> children;
        
        /**
         * Node Constructor
         * @param element E
         */
        public Node(E element) {
            this(element, null);
        }
        
        /**
         * Node Constructor with parent
         * @param element E
         * @param parent node
         */
        public Node(E element, Node<E> parent) {
            super(element);
            setParent(parent);
            children = new PositionalLinkedList<Node<E>>();
        }
        
        /**
         * Setter for parent
         * @param p node
         */
        public void setParent(Node<E> p) {
            parent = p;
        }
        
        /**
         * Getter for Parent
         * @return parent
         */
        public Node<E> getParent() {
            return parent;
        }
        
        /**
         * GetChildren method
         * @return children
         */
        public PositionalList<Node<E>> getChildren() {
            return children;
        }
    }
	/**
	 * Validate method
	 * @param p position
	 * @return node
	 */
	private Node<E> validate(Position<E> p) {
        if(!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a legal general tree node");
        }
        return (Node<E>)p;
    }
}