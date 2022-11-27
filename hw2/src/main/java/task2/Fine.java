package task2;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents information about traffic fine (only its type and amount).
 */
public class Fine {
    private String type;
    @JsonProperty("fine_amount")
    private Double amount;

    public Fine() {
    }

    public Fine(String type, Double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}