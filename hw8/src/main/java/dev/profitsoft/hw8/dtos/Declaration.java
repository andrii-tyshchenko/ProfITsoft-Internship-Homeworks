package dev.profitsoft.hw8.dtos;

import org.springframework.data.mongodb.core.mapping.Field;

public class Declaration {
    @Field("position_uk")
    private String positionUA;
    @Field("position_en")
    private String positionEN;
    @Field("office_uk")
    private String officeUA;
    @Field("office_en")
    private String officeEN;
    @Field("region_uk")
    private String regionUA;
    @Field("region_en")
    private String regionEN;
    private String url;
    private String year;
    private Double income;
    @Field("family_income")
    private Double familyIncome;

    public String getPositionUA() {
        return positionUA;
    }

    public void setPositionUA(String positionUA) {
        this.positionUA = positionUA;
    }

    public String getPositionEN() {
        return positionEN;
    }

    public void setPositionEN(String positionEN) {
        this.positionEN = positionEN;
    }

    public String getOfficeUA() {
        return officeUA;
    }

    public void setOfficeUA(String officeUA) {
        this.officeUA = officeUA;
    }

    public String getOfficeEN() {
        return officeEN;
    }

    public void setOfficeEN(String officeEN) {
        this.officeEN = officeEN;
    }

    public String getRegionUA() {
        return regionUA;
    }

    public void setRegionUA(String regionUA) {
        this.regionUA = regionUA;
    }

    public String getRegionEN() {
        return regionEN;
    }

    public void setRegionEN(String regionEN) {
        this.regionEN = regionEN;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getFamilyIncome() {
        return familyIncome;
    }

    public void setFamilyIncome(Double familyIncome) {
        this.familyIncome = familyIncome;
    }
}