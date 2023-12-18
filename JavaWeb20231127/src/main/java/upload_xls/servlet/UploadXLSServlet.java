package upload_xls.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import upload_xls.service.XLSService;

@WebServlet("/upload/xls")
@MultipartConfig(
		location = "C:\\FileServer\\upload\\xls",
		fileSizeThreshold = 1024 * 1024 * 2, // 2MB 會暫存在記憶體處理, 反之則會暫存在虛擬記憶體預處理
		maxFileSize = 1024 * 1024 * 10 // 檔案限制最大 size
)
public class UploadXLSServlet extends HttpServlet {
	 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 取得上傳文件
		Part filePart = req.getPart("xlsFile");
		String fileName = getFileName(filePart);
		resp.getWriter().println("上傳檔案名稱: " + fileName);
		resp.getWriter().println("<p>");
		// 存檔
		filePart.write(fileName);
		resp.getWriter().println("存檔完成");
		// -----------------------------------------------------------------------
		// 取得 MultipartConfig 設定資料
		MultipartConfig config = getClass().getAnnotation(MultipartConfig.class);
		// 基礎路徑
		String location = config.location(); // 得到 "C:\\FileServer\\upload\\xls"
		// 絕對路徑
		String realPath = location + File.separator + fileName;
		
		XLSService xlsService = new XLSService();
		int count = 0;
		try {
			count = xlsService.importService(realPath);
		} catch (Exception e) {
			System.err.println(e);
		}
		resp.getWriter().println("<p>");
		resp.getWriter().println("匯入完成, 筆數: " + count);
	}
	
	private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
	
}
