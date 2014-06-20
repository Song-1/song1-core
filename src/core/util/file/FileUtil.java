package core.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import core.bean.FileBean;
import core.util.StringUtil;

/**
 * 
 * @ClassName: FileUtil
 * @author Jeckey.Liu
 * @date 2014年5月7日 下午4:02:22
 *
 */
public class FileUtil {
	public static String getSeparator() {
		return File.separator;
	}

	public static String getPathSeparator() {
		return File.pathSeparator;
	}

	public static void uploadFile(MultipartFile mfile, String path) {
		File dirFile = new File(path);
		if (!dirFile.exists())
			dirFile.mkdir();
		System.out.println(mfile.getOriginalFilename());
		File uploadedFile = new File(path + mfile.getOriginalFilename());
		if (!uploadedFile.exists())
			try {
				uploadedFile.createNewFile();
				FileCopyUtils.copy(mfile.getBytes(), uploadedFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public static List<FileBean> getFileList(String path) {
		File file = new File(path);
		List list = new ArrayList();
		if (!file.isDirectory()) {
			FileBean fileBean = new FileBean();

			fileBean.setText(file.getName());
			fileBean.setPath(file.getPath());
			fileBean.setAbsolutepath(file.getAbsolutePath());
			fileBean.setLeaf(true);
			list.add(fileBean);
		} else if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; ++i) {
				File readfile = new File(path + filelist[i]);
				if (!readfile.isDirectory()) {
					FileBean fileBean = new FileBean();

					fileBean.setText(readfile.getName());
					fileBean.setPath(readfile.getPath());
					fileBean.setAbsolutepath(readfile.getAbsolutePath());
					fileBean.setLeaf(true);
					list.add(fileBean);
				} else if (readfile.isDirectory()) {
					FileBean fileBean = new FileBean();

					fileBean.setText(filelist[i]);
					fileBean.setPath(readfile.getPath());
					fileBean.setAbsolutepath(readfile.getAbsolutePath());
					fileBean.setLeaf(false);
					list.add(fileBean);
				}
			}
		}

		return list;
	}

	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file) {
		if ((!file.exists()) || (!file.isFile())) {
			response.setStatus(404);
		} else {
			InputStream in = null;
			try {
				in = new FileInputStream(file);
				response.reset();
				response.setHeader("Accept-Ranges", "bytes");
				long start = 0L;
				long end = file.length();
				response.setContentLength((int) (end - start));
				response.setContentType("application/octet-stream");
				String fileName = StringUtil.convertGb2312ToIso8859(file.getName());
				response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				WriteUtil.write(in, response.getOutputStream(), 1024);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void copyFile(File fromFile, File toFile) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(fromFile);
			out = new FileOutputStream(toFile);
			WriteUtil.write(in, out, 1024);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String path) {
		File file = new File(path);
		downloadFile(request, response, file);
	}

	public static String getSystemPrint() throws UnsupportedEncodingException {
		System.out.println(System.getProperty("file.encoding"));
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		String str = null;
		System.out.println("请输入内容:");
		try {
			str = buf.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
	}

	public static List<String> txtReaderLine(File file, String charsetName) throws IOException {
		return txtReaderLine(new FileInputStream(file), charsetName);
	}

	public static List<String> txtReaderLine(InputStream in, String charsetName) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, charsetName));
		String line = null;
		List list = new ArrayList();
		while ((line = bufferedReader.readLine()) != null) {
			list.add(line);
		}
		return list;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("file.encoding"));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(
				"E://资料备份/音乐1号/migu/singeridlist2.txt")), "UTF-8"));
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
			String[] lines = line.split("\\|", -1);
			String out = null;
			System.out.println("长度：" + lines.length);
			for (int i = 0; i < lines.length; ++i) {
				if (i == 0)
					out = lines[i] + "-";
				else {
					out = out + lines[i] + "-";
				}
			}
			System.out.println(out);
			System.out
					.println("===============================================================================================");
		}
	}
}