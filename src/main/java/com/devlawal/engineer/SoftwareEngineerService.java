package com.devlawal.engineer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;
    private final AiService aiService;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository, AiService aiService) {
        this.softwareEngineerRepository = softwareEngineerRepository;
        this.aiService = aiService;
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public void addSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        String message = """
                Based on the provided tech stack %s by %s, please provide a well-structured and summarized learning path for the individual.
                Ensure it is well formatted and written in clear, human-sounding language.
                """.formatted(softwareEngineer.getStack(), softwareEngineer.getName());
        String response = aiService.chat(message);
        softwareEngineer.setRecommendedLearningPath(response);
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
