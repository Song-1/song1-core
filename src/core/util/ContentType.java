package core.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ContentType {
	

	public static enum ImageEnum {
	     JPG("image/jpeg"),
	     PNG("image/png");
	     private String contentType;
	     public String getContentType(){
	    	 return this.contentType;
	     }
	     private ImageEnum(String contentType){
	    	 this.contentType = contentType;
	     }
	}
	public static enum AudioEnum {
		MP3("audio/mpeg"),
		FLAC("audio/flac");
		private String contentType;
		public String getContentType(){
			return this.contentType;
		}
		private AudioEnum(String contentType){
			this.contentType = contentType;
		}
	}
	public final static Map<String,String> contentTypeMap = new HashMap<String,String>() {
		private static final long serialVersionUID = 1L;
		{
			put("stream", "application/octet-stream");
			put("mp3", "audio/mpeg");
			put("flac", "audio/flac");
			put("jpeg", "image/jpeg");
			put("jpg", "image/jpeg");
			put("png", "image/png");
			put("bmp", "image/bmp");
		}
	};
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("E:/tmp/【E盘】后台/场景/驾驶/西北风/山丹丹花开红艳艳/龚琳娜 - 桃花红 杏花白.mp3");
		String contentType = Files.probeContentType(path); 
		System.out.println("File content type is : " + contentType);  
		System.out.println(ImageEnum.JPG.toString().toLowerCase());
	}
}
