package pl.oncode.glass.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table( name = "user" )
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @NotEmpty(message = "Username can't be empty")
    @Size(max = 30, message = "Username can have maximum of 30 characters")
    private String username;

    @NotEmpty(message = "Password can't be empty")
    @Size(max = 100, message = "Password can have maximum of 100 characters")
    private String password;

    @NotEmpty(message = "First Name can't be empty")
    @Size(max = 30, message = "Firstname can have maximum of 30 characters")
    private String firstname;

    @NotEmpty(message = "Last Name can't be empty")
    @Size(max = 30, message = "Lastname can have maximum of 30 characters")
    private String lastname;

    private int active;

    @Size(max = 100, message = "Roles can have maximum of 30 characters")
    private String roles = "";

    @Size(max = 100, message = "Username can have maximum of 30 characters")
    private String permissions = "";

    public User(String username,
                String password,
                String firstname,
                String lastname,
                String roles,
                String permissions) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roles = roles;
        this.permissions = permissions;
        this.active = 1;
    }

    protected User() {}

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFullname() {
        return this.firstname + " " + this.lastname;
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

    public static class UserBuilder {
        private String username;
        private String password;
        private String firstname;
        private String lastname;
        private String roles;
        private String permissions;

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserBuilder setRoles(String roles) {
            this.roles = roles;
            return this;
        }

        public UserBuilder setPermissions(String permissions) {
            this.permissions = permissions;
            return this;
        }

        public User createUser() {
            return new User(username, password, firstname, lastname, roles, permissions);
        }
    }

}
