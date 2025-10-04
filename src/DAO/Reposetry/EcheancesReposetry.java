package DAO.Reposetry;

import Config.BaseDonne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class EcheancesReposetry {
    private Connection connection;
    public EcheancesReposetry() throws SQLException {
        this.connection = BaseDonne.getInstance().getConnection();
    }

    public void genererEcheances(int idCredit, double montantOctroye, int dureeEnMois, LocalDate dateDebut) throws SQLException {
        double mensualite = montantOctroye / dureeEnMois;

        String insertEcheance = "INSERT INTO echeance (idCredit, dateEcheance, mensualite, statutPaiement) VALUES (?, ?, ?, ?)";

        PreparedStatement psEch = connection.prepareStatement(insertEcheance);

        for (int i = 1; i <= dureeEnMois; i++) {
            LocalDate dateEcheance = dateDebut.plusMonths(i);

            psEch.setInt(1, idCredit);
            psEch.setDate(2, java.sql.Date.valueOf(dateEcheance));
            psEch.setDouble(3, mensualite);
            psEch.setString(4, "IMPAYENONREGLE"); // par défaut

            psEch.addBatch(); // ajouter à la batch
        }

        psEch.executeBatch(); // exécuter toutes les insertions
        System.out.println(dureeEnMois + " échéances générées pour le crédit " + idCredit);
    }

    public void update(int idCredit, LocalDate dateDePaiement) {
        String sql = " UPDATE echeance SET dateDePaiement = ? WHERE idEcheance = ( SELECT idEcheance FROM echeance WHERE idCredit = ? AND dateDePaiement IS NULL ORDER BY dateEcheance ASC LIMIT ) ";

        try (Connection connection = BaseDonne.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            if (dateDePaiement != null)
                ps.setDate(1, java.sql.Date.valueOf(dateDePaiement));
            else
                ps.setNull(1, java.sql.Types.DATE);

            ps.setInt(2, idCredit);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("✅ Première échéance du contrat " + idCredit + " mise à jour avec succès !");
            } else {
                System.out.println("⚠️ Aucune échéance trouvée sans date de paiement pour le contrat " + idCredit);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour de l’échéance : " + e.getMessage());
        }
    }


}
