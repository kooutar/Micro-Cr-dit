package Views;

import Service.EmployeService;
import enums.TypeContrat;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeView {
    private EmployeService employeService =new EmployeService();

    public EmployeeView() throws SQLException {
    }

    public void ajoutEmployee(String nom, String prenom, LocalDate dateDeNaissance, String ville, int nombreEnfants, boolean investissement, double placement, String situationFamiliale, LocalDateTime createdAt, Double salaire, int anciennete, String poste, TypeContrat typeContrat, String secteur) throws SQLException {
        employeService.ajouterEmployee( nom,  prenom,  dateDeNaissance,  ville,  nombreEnfants,  investissement,  placement,  situationFamiliale,  createdAt, salaire, anciennete, poste, typeContrat, secteur);
    }
}
