package pl.oncode.glass.web.dto.addUser;

import pl.oncode.glass.model.User;

import javax.validation.constraints.NotEmpty;

public class AddUserDto {
    @NotEmpty(message = "Username can't be empty")
    private String username;

    @NotEmpty(message = "Password can't be empty")
    private String password;

    @NotEmpty(message = "First Name can't be empty")
    private String firstname;

    @NotEmpty(message = "Last Name can't be empty")
    private String lastname;

    private int active;
    private String roles = "";
    private String permissions = "";

    public AddUserDto(String username,
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
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public User createUser() {
        return new User.UserBuilder()
                .setUsername(this.getUsername())
                .setPassword(this.getPassword())
                .setFirstname(this.getFirstname())
                .setLastname(this.getLastname())
                .setRoles(this.getRoles())
                .setPermissions(this.getPermissions())
                .createUser();
    }
}
