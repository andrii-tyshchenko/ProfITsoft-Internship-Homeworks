package dev.profitsoft.hw8.dtos;

public class PEPBasicInfoDto {
    private String fullNameUA;
    private String dateOfBirth;
    private Boolean died;
    private Boolean isPEP;
    private String typeOfOfficialUA;
    private String lastWorkplaceUA;
    private String lastJobTitleUA;
    private String url;

    public String getFullNameUA() {
        return fullNameUA;
    }

    public void setFullNameUA(String fullNameUA) {
        this.fullNameUA = fullNameUA;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getDied() {
        return died;
    }

    public void setDied(Boolean died) {
        this.died = died;
    }

    public Boolean getIsPEP() {
        return isPEP;
    }

    public void setIsPEP(Boolean isPEP) {
        this.isPEP = isPEP;
    }

    public String getTypeOfOfficialUA() {
        return typeOfOfficialUA;
    }

    public void setTypeOfOfficialUA(String typeOfOfficialUA) {
        this.typeOfOfficialUA = typeOfOfficialUA;
    }

    public String getLastWorkplaceUA() {
        return lastWorkplaceUA;
    }

    public void setLastWorkplaceUA(String lastWorkplaceUA) {
        this.lastWorkplaceUA = lastWorkplaceUA;
    }

    public String getLastJobTitleUA() {
        return lastJobTitleUA;
    }

    public void setLastJobTitleUA(String lastJobTitleUA) {
        this.lastJobTitleUA = lastJobTitleUA;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}