package soc.pr2.output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import soc.pr2.application.Utilities;
import soc.pr2.data.Node;

public class Export {
	public Export() {

	}

	public OutputStream exportar(List<Node> lNodes, String ruta)
			throws IOException {
		OutputStream output = new FileOutputStream(ruta);
		int i = 0;

		try {
			for (Node masterNode : lNodes) {
				output.write(masterNode.toString().getBytes());
				for (Node auxNode : masterNode.getListNodes())
					output.write((";" + auxNode.toString()).getBytes());

				if (i < lNodes.size() - 1)
					output.write(Utilities.LINE_SEPARATOR.getBytes());
				i++;
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		output.close();
		return output;
	}

}