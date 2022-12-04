package task1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FineStatisticsUtil {
    /**
     * Returns List of Fines from given FinesStatistics object (every Fine has unique "type" field,
     * and its "amount" field is a sum of all appeared values of this field for this unique "type" value),
     * descending sorted by "amount" field.
     * @param finesStatistics - object, which contains List of traffic fines.
     * @return List of fines with unique types with total sum of all accrualed amounts for each type,
     * descending sorted by sum.
     */
    public static List<Fine> getUniqueFinesWithTotalAmount(FinesStatistics finesStatistics) {
        Map<String, Double> uniqueTypesAndTotalAmount = finesStatistics.getFines().stream()
                .collect(Collectors.toMap(Fine::getType, Fine::getAmount, Double::sum));

        return uniqueTypesAndTotalAmount.entrySet().stream()
                .map(entry -> new Fine(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(Fine::getAmount).reversed().thenComparing(Fine::getType))
                .toList();
    }
}