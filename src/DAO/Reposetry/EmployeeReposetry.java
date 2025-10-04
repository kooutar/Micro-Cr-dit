package DAO.Reposetry;

import Config.BaseDonne;
import enums.Secteur;
import enums.TypeContrat;
import models.Person.Employe;
import models.Person.Persone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class EmployeeReposetry {

    private Connection connection;

    public EmployeeReposetry() throws SQLException {
        this.connection = BaseDonne.getInstance().getConnection();
    }
    public void AjouterModifier(String nom, String prenom, LocalDate dateDeNaissance, String ville, int nombreEnfants, boolean investissement, double placement, String situationFamiliale, LocalDateTime createdAt, int score, double salaire, int anciennete, String poste, TypeContrat typeContrat, Secteur secteur, Optional<Integer> idEmployee) throws SQLException {
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
            ps.setString(15, secteur.name());
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
            ps.setString(15, secteur.name());
            ps.setString(16, "EMPLOYE");

            int rows = ps.executeUpdate();
            System.out.println(rows + " client(s) ajouté(s).");
        }
    }


    public Persone getPersonne(int id) throws SQLException {
        String selectQuery = "SELECT * FROM personne WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(selectQuery);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            LocalDate dateNaissance = rs.getDate("dateNaissance").toLocalDate();
            String ville = rs.getString("ville");
            int nombreEnfants = rs.getInt("nombreEnfants");
            boolean investissement = rs.getBoolean("investissement");
            double placement = rs.getDouble("placement");
            String situationFamiliale = rs.getString("situation_familiale");
            LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();
            int score = rs.getInt("score");
            double salaire = rs.getDouble("salaire");
            int anciennete = rs.getInt("anciennete");
            String poste = rs.getString("poste");
            TypeContrat typeContrat = TypeContrat.valueOf(rs.getString("typeContrat"));
            Secteur secteur = Secteur.valueOf(rs.getString("secteur"));
            boolean estClient = rs.getBoolean("estCleint");
            // Retourner un objet Personne (à créer si pas encore fait)
            return new Employe( nom, prenom, dateNaissance, ville, nombreEnfants, investissement, placement,
                    situationFamiliale,  salaire, anciennete, poste, typeContrat, secteur,estClient,score);
        } else {
            return null; // Aucun client trouvé
        }
    }


    public Employe estClient(int idClient) {
        String req = "SELECT * FROM personne WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, idClient);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Récupération de la valeur booléenne de la colonne estClient
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                LocalDate dateNaissance = rs.getDate("dateNaissance").toLocalDate();
                String ville = rs.getString("ville");
                int nombreEnfants = rs.getInt("nombreEnfants");
                boolean investissement = rs.getBoolean("investissement");
                double placement = rs.getDouble("placement");
                String situationFamiliale = rs.getString("situation_familiale");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();
                int score = rs.getInt("score");
                System.out.println("estClient fct score "+ score);
                double salaire = rs.getDouble("salaire");
                int anciennete = rs.getInt("anciennete");
                String poste = rs.getString("poste");
                TypeContrat typeContrat = TypeContrat.valueOf(rs.getString("typeContrat"));
                Secteur secteur = Secteur.valueOf(rs.getString("secteur"));
                boolean estClient = rs.getBoolean("estCleint");

                // Retourner un objet Personne (à créer si pas encore fait)
                return new Employe( nom, prenom, dateNaissance, ville, nombreEnfants, investissement, placement,
                        situationFamiliale,  salaire, anciennete, poste, typeContrat, secteur,estClient,score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Par défaut si rien trouvé ou erreur
    }



}
