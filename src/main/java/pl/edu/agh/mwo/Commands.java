package pl.edu.agh.mwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;

public class Commands {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final String LOG_FILE_PATH = new EnvManager().getLogFilePath();
    private FileManager fileManager = new FileManager();
    private EnvManager envManager = new EnvManager();

    public Commands() throws FileNotFoundException {
    }


    public void start(String projectName, String taskName) throws FileNotFoundException {
        stop();
        File tasks = new File(LOG_FILE_PATH);
        LocalDateTime currentTime = LocalDateTime.now();

        String formatDateTime = currentTime.format(formatter);
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(tasks, true));
        printWriter.append(formatDateTime + ",," + projectName + "," + taskName + "\n");
        printWriter.close();
    }

    public void stop() throws FileNotFoundException {
        File tasks = new File(LOG_FILE_PATH);
        LocalDateTime currentTime = LocalDateTime.now();
        String formatDateTime = currentTime.format(formatter);

        fileManager.overrideAllEmptyStopTimeCells(formatDateTime);
    }

    public void list(String filteringParameter) throws FileNotFoundException {
        List<Task> allTasksList = fileManager.getTasks();

        LocalDateTime currentDayStart  = LocalDate.now().atStartOfDay();

        LocalDateTime currentMonthStart  = LocalDate.now()
                .with(firstDayOfMonth())
                .atStartOfDay();

        LocalDateTime previousMonthStart = LocalDate.now()
                .minusMonths(1)
                .with(firstDayOfMonth())
                .atStartOfDay();

        switch(filteringParameter) {
            case "--today":
                System.out.println(
                        "       start        "+
                                "    " + "        stop        " +
                                "        " + "project" +
                                "        " + "   task   "
                );
                for(Task task:allTasksList){
                    if(task.start.isAfter(currentDayStart)) {
                        System.out.println(task);
                    }
                }
                break;
            case "--current-month":
                System.out.println(
                        "       start        "+
                                "    " + "        stop        " +
                                "        " + "project" +
                                "        " + "   task   "
                );

                for(Task task:allTasksList){
                    if(task.start.isAfter(currentMonthStart)) {
                        System.out.println(task);
                    }
                }
                break;
            case "--previous-month":
                System.out.println(
                        "       start        "+
                                "    " + "        stop        " +
                                "        " + "project" +
                                "        " + "   task   "
                );

                for(Task task:allTasksList){
                    if((task.start.isAfter(previousMonthStart) && task.start.isBefore(currentMonthStart))) {
                        System.out.println(task);
                    }
                }
                break;
            default:
                System.out.println("Please provide one of the parameters:\n --today\n --current-month\n --previous-month");
        }
    }
    public void changeFile(String fileName) throws FileNotFoundException {
        envManager.setNewLogFileName(fileName);
    }

    public void report(ReportGenerator reportGenerator, ReportCalc reportCalc) throws FileNotFoundException {
        reportGenerator.displayReport(reportCalc);
    }

    public List<Task> last(int count, boolean printResult) throws FileNotFoundException {
        List<Task> allTasksList = fileManager.getTasks();
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (int index=allTasksList.size()-1; index>=0; index-=1) {
            if (filteredTasks.size()<count){
                filteredTasks.add(allTasksList.get(index));
            } else {
                break;
            }
        }
        if (printResult) {
        System.out.println(
                "index"+
                "        " + "project" +
                "        " + "   task   "
        );
            for(int index=0; index<filteredTasks.size(); index+=1){
                System.out.println(
                        "  ["+index+"]"+
                                "        " + filteredTasks.get(index).projectName +
                                "        " + filteredTasks.get(index).taskName
                );
            }
        }
        return filteredTasks;
    }

    public void resume (int taskIndex) throws FileNotFoundException {
        List<Task> taskList = last(taskIndex+1, false);
        Task taskToContinue = taskList.get(taskIndex);

        start(taskToContinue.projectName, taskToContinue.taskName);
    }
}
