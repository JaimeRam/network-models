package soc.pr2.output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Export {
	public Export() {

	}

	public OutputStream exportar(String model, String ruta) throws IOException {
		OutputStream output = new FileOutputStream(ruta);

		try {
			output.write(model.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}
		output.close();
		return output;
	}

}