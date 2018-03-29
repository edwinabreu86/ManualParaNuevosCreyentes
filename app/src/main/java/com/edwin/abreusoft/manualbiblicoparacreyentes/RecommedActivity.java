package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class RecommedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommed);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView textView = findViewById(R.id.text_view);
        textView.setText(Reccommend.recommendText);

    }

    private static class Reccommend {
        static String recommendText = "Para preparar un nuevo creyente se requiere de un buen maestro, " +
                "que se enfoque no en los beneficios materiales, sino en capacitar al líder que todo creyente lleva dentro. \n\n" +
                "Se sugiere que, con la ayuda del Todo Poderoso, cada discípulo procure memorizar este material en forma de juego didáctico, enfocándose en la enseñanza. \n\n" +
                "El liderazgo lo da DIOS a quien él quiere, llevándole gradualmente al propósito por el cual ha nacido, si lo asume con responsabilidad. \n\n" +
                "Para enseñar la Palabra se debe mantener un ambiente adecuado que facilite la memorización, " +
                "a fin de perder lo que toda persona presenta al pararse ante un pulpito o escenario: el miedo escénico.";
    }
}
