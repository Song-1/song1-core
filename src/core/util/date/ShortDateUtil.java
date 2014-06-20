package core.util.date;

import core.util.SixtyTwoCountUtil;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class ShortDateUtil
{
  private static SimpleDateFormat formatter = null;

  public static String getShortDate(String sdate, String type)
    throws ParseException
  {
    String returnValue = null;
    formatter = new SimpleDateFormat(type);
    Date date = formatter.parse(sdate);

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    int year = cal.get(1);
    int month = cal.get(2) + 1;
    int dates = cal.get(5);

    returnValue = String.valueOf(year - 2000) + StringUtils.upperCase(SixtyTwoCountUtil.gen(month, 1)) + SixtyTwoCountUtil.gen(dates, 1);

    return returnValue;
  }

  public static void main(String[] args) {
    String value = "000" + String.valueOf(999);
    if (value.length() > 4) {
      value = value.substring(value.length() - 4, value.length());
    }
    System.out.println(value);
  }
}