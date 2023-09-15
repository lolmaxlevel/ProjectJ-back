package com.lolmaxlevel.backend_j.controller;

import com.lolmaxlevel.backend_j.dto.ResponseMessage;
import com.lolmaxlevel.backend_j.dto.SchoolAddResponse;
import com.lolmaxlevel.backend_j.model.SchoolMaterial;
import com.lolmaxlevel.backend_j.repository.SchoolMaterialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/school")
public class SchoolMaterialController {

    private final SchoolMaterialRepository schoolMaterialRepository;

    SchoolMaterialController(SchoolMaterialRepository SchoolMaterialRepository) {
        this.schoolMaterialRepository = SchoolMaterialRepository;
    }

    @PostMapping("/add")
    SchoolAddResponse addSchoolMaterial(@RequestParam("name") String material_name,
                                        @RequestParam("link") String material_link,
                                        @RequestParam("grade") String grade,
                                        @RequestParam("type") String type) {
        log.info("Add school material request: {}", material_name);
        try {
            SchoolMaterial schoolMaterial = schoolMaterialRepository.save(new SchoolMaterial(material_name, material_link, grade, type));
            return new SchoolAddResponse("Added", schoolMaterial.getName(), schoolMaterial.getLink(), schoolMaterial.getId(), schoolMaterial.getGrade(), schoolMaterial.getType());
        } catch (Exception e) {
            return new SchoolAddResponse("Error: " + e.getMessage());
        }

    }

    @GetMapping(value = "/all-materials")
    SchoolMaterial[] getAllSchoolMaterials() {
        log.info("Get all school materials request");
        return schoolMaterialRepository.findAll().toArray(new SchoolMaterial[0]);
    }

    @GetMapping(value = "/materials-by-grade")
    SchoolMaterial[] getSchoolMaterialsByGrade(@RequestParam String grade) {
        log.info("Get school materials by grade request: {}", grade);
        return schoolMaterialRepository.findAllByGrade(grade).toArray(new SchoolMaterial[0]);
    }

    @PostMapping(value = "/delete-material")
    ResponseMessage deleteSchoolMaterial(@RequestParam Long id) {
        log.info("Delete school material request: {}", id);
        schoolMaterialRepository.deleteById(id);
        return new ResponseMessage("Deleted");
    }

    @PutMapping(value = "/update-material")
    ResponseMessage updateSchoolMaterial(@RequestParam Long id,
                                         @RequestParam String name,
                                         @RequestParam String link,
                                         @RequestParam String grade,
                                         @RequestParam String type) {
        log.info("Update school material request: {}", id);
        SchoolMaterial material = schoolMaterialRepository.findById(id).orElse(null);
        if (material == null) {
            return new ResponseMessage("Not found");
        }
        material.setName(name);
        material.setLink(link);
        material.setGrade(grade);
        material.setType(type);
        schoolMaterialRepository.save(material);
        return new ResponseMessage("Updated");
    }


}
