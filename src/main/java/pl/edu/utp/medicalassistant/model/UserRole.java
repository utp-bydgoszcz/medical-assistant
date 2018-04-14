package pl.edu.utp.medicalassistant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements GrantedAuthority {

	private String role;

	@Override
	public String getAuthority() {
		return role;
	}
}
