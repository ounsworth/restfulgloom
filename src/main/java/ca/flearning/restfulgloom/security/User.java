package ca.flearning.restfulgloom.security;

public class User {

    private String name;
    private String role = "User";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
