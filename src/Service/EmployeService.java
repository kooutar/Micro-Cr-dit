package Service;

import DAO.Reposetry.EmployeeReposetry;
import enums.Secteur;
import enums.TypeContrat;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class EmployeService {
    private EmployeeReposetry employeeReposetry;
    private ScoreService scoreService;

    public EmployeService() throws SQLException {
        employeeReposetry = new EmployeeReposetry();
        scoreService = new ScoreService();
    }


    public void ajouterEmployee(String nom, String prenom, LocalDate dateDeNaissance, String ville, int nombreEnfants, boolean investissement, double placement, String situationFamiliale, LocalDateTime createdAt, Double salaire, int anciennete, String poste, TypeContrat typeContrat, Secteur secteur) throws SQLException {
        int score = scoreService.scoreGlobale(dateDeNaissance, situationFamiliale, nombreEnfants, salaire, anciennete,typeContrat,secteur);
        Optional<Integer> idEmployee = Optional.empty();
        employeeReposetry.AjouterModifier(nom, prenom, dateDeNaissance, ville, nombreEnfants, investissement, placement, situationFamiliale, createdAt, score, salaire, anciennete, poste, typeContrat, secteur, idEmployee);

    }

    public void prendreCredit(int idCleint, double montant) throws SQLException {
        employeeReposetry.getPersonne(idCleint);
    }
}
