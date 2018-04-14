package pl.edu.utp.medicalassistant.security.model;

import lombok.Data;

import java.security.Principal;

/**
 * Created by Cezary Kolaszewski on 29.08.2017.
 */
@Data
public class PrincipalImpl implements Principal {
    private String name;

    public PrincipalImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
