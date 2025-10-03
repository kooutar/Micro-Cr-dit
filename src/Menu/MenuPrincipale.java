package Menu;

import Service.CreditService;
import Service.ScoreService;
import Views.CreditView;
import Views.EmployeeView;
import enums.Decision;
import enums.Secteur;
import enums.TypeContrat;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipale {
    private static Scanner sc = new Scanner(System.in);
    private ScoreService scoreService = new ScoreService();
    private EmployeeView employeeView = new EmployeeView();
    private CreditView creditView = new CreditView();

    public MenuPrincipale() throws SQLException {
        System.out.println("------------------Menu Principal--------------");
        System.out.println("1: Vous êtes un nouveau Client");
        System.out.println("2: Vous êtes déjà un Client");
        System.out.println("3: Prendre un crédit");

        int choix = lireInt("Votre choix: ");
        switch (choix) {
            case 1:
                menuNewClient();
                break;
            case 2:
                menuDejaClient();
                break;
            case 3:
                menuCredit();
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }

    private void menuCredit() throws SQLException {
        System.out.println("------ Menu Crédit ------");

        int idClient = lireInt("Entrez l'id du client : ");
        double montant = lireDouble("Entrez le montant demandé : ");
        double tauxInteret = lireDouble("Entrez le taux d'intérêt : ");
        int dureeEnMois = lireInt("Entrez la durée en mois : ");
        String typeCredit = lireString("Entrez le type de crédit : ");

        // Gestion des erreurs pour l'enum Decision
        Decision decision = null;
        while (decision == null) {
            try {
                System.out.print("Entrez la décision (ACCORD_IMMEDIAT, REFUS_AUTOMATIQUE, ETUDE_MANUELLE) : ");
                String saisieDecision = sc.nextLine().trim().toUpperCase();
                 decision = Decision.valueOf(saisieDecision);

            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Décision invalide, réessayez !");
            }
        }

        creditView.ajoutCredit(idClient, montant, tauxInteret, dureeEnMois, typeCredit, decision);
    }

    private void menuDejaClient() {
        System.out.println("⚠️ Fonctionnalité pas encore implémentée.");
    }

    private void menuNewClient() throws SQLException {
        System.out.println("------ Menu nouveau Client ------");
        System.out.println("1: Compte Employé");
        System.out.println("2: Compte Professionnel");

        int choix = lireInt("Votre choix: ");
        switch (choix) {
            case 1:
                menuInfoEmploye();
                break;
            case 2:
                System.out.println("⚠️ Menu Professionnel pas encore implémenté.");
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }

    private void menuInfoEmploye() throws SQLException {
        scoreService = new ScoreService();
        System.out.println("------ Nouveau Employé ------");

        String nom = lireString("Nom : ");
        String prenom = lireString("Prénom : ");

        LocalDate dateDeNaissance = null;
        while (dateDeNaissance == null) {
            try {
                String dateStr = lireString("Date de naissance (yyyy-MM-dd) : ");
                dateDeNaissance = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ Format de date invalide, utilisez yyyy-MM-dd !");
            }
        }

        String ville = lireString("Ville : ");
        String situationFamiliale = lireString("Situation familiale : ");
        int nombreEnfants = lireInt("Nombre d'enfants : ");
        boolean investissement = lireBoolean("Avez-vous des investissements (true/false) : ");
        double placement = lireDouble("Placement : ");
        double salaire = lireDouble("Salaire : ");
        int anciennete = lireInt("Ancienneté (en années) : ");
        String poste = lireString("Poste : ");

        TypeContrat typeContrat = null;
        while (typeContrat == null) {
            try {
                String saisieContrat = lireString("Type de contrat (CDI, CDD, INTERIM, FREELANCE) : ").toUpperCase();
                typeContrat = TypeContrat.valueOf(saisieContrat);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Type de contrat invalide, réessayez !");
            }
        }

        Secteur secteur = null;
        while (secteur == null) {
            try {
                String saisieSecteur = lireString("Secteur (PUBLIC, GRANDE_ENTREPRISE, PME) : ").toUpperCase();
                secteur = Secteur.valueOf(saisieSecteur);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Secteur invalide, réessayez !");
            }
        }

        LocalDateTime createdAt = LocalDateTime.now();
        employeeView.ajoutEmployee(
                nom, prenom, dateDeNaissance, ville, nombreEnfants,
                investissement, placement, situationFamiliale, createdAt,
                salaire, anciennete, poste, typeContrat, secteur
        );
    }

    // ---- Méthodes utilitaires pour lire avec gestion d'erreurs ----
    private int lireInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Veuillez entrer un entier valide !");
                sc.nextLine();
            }
        }
    }

    private double lireDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Veuillez entrer un nombre valide !");
                sc.nextLine();
            }
        }
    }

    private boolean lireBoolean(String message) {
        while (true) {
            try {
                System.out.print(message);
                return sc.nextBoolean();
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Veuillez entrer true ou false !");
                sc.nextLine();
            }
        }
    }

    private String lireString(String message) {
        System.out.print(message);
        sc.nextLine(); // vider le buffer si besoin
        return sc.nextLine();
    }
}
