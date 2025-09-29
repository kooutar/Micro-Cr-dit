package models.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Persone {
    private int id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;   // tu peux utiliser LocalDate si tu veux
    private String ville;
    private int nombreEnfants;
    private boolean investissement;
    private double placement;
    private String situationFamiliale;
    private LocalDateTime createdAt;
    private double score;

    public Persone(String nom, String prenom, LocalDate dateNaissance, String ville,
                    int nombreEnfants, boolean investissement, double placement,
                    String situationFamiliale) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.nombreEnfants = nombreEnfants;
        this.investissement = investissement;
        this.placement = placement;
        this.situationFamiliale = situationFamiliale;
        this.createdAt = LocalDateTime.now();
        this.score = 0.0;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public int getNombreEnfants() { return nombreEnfants; }
    public void setNombreEnfants(int nombreEnfants) { this.nombreEnfants = nombreEnfants; }

    public boolean getInvestissement() { return investissement; }
    public void setInvestissement(boolean investissement) { this.investissement = investissement; }

    public double getPlacement() { return placement; }
    public void setPlacement(double placement) { this.placement = placement; }

    public String getSituationFamiliale() { return situationFamiliale; }
    public void setSituationFamiliale(String situationFamiliale) { this.situationFamiliale = situationFamiliale; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    // Méthode abstraite (chaque type de Personne calcule son score différemment)
    public abstract double calculerScore();

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", ville='" + ville + '\'' +
                ", enfants=" + nombreEnfants +
                ", investissement=" + investissement +
                ", placement=" + placement +
                ", situationFamiliale='" + situationFamiliale + '\'' +
                ", score=" + score +
                ", createdAt=" + createdAt +
                '}';
    }

}
