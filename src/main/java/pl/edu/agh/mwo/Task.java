package pl.edu.agh.mwo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {
    LocalDateTime start;
    LocalDateTime stop;
    String projectName;
    String taskName;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getStop() {
        return stop;
    }

    public void setStop(LocalDateTime stop) {
        this.stop = stop;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Task (LocalDateTime start, LocalDateTime stop, String projectName, String taskName) {
        this.start = start;
        this.stop = stop;
        this.projectName = projectName;
        this.taskName = taskName;

    }

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
        Task task = null;
        try {
            LocalDateTime startTime = LocalDateTime.parse(vals.get(0));
            LocalDateTime stopTime = LocalDateTime.parse(vals.get(1));
            String projectName = vals.get(2);
            String taskName = vals.get(3);

            task = new Task(startTime, stopTime, projectName, taskName);
        } catch (Exception e) {
            task = new Task(LocalDateTime.parse(vals.get(0)), null, vals.get(2), vals.get(3));
        }

        return task;
    }

    @Override
    public String toString() {
        if(stop==null){
            return start +
                    "    " + "                   " +
                    "           " + projectName +
                    "          " + taskName;
        } else {
            return start +
                    "    " + stop +
                    "           " + projectName +
                    "          " + taskName;
        }

    }
}