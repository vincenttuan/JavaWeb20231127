package upload_xls.service;

import java.io.FileInputStream;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XLSService {
	
	// 利用 POI 分析 Excel 資訊並匯入到資料庫
	public void importService(String path) throws Exception {
		FileInputStream input = new FileInputStream(path);
		POIFSFileSystem fs = new POIFSFileSystem(input);
		Workbook workbook;
		workbook = WorkbookFactory.create(fs);
		Sheet sheet = workbook.getSheetAt(0);
		Row row;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = (Row) sheet.getRow(i);
			String name = row.getCell(0).getStringCellValue();
			String sex = row.getCell(1).getStringCellValue();
			int salary = (int)row.getCell(2).getNumericCellValue();
			int age = (int)row.getCell(3).getNumericCellValue();
			System.out.printf("%s %s %d %d%n", name, sex, salary, age);
		}
	}
}
