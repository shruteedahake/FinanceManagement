package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getConnectionString(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
            return properties.getProperty("connectionString");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}