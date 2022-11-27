package task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File directory = new File("hw2/src/main/resources/fines");

        FinesStatistics finesStatistics = new FinesStatistics();
        List<Fine> fines = new ArrayList<>();

        for (File file : directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"))) {
            try {
                fines.addAll(finesStatistics.getFinesFromJson(file));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        finesStatistics.setFines(fines);

        File destination = new File("hw2/src/main/resources/fines/statistics/fines_statistics.xml");

        try {
            finesStatistics.writeStatisticsToXml(destination);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}