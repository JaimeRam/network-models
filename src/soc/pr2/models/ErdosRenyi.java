package soc.pr2.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.SwingWorker;

import soc.pr2.application.Interfaz;
import soc.pr2.application.Utilities;
import soc.pr2.data.Node;

public class ErdosRenyi extends SwingWorker<Void, Integer> {

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

	public List<Node> getListNodes() {
		return listNodes;
	}

	@Override
	protected Void doInBackground() throws Exception {
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
			publish((int) progress);
		}
		Utilities.exportCSV(listNodes);
		Node.ID_COUNT = 0; // Reiniciamos el contador de nodos para las
							// siguientes ejecuciones
		return null;
	}

	protected void process(List<Integer> chunks) {
		Interfaz.setStatusProgress(chunks.get(chunks.size() - 1));
	}
}