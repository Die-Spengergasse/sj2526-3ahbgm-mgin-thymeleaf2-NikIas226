package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

@Entity

public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private int id;
    @Column(name = "m_name")
    private String name;
    @Column(name = "m_dosage")
    @Min(value = 0, message = "Dosage darf nicht negativ sein")
    private int dosage;
    @Column (name = "m_expiration_date")
    @FutureOrPresent(message = "Das Ablaufdatum darf nicht in der Vergangenheit liegen")
    private LocalDate expirationDate;

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
