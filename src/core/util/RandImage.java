package core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RandImage extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	public static final int width = 120;
	public static final int height = 50;

	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int width = 0;
		int height = 0;
		String width_str = request.getParameter("width");
		String height_str = request.getParameter("height");
		String format = request.getParameter("format");
		if (width_str != null) {
			try {
				width = Integer.parseInt(width_str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (width == 0) {
			width = this.width;
		}
		if (height_str != null) {
			try {
				height = Integer.parseInt(height_str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (height == 0) {
			height = this.height;
		}
		BufferedImage image = new BufferedImage(width, height, 1);

		Graphics g = image.getGraphics();

		Random random = new Random();

		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		g.setFont(new Font("Times New Roman", 0, 45));

		g.setColor(new Color(255, 255, 255));
		g.drawRect(0, 0, width - 1, height - 1);

		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; ++i) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(30);
			int yl = random.nextInt(30);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String sRand = "";
		for (int i = 0; i < 4; ++i) {
			String rand = String.valueOf(random.nextInt(10));
			sRand = sRand + rand;

			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));

			g.drawString(rand, 26 * i + 15, 40);
		}

		request.getSession(true).setAttribute("rand", sRand);

		g.dispose();

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		if (format == null || format.trim().equals("")) {
			format = ContentType.ImageEnum.JPG.toString().toLowerCase();
		}
		response.setContentType(ContentType.contentTypeMap.get(format));

		ImageIO.write(image, format, response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}
}