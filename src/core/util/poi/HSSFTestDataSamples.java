package core.util.poi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import core.util.poi.POIDataSamples;

/**
 * Centralises logic for finding/opening sample files in the
 * src/testcases/org/apache/poi/hssf/hssf/data folder.
 *
 * @author Josh Micich
 */
public final class HSSFTestDataSamples {

	private static final POIDataSamples _inst = POIDataSamples.getSpreadSheetInstance();

	public static InputStream openSampleFileStream(String sampleFileName) {
		return _inst.openResourceAsStream(sampleFileName);
	}

	public static File getSampleFile(String sampleFileName) {
		return _inst.getFile(sampleFileName);
	}

	public static byte[] getTestDataFileContent(String fileName) {
		return _inst.readFile(fileName);
	}

	public static HSSFWorkbook openSampleWorkbook(String sampleFileName) {
		try {
			return new HSSFWorkbook(_inst.openResourceAsStream(sampleFileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Writes a spreadsheet to a <tt>ByteArrayOutputStream</tt> and reads it
	 * back from a <tt>ByteArrayInputStream</tt>.
	 * <p/>
	 * Useful for verifying that the serialisation round trip
	 */
	public static HSSFWorkbook writeOutAndReadBack(HSSFWorkbook original) {

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
			original.write(baos);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			return new HSSFWorkbook(bais);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
