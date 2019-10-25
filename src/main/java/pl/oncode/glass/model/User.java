package pl.oncode.glass.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table( name = "user" )
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotEmpty(message = "Username can't be empty")
    private String username;

    @NotEmpty(message = "Password can't be empty")
    private String password;

    private int active;

    private String roles = "";

    private String permissions = "";

    public User(String username, String password, String roles, String permissions) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = 1;
    }

    protected User() {}

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public List<String> getRoleList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList() {
        if(this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
