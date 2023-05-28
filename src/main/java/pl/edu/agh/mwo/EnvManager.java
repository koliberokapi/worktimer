package pl.edu.agh.mwo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvManager {
    private static final String ENV_PATH = "env.properties";
    private Properties properties;

    public EnvManager() {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(ENV_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogFilePath() {
        return properties.getProperty("LOG_FILE_PATH");
    }

    public void setNewLogFileName(String newLogFileName) {
        properties.setProperty("LOG_FILE_PATH", "datasource/"+newLogFileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(ENV_PATH)) {
            properties.store(fileOutputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

