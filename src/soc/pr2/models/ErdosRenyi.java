package soc.pr2.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import soc.pr2.application.Utilities;
import soc.pr2.data.Node;

public class ErdosRenyi implements Runnable {

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

	public void run() {
		generate();
	}

	private void generate() {
		for (int i = 0; i < N; i++) {
			Node masterNode = listNodes.get(i);
			for (int j = i + 1; j < listNodes.size(); j++) {

				Node neighboringNode = listNodes.get(j);

				Random number = new Random();
				float probability = number.nextFloat();

				if (probability <= p)
					masterNode.addAdjacentNode(neighboringNode);
			}
			float progress = (((float) i + 1) / (float) N) * 100;
			System.out.println(progress + "% completado");
		}
		Utilities.exportCSV(listNodes);
		Node.ID_COUNT = 0; // Reiniciamos el contador de nodos para las
							// siguientes ejecuciones
	}

	public List<Node> getListNodes() {
		return listNodes;
	}
}