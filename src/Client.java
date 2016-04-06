/**
 * Created by chawki on 16/02/16.
 */

import java.util.Scanner;

public class Client {

    /* Attributs/constructeur */
    private Compte[] comptes = new Compte[100];
    private int nombreDeComptes = 0;
    private String nom;
    private Scanner sc;



    /* Methodes */

    //creer un nouveau client
    public Client(String nouveauClient) {
        nom = nouveauClient;
        ajouterCompte();
    }

    // Ajouter un compte
    public void ajouterCompte() {
        nombreDeComptes++;
        comptes[nombreDeComptes] = new Compte();
    }

    // Retourne le solde total du client
    public double soldeTotal() {
        double somme = 0;
        for (int i = 1; i <= nombreDeComptes; i++)
            somme += comptes[i].getSolde();
        return somme;
    }

    // Afficher le solde total du client
    public void afficherSolde() {
        System.out.println("votre solde total est :  " + soldeTotal() + " DA");
    }

    //Affiche le billan d'un client sur ces comptes
    public void afficherBilan() {
        System.out.println("Bilan du/des compte(s) de " + nom);
        afficherSolde();
        for (int i = 1; i <= nombreDeComptes; i++)
            System.out.println(" Le solde du compte n°" + i + " est de " + comptes[i].getSolde() + " DA");
        System.out.println();
    }

    //Retourner le nom du client
    public String getNom() {
        return nom;
    }

    //Affiche les compte du client
    public void afficherCompte() {

        for (int i = 1; i <= nombreDeComptes; i++) {
            System.out.println("compte " + i + " : " + comptes[i].getSolde() + " DA");
        }
    }

    //Menu du client ( transaction , ajout , ... ect )
    public void interaction() {
        boolean fini = false;

        while (!fini) {
            System.out.println("==========================  Menu  ==================================");
            System.out.println("Quelle operation voulez-vous effectuer sur le client ," + nom);
            System.out.println(" 1) Faire un dépot");
            System.out.println(" 2) Faire un retrait");
            System.out.println(" 3) Faire un virement");
            System.out.println(" 4) Créer un compte ");
            System.out.println(" 5) Suprimer un compte");
            System.out.println(" 6) Afficher le bilan des comptes");
            System.out.println(" 7) Revenir au menu principal");
            System.out.println("====================================================================");
            System.out.println();

            System.out.print("Votre choix: ");
            sc = new Scanner(System.in);
            int reponse = sc.nextInt();
            System.out.println();


            switch (reponse) {

                case 1:

                    interactionDepot();
                    break;


                case 2:

                    InteractionRetrait();
                    break;


                case 3:
                    interactionVirement();

                    break;


                case 4:
                    interactionAddcompte();

                    break;

                case 5:

                    interactionDeleteCompte();
                    break;


                case 6:
                    afficherBilan();
                    System.out.println();
                    break;


                case 7:
                    fini = true;
                    System.out.println("Retour en arriere ! ");
                    System.out.println();
                    break;

                default:
                    System.out.println("erreur de saisi , recomencer ! ");
                    System.out.println();
                    break;

            }
        }
    }

    private void interactionDeleteCompte() {
        if (nombreDeComptes > 1) {

            comptes[nombreDeComptes].virer(comptes[nombreDeComptes].getSolde(), comptes[nombreDeComptes - 1]);
            nombreDeComptes--;
            System.out.println("le dernier compte a été suprimer et son solde a été virer au compte n°" + nombreDeComptes);
        } else {
            System.out.println(" vous poseder qu'un seule compte , impossible de le suprimer ! ");
        }
        System.out.println();
    }

    private void interactionAddcompte() {
        ajouterCompte();
        System.out.println("Le compte n°" + nombreDeComptes + " a été créé \n");
    }

    private void interactionVirement() {
        if (nombreDeComptes > 1) {
            System.out.print("De quel montant ? : ");
            double montantVir = sc.nextDouble();

            System.out.println();
            System.out.println("Vos comptes :");
            afficherCompte();
            System.out.println();

            System.out.print("Compte emetteur : ");
            int emetteur = sc.nextInt();


            System.out.print("Compte destinataire : ");
            int destinataire = sc.nextInt();

            System.out.println();
            // TODO: find other name for the variables
            boolean isNombreCompte = (emetteur <= nombreDeComptes) && (destinataire <= nombreDeComptes);
            boolean isSoldeOk = comptes[emetteur].getSolde() >= montantVir;

            if (isNombreCompte && (nombreDeComptes > 1) && isSoldeOk) {
                comptes[emetteur].virer(montantVir, comptes[destinataire]);
                System.out.println("Le virement a étté effectué");
                System.out.println();
                afficherBilan();
                System.out.println(" ");

            } else {
                System.out.println("Un des comptes n'existe pas ou le montant est superieur au solde du compte ! ");
                System.out.println(" ");
            }
        } else System.out.println("Vous n'avez qu'un seul compte ! ");
        System.out.println();
    }

    private void InteractionRetrait() {
        System.out.println("De quel montant ? : ");
        double montantMoin = sc.nextDouble();
        int choixCompte2 = 1;
        if (nombreDeComptes > 1) {

            System.out.println();
            System.out.println("Sur quelle compte ? : ");
            afficherCompte();
            System.out.println();
            System.out.print("votre choix : ");
            choixCompte2 = sc.nextInt();

        }
        if (montantMoin <= comptes[choixCompte2].getSolde()) {
            comptes[choixCompte2].retrait(montantMoin);
            System.out.println("Le retrait a été effectué : ");
            afficherSolde();
        } else System.out.println(" Retrait impossible , montant superieur au solde du compte ! ");
        System.out.println(" ");
    }

    private void interactionDepot() {
        System.out.print("De quel montant ? : ");

        double montantPlus = sc.nextDouble();
        int choixCompte1 = 1;
        if (nombreDeComptes > 1) {

            System.out.println();
            System.out.println("Sur quelle compte ? : ");
            afficherCompte();
            System.out.println();
            System.out.print("votre choix : ");
            choixCompte1 = sc.nextInt();

        }
        comptes[choixCompte1].depot(montantPlus);
        System.out.println("Le depot a été effectué");
        System.out.println();
        afficherSolde();
        System.out.println(" ");
        return;
    }
}

