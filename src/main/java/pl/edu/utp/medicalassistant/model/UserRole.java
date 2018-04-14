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

	private Role role;

	@Override
	public String getAuthority() {
		return role.getId();
	}

	public static enum Role {
		MEDICAL_RESCUER("dedical-rescuer", "Ratownik medyczny"),
		RESCUER("rescuer", "Ratownik"),
		PASSERBY("other-person", "Niewykwalifikowany");

		@Getter
		private final String id;
		private final String name;

		private Role(String id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

	}
}
