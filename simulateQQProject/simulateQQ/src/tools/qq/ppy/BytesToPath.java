package tools.qq.ppy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BytesToPath {
	public static byte[] getBytes(String path) {
		byte[] bytes = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] mid = new byte[1024];
			int len = -1;
			while ((len = bis.read(mid)) != -1) {
				bos.write(mid, 0, len);
			}
			bos.flush();
			bytes = bos.toByteArray();

			if (null != bos)
				bos.close();
			if (null != bis)
				bis.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return bytes;
	}

	public static String getPath(byte[] bytes, String num) {
		try {
			System.out.println(num);
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(new File("F:\\Myeclipseproject\\用户列表头像与背景\\" + num)));
			bos.write(bytes, 0, bytes.length);
			bos.flush();
			if (null != bos)
				bos.close();
			return "F:\\Myeclipseproject\\用户列表头像与背景\\" + num;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return null;
	}
}
