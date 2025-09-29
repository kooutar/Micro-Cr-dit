package models.Person;

import enums.Secteur;
import enums.TypeContrat;

import java.time.LocalDate;

public class Employe extends Persone{
    private double salaire;
    private int anciennete;        // en années
    private String poste;
    private TypeContrat typeContrat;    // ex: CDI, CDD
    private Secteur secteur;        // PUBLIC, GRANDE_ENTREPRISE, PME

    // Constructeur
    public Employe(String nom, String prenom, LocalDate dateNaissance, String ville,
                   int nombreEnfants, boolean investissement, double placement,
                   String situationFamiliale,
                   double salaire, int anciennete, String poste,
                   TypeContrat typeContrat, Secteur secteur) {
        super(nom, prenom, dateNaissance, ville, nombreEnfants, investissement, placement, situationFamiliale);
        this.salaire = salaire;
        this.anciennete = anciennete;
        this.poste = poste;
        this.typeContrat = typeContrat;
        this.secteur = secteur;
    }

    // Getters / Setters
    public double getSalaire() { return salaire; }
    public void setSalaire(double salaire) { this.salaire = salaire; }

    public int getAnciennete() { return anciennete; }
    public void setAnciennete(int anciennete) { this.anciennete = anciennete; }

    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }

    public TypeContrat getTypeContrat() { return typeContrat; }
    public void setTypeContrat(TypeContrat typeContrat) { this.typeContrat = typeContrat; }

    public Secteur getSecteur() { return secteur; }
    public void setSecteur(Secteur secteur) { this.secteur = secteur; }

    // Implémentation du calcul de score spécifique à un employé
    @Override
    public double calculerScore() {
        double score = 50;
        score += (salaire / 1000);
        score += anciennete * 2;
//        if ("CDI".equalsIgnoreCase(typeContrat)) score += 10;
//        if ("PUBLIC".equalsIgnoreCase(secteur)) score += 5;

        setScore(score);
        return score;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", salaire=" + salaire +
                ", anciennete=" + anciennete +
                ", poste='" + poste + '\'' +
                ", typeContrat='" + typeContrat + '\'' +
                ", secteur='" + secteur + '\'' +
                ", score=" + getScore() +
                '}';
    }
}
