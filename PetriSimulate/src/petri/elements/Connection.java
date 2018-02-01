package petri.elements;

import petri.node.Node;

public class Connection {

	private Node NextNode;
	/**
	 * @return the nextNode
	 */
	public Node getNextNode() {
		return NextNode;
	}
	public Connection(Node NextNode) {
		this.NextNode = NextNode;
	}
	
}
