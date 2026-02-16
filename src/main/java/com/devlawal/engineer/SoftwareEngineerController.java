package com.devlawal.engineer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/softwaare-engineer")
public class SoftwareEngineerController {

    @GetMapping
    public List<SoftwareEngineer> getSoftwareEngineers() {
        return List.of(
                new SoftwareEngineer(1, "Lawal", List.of("Java", "SpringBoot", "React", "JavaScript")),
                new SoftwareEngineer(2, "Reza", List.of("Python", "Django", "React", "JavaScript")),
                new SoftwareEngineer(3, "Ami", List.of("C#", ".Net", "React", "JavaScript"))
        );
    }
}
