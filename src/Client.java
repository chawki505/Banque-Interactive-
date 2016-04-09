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
            System.out.println("====================================================================\n");


            System.out.print("Votre choix: ");
            sc = new Scanner(System.in);
            String reponse = sc.nextLine();
            System.out.println();


            switch (reponse) {

                case "1":

                    interactionDepot();
                    break;


                case "2":

                    InteractionRetrait();
                    break;


                case "3":
                    interactionVirement();

                    break;


                case "4":
                    interactionAddcompte();

                    break;

                case "5":

                    interactionDeleteCompte();
                    break;


                case "6":
                    afficherBilanClient();
                    break;


                case "7":
                    fini = true;
                    System.out.println("Retour en arriere ! \n");
                    break;

                default:
                    System.out.println("erreur de saisi , recomencer ! \n");
                    break;

            }
        }
    }


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
    public void afficherBilanClient() {
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


    private void interactionDeleteCompte() {
        if (nombreDeComptes > 1) {
            comptes[nombreDeComptes].virer(comptes[nombreDeComptes].getSolde(), comptes[nombreDeComptes - 1]);
            nombreDeComptes--;
            System.out.println("le dernier compte a été suprimer et son solde a été virer au compte n°" + nombreDeComptes + "\n");
        } else {
            System.out.println(" vous poseder qu'un seule compte , impossible de le suprimer ! \n");
        }
    }

    private void interactionAddcompte() {
        ajouterCompte();
        System.out.println("Le compte n°" + nombreDeComptes + " a été créé \n");
    }

    private void interactionVirement() {
        if (nombreDeComptes > 1) {
            System.out.print("De quel montant ? : ");
            double montantVir = sc.nextDouble();

            System.out.println("\nVos comptes :");
            afficherCompte();

            System.out.print("\nCompte emetteur : ");
            int emetteur = sc.nextInt();


            System.out.print("Compte destinataire : ");
            int destinataire = sc.nextInt();






            // TODO: je dois trouver un autre nom
            // permet de tester si le numero de compte en saisi ne depasse pas le nombre de compte total
            boolean test1 = (emetteur <= nombreDeComptes) && (destinataire <= nombreDeComptes);
            // permet de tester si le montant debiter du compte emetteur est inferieur au solde de se compte
            boolean test2 = comptes[emetteur].getSolde() >= montantVir;

            if (test1 && nombreDeComptes > 1 && test2) {
                comptes[emetteur].virer(montantVir, comptes[destinataire]);
                System.out.println("\nLe virement a étté effectué\n");
                afficherBilanClient();
                System.out.println(" ");

            } else {
                System.out.println("\nUn des comptes n'existe pas ou le montant est superieur au solde du compte ! \n");
            }
        } else System.out.println("Vous n'avez qu'un seul compte ! \n");

    }

    private void InteractionRetrait() {
        System.out.println("De quel montant ? : ");
        double montantMoin = sc.nextDouble();
        int choixCompte = 1;
        if (nombreDeComptes > 1) {

            System.out.println("\nSur quelle compte ? : ");
            afficherCompte();

            System.out.print("\nvotre choix : ");
            choixCompte = sc.nextInt();

        }
        if (montantMoin <= comptes[choixCompte].getSolde()) {
            comptes[choixCompte].retrait(montantMoin);
            System.out.println("Le retrait a été effectué : ");
            afficherSolde();
        } else System.out.println(" Retrait impossible , montant superieur au solde du compte ! \n");
    }

    private void interactionDepot() {
        System.out.print("De quel montant ? : ");
        double montantPlus = sc.nextInt();

        int choixCompte = 1;

        if (nombreDeComptes > 1) {
            System.out.println("\nSur quelle compte ? : ");
            afficherCompte();
            System.out.print("\nvotre choix : ");
            choixCompte = sc.nextInt();
        }
        comptes[choixCompte].depot(montantPlus);
        System.out.println("Le depot a été effectué\n");
        afficherSolde();
        System.out.println(" ");

    }

}

