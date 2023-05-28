package pl.edu.agh.mwo;

import java.io.FileNotFoundException;

public class WorkTimer {

    public static void main(String[] args) throws FileNotFoundException {

        Commands commands = new Commands();

        if (args.length != 0) {
            if (args[0].contains("start")
                    && args[1].contains("-p")
                    && args[3].contains("-t")) {
                System.out.println("app started, start class to be called with two arguments (args[2] - project name,args[4] - task name)");
                commands.start(args[2], args[4]);
            }
            else if (args[0].contains("stop")) {
                commands.stop();
            }
            else if (args[0].contains("continue")) {
                System.out.println("app started, buy without arguments, last run project");
            }
            else if (args[0].contains("last")) {
                System.out.println("list of last tasks with indexes");
            }
            else if (args[0].contains("list")) {
                System.out.println("list of all tasks");
                commands.list(args[1]);
            }
            else if (args[0].contains("report")) {
                System.out.println("report with args[1] without first two -- in argument of class");
            }
            else if (args[0].contains("file") && args[1].contains("-f")) {
                commands.changeFile(args[2]);
                System.out.println("changing source file");
            } else {
                helpPrinter();
            }
        } else {
            helpPrinter();
        }

    }
    public static void helpPrinter() {
        System.out.println(
                "Please use one of following arguments:\n" +
                        "start -p \"Project Name\" -t \"Task Name\": to start new action, if project or task not exist it will be created automatically\n" +
                        "stop: to stop action\n" +
                        "continue: to continue last action\n" +
                        "last: to display running tasks\n" +
                        "list: to display list of all tasks in time\n" +
                        "report: to display report of actions with summary\n" +
                        "file -f \"newFilename.csv\": to change the source file\n" +
                        "help: to display all options\n"

        );
    }
}
