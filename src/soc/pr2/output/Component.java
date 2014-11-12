package soc.pr2.output;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Component {

	public Component() {

	}

	@SuppressWarnings("static-access")
	public String generarSalida() {
		int option = 0;
		String ruta = null;
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv",
				"csv");
		fileChooser.setFileFilter(filter);
		int checker = fileChooser.showSaveDialog(null);

		if (checker == fileChooser.APPROVE_OPTION) {

			ruta = fileChooser.getSelectedFile().getAbsolutePath();
			if (!ruta.endsWith(".csv"))
				ruta += ".csv";

			if (new File(ruta).exists()) {
				option = JOptionPane.showConfirmDialog(null,
						"El fichero existe ¿deseas reemplazarlo?");
				while (JOptionPane.NO_OPTION == option) {
					fileChooser.setVisible(true);
					int checker2 = fileChooser.showSaveDialog(null);
					if (checker2 == fileChooser.APPROVE_OPTION) {

						ruta = fileChooser.getSelectedFile().getAbsolutePath();
						if (!ruta.endsWith(".csv"))
							ruta += ".csv";
						if (new File(ruta).exists())
							option = JOptionPane.showConfirmDialog(null,
									"El fichero existe ¿deseas reemplazarlo?");
						else
							option = JOptionPane.OK_OPTION;
					}
				}
			}

		} else {

			JOptionPane.showMessageDialog(null, "Tienes que escoger una ruta");

		}
		fileChooser.setVisible(false);

		return ruta;
	}

}
