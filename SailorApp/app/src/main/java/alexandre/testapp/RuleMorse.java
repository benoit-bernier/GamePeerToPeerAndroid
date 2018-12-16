package alexandre.testapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class RuleMorse extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Règle du jeu");
        builder.setMessage("Tu es en danger ! Envoie un SOS en morse aux gardes-côtes pour qu'ils viennent t'aider.\n\nAppuie rapidement sur l'écran pour envoyer un signal court / Appuie longtemps sur l'écran pour envoyer un signal long. \n\n Indice : S = . . . / O = _ _ _");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // rien à faire
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
