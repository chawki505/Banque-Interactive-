/**
 * Created by chawki on 16/02/16.
 */

public class Main {


    public static void main(String[] args) {

        BanqueInteractive BanqueChawkiEtIshak = new BanqueInteractive();

        //pour avoir aumoin 2 client au demarage du programme
        BanqueChawkiEtIshak.ajouterClient("chawki");
        BanqueChawkiEtIshak.ajouterClient("ishak");

        BanqueChawkiEtIshak.interaction();

    }
}
