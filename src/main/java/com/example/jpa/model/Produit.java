package com.example.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;
    private String typeThe;
    private String origine;
    private Float prix;
    private Integer quantiteStock;
    @Column(length = 500)
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReception;

}
