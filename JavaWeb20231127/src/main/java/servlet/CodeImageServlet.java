package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/code/image")
public class CodeImageServlet extends HttpServlet {
	
	// 利用 Java 2D 產生驗證碼圖形
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 利用 Random 產生一個四位隨機數 
		Random random = new Random();
		String code = String.format("%04d", random.nextInt(10000)); // 0~9999
		// 將 code 存放到 session 變數中, 以利後續比對使用
		HttpSession session = req.getSession();
		session.setAttribute("code", code);
		// 1. 建立圖檔暫存區
		BufferedImage img = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		// 2. 建立畫布
		Graphics g = img.getGraphics();
		// 3. 設定顏色
		g.setColor(Color.YELLOW);
		// 4. 塗滿背景
		g.fillRect(0, 0, 80, 30);
		// 5. 設定顏色
		g.setColor(Color.BLACK);
		// 6. 設定自型
		g.setFont(new Font("Arial", Font.PLAIN, random.nextInt(20) + 10));
		// 7. 繪文字
		g.drawString(code, 10, 23);
		// 8. 干擾線
		g.setColor(Color.RED);
		for(int i=0;i<10;i++) {
			int x1 = random.nextInt(80);
			int y1 = random.nextInt(30);
			int x2 = random.nextInt(80);
			int y2 = random.nextInt(30);
			g.drawLine(x1, y1, x2, y2);
		}
		g.setColor(Color.BLUE);
		for(int i=0;i<10;i++) {
			int x1 = random.nextInt(80);
			int y1 = random.nextInt(30);
			int x2 = random.nextInt(80);
			int y2 = random.nextInt(30);
			g.drawLine(x1, y1, x2, y2);
		}
		
		// 設定回傳類型
		resp.setContentType("image/png");
		// 將圖片資料以串流回給瀏覽器
		ImageIO.write(img, "PNG", resp.getOutputStream());
	}
	
	
}
