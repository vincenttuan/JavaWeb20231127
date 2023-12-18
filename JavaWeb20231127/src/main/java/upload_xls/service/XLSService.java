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
		// 根據 path 找到文件
		FileInputStream input = new FileInputStream(path);
		// 建立 POI 文件系統, 用來讀取 Excel 文件
		POIFSFileSystem fs = new POIFSFileSystem(input);
		// 建立並獲取 Excel 工作簿 
		Workbook workbook;
		workbook = WorkbookFactory.create(fs);
		// 建立並獲取 Excel 工作表
		Sheet sheet = workbook.getSheetAt(0);
		// 從 1 開始, 因為 0 是標題列 
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			// 取得列
			Row row = (Row) sheet.getRow(i);
			// 取得第一欄
			String name = row.getCell(0).getStringCellValue();
			// 取得第二欄
			String sex = row.getCell(1).getStringCellValue();
			// 取得第三欄
			int salary = (int)row.getCell(2).getNumericCellValue();
			// 取得第四欄
			int age = (int)row.getCell(3).getNumericCellValue();
			
			System.out.printf("%s %s %d %d%n", name, sex, salary, age);
		}
	}
}
