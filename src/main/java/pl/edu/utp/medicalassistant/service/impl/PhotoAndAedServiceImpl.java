package pl.edu.utp.medicalassistant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.utp.medicalassistant.model.Aed;
import pl.edu.utp.medicalassistant.repository.AedRepostiory;
import pl.edu.utp.medicalassistant.service.PhotoAndAEDService;

import java.util.List;

@Service
public class PhotoAndAedServiceImpl implements PhotoAndAEDService{

    private final AedRepostiory aedRepostiory;

    @Autowired
    public PhotoAndAedServiceImpl(AedRepostiory aedRepostiory) {
        this.aedRepostiory = aedRepostiory;
    }

    @Override
    public List<Aed> getAedList() {
        return aedRepostiory.findAll();
    }

    @Override
    public void saveAedList(List<Aed> aeds) {
        aedRepostiory.saveAll(aeds);
    }


}
