package com.example.jpa.service;

import com.example.jpa.model.Produit;
import com.example.jpa.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository repository;

    /**
     * Récupérer tous les produits
     */
    public List<Produit> findAll() {
        return repository.findAll();
    }

    /**
     * Trouver un produit par ID
     */
    public Produit findById(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new Exception("ID invalide");
        }

        Optional<Produit> produit = repository.findById(id);
        if (produit.isEmpty()) {
            throw new Exception("Produit non trouvé avec l'id : " + id);
        }

        return produit.get();
    }

    /**
     * Sauvegarder un produit
     * insertion si id null
     * update si id existant
     */
    public Produit save(Produit produit) throws Exception {

        if (produit == null) {
            throw new Exception("Le produit ne peut pas être null");
        }

        // nom : obligatoire, max 100
        if (produit.getNom() == null || produit.getNom().isBlank()) {
            throw new Exception("Le nom est obligatoire");
        }
        if (produit.getNom().length() > 100) {
            throw new Exception("Le nom ne doit pas dépasser 100 caractères");
        }

        // type de thé
        if (!List.of("Vert", "Noir", "Oolong", "Blanc", "Pu-erh")
                .contains(produit.getTypeThe())) {
            throw new Exception("Type de thé invalide");
        }

        // origine
        if (!List.of("Chine", "Japon", "Inde", "Sri Lanka", "Taiwan")
                .contains(produit.getOrigine())) {
            throw new Exception("Origine invalide");
        }

        // prix : entre 5 et 100
        if (produit.getPrix() < 5 || produit.getPrix() > 100) {
            throw new Exception("Le prix doit être entre 5 et 100");
        }

        // quantité stock : minimum 0
        if (produit.getQuantiteStock() < 0) {
            throw new Exception("La quantité en stock ne peut pas être négative");
        }

        // description : max 500
        if (produit.getDescription() != null &&
                produit.getDescription().length() > 500) {
            throw new Exception("La description ne doit pas dépasser 500 caractères");
        }

        // date réception
        if (produit.getDateReception() == null) {
            throw new Exception("La date de réception est obligatoire");
        }

        return repository.save(produit);
    }

    /**
     * Supprimer un produit par ID
     */
    public void deleteById(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new Exception("ID invalide");
        }

        if (!repository.existsById(id)) {
            throw new Exception("Impossible de supprimer : produit inexistant");
        }

        repository.deleteById(id);
    }
}
