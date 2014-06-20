package core.util.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WritePhotoUtil {
	public static String writePhoto(HttpServletRequest request, HttpServletResponse respone, String code, String path) {
		OutputStream out = null;
		try {
			out = respone.getOutputStream();
			BufferedImage imageie = null;
			imageie = ImageIO.read(new File(path));
			respone.setContentType("image/" + PhotoUtil.getTypeByPath(path));
			respone.setHeader("Content-Disposition", "attachment;filename=" + PhotoUtil.getNameByPath(path));
			ImageIO.write(imageie, PhotoUtil.getTypeByPath(path), out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
			} catch (Exception localException3) {
			}
			try {
				out.close();
			} catch (Exception localException4) {
			}
		}
		return null;
	}
}