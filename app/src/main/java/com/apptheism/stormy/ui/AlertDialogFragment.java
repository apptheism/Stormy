package com.apptheism.stormy.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import com.apptheism.stormy.R;

public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );

        builder.setTitle(R.string.alert_dialog_title)
                .setMessage(R.string.alert_dialog_message)
                .setPositiveButton(R.string.alert_dialog_button_ok, null);

        return builder.create();
    }


}
