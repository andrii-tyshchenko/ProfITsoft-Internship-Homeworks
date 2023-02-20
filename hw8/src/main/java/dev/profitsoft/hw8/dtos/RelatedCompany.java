package dev.profitsoft.hw8.dtos;

import org.springframework.data.mongodb.core.mapping.Field;

public class RelatedCompany {
    @Field("to_company_uk")
    private String nameUA;
    @Field("to_company_en")
    private String nameEN;
    @Field("to_company_short_uk")
    private String shortNameUA;
    @Field("to_company_short_en")
    private String shortNameEN;
    @Field("to_company_edrpou")
    private String EDRPOUCode;
    @Field("to_company_founded")
    private String founded;
    @Field("to_company_is_state")
    private Boolean isState;
    @Field("relationship_type_uk")
    private String relationshipTypeUA;
    @Field("relationship_type_en")
    private String relationshipTypeEN;
    private String share;
    @Field("date_confirmed")
    private String dateConfirmed;
    @Field("date_established")
    private String dateEstablished;
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

    public String getShortNameUA() {
        return shortNameUA;
    }

    public void setShortNameUA(String shortNameUA) {
        this.shortNameUA = shortNameUA;
    }

    public String getShortNameEN() {
        return shortNameEN;
    }

    public void setShortNameEN(String shortNameEN) {
        this.shortNameEN = shortNameEN;
    }

    public String getEDRPOUCode() {
        return EDRPOUCode;
    }

    public void setEDRPOUCode(String EDRPOUCode) {
        this.EDRPOUCode = EDRPOUCode;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public Boolean getIsState() {
        return isState;
    }

    public void setIsState(Boolean isState) {
        this.isState = isState;
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

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getDateConfirmed() {
        return dateConfirmed;
    }

    public void setDateConfirmed(String dateConfirmed) {
        this.dateConfirmed = dateConfirmed;
    }

    public String getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(String dateEstablished) {
        this.dateEstablished = dateEstablished;
    }

    public String getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(String dateFinished) {
        this.dateFinished = dateFinished;
    }
}