package DAO.Reposetry;

import Config.BaseDonne;
import enums.Decision;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class CreditReposetry {
    private Connection connection;
    public CreditReposetry() throws SQLException {
        this.connection = BaseDonne.getInstance().getConnection();
    }


    public void AjouterModifier(int idClient, LocalDate dateDeCredit, double montantDemande, Double montantOctroye, double tauxInteret, int dureeEnMois, String typeCredit, Decision decision, Optional<Integer> idCredit) throws SQLException {
        if (idCredit.isPresent()) {
            // ----- Cas modification -----//
            int id = idCredit.get();
            System.out.println("------ Modification du crédit avec ID : " + id + " ------");

            String updateQuery = "UPDATE credit SET idClient=?, dateDeCredit=?, montantDemande=?, "
                    + "montantOctroye=?, tauxInteret=?, dureeEnMois=?, typeCredit=?, decision=? "
                    + "WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(updateQuery);

            ps.setInt(1, idClient);
            ps.setDate(2, java.sql.Date.valueOf(dateDeCredit));
            ps.setDouble(3, montantDemande);

            if (montantOctroye != null) {
                ps.setDouble(4, montantOctroye);
            } else {
                ps.setNull(4, java.sql.Types.DOUBLE);
            }

            ps.setDouble(5, tauxInteret);
            ps.setInt(6, dureeEnMois);
            ps.setString(7, typeCredit);
            ps.setString(8, decision.name());
            ps.setInt(9, id);

            int rows = ps.executeUpdate();
            System.out.println(rows + " crédit(s) modifié(s).");

        } else {
            // ----- Cas ajout -----
            System.out.println("------ Ajout d’un nouveau crédit ------");

            String insertQuery = "INSERT INTO credit (idClient, dateDeCredit, montantDemande, "
                    + "montantOctroye, tauxInteret, dureeEnMois, typeCredit, decision) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(insertQuery);

            ps.setInt(1, idClient);
            ps.setDate(2, java.sql.Date.valueOf(dateDeCredit));
            ps.setDouble(3, montantDemande);

            if (montantOctroye != null) {
                ps.setDouble(4, montantOctroye);
            } else {
                ps.setNull(4, java.sql.Types.DOUBLE);
            }

            ps.setDouble(5, tauxInteret);
            ps.setInt(6, dureeEnMois);
            ps.setString(7, typeCredit);
            ps.setString(8, decision.name());

            int rows = ps.executeUpdate();
            System.out.println(rows + " crédit(s) ajouté(s).");
        }
    }

}
