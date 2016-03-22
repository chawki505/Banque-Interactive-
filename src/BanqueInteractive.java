/**
 * Created by chawki on 17/02/16.
 */


import java.util.Scanner;

public class BanqueInteractive {

    /* Attributs/constructeur */
    private int NombreDeClients = 0;
    private Client[] clients = new Client[100];


    /*  Methodes */

    //creation d'un new client
    public void ajouterClient(String nom) {
        NombreDeClients++;
        clients[NombreDeClients] = new Client(nom);
    }

    //affiche le bilan de la baqnue
    public void afficherBilan() {
        for (int i = 1; i <= NombreDeClients; i++)
            clients[i].afficherBilan();
        System.out.println();
    }

    //menu de la banque
    public void interaction() {
        boolean fini = true;
        while (fini) {
            System.out.println("======== Bienvenu dans la banque Chawki & ishak  ========= ");
            System.out.println("-------------------  Menu  --------------------");
            System.out.println("Quelle operation voulez-vous effectuer?");
            System.out.println(" 1) Ajouter un client");
            System.out.println(" 2) Afficher le bilan de la banque");
            System.out.println(" 3) Effectuer des operations sur un client");
            System.out.println(" 4) Quitter le programme");
            System.out.println("==================================================");
            System.out.print("Votre choix: ");
            Scanner sc = new Scanner(System.in);
            String reponse = sc.nextLine();
            System.out.println();
            switch (reponse) {
                case "1":
                    System.out.print("Entrez le nom du client: ");
                    String nom = sc.nextLine();
                    ajouterClient(nom);
                    System.out.println("Le client " + nom + " a été ajouté");
                    System.out.println(" ");
                    break;

                case "2":
                    afficherBilan();
                    break;

                case "3":
                    System.out.println("Choisissez le client:");
                    for (int i = 1; i <= NombreDeClients; i++)
                        System.out.println(" " + i + ") " + clients[i].getNom());
                    System.out.print("Votre choix : ");
                    int numero = sc.nextInt();
                    while (numero <= 0 || numero > NombreDeClients) {
                        System.out.println();
                        System.out.println("erreur de saisi recommencer !");
                        System.out.print("Votre choix : ");
                        numero = sc.nextInt();
                    }
                    System.out.println();
                    clients[numero].interaction();
                    System.out.println();
                    break;

                case "4":
                    fini = false;
                    System.out.println("au revoir ! a la prochaine ");
                    break;

                default:
                    System.out.println("erreur de saisi , recomencer ! ");
                    System.out.println();
                    break;
            }
        }
    }
}

