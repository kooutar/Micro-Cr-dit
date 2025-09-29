package models.Person;

import java.time.LocalDate;

public class Professionnel extends Persone {

    private double revenu;
    private String immatriculationFiscale;
    private String secteurActivite;   // agriculture, commerce, service...
    private String activite;          // avocat, mécanicien, etc.

    // Constructeur
    public Professionnel(String nom, String prenom, LocalDate dateNaissance, String ville,
                         int nombreEnfants, boolean investissement, double placement,
                         String situationFamiliale,
                         double revenu, String immatriculationFiscale,
                         String secteurActivite, String activite) {
        super(nom, prenom, dateNaissance, ville, nombreEnfants, investissement, placement, situationFamiliale);
        this.revenu = revenu;
        this.immatriculationFiscale = immatriculationFiscale;
        this.secteurActivite = secteurActivite;
        this.activite = activite;
    }

    // Getters / Setters
    public double getRevenu() { return revenu; }
    public void setRevenu(double revenu) { this.revenu = revenu; }

    public String getImmatriculationFiscale() { return immatriculationFiscale; }
    public void setImmatriculationFiscale(String immatriculationFiscale) { this.immatriculationFiscale = immatriculationFiscale; }

    public String getSecteurActivite() { return secteurActivite; }
    public void setSecteurActivite(String secteurActivite) { this.secteurActivite = secteurActivite; }

    public String getActivite() { return activite; }
    public void setActivite(String activite) { this.activite = activite; }

    // Implémentation du calcul de score spécifique à un professionnel
    @Override
    public double calculerScore() {
        double score = 45;
        score += (revenu / 2000);
        if ("commerce".equalsIgnoreCase(secteurActivite)) score += 5;
        if ("service".equalsIgnoreCase(secteurActivite)) score += 3;

        setScore(score);
        return score;
    }

    @Override
    public String toString() {
        return "Professionnel{" +
                "nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", revenu=" + revenu +
                ", secteurActivite='" + secteurActivite + '\'' +
                ", activite='" + activite + '\'' +
                ", score=" + getScore() +
                '}';
    }
}
