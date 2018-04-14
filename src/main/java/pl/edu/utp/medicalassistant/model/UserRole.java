package pl.edu.utp.medicalassistant.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
@Data
public class UserRole implements GrantedAuthority{

    private Long id;
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
