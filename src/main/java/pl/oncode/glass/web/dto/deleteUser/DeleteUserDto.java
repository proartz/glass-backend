package pl.oncode.glass.web.dto.deleteUser;

import pl.oncode.glass.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteUserDto {

    private long id;

    public DeleteUserDto(long id) {
        this.id = id;
    }

    public DeleteUserDto() {
    }

    public long getId() {
        return id;
    }
}
