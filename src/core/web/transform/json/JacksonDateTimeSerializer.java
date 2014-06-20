package core.web.transform.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
/**
 * 
* @ClassName: JacksonDateTimeSerializer 
* @Description: TODO
* @author Jeckey.Liu
* @date 2014年4月15日 下午3:05:38 
*
 */
public class JacksonDateTimeSerializer extends JsonSerializer<Date> {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonProcessingException {
		if (value != null) {
			String formattedDate = this.formatter.format(value);
			jgen.writeString(formattedDate);
		} else {
			jgen.writeString("");
		}
	}
}