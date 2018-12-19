package br.senac.oracle.ex01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClassGenerator {

	public static void main(String[] args) {
		
		File directory = new File("target" + File.separator + "metaspace");
		if (!directory.exists()) {
			directory.mkdirs();
		}

		for (int i = 0; i < 1000; i++) {
			String className = "Hello" + String.format("%04d", i);

			File sourceFile = new File(directory, className + ".java");
			FileWriter writer = null;
			try {
				writer = new FileWriter(sourceFile);
				writer.write(
						"public class " + className + " {" + System.lineSeparator() 
						+ "\tpublic static void main(String[] args) {" + System.lineSeparator()
						+ "\t\tSystem.out.println(\"Hello world " + String.format("%04d", i) + "\");" + System.lineSeparator()
						+ "\t}" + System.lineSeparator()
						+ "}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
