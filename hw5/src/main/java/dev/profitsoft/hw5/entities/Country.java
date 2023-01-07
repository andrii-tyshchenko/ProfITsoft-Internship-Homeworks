package dev.profitsoft.hw5.entities;

/**
 * Represents some information about country.
 */
public class Country {
    private Integer id;
    private String name;
    /**
     * ISO 3166-1 alpha-2 code of country (i.e. Ukraine - UA)
     */
    private String isoCode;

    public Country() {
    }

    public Country(Integer id, String name, String isoCode) {
        this.id = id;
        this.name = name;
        this.isoCode = isoCode;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }
}