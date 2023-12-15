package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload/product")
@MultipartConfig(
		location = "C:\\FileServer\\upload\\product",
		fileSizeThreshold = 1024 * 1024 * 2, // 2MB 會暫存在記憶體處理, 反之則會暫存在虛擬記憶體預處理
		maxFileSize = 1024 * 1024 * 10 // 檔案限制最大 size
)
public class UploadProductServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String name = req.getParameter("productName");
		resp.getWriter().println("name: " + name);
		resp.getWriter().println("<p>");
		
		String price = req.getParameter("productPrice");
		resp.getWriter().println("price: " + price);
		resp.getWriter().println("<p>");
		
		// 取得上傳文件
		Part filePart = req.getPart("productFile");
		String fileName = getFileName(filePart);
		resp.getWriter().println("fileName: " + fileName);
		resp.getWriter().println("<p>");
		// 存檔
		filePart.write(fileName);
		resp.getWriter().println("存檔完成");
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
