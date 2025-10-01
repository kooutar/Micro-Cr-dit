package DAO.Reposetry;

import Config.BaseDonne;
import enums.TypeContrat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class EmployeeReposetry {
    private Connection connection;
    public EmployeeReposetry() throws SQLException {
        this.connection = BaseDonne.getInstance().getConnection();
    }
    public void AjouterModifier(
            String nom,
            String prenom,
            LocalDate dateDeNaissance,
            String ville,
            int nombreEnfants,
            boolean investissement,
            double placement,
            String situationFamiliale,
            LocalDateTime createdAt,
            int score,
            double salaire,
            int anciennete,
            String poste,
            TypeContrat typeContrat,
            String secteur,
            Optional<Integer> idEmployee
    ) throws SQLException {
        if (idEmployee.isPresent()) {
            // Cas modification
            int id = idEmployee.get();
            System.out.println("------ Modification du client avec ID : " + id + " ------");

            String updateQuery = "UPDATE personne SET nom=?, prenom=?, dateNaissance=?, ville=?, nombreEnfants=?, "
                    + "investissement=?, placement=?, situation_familiale=?, createdAt=?, score=?, salaire=?, anciennete=?, poste=?, typeContrat=?, secteur=? "
                    + "WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(updateQuery);

            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setDate(3, java.sql.Date.valueOf(dateDeNaissance));
            ps.setString(4, ville);
            ps.setInt(5, nombreEnfants);
            ps.setBoolean(6, investissement);
            ps.setDouble(7, placement);
            ps.setString(8, situationFamiliale);
            ps.setTimestamp(9, java.sql.Timestamp.valueOf(createdAt));
            ps.setInt(10, score);
            ps.setDouble(11, salaire);
            ps.setInt(12, anciennete);
            ps.setString(13, poste);
            ps.setString(14, typeContrat.name());
            ps.setString(15, secteur);
            ps.setInt(16, id);

            int rows = ps.executeUpdate();
            System.out.println(rows + " client(s) modifié(s).");

        } else {
            // Cas ajout
            System.out.println("------ Ajout d’un nouveau client ------");

            String insertQuery = "INSERT INTO personne (nom, prenom, dateNaissance, ville, nombreEnfants, "
                    + "investissement, placement, situation_familiale, createdAt, score, salaire, anciennete, poste, typeContrat, secteur, type_personne) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(insertQuery);

            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setDate(3, java.sql.Date.valueOf(dateDeNaissance));
            ps.setString(4, ville);
            ps.setInt(5, nombreEnfants);
            ps.setBoolean(6, investissement);
            ps.setDouble(7, placement);
            ps.setString(8, situationFamiliale);
            ps.setTimestamp(9, java.sql.Timestamp.valueOf(createdAt));
            ps.setInt(10, score);
            ps.setDouble(11, salaire);
            ps.setInt(12, anciennete);
            ps.setString(13, poste);
            ps.setString(14, typeContrat.name());
            ps.setString(15, secteur);
            ps.setString(16, "EMPLOYE");

            int rows = ps.executeUpdate();
            System.out.println(rows + " client(s) ajouté(s).");
        }
    }


}
