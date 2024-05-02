package dan.ms.tp.msusuarios.dto;

import lombok.Data;

@Data
public class AuthTokenDto {
    public String token;

    public AuthTokenDto(String token) {
        this.token = token;
    }
    
    public AuthTokenDto() {
        super();
    }
}
