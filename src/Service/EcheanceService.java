package Service;

import DAO.Reposetry.EcheancesReposetry;
import DAO.Reposetry.EmployeeReposetry;

import java.sql.SQLException;
import java.time.LocalDate;

public class EcheanceService {
    EcheancesReposetry echeancesReposetry= new EcheancesReposetry();

    public EcheanceService() throws SQLException {
    }

    public  void payeeEcheance(int idCredit, LocalDate dateDePaiement){
        echeancesReposetry.update(idCredit,dateDePaiement);
    }

    public void updateStatusPaiement(){

    }


}
