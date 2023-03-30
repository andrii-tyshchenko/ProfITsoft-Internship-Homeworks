package dev.profitsoft.hw10.data;

import java.util.List;

public class Role {
    private String id;
    private List<String> privileges;

    public Role(String id, List<String> privileges) {
        this.id = id;
        this.privileges = privileges;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }
}