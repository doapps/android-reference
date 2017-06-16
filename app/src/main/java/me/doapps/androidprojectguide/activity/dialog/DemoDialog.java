package me.doapps.androidprojectguide.activity.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.doapps.androidprojectguide.R;

/**
 * Created by doapps on 6/16/17.
 */

public class DemoDialog extends AlertDialog {

    private String titulo;
    private SelectOption selectOption;

    public DemoDialog(Context context, String titulo) {
        super(context);
        this.titulo = titulo;
        init();
    }

    protected DemoDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    protected DemoDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init();
    }


    private void init() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.dialog_demo, null);

        TextView tituloText = (TextView) view.findViewById(R.id.titulo_text);
        Button buttonAceptar = (Button) view.findViewById(R.id.aceptar_button);
        Button buttonExit = (Button) view.findViewById(R.id.exit_button);

        buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption.aceptar();
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption.exit();
            }
        });

        setView(view);
        setCancelable(false);
    }

    public interface SelectOption {

        void aceptar();

        void exit();
    }

    public void setOnSelectOption(SelectOption selectOption) {
        this.selectOption = selectOption;
    }
}