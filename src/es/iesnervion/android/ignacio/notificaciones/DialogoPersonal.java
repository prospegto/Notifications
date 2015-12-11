package es.iesnervion.android.ignacio.notificaciones;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;

public class DialogoPersonal extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    builder.setView(inflater.inflate(R.layout.dialogo_personal, null))
	           .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                   dialog.cancel();
	               }
	           });
 
        return builder.create();
    }
}