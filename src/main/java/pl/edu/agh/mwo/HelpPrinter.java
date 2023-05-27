package pl.edu.agh.mwo;

public class HelpPrinter {
    public void helpPrinter () {
        System.out.println(
             "Please use one of following arguments:" +
                     "start -p \"Project Name\" -t \"Task Name\": to start new action, if project or task not exist it will be created automatically" +
                     "stop: to stop action"+
                     "continue: to continue last action"+
                     "last: to display running tasks"+
                     "list: to display list of all tasks in time"+
                     "report: to display report of actions with summary"+
                     "help: to display all options"

        );
    }
}
