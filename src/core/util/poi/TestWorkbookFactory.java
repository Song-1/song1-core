package core.util.poi;

import junit.framework.TestCase;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class TestWorkbookFactory extends TestCase {
	private String xls;
	private String xlsx;
	private String txt;

	protected void setUp() {
		xls = "SampleSS.xls";
		xlsx = "SampleSS.xlsx";
		txt = "SampleSS.txt";
	}

	public void testCreateNative() throws Exception {
		Workbook wb;

		// POIFS -> hssf
		wb = WorkbookFactory.create(new POIFSFileSystem(HSSFTestDataSamples.openSampleFileStream(xls)));
		assertNotNull(wb);
		assertTrue(wb instanceof HSSFWorkbook);

		// Package -> xssf
		wb = WorkbookFactory.create(OPCPackage.open(HSSFTestDataSamples.openSampleFileStream(xlsx)));
		assertNotNull(wb);
		assertTrue(wb instanceof XSSFWorkbook);
	}

	/**
	 * Creates the appropriate kind of Workbook, but checking the mime magic at
	 * the start of the InputStream, then creating what's required.
	 */
	public void testCreateGeneric() throws Exception {
		Workbook wb;

		// InputStream -> either
		wb = WorkbookFactory.create(HSSFTestDataSamples.openSampleFileStream(xls));
		assertNotNull(wb);
		assertTrue(wb instanceof HSSFWorkbook);

		wb = WorkbookFactory.create(HSSFTestDataSamples.openSampleFileStream(xlsx));
		assertNotNull(wb);
		assertTrue(wb instanceof XSSFWorkbook);

		// File -> either
		wb = WorkbookFactory.create(HSSFTestDataSamples.getSampleFile(xls));
		assertNotNull(wb);
		assertTrue(wb instanceof HSSFWorkbook);

		wb = WorkbookFactory.create(HSSFTestDataSamples.getSampleFile(xlsx));
		assertNotNull(wb);
		assertTrue(wb instanceof XSSFWorkbook);

		// Invalid type -> exception
		try {
			wb = WorkbookFactory.create(HSSFTestDataSamples.openSampleFileStream(txt));
			fail();
		} catch (IllegalArgumentException e) {
			// Good
		}
	}
}
