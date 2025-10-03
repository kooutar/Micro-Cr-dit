package Service;

import DAO.Reposetry.CreditReposetry;
import DAO.Reposetry.EmployeeReposetry;
import enums.Decision;
import models.Person.Employe;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class CreditService {
 private CreditReposetry creditReposetry =new CreditReposetry();
 private EmployeeReposetry employeeReposetry= new EmployeeReposetry();
 public CreditService() throws SQLException {}

    public void ajoutCredit(int idClient,  double montantDemande,
                            double tauxInteret, int dureeEnMois,
                            String typeCredit, Decision decision) throws SQLException {
        Optional<Integer> idCredit = Optional.empty();
        LocalDate dateDeCredit = LocalDate.now();
        Employe employe = employeeReposetry.estClient(idClient); // récupère l'employé
        double montantOctroye = 0.0;

        if (employe != null) {
            if (!employe.getEstClient()) {
                // ----- Cas nouveau client -----
                montantOctroye = employe.getSalaire() * 4;
                creditReposetry.AjouterModifier(idClient,dateDeCredit,montantDemande,montantOctroye,tauxInteret,dureeEnMois,typeCredit,decision,idCredit);
            } else {
                // ----- Cas client existant -----
                double score = employe.getScore();

                if (score >= 60 && score <= 80) {
                    montantOctroye = employe.getSalaire() * 7;
                } else if (score > 80) {
                    montantOctroye = employe.getSalaire() * 10;
                } else {
                    System.out.println("Score insuffisant pour obtenir un crédit !");
                    return; // on arrête, pas d’ajout
                }
            }

            // Vérifier si le montant demandé ne dépasse pas le montant autorisé
            if (montantDemande <= montantOctroye) {
                // Appel à la fonction AjouterModifier Credit
              /*  AjouterModifier(
                        idClient,
                        dateDeCredit,
                        montantDemande,
                        montantOctroye,
                        tauxInteret,
                        dureeEnMois,
                        typeCredit,
                        decision,
                        idCredit
                );*/
                System.out.println("Crédit accordé avec un montant octroyé de : " + montantOctroye);
            } else {
                System.out.println("Le montant demandé dépasse le maximum autorisé (" + montantOctroye + ")");
            }
        } else {
            System.out.println("Aucun employé trouvé avec cet ID !");
        }
    }



}







