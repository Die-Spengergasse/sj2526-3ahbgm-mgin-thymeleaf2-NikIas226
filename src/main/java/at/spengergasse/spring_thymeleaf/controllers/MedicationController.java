package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Medication;
import at.spengergasse.spring_thymeleaf.entities.MedicationRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medication")
public class MedicationController {

    private MedicationRepository medicationRepository;

    public MedicationController(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @GetMapping("/list")
    public String medications(Model model) {
        model.addAttribute("medications", medicationRepository.findAll());
        return "medlist";
    }

    @GetMapping("/add")
    public String addMedication(Model model) {
        model.addAttribute("medication", new Medication());
        return "add_medication";
    }

    @PostMapping("/add")
    public String addMedication(@ModelAttribute("medication") Medication medication) {
        medicationRepository.save(medication);
        return "redirect:/medication/list";
    }

    @GetMapping("/edit/{id}")
    public String editMedication(@PathVariable Integer id, Model model) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid medication Id:" + id));
        model.addAttribute("medication", medication);
        return "edit_medication";
    }

    @PostMapping("/edit/{id}")
    public String updateMedication(@PathVariable Integer id, @ModelAttribute("medication") Medication medication) {
        medicationRepository.save(medication);
        return "redirect:/medication/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteMedication(@PathVariable Integer id) {
        medicationRepository.deleteById(id);
        return "redirect:/medication/list";
    }
}