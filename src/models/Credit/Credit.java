package models.Credit;

import enums.Decision;

import java.time.LocalDate;

public class Credit {
    private int id;
    private int idClient; // clé étrangère vers Personne
    private LocalDate dateDeCredit;
    private double montantDemande;
    private Double montantOctroye; // peut être null si pas encore validé
    private double tauxInteret;
    private int dureeEnMois;
    private String typeCredit;
    private Decision decision;

    // Enum pour la décision


    // Constructeur vide (obligatoire pour certains frameworks / JDBC)
    public Credit() {}

    // Constructeur complet
    public Credit(int id, int idClient, LocalDate dateDeCredit, double montantDemande, Double montantOctroye,
                  double tauxInteret, int dureeEnMois, String typeCredit, Decision decision) {
        this.id = id;
        this.idClient = idClient;
        this.dateDeCredit = dateDeCredit;
        this.montantDemande = montantDemande;
        this.montantOctroye = montantOctroye;
        this.tauxInteret = tauxInteret;
        this.dureeEnMois = dureeEnMois;
        this.typeCredit = typeCredit;
        this.decision = decision;
    }

    // Getters
    public int getId() { return id; }
    public int getIdClient() { return idClient; }
    public LocalDate getDateDeCredit() { return dateDeCredit; }
    public double getMontantDemande() { return montantDemande; }
    public Double getMontantOctroye() { return montantOctroye; }
    public double getTauxInteret() { return tauxInteret; }
    public int getDureeEnMois() { return dureeEnMois; }
    public String getTypeCredit() { return typeCredit; }
    public Decision getDecision() { return decision; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setIdClient(int idClient) { this.idClient = idClient; }
    public void setDateDeCredit(LocalDate dateDeCredit) { this.dateDeCredit = dateDeCredit; }
    public void setMontantDemande(double montantDemande) { this.montantDemande = montantDemande; }
    public void setMontantOctroye(Double montantOctroye) { this.montantOctroye = montantOctroye; }
    public void setTauxInteret(double tauxInteret) { this.tauxInteret = tauxInteret; }
    public void setDureeEnMois(int dureeEnMois) { this.dureeEnMois = dureeEnMois; }
    public void setTypeCredit(String typeCredit) { this.typeCredit = typeCredit; }
    public void setDecision(Decision decision) { this.decision = decision; }

    // Pour affichage
    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", dateDeCredit=" + dateDeCredit +
                ", montantDemande=" + montantDemande +
                ", montantOctroye=" + montantOctroye +
                ", tauxInteret=" + tauxInteret +
                ", dureeEnMois=" + dureeEnMois +
                ", typeCredit='" + typeCredit + '\'' +
                ", decision=" + decision +
                '}';
    }
}
