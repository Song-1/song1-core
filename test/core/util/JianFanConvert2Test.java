package core.util;

import java.io.FileNotFoundException;
import java.io.IOException;

public class JianFanConvert2Test {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		JianFanConvert2 jfc = JianFanConvert2.getInstance(JianFanConvert2.TRADITIONAL);
		String simplifiedStr = jfc.fan2jian("有背光的機械式鍵盤");
		System.out.println(simplifiedStr);
	}
}
