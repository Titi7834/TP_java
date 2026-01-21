package com.example.jpa.controller;

import com.example.jpa.model.Produit;
import com.example.jpa.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    /**
     * Liste des produits
     */
    @GetMapping("/")
    public String listeProduits(Model model) {
        model.addAttribute("listeproduits", produitService.findAll());
        return "index";
    }

    /**
     * Formulaire d'ajout
     */
    @GetMapping("/nouveau")
    public String formAjout(Model model) {
        model.addAttribute("produit", new Produit());
        return "formulaire-produit";
    }

    /**
     * Enregistrer (ajout ou modification)
     */
    @PostMapping("/enregistrer")
    public String saveProduit(@ModelAttribute Produit produit) throws Exception {
        produitService.save(produit);
        return "redirect:/";
    }

    /**
     * Afficher le formulaire de modification
     */
    @GetMapping("/modifier/{id}")
    public String formModification(@PathVariable Long id, Model model) throws Exception {
        Produit produit = produitService.findById(id);
        model.addAttribute("produit", produit);
        return "formulaire-produit";
    }

    /**
     * Supprimer un produit
     */
    @GetMapping("/supprimer/{id}")
    public String supprimerProduit(@PathVariable Long id) throws Exception {
        produitService.deleteById(id);
        return "redirect:/";
    }
}