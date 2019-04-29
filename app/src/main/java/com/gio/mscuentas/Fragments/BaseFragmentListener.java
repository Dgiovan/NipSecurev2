package com.gio.mscuentas.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;

public class BaseFragmentListener extends Fragment {

    OnFragmentInteractionListener onFragmentInteractionListener;
    private AlertDialog alertDialog;

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener onFragmentInteractionListener) {
        this.onFragmentInteractionListener = onFragmentInteractionListener;
    }

    protected void showAlertDialog(String title,String message,String okText, String cancelText)
    {
        if(alertDialog == null)
        {
            alertDialog = new android.app.AlertDialog.Builder(getActivity()).create();
            alertDialog.setCancelable(false);
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, okText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialogOKButtonPressed();
                    dialog.dismiss();
                    alertDialog = null;
                }
            });

            if (cancelText != null)
            {
                alertDialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, cancelText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialogCancelButtonPressed();
                        dialog.dismiss();
                        alertDialog = null;
                    }
                });
            }
            alertDialog.show();
        }
    }

    private void alertDialogCancelButtonPressed() {
    }

    private void alertDialogOKButtonPressed() {
    }
}
