package me.doapps.androidprojectguide.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import me.doapps.androidprojectguide.R;
import me.doapps.androidprojectguide.activity.dialog.DemoDialog;

/**
 * Created by doapps on 5/30/16.
 */
public class LoginActivity extends AppCompatActivity {

    EditText emailEdit;
    EditText passEdit;
    private DemoDialog demoDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        demoDialog = new DemoDialog(this, "Mensaje de Problemas");
        demoDialog.show();

        demoDialog.setOnSelectOption(new DemoDialog.SelectOption() {
            @Override
            public void aceptar() {

                //Depende de lo que deseas colocar en este método

            }

            @Override
            public void exit() {

                demoDialog.cancel();

            }
        });

    }
}
