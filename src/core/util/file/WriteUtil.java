package core.util.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WriteUtil {
	public static void write(InputStream[] ins, OutputStream out, int buffersize) throws IOException {
		InputStream[] arrayOfInputStream = ins;
		int j = ins.length;
		for (int i = 0; i < j; ++i) {
			InputStream in = arrayOfInputStream[i];
			write(in, out, buffersize);
		}
	}

	public static void write(InputStream in, OutputStream out, int buffersize) throws IOException {
		byte[] bytes = new byte[buffersize];
		int readLength = 0;
		while ((readLength = in.read(bytes)) > 0)
			out.write(bytes, 0, readLength);
	}
}