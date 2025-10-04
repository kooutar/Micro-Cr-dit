package Views;

import Service.CreditService;
import enums.Decision;

import java.sql.SQLException;


public class CreditView {
    private CreditService creditService = new CreditService();

    public CreditView() throws SQLException {
    }

    public void ajoutCredit(int idClient , double Montant , double tauxInteret , int dureeEnMois , String typeCredit)throws SQLException {
    creditService.ajoutCredit(idClient,Montant,tauxInteret, dureeEnMois,typeCredit);

    }
}
