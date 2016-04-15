/**
 * Created by chawki on 16/02/16.
 */

import java.util.Objects;
import java.util.Scanner;

public class Client {


    /* TODO: Attributs/constructeur */
    private Compte[] comptes = new Compte[100];
    private int nombreDeComptes = 0;
    private String nom;
    private Scanner clavier;


    //creer un nouveau client
    public Client(String nouveauClient) {
        nom = nouveauClient;
        ajouterCompte();
    }


     /* TODO: Methodes */


    //Menu du client ( transaction , ajout , ... ect )
    public void interaction() {
        boolean fini = false;

        while (!fini) {

            System.out.println("\n==========================  Menu  ==================================");
            System.out.println("Quelle operation voulez-vous effectuer sur le client ," + nom);
            System.out.println(" 1) Faire un dépot");
            System.out.println(" 2) Faire un retrait");
            System.out.println(" 3) Faire un virement");
            System.out.println(" 4) Créer un compte ");
            System.out.println(" 5) Suprimer un compte");
            System.out.println(" 6) Afficher le bilan des comptes");
            System.out.println(" 7) Revenir au menu principal");
            System.out.println("====================================================================\n");

            clavier = new Scanner(System.in);
            System.out.print("Votre choix: ");
            String reponse = clavier.nextLine();
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


    // Creer ou ajouter un 2eme compte
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
        for (int i = 1; i <= nombreDeComptes; i++) {
            System.out.println(" Le solde du compte n°" + i + " est de " + comptes[i].getSolde() + " DA");
        }
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

    //choix menu 5
    private void interactionDeleteCompte() {
        if (nombreDeComptes > 1) {
            comptes[nombreDeComptes].virer(comptes[nombreDeComptes].getSolde(), comptes[nombreDeComptes - 1]);
            nombreDeComptes--;
            System.out.println("le dernier compte a été suprimer et son solde a été virer au compte n°" + nombreDeComptes + "\n");
        } else {
            System.out.println(" vous poseder qu'un seule compte , impossible de le suprimer ! \n");
        }
    }

    //choix menu 4
    private void interactionAddcompte() {
        ajouterCompte();
        System.out.println("Le compte n°" + nombreDeComptes + " a été créé \n");
    }

    //choix menu 3
    private void interactionVirement() {
        if (nombreDeComptes > 1) {
            double montantVir = -1;
            int emetteur = -1, destinataire = -1;


            try {
                System.out.print("De quel montant ? : ");
                montantVir = clavier.nextDouble();

                System.out.println("\nVos comptes :");
                afficherCompte();

                System.out.print("\nCompte emetteur : ");
                emetteur = clavier.nextInt();

                System.out.print("Compte destinataire : ");
                destinataire = clavier.nextInt();

            } catch (Exception ignored) {

            }

            if (montantVir != -1 && emetteur != -1 && destinataire != -1) {
                // TODO: je dois trouver un autre nom
                // permet de tester si le numero de compte en saisi ne depasse pas le nombre de compte total
                boolean test1 = (emetteur <= nombreDeComptes) && (destinataire <= nombreDeComptes);
                // permet de tester si le montant debiter du compte emetteur est inferieur au solde de se compte
                boolean test2 = comptes[emetteur].getSolde() >= montantVir;

                if (test1 && nombreDeComptes > 1 && test2) {
                    comptes[emetteur].virer(montantVir, comptes[destinataire]);
                    System.out.println("\nLe virement a étté effectué\n");
                    afficherBilanClient();

                } else {
                    System.out.println("\nUn des comptes n'existe pas ou le montant est superieur au solde du compte ! \n");
                }
            } else System.out.println("Erreur de saisi clavier !! ");
        } else System.out.println("Vous n'avez qu'un seul compte ! \n");

    }


    // Renflouement des comptes lors d'un retrait
    public void renflouer(double montantMoin) {

        for (int i = 1; i <= nombreDeComptes; i++) {
            comptes[i].retrait(montantMoin);
            if (comptes[i].getSolde() < 0) {
                montantMoin = Math.abs(comptes[i].getSolde());
                comptes[i].setSolde(0);
            }
            if (montantMoin < comptes[i].getSolde()) {
                break;
            }
        }
    }

    //choix menu 2
    private void InteractionRetrait() {

        int choixCompte = 1;
        try {

            System.out.print("De quel montant ? : ");
            double montantMoin = clavier.nextDouble();

            if (nombreDeComptes == choixCompte) {
                if (montantMoin <= comptes[choixCompte].getSolde()) {
                    comptes[choixCompte].retrait(montantMoin);
                    System.out.println("Le retrait a été effectué ! ");
                } else {
                    System.out.println("Retrait impossible , montant superieur au solde du compte ! \n ");
                }
            } else {
                System.out.println("\nSur quelle compte ? : ");
                afficherCompte();
                System.out.print("\nvotre choix : ");
                choixCompte = clavier.nextInt();

                if (montantMoin <= comptes[choixCompte].getSolde()) {
                    comptes[choixCompte].retrait(montantMoin);
                    System.out.println("Le retrait a été effectué ! ");
                } else {
                    if (montantMoin <= soldeTotal()) {
                        System.out.print("Le retrait est possible avec un renflouement , voulez vous le faire (oui/non) ? :");
                        Scanner sc = new Scanner(System.in);
                        String choix = sc.nextLine();
                        if (Objects.equals(choix, "oui")) {
                            renflouer(montantMoin);
                            System.out.println("Le retrait a été effectué avec renflouement des comptes ! ");
                            afficherSolde();
                        } else {
                            System.out.println("Retrait impossible , montant superieur au solde du compte ! \n ");
                        }


                    } else {
                        System.out.println("Retrait impossible meme avec renflouement , montant superieur au solde du compte ! \n ");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur de saisi clavier !! ");
        }
    }

    //choix menu 1

    private void interactionDepot() {
        double montantPlus;
        try {
            System.out.print("De quel montant ? : ");
            montantPlus = clavier.nextInt();

            int choixCompte = 1;

            if (nombreDeComptes > 1) {
                System.out.println("\nSur quelle compte ? : ");
                afficherCompte();
                System.out.print("\nvotre choix : ");
                choixCompte = clavier.nextInt();
            }

            comptes[choixCompte].depot(montantPlus);
            System.out.println("Le depot a été effectué\n");
            afficherSolde();
        } catch (Exception e) {
            System.out.println("Erreur de saisi clavier !! ");
        }
    }

}

