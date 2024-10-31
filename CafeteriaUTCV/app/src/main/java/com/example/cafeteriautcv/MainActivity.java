package com.example.cafeteriautcv;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String comidas[] = {
            "Torta",
            "Enchiladas",
            "Tacos de longaniza",
            "Guaraches",
            "Tostadas",
            "Gorditas",
            "Sopa",
            "Cafe",
            "Empanadas",
            "Tacos dorados",
            "Chilaquiles",
            "Platanos fritos",
            "Pan",
            "Hamburguesas",
            "Hot dogs",
            "Quesadillas",
            "Tlayudas",
            "Pozole",
            "Tamales",
            "Elotes",
            "Carnitas",
            "Ceviche",
            "Mole",
            "Menudo"
    };

    Random random = new Random();
    Button btnAgregar, btnAtender;
    LinearLayout productosContainer;
    //TextView txtFood;
    int i = 0;
    Queue<String> colaComidas = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAgregar = findViewById(R.id.btnAgregar);
        btnAtender = findViewById(R.id.btnAtender);
        productosContainer = findViewById(R.id.productosContainer);

    }

    public void agregarComida(View v) {
        if (i < comidas.length) {
            String comida =  comidas[random.nextInt(comidas.length)];
            colaComidas.add(comida); // Agregar a la cola

            View cardView = crearVistaComida(comida);
            productosContainer.addView(cardView);
        } else {
            Log.d("MainActivity", "No hay más comidas para agregar");
        }
    }

    private View crearVistaComida(String comida) {
        View cardView = LayoutInflater.from(this).inflate(R.layout.card_producto, productosContainer, false);
        TextView producto = cardView.findViewById(R.id.txtFood);
        producto.setText(comida);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 25); // Margen inferior de 25dp
        cardView.setLayoutParams(layoutParams);

        return cardView;
    }

    public void atenderComida(View view) {
        if (!colaComidas.isEmpty()) {
            String comidaAtendida = colaComidas.poll();

            if (productosContainer.getChildCount() > 0) {
                productosContainer.removeViewAt(0);
            }
        } else {
            Toast.makeText(this, "No hay más pedidos en la cola para atender", Toast.LENGTH_SHORT).show();
        }
    }
}