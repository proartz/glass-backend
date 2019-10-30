package pl.oncode.glass.web.dto.jwtResponse;

public class JWTResponseDto {

    private String token;

    public JWTResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
