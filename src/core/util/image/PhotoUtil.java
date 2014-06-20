package core.util.image;

import org.apache.commons.lang.StringUtils;

public class PhotoUtil {
	public static String getNameByPath(String path) {
		String name = null;
		if ((path != null) && (path.length() > 0)) {
			name = StringUtils.substring(path, path.lastIndexOf("=") + 1, path.indexOf("."));
		}
		return name;
	}

	public static String getTypeByPath(String path) {
		String type = null;
		if ((path != null) && (path.length() > 0)) {
			type = StringUtils.substring(path, path.indexOf(".") + 1, path.length());
		}
		return type;
	}
}