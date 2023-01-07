package dev.profitsoft.hw5.dtos;

import java.util.Objects;

public class PublisherDetailsDto {
    private Integer id;
    private String name;
    private Integer countryId;
    private String countryName;

    public PublisherDetailsDto() {
    }

    public PublisherDetailsDto(Integer id, String name, Integer countryId, String countryName) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublisherDetailsDto that = (PublisherDetailsDto) o;

        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
                && Objects.equals(countryId, that.countryId) && Objects.equals(countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countryId, countryName);
    }
}