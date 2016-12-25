package sendclass.utils;

public class StringUtils extends  org.apache.commons.lang3.StringUtils {
	public static String handleSpace(String path){
		if(path != null){
			path = path.replaceAll(" ", "%20");
		}
		return path;
	}
}
