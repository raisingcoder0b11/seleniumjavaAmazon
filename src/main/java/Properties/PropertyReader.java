package Properties;
import java.io.*;
import java.util.Properties;


public class PropertyReader {

    public static Properties getPropertyObject() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/sakthilavanya/Desktop/amazon/src/test/resources/application.properties");
        Properties property = new Properties();
        property.load(fileInputStream);
        return property;
    }

    public static String getUrl() throws IOException {
        return getPropertyObject().getProperty("url");
    }
}

