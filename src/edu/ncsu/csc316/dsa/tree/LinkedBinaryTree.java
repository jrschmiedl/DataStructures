package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;

/**
 * LinkedBinaryTree class
 * @author jrschmie Jacob Schmiedl
 *
 * @param <E> data
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
	
	/** Root node */
    private Node<E> root;
    
    /** Size */ 
    private int size;
    
    /**
     * LinkedBinaryTree constructor
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * Validate method
     * @param p position
     * @return node
     */
    protected Node<E> validate(Position<E> p) {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a valid linked binary node");
        }
        return (Node<E>) p;
    }
    
    /**
     * Node class
     * @author jrschmie Jacob Schmiedl
     *
     * @param <E> data
     */
    public static class Node<E> extends AbstractNode<E> {
    	
    	/** Parent node */
        private Node<E> parent;

    	/** Left node */
        private Node<E> left;

    	/** Right node */
        private Node<E> right;
        
        /**
         * Node constructor
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
        }

        /**
         * Getter Left method
         * @return left
         */
        public Node<E> getLeft() {
            return left;
        }
        
        /**
         * Getter right method
         * @return right
         */
        public Node<E> getRight() {
            return right;
        }
        
        /**
         * Setter left method
         * @param left node
         */
        public void setLeft(Node<E> left) {
            this.left = left;
        }
        
        /**
         * Setter right method
         * @param right node
         */
        public void setRight(Node<E> right) {
            this.right = right;
        }

        /**
         * Getter parent method
         * @return parent
         */
        public Node<E> getParent() {
            return parent;
        }
        
        /**
         * Setter parent method
         * @param parent node
         */
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
    }

    @Override
    public Position<E> left(Position<E> p) {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) {
        Node<E> node = validate(p);
        return node.getRight();
    }

    @Override
    public Position<E> addLeft(Position<E> p, E value) {
        Node<E> node = validate(p);
        if (left(node) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        Node<E> leftNode = createNode(value, node, null, null);
        node.setLeft(leftNode);
        size++;
		return leftNode;
    }

    @Override
    public Position<E> addRight(Position<E> p, E value) {
        Node<E> node = validate(p);
        if (right(node) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        Node<E> rightNode = createNode(value, node, null, null);
        node.setRight(rightNode);
        size++;
        return rightNode;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> addRoot(E value) {
        if (root() != null) {
            throw new IllegalArgumentException("The tree already has a root.");
        }
        this.root = createNode(value, null, null, null);
        size++;
        return root;
    }

    @Override
    public E remove(Position<E> p) {
        if (numChildren(p) == 2){
            throw new IllegalArgumentException("The node has two children");
        }
        Node<E> node = validate(p);
        
        Node<E> child = null;
        if (node.getLeft() != null) {
        	child = node.getLeft();
        } else {
        	child = node.getRight();
        }
        if (child != null) {
        	child.setParent(node.getParent());
        }
        if (node == root) {
        	root = child;
        } else {
        	Node<E> parent = node.getParent();
        	if (node == parent.getLeft()) {
        		parent.setLeft(child);
        	} else {
        		parent.setRight(child);
        	}
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * CreateNode Method
     * @param e E
     * @param parent Node
     * @param left Node
     * @param right Node
     * @return newNode
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        Node<E> newNode = new Node<E>(e);
        newNode.setParent(parent);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    /**
     * SetRoot method
     * @param p position
     * @return root
     */
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }

	@Override
	public Iterable<Position<E>> preOrder() {
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
        for (Position<E> c : children(p)) {
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
			for(Position<E> child : children(newP)) {
				queue.enqueue(child);
			}
		}
        return list;
	}
}