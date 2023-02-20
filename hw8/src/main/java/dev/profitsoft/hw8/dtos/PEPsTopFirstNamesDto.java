package dev.profitsoft.hw8.dtos;

import org.springframework.data.annotation.Id;

public class PEPsTopFirstNamesDto {
    @Id
    private String firstName;
    private Integer quantity;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}