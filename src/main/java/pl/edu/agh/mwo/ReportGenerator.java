package pl.edu.agh.mwo;

import java.util.HashMap;

public class ReportGenerator {

    public void displayReport(ReportCalc reportCalc) {
        HashMap<String, HashMap<String, Long>> data = reportCalc.calculateReport();
        System.out.printf("%-20s %-20s %20s", "NAZWA PROJEKTU", "NAZWA TASKA", "CZAS TRWANIA");
        System.out.println();
        for(String m : data.keySet()){
            System.out.println("------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %20s", m, " ", reportCalc.getDurationBreakdown(reportCalc.sumPerProject(m)));
            System.out.println();
            System.out.println("------------------------------------------------------------------");
            for(String taskName: data.get(m).keySet()) {
                Long taskDuration = data.get(m).get(taskName);
                System.out.printf("%-20s %-20s %20s", " ", taskName, ReportCalc.getDurationBreakdown(taskDuration));
                System.out.println();
            }


        }

    }
}
