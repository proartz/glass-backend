package pl.oncode.glass.web.dto.fetchUser;

import pl.oncode.glass.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FetchUserDto {

    private long id;
    private String username;
    private String firstname;
    private String lastname;
    private int active;
    private String roles = "";
    private String permissions = "";

    public FetchUserDto(long id,
                String username,
                String firstname,
                String lastname,
                int active,
                String roles,
                String permissions) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.active = active;
        this.roles = roles;
        this.permissions = permissions;
    }

    public FetchUserDto() {}

    public Long getId() {
            return id;
        }

    public String getUsername() {
        return username;
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

    public static FetchUserDto createFetchUserDto(User user) {
        return new FetchUserDto.FetchUserDtoBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setActive(user.getActive())
                .setRoles(user.getRoles())
                .setPermissions(user.getPermissions())
                .createFetchUserDto();
    }

    public static class FetchUserDtoBuilder {
        private long id;
        private String username;
        private String firstname;
        private String lastname;
        private int active;
        private String roles = "";
        private String permissions = "";

        public FetchUserDtoBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public FetchUserDtoBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public FetchUserDtoBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public FetchUserDtoBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public FetchUserDtoBuilder setActive(int active) {
            this.active = active;
            return this;
        }

        public FetchUserDtoBuilder setRoles(String roles) {
            this.roles = roles;
            return this;
        }

        public FetchUserDtoBuilder setPermissions(String permissions) {
            this.permissions = permissions;
            return this;
        }

        public FetchUserDto createFetchUserDto() {
            return new FetchUserDto(id, username, firstname, lastname, active, roles, permissions);
        }
    }
}
