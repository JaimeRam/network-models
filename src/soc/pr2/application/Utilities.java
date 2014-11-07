package soc.pr2.application;

import java.util.List;

import soc.pr2.data.Node;

public class Utilities {

	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	public static void getCSV() {

	}

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
}
