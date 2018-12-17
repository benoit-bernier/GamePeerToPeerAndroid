package alexandre.testapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class RuleDrapeau extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Règle du jeu");
        builder.setMessage("Moussaillon ! Hisse le drapeau breton pour fêter le retour de nos fidèles compagnon !!\n\nMalheureusement, c'est un un drapeau 'made in China', il te faudra y reprendre à plusieurs fois pour mettre le drapeau à la bonne position.\n\n- Fait un glissement de doigt du haut vers le bas pour abaisser le drapeau.\n\n Fait un glissement de doigt du bas vers le haut pour le monter");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // rien à faire
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}

