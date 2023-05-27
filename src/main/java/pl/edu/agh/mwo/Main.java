package pl.edu.agh.mwo;

public class Main {

    public static void main(String[] args) {

        if(args.length!=0) {
            if (args[0].contains("start")
                    && args[1].contains("-p")
                    && args[3].contains("-t")) {
                System.out.println("app started, start class to be called with two arguments (args[2] - project name,args[4] - task name)");
            }
            if (args[0].contains("stop")) {
                System.out.println("app stopped, stop class to be called");
            }
            if (args[0].contains("continue")){
                System.out.println("app started, buy without arguments, last run project");
            }
            if(args[0].contains("last")){
                System.out.println("list of last tasks with indexes");
            }
            if(args[0].contains("list")){
                System.out.println("list of all tasks");
            }
            if(args[0].contains("report")){
                System.out.println("report with args[1] without first two -- in argument of class");
            }
            if(args[0].contains("help")){
                HelpPrinter printer = new HelpPrinter();
                printer.helpPrinter();
            }
        }
        else{
            HelpPrinter printer = new HelpPrinter();
            printer.helpPrinter();
        }

    }
}
