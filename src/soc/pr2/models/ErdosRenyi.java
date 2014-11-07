package soc.pr2.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import soc.pr2.application.Utilities;
import soc.pr2.data.Node;

public class ErdosRenyi {

	private long N;
	private float p;
	private List<Node> listNodes;

	public ErdosRenyi(long numberOfNodes, float probability) {
		super();
		N = numberOfNodes;
		p = probability;
		initialize();
	}

	private void initialize() {
		listNodes = new ArrayList<Node>();

		for (long i = 0; i < N; i++)
			listNodes.add(new Node());
	}

	public void generate() {
		for (Node masterNode : listNodes) {
			for (Node neighboringNode : listNodes) {

				if (masterNode.equals(neighboringNode))
					continue;

				Random number = new Random();
				float probability = number.nextFloat();

				if (probability <= p) {
					masterNode.addAdjacentNode(neighboringNode);
					masterNode.increaseDegree();
					neighboringNode.increaseDegree();
				}
			}
		}
	}

	public String toString() {
		return Utilities.toString(listNodes);
	}
}