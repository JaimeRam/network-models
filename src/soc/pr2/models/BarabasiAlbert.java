package soc.pr2.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import soc.pr2.application.Utilities;
import soc.pr2.data.Node;

public class BarabasiAlbert {

	private long m;
	private int t;
	private List<Node> listNodes;
	public static double PROBABILITY = 0;
	public static long TOTAL_DEGREE = 0;

	public BarabasiAlbert(long m, int t) {
		super();
		this.m = m;
		this.t = t;
		listNodes = new ArrayList<Node>();
		initialize();
	}

	private void initialize() {

		for (long i = 0; i < m; i++)
			listNodes.add(new Node());

		for (Node masterNode : listNodes) {

			for (Node neighboringNode : listNodes) {

				if (masterNode.equals(neighboringNode))
					continue;

				masterNode.addAdjacentNode(neighboringNode);
				masterNode.increaseDegree();
				neighboringNode.increaseDegree();
				TOTAL_DEGREE += 2;
			}
		}
	}

	public void generate() {

		for (int i = 0; i < t; i++) {
			long addedEdge = 0;
			Node newNode = new Node();

			while (addedEdge < this.m) {

				for (Node neighboringNode : listNodes) {

					if (this.m == addedEdge)
						break;

					if (newNode.contains(neighboringNode))
						continue;

					Random number = new Random();
					float probability = number.nextFloat();

					if (probability <= neighboringNode.getDegree()
							/ TOTAL_DEGREE) {
						newNode.addAdjacentNode(neighboringNode);
						neighboringNode.addAdjacentNode(newNode);
						newNode.increaseDegree();
						neighboringNode.increaseDegree();

						TOTAL_DEGREE += 2;
						addedEdge++;
					}
				}
			}
			listNodes.add(newNode);
			addedEdge = 0;
		}
	}

	public String toString() {
		return Utilities.toString(listNodes);
	}
}
