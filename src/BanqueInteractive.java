/**
 * Created by chawki on 17/02/16.
 */


import java.util.Scanner;

public class BanqueInteractive {

    /* Attributs/constructeur */
    private int nombreDeClients = 0;
    private Client[] clients = new Client[100];
    private Scanner sc;


    /*  Methodes */


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

            sc = new Scanner(System.in);
            String reponse = sc.nextLine();
            System.out.println();
            switch (reponse) {
                case "1":
                    interactionAddClient();
                    break;

                case "2":
                    afficherBilanBanque();
                    break;

                case "3":
                    interactionMenuClient();
                    break;

                case "4":
                    fini = false;
                    System.out.println("au revoir ! a la prochaine ");
                    break;

                default:
                    System.out.println("erreur de saisi , recomencer ! \n");
                    break;
            }
        }
    }

    //creation d'un new client
    public void ajouterClient(String nom) {
        nombreDeClients++;
        clients[nombreDeClients] = new Client(nom);
    }

    //affiche le bilan de la baqnue
    public void afficherBilanBanque() {
        for (int i = 1; i <= nombreDeClients; i++)
            clients[i].afficherBilanClient();
        System.out.println();
    }


    private void interactionAddClient() {
        System.out.print("Entrez le nom du client: ");
        String nom = sc.nextLine();
        ajouterClient(nom);
        System.out.println("Le client " + nom + " a été ajouté\n");
    }

    private void interactionMenuClient() {
        if (nombreDeClients>0) {
            System.out.println("Choisissez le client:");
            for (int i = 1; i <= nombreDeClients; i++)
                System.out.println(" " + i + ") " + clients[i].getNom());
            System.out.print("Votre choix : ");
            int numero = sc.nextInt();
            while (numero <= 0 || numero > nombreDeClients) {
                System.out.println("\nerreur de saisi recommencer !");
                System.out.print("Votre choix : ");
                numero = sc.nextInt();
            }
            System.out.println();
            clients[numero].interaction();
            System.out.println();
        }
        else
            System.out.println("Impossible de faire des operation sur un client ( il n'existe aucun client ! )\n");
    }
}

