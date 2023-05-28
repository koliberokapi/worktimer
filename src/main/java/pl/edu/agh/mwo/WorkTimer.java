package pl.edu.agh.mwo;

import java.io.FileNotFoundException;
import java.util.List;

public class WorkTimer {

    public static void main(String[] args) throws FileNotFoundException {

        Commands commands = new Commands();

        if (args.length != 0) {
            if (args[0].contains("start")
                    && args[1].contains("-p")
                    && args[3].contains("-t")) {
                commands.start(args[2], args[4]);
            }
            else if (args[0].contains("stop")) {
                commands.stop();
            }
            else if (args[0].contains("continue")) {
                int index = Integer.parseInt(args[1]);
                commands.resume(index);
            }
            else if (args[0].contains("last")) {
                System.out.println("list of last tasks with indexes");
                int lastCount;
                if (args.length < 2) {
                    lastCount = 5;
                } else {
                    lastCount = Integer.parseInt(args[1]);
                }
                commands.last(lastCount, true);
            }
            else if (args[0].contains("list")) {
                commands.list(args[1]);
            }
            else if (args[0].contains("report")) {
                ReportCalc report = new ReportCalc();
                report.calculateReport();
            }
            else if (args[0].contains("file") && args[1].contains("-f")) {
                commands.changeFile(args[2]);
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
