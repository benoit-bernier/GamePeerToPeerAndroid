package alexandre.testapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class RuleP2P extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Utilisation du mode multi P2P");
        builder.setMessage("Bienvenue dans le mode multi. Voici comment défier un ami proche.\n\n1- Autorise + active le GPS dans les paramètres de ton téléphone.\n\n2- Active le WiFi P2P grâce au bouton en haut à gauche.\n\n3- Découvre tes amis proches avec le bouton en haut à droite.\n\n4- Clique sur le nom du téléphone de ton ami dans la liste blanche pour vous défier.\n\n5- Attendez d'avoir chacun un rôle de 'Client' ou de 'Serveur'.\n\n6- Vous êtes prêt à jouer. Appuyer sur la touche défier pour commencer !");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // rien à faire
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
