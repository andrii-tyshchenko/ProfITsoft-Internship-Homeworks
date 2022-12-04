package task1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        File directory = new File("hw3/src/main/resources/fines");

        FinesStatistics finesStatistics = new FinesStatistics();
        List<Fine> fines = new ArrayList<>();

        int amountOfThreads = 8;
        ExecutorService threadPool = Executors.newFixedThreadPool(amountOfThreads);

        for (File file : Objects.requireNonNull(directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".json")))) {
            //code for running without threads:
            //fines.addAll(finesStatistics.getFinesFromJson(file));

            CompletableFuture.runAsync(() -> fines.addAll(finesStatistics.getFinesFromJson(file)), threadPool);
        }

        threadPool.shutdown();

        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        finesStatistics.setFines(fines);

        List<Fine> uniqueFinesWithTotalAmount = FineStatisticsUtil.getUniqueFinesWithTotalAmount(finesStatistics);
        finesStatistics.setUniqueFinesWithTotalAmount(uniqueFinesWithTotalAmount);

        File destination = new File("hw3/src/main/resources/fines/statistics/fines_statistics.xml");

        try {
            finesStatistics.writeStatisticsToXml(destination);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        long finishTime = System.currentTimeMillis();
        System.out.printf("Time of execution: %d millisecond(s)", finishTime - startTime);
    }
}