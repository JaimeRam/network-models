package soc.pr2.data;

import java.util.ArrayList;
import java.util.List;

public class Node {

	public static long ID_COUNT = 0;

	private long node_id;
	private long degree;
	private List<Node> listNodes;

	public Node() {
		ID_COUNT++;
		node_id = ID_COUNT;
		degree = 0;
		listNodes = new ArrayList<Node>();
	}

	public long getNode_id() {
		return node_id;
	}

	public boolean equals(Node node) {
		return this.node_id == node.node_id;
	}

	public void increaseDegree() {
		degree++;
	}

	public String toString() {
		return Long.toString(node_id);
	}

	public long getDegree() {
		return degree;
	}

	public void addAdjacentNode(Node node) {
		listNodes.add(node);
	}

	public List<Node> getListNodes() {
		return listNodes;
	}

	public boolean contains(Node node) {
		return listNodes.contains(node);
	}

}
