package dev.profitsoft.hw8.dtos;

import org.springframework.data.mongodb.core.mapping.Field;

public class RelatedCountry {
    @Field("to_country_uk")
    private String nameUA;
    @Field("to_country_en")
    private String nameEN;
    @Field("relationship_type")
    private String relationshipType;
    @Field("date_established")
    private String dateEstablished;
    @Field("date_confirmed")
    private String dateConfirmed;
    @Field("date_finished")
    private String dateFinished;

    public String getNameUA() {
        return nameUA;
    }

    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(String dateEstablished) {
        this.dateEstablished = dateEstablished;
    }

    public String getDateConfirmed() {
        return dateConfirmed;
    }

    public void setDateConfirmed(String dateConfirmed) {
        this.dateConfirmed = dateConfirmed;
    }

    public String getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(String dateFinished) {
        this.dateFinished = dateFinished;
    }
}