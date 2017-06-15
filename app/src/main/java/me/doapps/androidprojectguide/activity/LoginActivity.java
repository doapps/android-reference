package me.doapps.androidprojectguide.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import me.doapps.androidprojectguide.R;

/**
 * Created by doapps on 5/30/16.
 */
public class LoginActivity extends AppCompatActivity {

    EditText emailEdit;
    EditText passEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
