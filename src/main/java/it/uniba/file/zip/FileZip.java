package it.uniba.file.zip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * Classe che modella un file zip.
 */
public final class FileZip {
	/**
	 * Zip file.
	 */
	private final ZipFile zipFile;

	/**
	 * Metodo costruttore della classe Zip.
	 * 
	 * @param zipFilePath
	 *            String che rappresenta il percorso dello zip file.
	 * @throws IOException
	 *             Lancia un' eccezone quando si verificano errori di input/output.
	 */
	public FileZip(final String zipFilePath) throws IOException {
		this.zipFile = new ZipFile(zipFilePath);
	}

	/**
	 * Restituisce il contenuto dello zip.
	 * 
	 * @param fileName
	 *            String che rappresenta il file.
	 * @return String che rappresenta il contenuto del file.
	 * @throws IOException
	 *             Lancia un'eccezione quando si verifica un errore di input/output.
	 * @throws FileNotInZipException
	 *             Lancia un'eccezione quando il file specificato non è nello zip
	 *             file.
	 * @throws ZipException
	 *             Lancia un'eccezione quando lo zip file specificato non è valido.
	 */
	public String getFileContent(final String fileName) throws IOException, FileNotInZipException, ZipException {
		if (contains(fileName)) {
			final InputStream stream = zipFile.getInputStream(zipFile.getEntry(fileName));
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			final StringBuilder stringBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			while (line != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			return stringBuilder.toString();
		} else {
			throw new FileNotInZipException(fileName);
		}
	}

	/**
	 * Verifica se un file è presente nello zip.
	 * 
	 * @param fileName
	 *            String che rappresenta uno specifico file.
	 * @return boolean che risulta essere true se il file specificato appartiene
	 *         allo zip, false altrimenti.
	 */
	public boolean contains(final String fileName) {
		final ZipEntry entry = (ZipEntry) zipFile.getEntry(fileName);
		return entry != null;
	}

	/**
	 * Chiude lo zip file.
	 * 
	 * @throws IOException
	 *             Lancia un'eccezione quando si verificano errori di input/output.
	 */
	public void close() throws IOException {
		zipFile.close();
	}

	/**
	 * Restituisce il riferimento allo zip file. *
	 * 
	 * @return riferimento allo zip file.
	 */
	public ZipFile getZipFile() {
		return zipFile;
	}
}
