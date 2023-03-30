package dev.profitsoft.hw10.dtos;

import java.util.List;

public class UserSaveDto {
    private String email;
    private String password;
    private String role;
    private boolean enabled;
    private List<String> booksIds;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getBooksIds() {
        return booksIds;
    }

    public void setBooksIds(List<String> booksIds) {
        this.booksIds = booksIds;
    }
}