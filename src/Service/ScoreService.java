package Service;

import DAO.Reposetry.EmployeeReposetry;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class ScoreService {
     EmployeeReposetry newCleint=new EmployeeReposetry();
      private int Score;
    public ScoreService() throws SQLException {
    }

    private  int calculeAge(LocalDate dateNaissance) {
        if (dateNaissance == null) {
            throw new IllegalArgumentException("La date de naissance ne peut pas être nulle");
        }
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }


    public int calculScoreAge(LocalDate dateNaissance) {
        int age = calculeAge(dateNaissance);


            if (age >= 18 && age <= 25) {
                return 4;
            } else if (age >= 26 && age <= 35) {
                return 8;
            } else if (age >= 36 && age <= 55) {
                return 10;
            } else if (age > 55) {
                return 6;
            } else {
                return 0; // moins de 18 ans
            }


    }

    public int calculScoreSituationFamiliale(String situation) {
        int score = 0;

        if (situation == null) return score;

        switch (situation.toLowerCase()) {
            case "marié":
                score = 3;
                break;
            case "CELIBATAIRE":
                score = 2;
                break;
            default:
                score = 0;
        }

        return score;
    }

    public int calculScoreEnfants(int nombreEnfants) {
        int score;

        switch (nombreEnfants) {
            case 0:
                score = 2;
                break;
            case 1:
            case 2:
                score = 1;
                break;
            default:
                score = 0;
        }

        return score;
    }



    public int calculScoreSalaire(double salaire) {
        int tranche;

        if (salaire >= 10000) {
            tranche = 5;
        } else if (salaire >= 8000) {
            tranche = 4;
        } else if (salaire >= 5000) {
            tranche = 3;
        } else if (salaire >= 3000) {
            tranche = 2;
        } else {
            tranche = 1;
        }

        // Switch sur la tranche
        switch (tranche) {
            case 5:
                return 30;
            case 4:
                return 25;
            case 3:
                return 20;
            case 2:
                return 15;
            case 1:
                return 10;
            default:
                return 0; // pour sécurité
        }
    }

    public int calculScoreAnciennete(int anciennete) {
        int tranche;

        if (anciennete >= 5) {
            tranche = 4; // 5 ans ou plus
        } else if (anciennete >= 2) {
            tranche = 3; // 2 à 4 ans
        } else if (anciennete >= 1) {
            tranche = 2; // 1 à 2 ans
        } else {
            tranche = 1; // moins de 1 an
        }

        switch (tranche) {
            case 4:
                return 5;
            case 3:
                return 3;
            case 2:
                return 1;
            case 1:
                return 0;
            default:
                return 0; // sécurité
        }
    }

    public  int scoreGlobale(LocalDate dateNaissance,String situation, int nombreEnfants, double salaire ,int anciennete){
        int score = 0;
        score = calculScoreAge(dateNaissance)+ calculScoreSituationFamiliale(situation) +calculScoreEnfants(nombreEnfants) +calculScoreSalaire(salaire)+ calculScoreAnciennete(anciennete);
        return score;
    }




}
