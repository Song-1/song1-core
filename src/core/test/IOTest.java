package core.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IOTest {

	public static void main(String args[]) {

		ReadDate();

//		WriteDate();

	}

	/**
	 * 读取数据
	 */
	public static void ReadDate() {
		String url = "D://work//diantaidizhi.txt";
		try {
			FileReader read = new FileReader(new File(url));
			StringBuffer sb = new StringBuffer();
			char ch[] = new char[1024];
			int d = read.read(ch);
			while (d != -1) {
				String str = new String(ch, 0, d);
				sb.append(str);
				d = read.read(ch);
			}
			System.out.print(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写入数据
	 */
	public static void WriteDate() {

		try {
			File file = new File("D://work//diantai.txt");
//			if (file.exists()) {
//				file.delete();
//			}
//
//			file.createNewFile();

			BufferedWriter output = new BufferedWriter(new FileWriter(file));

			ArrayList aList = new ArrayList();
			for (int i = 0; i < 10; i++) {
				aList.add(Math.random() * 100);
			}

			for (int i = 0; i < aList.size(); i++) {
				output.write(String.valueOf(aList.get(i)) + "\n");
			}
			output.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}