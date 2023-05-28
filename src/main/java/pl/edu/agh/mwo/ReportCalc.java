

package pl.edu.agh.mwo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ReportCalc {

    public Long convertTimesFromLocalToMillisec(LocalDateTime time) {
        ZonedDateTime zonedDateTimeStop = ZonedDateTime.of(time, ZoneId.systemDefault());
        long date = zonedDateTimeStop.toInstant().toEpochMilli();
        return date;
    }

    public static String getDurationBreakdown(long millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(64);
        if (days != 0) {
            sb.append(days);
            sb.append("D ");
        }
        if (hours != 0) {
            sb.append(hours);
            sb.append("H ");
        }
        if (minutes != 0) {
            sb.append(minutes);
            sb.append("M ");
        }
        if (seconds != 0) {
            sb.append(seconds);
            sb.append("S");
        }

        return (sb.toString());
    }

    public void calculateReport() {

        HashMap<String, HashMap<String, Long>> projectMap = new HashMap<>();
        List<String> times = new ArrayList<>();
        FileManager fileManager = new FileManager();
        List<Task> listForReport = fileManager.getTasks();

        for (Task task : listForReport) {
            if (!projectMap.containsKey(task.getProjectName())) {
                projectMap.put(task.getProjectName(), new HashMap<>());
            }
        }

        for (Task task : listForReport) {
            long dateStop = convertTimesFromLocalToMillisec(task.getStop());
            long dateStart = convertTimesFromLocalToMillisec(task.getStart());
            long duration = dateStop - dateStart;
            Map<String, Long> actualMap = projectMap.get(task.getProjectName());
            if(actualMap.containsKey(task.getTaskName())){
                Long actual = actualMap.get(task.getTaskName());
                actualMap.put(task.getTaskName(), actual + duration);
            } else {
                actualMap.put(task.getTaskName(), duration);
            }
        }

        for(String m : projectMap.keySet()){
            for(String taskName: projectMap.get(m).keySet()) {
                Long taskDuration = projectMap.get(m).get(taskName);
                times.add(getDurationBreakdown(taskDuration));
            }

        }

        System.out.println(projectMap);
        System.out.println(times);
    }

}


