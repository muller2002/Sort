
import java.util.Arrays;
import java.util.Vector;

public class BinaryTree {
	Vector<Integer> tempOut = new Vector<Integer>();
	Node root;
	
	public void clear() {
		root = null;
	}
	
	public void addNode(int key) {

		Node newNode = new Node(key);


		if (root == null) {//Falls kein Root vorhanden ist wird hier eine erstellt und abgebrochen

			root = newNode;
			return;
		}

		// root als start Node festlegen

		Node focusNode = root;

		// parant Node als "Vor"- Node festlegen, wenn zurückgegangen wird

		Node parent;

		while (true) { // Break Condition beachten


			parent = focusNode;	// Focus node wird als parent node festgelegt

			// Check, ob nach links geht
			
			if (key < focusNode.key) {

				// focus Node wird als linke node festgelegt

				focusNode = focusNode.leftChild;

				// check, ob focus Node kinder hat

				if (focusNode == null) {

					// wenn nein: wert Schreiben, break

					parent.leftChild = newNode;
					return; // Break Condition erreicht

				}

			} else { // wenn node nicht nach links, dann muss nch rechts

				focusNode = focusNode.rightChild;

				// wenn child kein Wert, dann schreiben

				if (focusNode == null) {

					// wenn nein: wert Schreiben, break

					parent.rightChild = newNode;
					return; // Break condition

				}

			}

		}

	}
	public Integer[] sortedInteger() {
		tempOut.clear();
		sort(root, 0);
		return (Integer[])(tempOut.toArray(new Integer[0]));
	}
	public int[] sortedInt() {
	
		return Arrays.stream(sortedInteger()).mapToInt(Integer::intValue).toArray();
	}
	public Vector<Integer> sortedVector() {
		tempOut.clear();
		sort(root, 0);
		return tempOut;
	}
	//Krasser Rekursions scheiss
	private void sort(Node focusNode, int i) {
		if (focusNode != null) {
			i++;
			sort(focusNode.leftChild, i);
			tempOut.add(focusNode.toInt());
			System.out.println(focusNode.test());
			i++;
			sort(focusNode.rightChild, i);

		}

	}

}
class Node {

	int key;
	Node leftChild;
	Node rightChild;
	
	Node(int key) {

		this.key = key;

	}
	
	public String test() {
		return "test";
	}
	
	public int toInt() {
		return key;
	}
	
	public void delete() {
		if(leftChild != null) {
			leftChild.delete();
			leftChild = null;
		}
		if(rightChild != null) {
			rightChild.delete();
			rightChild = null;
		}
	}

}