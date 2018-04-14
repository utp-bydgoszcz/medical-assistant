package pl.edu.utp.medicalassistant.service;

import pl.edu.utp.medicalassistant.model.Aed;

import java.util.List;

public interface PhotoAndAEDService {


    List<Aed> getAedList();

    void saveAedList(List<Aed> aeds);
}
