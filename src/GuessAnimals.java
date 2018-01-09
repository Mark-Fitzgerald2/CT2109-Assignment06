import javax.swing.JOptionPane;

/**
 * Michael Madden, 2012-2014.
 * Program to play "What animal are you thinking of?". Uses a binary tree.
 * Each internal node is a yes/no question. Yes goes to left side, No goes to right side.
 * Each leaf is an answer. If the answer is wrong, ask a new question for which the wrong
 * guess is no and the right answer is yes.
 */
public class GuessAnimals 
{
	public static void main(String[] args) 
	{
		String answer;
		BinaryTree<String> animals = createTree("Is it a bird?", "Penguin", "Bear");
		
		while (true)
		{
			JOptionPane.showMessageDialog(null, "Think of an animal. I will try to guess it ...");

			BinaryNodeInterface<String> currentNode = animals.getRootNode();
			while (! currentNode.isLeaf())
			{
				// Ask the question
				answer = JOptionPane.showInputDialog(currentNode.getData());
				if (answer.toLowerCase().startsWith("y")) {
					currentNode = currentNode.getLeftChild();
				}
				else {
					currentNode = currentNode.getRightChild();				
				}
			}

			// At the leaf: got an answer (an animal) that might be wrong
			answer = JOptionPane.showInputDialog("Is it a " + currentNode.getData() + "?");
			if (answer.toLowerCase().startsWith("y")) {
				JOptionPane.showMessageDialog(null, "Great!! Let's play again.");
			}
			else {
				// The answer was wrong. I need to expand my tree.
				String newAnimal = JOptionPane.showInputDialog("I don't know. What is the right answer?");
				String oldAnimal = currentNode.getData();
				String newQn = JOptionPane.showInputDialog("Enter a question for which the answer is YES for "
						+ newAnimal + " and NO for " + oldAnimal);
				
				// Get rid of surrounding spaces
				newQn= newQn.trim();
				// Tack on a question mark if one is missing
				if (! newQn.endsWith("?"))
					newQn += "?";
				
				// Replace the current node with this question, and add left & right children with the answers
				currentNode.setData(newQn);
				currentNode.setLeftChild(new BinaryNode<String>(newAnimal));
				currentNode.setRightChild(new BinaryNode<String>(oldAnimal));
			}
		}
	}
	
	public static BinaryTree<String> createTree(String qn, String yesAns, String noAns)
	{
		// Example of a tree:
		// Is it a bird? left child is "Bear", right child is "Penguin".
		BinaryTree<String> left = new BinaryTree<String>(yesAns);
		BinaryTree<String> rght = new BinaryTree<String>(noAns);
		BinaryTree<String> tree = new BinaryTree<String>(qn, left, rght);
		return tree;
	}

}
