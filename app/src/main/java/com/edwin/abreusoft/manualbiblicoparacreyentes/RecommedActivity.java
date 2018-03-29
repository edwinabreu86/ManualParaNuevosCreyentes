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
                "enfocado no en beneficios materiales, sino en capacitar al líder que todo creyente lleva dentro. \n\n" +
                "Se sugiere que cada discípulo, con ayuda del Todo Poderoso, procure memorizar este material en forma de juego didáctico, enfocándose en cada enseñanza. \n\n" +
                "El liderazgo lo da Dios a quien él quiere, llevándole gradualmente al propósito por el cual ha nacido, si el individuo lo asume con responsabilidad. \n\n" +
                "Para enseñar la palabra de Dios se debe mantener un ambiente adecuado que facilite la memorización, " +
                "lo que ayuda perder lo que toda persona presenta al pararse ante un pulpito o escenario: el miedo escénico.";
    }
}
