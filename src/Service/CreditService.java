package Service;

import DAO.Reposetry.CreditReposetry;
import DAO.Reposetry.EcheancesReposetry;
import DAO.Reposetry.EmployeeReposetry;
import enums.Decision;
import models.Person.Employe;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class CreditService {
 private CreditReposetry creditReposetry =new CreditReposetry();
 private EmployeeReposetry employeeReposetry= new EmployeeReposetry();
    private EcheancesReposetry echeancesReposetry= new EcheancesReposetry();
 public CreditService() throws SQLException {}

    public void ajoutCredit(int idClient, double montantDemande,
                            double tauxInteret, int dureeEnMois,
                            String typeCredit) throws SQLException {

        Optional<Integer> idCredit = Optional.empty();
        LocalDate dateDeCredit = LocalDate.now();
        Employe employe = employeeReposetry.estClient(idClient); // récupère l'employé
        double montantOctroye = 0.0;
        Decision decision = null;

        if (employe != null) {
            double score = employe.getScore();
            System.out.println("score de bdd "+score);
            // ---- Détermination de la décision en fonction du score ----
            if (score >= 80) {
                decision = Decision.ACCORD_IMMEDIAT;

            } else if (score >= 60) {
                decision = Decision.ETUDE_MANUELLE;
            } else {
                decision = Decision.REFUS_AUTOMATIQUE;
            }

            // ---- Cas refus automatique ----
            if (decision == Decision.REFUS_AUTOMATIQUE) {
                System.out.println("Score insuffisant (" + score + ") → Crédit refusé automatiquement !");
                creditReposetry.AjouterModifier(
                        idClient, dateDeCredit, montantDemande, 0.0,
                        tauxInteret, dureeEnMois, typeCredit, decision, idCredit
                );
                return;
            }

            // ---- Calcul du montant octroyé ----
            if (!employe.getEstClient()) {
                // Nouveau client
                if (score >= 60 && score <= 80) {
                    montantOctroye = employe.getSalaire() * 4;
                }
            } else {
                // Client existant
                if (score >= 60 && score <= 80) {
                    montantOctroye = employe.getSalaire() * 7;
                } else if (score > 80) {
                    montantOctroye = employe.getSalaire() * 10;
                }
            }
            echeancesReposetry.genererEcheances(idClient,montantOctroye,dureeEnMois,dateDeCredit);
            // ---- Vérification du montant demandé ----
            if (montantDemande <= montantOctroye) {
                creditReposetry.AjouterModifier(
                        idClient, dateDeCredit, montantDemande, montantOctroye,
                        tauxInteret, dureeEnMois, typeCredit, decision, idCredit
                );
                System.out.println("Décision: " + decision + " | Crédit accordé avec montant octroyé : " + montantOctroye);
            } else {
                System.out.println("Le montant demandé (" + montantDemande + ") dépasse le maximum autorisé (" + montantOctroye + ")");
            }
        } else {
            System.out.println("Aucun employé trouvé avec cet ID !");
        }
    }




}







