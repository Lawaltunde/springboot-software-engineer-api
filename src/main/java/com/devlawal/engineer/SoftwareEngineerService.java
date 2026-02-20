package com.devlawal.engineer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public void addSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }

    public void deleteSoftwareEngineerById(Integer id) {
        boolean exists = softwareEngineerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("SoftwareEngineer with id " + id + " doesn't exist");
        }
        softwareEngineerRepository.deleteById(id);
    }

    public void updateSoftwareEngineer(Integer id, SoftwareEngineer updatedSoftwareEngineer) {
        SoftwareEngineer update = softwareEngineerRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("SoftwareEngineer with id " + id + " doesn't exist"));
        update.setName(updatedSoftwareEngineer.getName());
        update.setStack(updatedSoftwareEngineer.getStack());
        softwareEngineerRepository.save(update);
    }
}
