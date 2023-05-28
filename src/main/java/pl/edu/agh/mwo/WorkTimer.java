package pl.edu.agh.mwo;

import java.io.FileNotFoundException;

public class WorkTimer {

    public static void main(String[] args) throws FileNotFoundException {

        Commands commands = new Commands();
        ReportGenerator reportGenerator = new ReportGenerator();
        ReportCalc reportCalc = new ReportCalc();

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
            }
            else if (args[0].contains("last")) {
            }
            else if (args[0].contains("list")) {
                commands.list(args[1]);
            }
            else if (args[0].contains("report")) {
                commands.report(reportGenerator, reportCalc);
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
