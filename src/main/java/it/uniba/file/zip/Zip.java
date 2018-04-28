package it.uniba.file.zip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Zip {
	private ZipFile zipFile;
	
	public Zip(String zipFilePath) throws IOException{
		this.zipFile=new ZipFile(zipFilePath);
	}
	
	public String getFileContent(String fileName) throws IOException, FileNotInZipException{
		if(contains(fileName)) {
			InputStream stream = zipFile.getInputStream(zipFile.getEntry(fileName));
			BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();
		    	while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
		    	}
		    	br.close();
		    	return sb.toString();
		}else {
			throw new FileNotInZipException(fileName);
		}
	}

	public boolean contains(String fileName) {
		ZipEntry entry=(ZipEntry) zipFile.getEntry(fileName);
		if(entry!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void close() throws IOException {
		zipFile.close();
	}
}
