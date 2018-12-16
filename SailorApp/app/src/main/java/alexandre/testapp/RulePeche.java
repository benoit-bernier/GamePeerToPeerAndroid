package alexandre.testapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class RulePeche extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Règle du jeu");
        builder.setMessage("Je sais que tu as toujours voulu être un pêcheur ! Voici ta chance. Ton objectif est de pêcher 5 poissons.\n\n Comment pêcher :\n\n1- Place ton téléphone à l'horizontal, ton écran vers la gauche.\n\n2- Attend le poisson.\n\n3- Quand ton téléphone vibre, un poisson a mordu à l'hammeçon ! Passe rapidement ton téléphone à la verticale avec ton écran toujours sur la gauche.\n\n4-Recommence jusqu'à devenir le roi de la pêche !\n\nTips : On ne devient pas pêcheur en jouant à un jeu de pêche." );
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // rien à faire
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
