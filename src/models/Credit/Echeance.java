package models.Credit;



import enums.StatutPaiement;

import java.time.LocalDate;

public class Echeance {
    private int id;
    private int idCredit;              // clé étrangère vers Credit
    private LocalDate dateEcheance;    // date prévue
    private double mensualite;         // montant de la mensualité
    private LocalDate dateDePaiement;  // null si pas encore payé
    private StatutPaiement statutPaiement; // enum
    // ---- Constructeurs ----
    public Echeance() {}

    public Echeance(int idCredit, LocalDate dateEcheance, double mensualite,
                    LocalDate dateDePaiement, StatutPaiement statutPaiement) {
        this.idCredit = idCredit;
        this.dateEcheance = dateEcheance;
        this.mensualite = mensualite;
        this.dateDePaiement = dateDePaiement;
        this.statutPaiement = statutPaiement;
    }

    // ---- Getters & Setters ----
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public double getMensualite() {
        return mensualite;
    }

    public void setMensualite(double mensualite) {
        this.mensualite = mensualite;
    }

    public LocalDate getDateDePaiement() {
        return dateDePaiement;
    }

    public void setDateDePaiement(LocalDate dateDePaiement) {
        this.dateDePaiement = dateDePaiement;
    }

    public StatutPaiement getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(StatutPaiement statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    @Override
    public String toString() {
        return "Echeance{" +
                "id=" + id +
                ", idCredit=" + idCredit +
                ", dateEcheance=" + dateEcheance +
                ", mensualite=" + mensualite +
                ", dateDePaiement=" + dateDePaiement +
                ", statutPaiement=" + statutPaiement +
                '}';
    }
}
