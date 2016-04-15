/**
 * Created by chawki on 16/02/16.
 */

public class Compte {

    /* TODO: Attributs/constructeur */

    private double solde = 0;

    /*  TODO: Methodes */

    // pour faire un dépôt sur le compte.
    public void depot(double valeur) {
        solde += valeur;
    }


    // pour faire un retrait sur le compte.
    public void retrait(double valeur) {
        solde -= valeur;
    }


    // pour obtenir la valeur du solde ( Getters )
    public double getSolde() {
        return solde;
    }

    // pour modifier la valeur du solde ( Setters )
    public void setSolde(double solde) {
        this.solde = solde;
    }

    // pour faire un virement entre 2 comptes
    public void virer(double valeur, Compte destinataire) {
        retrait(valeur);
        destinataire.depot(valeur);

    }
}