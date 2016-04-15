/**
 * Created by chawki on 17/02/16.
 */


import java.util.Scanner;

public class BanqueInteractive {

    /* TODO: Attributs/constructeur */
    private int nombreDeClients = 0;
    private Client[] client = new Client[100];
    private Scanner clavier;


    /* TODO: Methodes */

    //menu de la banque
    public void interaction() {
        boolean fini = true;
        while (fini) {
            System.out.println("         \n__________________________________________      ");
            System.out.println("        [| Bienvenu dans la banque Chawki & ishak |] ");
            System.out.println("      +-------------------  Menu  --------------------+   ");
            System.out.println("      | Quelle operation voulez-vous effectuer?       |  ");
            System.out.println("      |  1) Ajouter un client                         |  ");
            System.out.println("      |  2) Afficher le bilan de la banque            |  ");
            System.out.println("      |  3) Effectuer des operations sur un client    |  ");
            System.out.println("      |  4) Quitter le programme                      |  ");
            System.out.println("      +-----------------------------------------------+  ");

            clavier = new Scanner(System.in);
            System.out.print("Votre choix: ");
            String reponse = clavier.nextLine();
            System.out.println();

            switch (reponse) {
                case "1":
                    interactionAddClient();
                    break;

                case "2":
                    if (nombreDeClients != 0)
                        afficherBilanBanque();
                    else System.out.println("la baqnue est vide ! ");
                    break;

                case "3":
                    interactionMenuClient();
                    break;

                case "4":
                    fini = false;
                    System.out.println("au revoir ! a la prochaine ");
                    break;

                default:
                    System.out.println("Erreur de saisi clavier !! \n");
                    break;
            }
        }
    }


    //creation d'un new client
    public void ajouterClient(String nom) {
        nombreDeClients++;
        client[nombreDeClients] = new Client(nom);
    }

    //affiche le bilan de la baqnue
    public void afficherBilanBanque() {
        for (int i = 1; i <= nombreDeClients; i++)
            client[i].afficherBilanClient();
    }

    //choix menu 1
    private void interactionAddClient() {
        System.out.print("Entrez le nom du client: ");
        String nom = clavier.nextLine();
        ajouterClient(nom);
        System.out.println("Le client " + nom + " a été ajouté\n");
    }

    //choix menu 3
    private void interactionMenuClient() {

        if (nombreDeClients != 0) {
            try {

                System.out.println("Choisissez le client:");
                for (int i = 1; i <= nombreDeClients; i++) {
                    System.out.println(" " + i + ") " + client[i].getNom());
                }

                System.out.print("Votre choix : ");
                int numero = clavier.nextInt();
                while (numero <= 0 || numero > nombreDeClients) {
                    System.out.println("\nLe numero saisi n'existe pas dans la base de donnée ,recommencer !");
                    System.out.print("Votre choix : ");
                    numero = clavier.nextInt();
                }

                client[numero].interaction();

            } catch (Exception e) {
                System.out.println("Erreur de saisi clavier !! ");
            }
        } else {
            System.out.println("Impossible de faire des operation sur un client ( il n'existe aucun client ! )\n");
        }

    }
}

