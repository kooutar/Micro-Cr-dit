package Menu;

import Service.NouveauClientService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuPrincipale {
    private static Scanner sc = new Scanner(System.in);
    private NouveauClientService nouveauClientService ;
    public MenuPrincipale() {
        System.out.println("------------------Menu Principal--------------");
        System.out.println("1: vous etes un niveau Client");
        System.out.println("2: vous etes deja un  Client");
        int choix = sc.nextInt();
        sc.nextLine();
        switch (choix) {
            case 1:
                menuNewCleint();
            case 2:
                menuDejaCleint();
        }

    }

    private void menuDejaCleint() {

    }

    private void menuNewCleint() {

        System.out.println("------------------Menu  nouveau Cleint --------------");
        System.out.println("1 compte Employee");
        System.out.println("2 compte Profesionnel");
        int  choix = sc.nextInt();
        sc.nextLine();
        switch (choix) {
            case 1:
                MenuInfoEmploye();
            case 2:
        }



    }

    private void MenuInfoEmploye() {
        nouveauClientService = new NouveauClientService();
        System.out.println("----------------- Menu nouveau Employé --------------");

        System.out.print("Nom: ");
        String nom = sc.nextLine();

        System.out.print("Prénom: ");
        String prenom = sc.nextLine();

        System.out.print("Date de naissance (yyyy-MM-dd): ");
        String dateStr = sc.nextLine();
        LocalDate dateDeNaissance = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Ville: ");
        String ville = sc.nextLine();

        System.out.print("Situation familiale: ");
        String situationFamiliale = sc.nextLine();

        System.out.print("Nombre d'enfants: ");
        int nombreEnfants = sc.nextInt();

        System.out.print("Vous avez des investissements (true/false): ");
        boolean investissement = sc.nextBoolean();

        System.out.print("Placement: ");
        double placement = sc.nextDouble();

        sc.nextLine(); // consommer le saut de ligne restant

        // --- Nouveaux champs ---
        System.out.print("Salaire: ");
        double salaire = sc.nextDouble();

        System.out.print("Ancienneté (en années): ");
        int anciennete = sc.nextInt();

        sc.nextLine(); // consommer le saut de ligne restant

        System.out.print("Poste: ");
        String poste = sc.nextLine();

        System.out.print("Type de contrat (CDI, CDD, Freelance…): ");
        String typeContrat = sc.nextLine();


        System.out.print("Combient des anne de traville : ");
        int auncinite = sc.nextInt();
        sc.nextLine();

        System.out.print("Secteur (public, grande_entreprise, PME): ");
        String secteur = sc.nextLine();

        LocalDateTime createdAt = LocalDateTime.now();

        // --- Calculs des scores ---
        int scoreAge = nouveauClientService.calculScoreAge(dateDeNaissance);
        int scoreSituation = nouveauClientService.calculScoreSituationFamiliale(situationFamiliale);
        int scoreEnfants = nouveauClientService.calculScoreEnfants(nombreEnfants);

        // Ici, si tu as des méthodes pour calculer le score salaire, ancienneté, poste, typeContrat, secteur, on peut les ajouter
        // Exemple :
         int scoreSalaire = nouveauClientService.calculScoreSalaire(salaire);
        // int scoreAnciennete = nouveauClientService.calculScoreAnciennete(anciennete);

        int totalScore = scoreAge + scoreSituation + scoreEnfants; // + scoreSalaire + scoreAnciennete + ...

        System.out.println("Score âge: " + scoreAge);
        System.out.println("Score situation familiale: " + scoreSituation);
        System.out.println("Score enfants: " + scoreEnfants);
        System.out.println("Score total: " + totalScore);
    }

}
