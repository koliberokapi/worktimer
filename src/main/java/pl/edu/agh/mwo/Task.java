package pl.edu.agh.mwo;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Task(LocalDateTime start, LocalDateTime stop, String projectName, String taskName) {

    public static Task of(String rawLine) {
        List<String> vals = new ArrayList<>();
        boolean inQuote = false;
        int start = 0, sLen = rawLine.length();

        for (int i = 0; i < sLen - 1; i++) {
            if (rawLine.charAt(i) == ',' && !inQuote) {
                vals.add(rawLine.substring(start, i));
                start = i + 1;
            } else if (rawLine.charAt(i) == '"') {
                inQuote = !inQuote;
            }
        }
        vals.add(rawLine.substring(start));


        return new Task(
                LocalDateTime.parse(vals.get(0)),
                LocalDateTime.parse(vals.get(1)),
                vals.get(2),
                vals.get(3)
        );
    }
}