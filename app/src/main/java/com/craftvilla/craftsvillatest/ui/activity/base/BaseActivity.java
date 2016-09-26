package com.craftvilla.craftsvillatest.ui.activity.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.widget.Toast;

import com.craftvilla.craftsvillatest.R;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progress;

    protected void showHud() {
        hideHud();
        progress = ProgressDialog.show(new ContextThemeWrapper(this,
                android.R.style.Theme_Holo_Light), "", "", true, false);
        progress.setContentView(R.layout.progress_view);
    }

    protected void hideHud() {
        if (progress != null) {
            progress.dismiss();
        }
    }

    private Toast toast;

    protected void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    protected void hideToast() {
        if (toast != null)
            toast.cancel();
    }
}
