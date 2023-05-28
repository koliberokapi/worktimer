package pl.edu.agh.mwo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileManager {

    private static final String LOG_FILE_PATH = new EnvManager().getLogFilePath();


    public FileManager() throws FileNotFoundException {
        File tasks = new File(LOG_FILE_PATH);
        if (!tasks.exists()) {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(tasks, true));
            printWriter.append("start,stop,projekt,zadanie\n");
            printWriter.close();
        }
    }

    public List<Task> getTasks() {
        try (Stream<String> lines = Files.lines(Path.of(LOG_FILE_PATH))) {
            return lines.skip(1)
                    .map(Task::of)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void overrideAllEmptyStopTimeCells(String newValue) {
        int stopTimeColumnIndex = 1;
        try {
            List<String> lines = Files.readAllLines(Path.of(LOG_FILE_PATH));
            for (int rowIndex=1; rowIndex<lines.size(); rowIndex+=1) {
                String line = lines.get(rowIndex);
                String[] cells = line.split(",");
                if (cells[stopTimeColumnIndex].equals("")) {
                    cells[stopTimeColumnIndex] = newValue;
                    line = String.join(",", cells);
                    lines.set(rowIndex, line);
                    Files.write(Path.of(LOG_FILE_PATH), lines);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
