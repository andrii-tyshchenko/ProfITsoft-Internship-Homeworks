package dev.profitsoft.hw8.dtos;

import org.springframework.data.mongodb.core.mapping.Field;

public class RelatedPerson {
    @Field("person_uk")
    private String nameUA;
    @Field("person_en")
    private String nameEN;
    @Field("is_pep")
    private Boolean isPEP;
    @Field("date_established")
    private String dateEstablished;
    @Field("date_confirmed")
    private String dateConfirmed;
    @Field("date_finished")
    private String dateFinished;
    @Field("relationship_type")
    private String relationshipTypeUA;
    @Field("relationship_type_en")
    private String relationshipTypeEN;

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

    public Boolean getIsPEP() {
        return isPEP;
    }

    public void setIsPEP(Boolean isPEP) {
        this.isPEP = isPEP;
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

    public String getRelationshipTypeUA() {
        return relationshipTypeUA;
    }

    public void setRelationshipTypeUA(String relationshipTypeUA) {
        this.relationshipTypeUA = relationshipTypeUA;
    }

    public String getRelationshipTypeEN() {
        return relationshipTypeEN;
    }

    public void setRelationshipTypeEN(String relationshipTypeEN) {
        this.relationshipTypeEN = relationshipTypeEN;
    }
}