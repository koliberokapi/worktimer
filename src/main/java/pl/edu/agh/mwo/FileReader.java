package pl.edu.agh.mwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileReader {

    static List<Task> getTasks() {
        try (Stream<String> lines = Files.lines(Path.of("datasource/log.csv"))) {
            return lines.skip(1)
                    .map(Task::of)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
