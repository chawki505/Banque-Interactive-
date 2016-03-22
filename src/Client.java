/**
 * Created by chawki on 16/02/16.
 */

import java.util.Scanner;

public class Client {

    /* Attributs/constructeur */
    private Compte[] comptes = new Compte[100];
    private int NombreDeComptes = 0;
    private String nom;



    /* Methodes */

    //creer un nouveau client
    public Client(String NouveauClient) {
        nom = NouveauClient;
        ajouterCompte();
    }

    // Ajouter un compte
    public void ajouterCompte() {
        NombreDeComptes++;
        comptes[NombreDeComptes] = new Compte();
    }

    // Retourne le solde total du client
    public double soldeTotal() {
        double somme = 0;
        for (int i = 1; i <= NombreDeComptes; i++)
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
        for (int i = 1; i <= NombreDeComptes; i++)
            System.out.println(" Le solde du compte n°" + i + " est de " + comptes[i].getSolde() + " DA");
        System.out.println();
    }

    //Retourner le nom du client
    public String getNom() {
        return nom;
    }

    //Affiche les compte du client
    public void afficherCompte() {

        for (int i = 1; i <= NombreDeComptes; i++) {
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
            Scanner sc = new Scanner(System.in);
            int reponse = sc.nextInt();
            System.out.println();


            switch (reponse) {

                case 1:

                    System.out.print("De quel montant ? : ");

                    double montantPlus = sc.nextDouble();
                    int choixCompte1 = 1;
                    if (NombreDeComptes > 1) {

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
                    break;


                case 2:

                    System.out.println("De quel montant ? : ");
                    double montantMoin = sc.nextDouble();
                    int choixCompte2 = 1;
                    if (NombreDeComptes > 1) {

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
                    break;


                case 3:
                    if (NombreDeComptes > 1) {
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
                        if ((emetteur <= NombreDeComptes) && (destinataire <= NombreDeComptes) && (NombreDeComptes > 1) && (comptes[emetteur].getSolde() >= montantVir)) {
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

                    break;


                case 4:
                    ajouterCompte();
                    System.out.println("Le compte n°" + NombreDeComptes + " a été créé");
                    System.out.println();
                    break;

                case 5:

                    if (NombreDeComptes > 1) {

                        comptes[NombreDeComptes].virer(comptes[NombreDeComptes].getSolde(), comptes[NombreDeComptes - 1]);
                        NombreDeComptes--;
                        System.out.println("le dernier compte a été suprimer et son solde a été virer au compte n°" + NombreDeComptes);
                    } else {
                        System.out.println(" vous poseder qu'un seule compte , impossible de le suprimer ! ");
                    }
                    System.out.println();
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
}

