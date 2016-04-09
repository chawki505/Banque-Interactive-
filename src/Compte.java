/**
 * Created by chawki on 16/02/16.
 */

public class Compte {

    /* Attributs/constructeur */

    private double solde = 0;

    /*  Methodes */

    // pour faire un dépôt sur le compte.
    public void depot(double valeur) {
        solde += valeur;
    }


    // pour faire un retrait sur le compte.
    public void retrait(double valeur) {
        solde -= valeur;
    }


    // pour obtenir la valeur du solde
    public double getSolde() {
        return solde;
    }


    // pour faire un virement entre 2 comptes
    public void virer(double valeur, Compte destinataire) {
            destinataire.depot(valeur);
            retrait(valeur);
    }
}