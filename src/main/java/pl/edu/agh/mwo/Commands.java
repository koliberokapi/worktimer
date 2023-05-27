package pl.edu.agh.mwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Commands {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public void start(String projectName, String taskName) throws FileNotFoundException {
        File tasks = new File("datasource/log.csv");
        LocalDateTime currentTime = LocalDateTime.now();

        String formatDateTime = currentTime.format(formatter);
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(tasks, true));
        printWriter.append(formatDateTime + "," + formatDateTime + "," + projectName + "," + taskName + "\n");
        printWriter.close();
    }
}
