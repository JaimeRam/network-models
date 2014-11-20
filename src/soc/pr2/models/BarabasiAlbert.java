package soc.pr2.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import soc.pr2.application.Utilities;
import soc.pr2.data.Node;

public class BarabasiAlbert extends SwingWorker<Void, Integer> {

	private long m;
	private long t;
	private List<Node> listNodes;
	private JTextArea statusLabel;
	public static double PROBABILITY = 0;
	public static long TOTAL_DEGREE = 0;

	public BarabasiAlbert(long m, int t, JTextArea statusLabel) {
		super();
		this.m = m;
		this.t = t;
		this.statusLabel = statusLabel;
		listNodes = new ArrayList<Node>();
		initialize();
	}

	private void initialize() {

		for (long i = 0; i < m; i++)
			listNodes.add(new Node());

		for (int i = 0; i < listNodes.size(); i++) {
			Node masterNode = listNodes.get(i);
			for (int j = i + 1; j < listNodes.size(); j++) {
				Node neighboringNode = listNodes.get(j);
				masterNode.addAdjacentNode(neighboringNode);
				neighboringNode.addAdjacentNode(masterNode);
				masterNode.increaseDegree();
				neighboringNode.increaseDegree();
				TOTAL_DEGREE += 2;
			}
		}
	}

	@Override
	protected Void doInBackground() throws Exception {

		for (int i = 0; i < t; i++) {
			long addedEdge = 0, j = 0;
			Node newNode = new Node();

			while (addedEdge < m) {
				int index = (int) (j % listNodes.size());
				Node neighboringNode = listNodes.get(index);

				if (!newNode.contains(neighboringNode)) {
					Random number = new Random();
					float probability = number.nextFloat();

					if (probability <= neighboringNode.getDegree()
							/ TOTAL_DEGREE) {
						newNode.addAdjacentNode(neighboringNode);
						neighboringNode.addAdjacentNode(newNode);
						newNode.increaseDegree();
						neighboringNode.increaseDegree();
						addedEdge++;
					}
				}
				j++;
			}
			TOTAL_DEGREE += m * 2;
			listNodes.add(newNode);
			float progress = (((float) i + 1) / (float) t) * 100;
			publish((int) progress);
		}
		Utilities.exportCSV(listNodes);
		Node.ID_COUNT = 0; // Reiniciamos el contador de nodos para las
							// siguientes ejecuciones
		return null;
	}

	protected void process(List<Integer> chunks) {
		statusLabel.setText(Integer.toString(chunks.get(chunks.size() - 1))
				+ "% completado");
	}

	public List<Node> getListNodes() {
		return listNodes;
	}
}