package soc.pr2.application;

import java.io.IOException;
import java.util.List;

import soc.pr2.data.Node;
import soc.pr2.output.Component;
import soc.pr2.output.Export;

public class Utilities {

	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	public static String toString(List<Node> lNodes) {
		String stringOut = "";
		int i = 0;

		for (Node node : lNodes) {
			stringOut += node.toString();

			for (Node neighboringNode : node.getListNodes())
				stringOut += ";" + neighboringNode.toString();

			if (i < lNodes.size() - 1)
				stringOut += LINE_SEPARATOR;
			i++;
		}

		return stringOut;
	}

	public static void exportCSV(List<Node> lNodes) {
		Component cmp = new Component();
		String rute = cmp.generarSalida();
		Export exp = new Export();

		if (rute != null)
			try {
				exp.exportar(lNodes, rute);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
