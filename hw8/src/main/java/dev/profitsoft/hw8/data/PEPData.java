package dev.profitsoft.hw8.data;

import dev.profitsoft.hw8.dtos.Declaration;
import dev.profitsoft.hw8.dtos.RelatedCompany;
import dev.profitsoft.hw8.dtos.RelatedCountry;
import dev.profitsoft.hw8.dtos.RelatedPerson;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Represents information about PEP (politically exposed person)
 */
@Document("peps")
public class PEPData {
    @Id
    private String id;
    @Field("first_name")
    private String firstNameUA;
    @Field("first_name_en")
    private String firstNameEN;
    private String patronymic;
    @Field("patronymic_en")
    private String patronymicEN;
    @Field("last_name")
    private String lastNameUA;
    @Field("last_name_en")
    private String lastNameEN;
    @Field("full_name")
    private String fullNameUA;
    @Field("full_name_en")
    private String fullNameEN;
    private String names;
    @Field("also_known_as_en")
    private String akaEN;
    @Field("also_known_as_uk")
    private String akaUA;
    @Field("date_of_birth")
    private String dateOfBirth;
    private Boolean died;
    @Field("is_pep")
    private Boolean isPEP;
    @Field("type_of_official")
    private String typeOfOfficialUA;
    @Field("type_of_official_en")
    private String typeOfOfficialEN;
    private List<Declaration> declarations;
    @Field("last_workplace")
    private String lastWorkplaceUA;
    @Field("last_workplace_en")
    private String lastWorkplaceEN;
    @Field("last_job_title")
    private String lastJobTitleUA;
    @Field("last_job_title_en")
    private String lastJobTitleEN;
    @Field("reason_of_termination")
    private String reasonOfTerminationUA;
    @Field("reason_of_termination_en")
    private String reasonOfTerminationEN;
    @Field("termination_date_human")
    private String terminationDate;
    @Field("related_companies")
    private List<RelatedCompany> relatedCompanies;
    @Field("related_persons")
    private List<RelatedPerson> relatedPersons;
    @Field("related_countries")
    private List<RelatedCountry> relatedCountries;
    private String url;
    private String photo;
    @Field("wiki_uk")
    private String wikiUA;
    @Field("wiki_en")
    private String wikiEN;
    @Field("reputation_assets_uk")
    private String reputationAssetsUA;
    @Field("reputation_assets_en")
    private String reputationAssetsEN;
    @Field("reputation_convictions_uk")
    private String reputationConvictionsUA;
    @Field("reputation_convictions_en")
    private String reputationConvictionsEN;
    @Field("reputation_crimes_uk")
    private String reputationCrimesUA;
    @Field("reputation_crimes_en")
    private String reputationCrimesEN;
    @Field("reputation_manhunt_uk")
    private String reputationManhuntUA;
    @Field("reputation_manhunt_en")
    private String reputationManhuntEN;
    @Field("reputation_sanctions_uk")
    private String reputationSanctionsUA;
    @Field("reputation_sanctions_en")
    private String reputationSanctionsEN;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstNameUA() {
        return firstNameUA;
    }

    public void setFirstNameUA(String firstNameUA) {
        this.firstNameUA = firstNameUA;
    }

    public String getFirstNameEN() {
        return firstNameEN;
    }

    public void setFirstNameEN(String firstNameEN) {
        this.firstNameEN = firstNameEN;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPatronymicEN() {
        return patronymicEN;
    }

    public void setPatronymicEN(String patronymicEN) {
        this.patronymicEN = patronymicEN;
    }

    public String getLastNameUA() {
        return lastNameUA;
    }

    public void setLastNameUA(String lastNameUA) {
        this.lastNameUA = lastNameUA;
    }

    public String getLastNameEN() {
        return lastNameEN;
    }

    public void setLastNameEN(String lastNameEN) {
        this.lastNameEN = lastNameEN;
    }

    public String getFullNameUA() {
        return fullNameUA;
    }

    public void setFullNameUA(String fullNameUA) {
        this.fullNameUA = fullNameUA;
    }

    public String getFullNameEN() {
        return fullNameEN;
    }

    public void setFullNameEN(String fullNameEN) {
        this.fullNameEN = fullNameEN;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getAkaEN() {
        return akaEN;
    }

    public void setAkaEN(String akaEN) {
        this.akaEN = akaEN;
    }

    public String getAkaUA() {
        return akaUA;
    }

    public void setAkaUA(String akaUA) {
        this.akaUA = akaUA;
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

    public String getTypeOfOfficialEN() {
        return typeOfOfficialEN;
    }

    public void setTypeOfOfficialEN(String typeOfOfficialEN) {
        this.typeOfOfficialEN = typeOfOfficialEN;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(List<Declaration> declarations) {
        this.declarations = declarations;
    }

    public String getLastWorkplaceUA() {
        return lastWorkplaceUA;
    }

    public void setLastWorkplaceUA(String lastWorkplaceUA) {
        this.lastWorkplaceUA = lastWorkplaceUA;
    }

    public String getLastWorkplaceEN() {
        return lastWorkplaceEN;
    }

    public void setLastWorkplaceEN(String lastWorkplaceEN) {
        this.lastWorkplaceEN = lastWorkplaceEN;
    }

    public String getLastJobTitleUA() {
        return lastJobTitleUA;
    }

    public void setLastJobTitleUA(String lastJobTitleUA) {
        this.lastJobTitleUA = lastJobTitleUA;
    }

    public String getLastJobTitleEN() {
        return lastJobTitleEN;
    }

    public void setLastJobTitleEN(String lastJobTitleEN) {
        this.lastJobTitleEN = lastJobTitleEN;
    }

    public String getReasonOfTerminationUA() {
        return reasonOfTerminationUA;
    }

    public void setReasonOfTerminationUA(String reasonOfTerminationUA) {
        this.reasonOfTerminationUA = reasonOfTerminationUA;
    }

    public String getReasonOfTerminationEN() {
        return reasonOfTerminationEN;
    }

    public void setReasonOfTerminationEN(String reasonOfTerminationEN) {
        this.reasonOfTerminationEN = reasonOfTerminationEN;
    }

    public String getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(String terminationDate) {
        this.terminationDate = terminationDate;
    }

    public List<RelatedCompany> getRelatedCompanies() {
        return relatedCompanies;
    }

    public void setRelatedCompanies(List<RelatedCompany> relatedCompanies) {
        this.relatedCompanies = relatedCompanies;
    }

    public List<RelatedPerson> getRelatedPersons() {
        return relatedPersons;
    }

    public void setRelatedPersons(List<RelatedPerson> relatedPersons) {
        this.relatedPersons = relatedPersons;
    }

    public List<RelatedCountry> getRelatedCountries() {
        return relatedCountries;
    }

    public void setRelatedCountries(List<RelatedCountry> relatedCountries) {
        this.relatedCountries = relatedCountries;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getWikiUA() {
        return wikiUA;
    }

    public void setWikiUA(String wikiUA) {
        this.wikiUA = wikiUA;
    }

    public String getWikiEN() {
        return wikiEN;
    }

    public void setWikiEN(String wikiEN) {
        this.wikiEN = wikiEN;
    }

    public String getReputationAssetsUA() {
        return reputationAssetsUA;
    }

    public void setReputationAssetsUA(String reputationAssetsUA) {
        this.reputationAssetsUA = reputationAssetsUA;
    }

    public String getReputationAssetsEN() {
        return reputationAssetsEN;
    }

    public void setReputationAssetsEN(String reputationAssetsEN) {
        this.reputationAssetsEN = reputationAssetsEN;
    }

    public String getReputationConvictionsUA() {
        return reputationConvictionsUA;
    }

    public void setReputationConvictionsUA(String reputationConvictionsUA) {
        this.reputationConvictionsUA = reputationConvictionsUA;
    }

    public String getReputationConvictionsEN() {
        return reputationConvictionsEN;
    }

    public void setReputationConvictionsEN(String reputationConvictionsEN) {
        this.reputationConvictionsEN = reputationConvictionsEN;
    }

    public String getReputationCrimesUA() {
        return reputationCrimesUA;
    }

    public void setReputationCrimesUA(String reputationCrimesUA) {
        this.reputationCrimesUA = reputationCrimesUA;
    }

    public String getReputationCrimesEN() {
        return reputationCrimesEN;
    }

    public void setReputationCrimesEN(String reputationCrimesEN) {
        this.reputationCrimesEN = reputationCrimesEN;
    }

    public String getReputationManhuntUA() {
        return reputationManhuntUA;
    }

    public void setReputationManhuntUA(String reputationManhuntUA) {
        this.reputationManhuntUA = reputationManhuntUA;
    }

    public String getReputationManhuntEN() {
        return reputationManhuntEN;
    }

    public void setReputationManhuntEN(String reputationManhuntEN) {
        this.reputationManhuntEN = reputationManhuntEN;
    }

    public String getReputationSanctionsUA() {
        return reputationSanctionsUA;
    }

    public void setReputationSanctionsUA(String reputationSanctionsUA) {
        this.reputationSanctionsUA = reputationSanctionsUA;
    }

    public String getReputationSanctionsEN() {
        return reputationSanctionsEN;
    }

    public void setReputationSanctionsEN(String reputationSanctionsEN) {
        this.reputationSanctionsEN = reputationSanctionsEN;
    }
}