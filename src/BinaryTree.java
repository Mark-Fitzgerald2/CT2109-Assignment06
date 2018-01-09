/** * A class that implements the ADT binary tree.
 *  * @author Frank M. Carrano * @version 2.0 */

public class BinaryTree<T> implements BinaryTreeInterface<T>, java.io.Serializable{
	private static final long serialVersionUID = -1932834476575953631L;
	private BinaryNodeInterface<T> root;
	private static int counter = 0;	
	public BinaryTree()
	{
		root = null;
	} 

	public BinaryTree(T rootData)
	{
		root = new BinaryNode<T>(rootData);
	} 

	public BinaryTree(T rootData, BinaryTree<T> leftTree,
 			BinaryTree<T> rightTree)
	{
		privateSetTree(rootData, leftTree, rightTree);
	} 

	public void setTree(T rootData)
	{
		root = new BinaryNode<T>(rootData);
	} 

	public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
			BinaryTreeInterface<T> rightTree)
	{
		privateSetTree(rootData, (BinaryTree<T>)leftTree,
 				(BinaryTree<T>)rightTree);
	} 

	private void privateSetTree(T rootData, BinaryTree<T> leftTree,
 			BinaryTree<T> rightTree)
	{
		root = new BinaryNode<T>(rootData);

		if ((leftTree != null) && !leftTree.isEmpty())
			root.setLeftChild(leftTree.root);

		if ((rightTree != null) && !rightTree.isEmpty())
		{
			if (rightTree != leftTree)
				root.setRightChild(rightTree.root);
			else
				root.setRightChild(rightTree.root.copy());
		} 

		if ((leftTree != null) && (leftTree != this))
			leftTree.clear();

 		if ((rightTree != null) && (rightTree != this))
			rightTree.clear();
	}

	private BinaryNode<T> copyNodes() // not essential
	{
		return (BinaryNode<T>)root.copy();
	} 

	public T getRootData()
	{
		T rootData = null;
		if (root != null)
			rootData = root.getData();
		return rootData;
	} 

	public boolean isEmpty()
	{
		return root == null;
	} 

	public void clear()
	{
		root = null;
	} 

	protected void setRootData(T rootData)
	{
		root.setData(rootData);
	} 

	protected void setRootNode(BinaryNodeInterface<T> rootNode)
	{
		root = rootNode;
	} 

	protected BinaryNodeInterface<T> getRootNode()
	{
		return root;
	} 

	public int getHeight()
	{
		return root.getHeight();
	} 

	public int getNumberOfNodes()
	{
		return root.getNumberOfNodes();
	} 

	public void inorderTraverse()
 	{
		inorderTraverse(root);
	}

	private void inorderTraverse(BinaryNodeInterface<T> node)
 	{
		if (node != null)
		{
			inorderTraverse(node.getLeftChild());
			System.out.println(node.getData());
			inorderTraverse(node.getRightChild());
		} 
	}	public void preorderTraverse() 	{				preorderTraverse(root);			}	private void preorderTraverse(BinaryNodeInterface<T> node) 	{				if (node != null) //stops printing null values		{			System.out.println(node.getData()); 			//prints out the current node before continuing			preorderTraverse(node.getLeftChild());			//attempts to check if there is a left child first			//uses recursion to print this out if the node exist			preorderTraverse(node.getRightChild());			//checks if there is a right child after left child		} 			}	public void postorderTraverse() 	{				postorderTraverse(root);			}	private void postorderTraverse(BinaryNodeInterface<T> node) 	{				if (node != null) //makes sure a null node isn't printed		{			postorderTraverse(node.getLeftChild());			//continues to check if there is a left child first			//this will get us to the bottom left of the tree 			//when it is at the bottom left there will be no children			//so it skips to printing			postorderTraverse(node.getRightChild());			//this will only be used when there is no more children to the left			//this will help give us the desired path			System.out.println(node.getData());			//the printing is the last part of the method			//this is so that the nodes at the smaller heights are printed first		} 			}	public void breadthFirst() 	{				breadthFirst(root);			}	@SuppressWarnings("unchecked")	private void breadthFirst(BinaryNodeInterface<T> node) {		Queue q = new ArrayQueue(); 		//create a queue		if (node == null){	        return; //prevents a null node being printed		}		while(! q.isEmpty()) { 	    	//loops until the queue is empty	    	q.dequeue();	    	//calls the dequeue method	    	//makes sure there isn't already nodes in the queue	    }	    q.enqueue(root); //enqueues the root 	    while(!q.isEmpty()){	    	//loops until the queue has been emptied	        node = (BinaryNodeInterface<T>) q.dequeue();	        //stores dequeued node	        System.out.println(node.getData());	        //prints the dequeued node	        if(node.getLeftChild() != null){	        	q.enqueue(node.getLeftChild());	        }	        //checks if there is a left child and adds it to the queue	        if(node.getRightChild() != null){	        	q.enqueue(node.getRightChild());	        }	        //checks if there is a right child and adds it to the queue	    }	} 	public BinaryTree<String> createRecursiveTree(int height)	{				counter++;		//creates counter value to add to the string stored		String string = "Node Number : " + counter + " Height: " + height; 		//creates a string to add to the tree		BinaryTree<String> tree;		//creates a reference to a tree		if (height <= 1) {			//checks if we are at the lowest point in the tree			tree = new BinaryTree<String>(string, null, null);			//adds this string to the tree			//the two nulls are used to prevent the method from being called again		}		else {			//otherwise it will carry out this 			tree = new BinaryTree<String>(string, 					createRecursiveTree(height-1), createRecursiveTree(height-1));			//stores the string			//then calls the method again by passing in the height minus one			//this shows we have gotten closer to the bottom of the tree diagram					}				return tree;		//returns the tree	}

} 