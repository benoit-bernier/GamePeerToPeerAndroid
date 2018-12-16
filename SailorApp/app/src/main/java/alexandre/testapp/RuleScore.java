package alexandre.testapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class RuleScore extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Echange des scores");
        builder.setMessage("Les défis étant réalisés, il faut maintenant envoyé le score à votre ami. Voici comment faire :\n\n1- Un des joueurs envoie son score en appuyant sur le bouton 'SCORE'.\n\n2- Le second joueur appuie ensuite également sur le bouton 'SCORE'\n\n3- Le premier joueur a avoir envoyé son score réappuie sur le bouton 'SCORE' afin de valider l'échange de score.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // rien à faire
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}