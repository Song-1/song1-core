package core.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * java url 是由protocol代表通信协议，host代表主机名，file代表文件名组成
 * file是由文件路径path，文件名，文件后缀组成
 * 
 * @author Administrator
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ CRequestTest.class })
public class AllTests {

}
