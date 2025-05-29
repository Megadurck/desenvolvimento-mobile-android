package com.example.hamburgueriaz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtNome;
    CheckBox cbBacon, cbCheddar, cbPicles;
    TextView txtQuantidade, txtPreco;
    Button btnMais, btnMenos, btnEnviar;

    int quantidade = 1;
    double precoBase = 10.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.edtNome);
        cbBacon = findViewById(R.id.cbBacon);
        cbCheddar = findViewById(R.id.cbCheddar);
        cbPicles = findViewById(R.id.cbPicles);
        txtQuantidade = findViewById(R.id.txtQuantidade);
        txtPreco = findViewById(R.id.txtPreco);
        btnMais = findViewById(R.id.btnMais);
        btnMenos = findViewById(R.id.btnMenos);
        btnEnviar = findViewById(R.id.btnEnviar);

        cbBacon.setOnCheckedChangeListener((buttonView, isChecked) -> atualizarInterface());
        cbCheddar.setOnCheckedChangeListener((buttonView, isChecked) -> atualizarInterface());
        cbPicles.setOnCheckedChangeListener((buttonView, isChecked) -> atualizarInterface());


        atualizarInterface();

        btnMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantidade++;
                atualizarInterface();
            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantidade > 1) {
                    quantidade--;
                    atualizarInterface();
                }
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edtNome.getText().toString().trim();
                if (nome.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Informe o nome do cliente.", Toast.LENGTH_SHORT).show();
                    return;
                }

                double total = calcularPreco();

                // Montando o resumo no formato pedido
                String resumoPedido = "Nome do cliente: " + nome +
                        "\nTem Bacon? " + (cbBacon.isChecked() ? "Sim" : "Não") +
                        "\nTem Queijo? " + (cbCheddar.isChecked() ? "Sim" : "Não") +
                        "\nTem Onion Rings? " + (cbPicles.isChecked() ? "Sim" : "Não") +
                        "\nQuantidade: " + quantidade +
                        "\nPreço final: R$ " + String.format("%.2f", total);

                // Chamando função que cria e envia o Intent
                enviarPedido(nome, resumoPedido);
            }
        });

    }

    private void atualizarInterface() {
        txtQuantidade.setText("Quantidade: " + quantidade);
        txtPreco.setText("Preço total: R$ " + String.format("%.2f", calcularPreco()));
    }

    private double calcularPreco() {
        double preco = precoBase;
        if (cbBacon.isChecked()) preco += 2.0;
        if (cbCheddar.isChecked()) preco += 2.0;
        if (cbPicles.isChecked()) preco += 3.0;
        return preco * quantidade;
    }

    private void enviarPedido(String nome, String resumoPedido) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"test@example.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de " + nome);
        intent.putExtra(Intent.EXTRA_TEXT, resumoPedido);

        try {
            startActivity(Intent.createChooser(intent, "Escolha um app de e-mail"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Nenhum app de e-mail instalado.", Toast.LENGTH_SHORT).show();
        }
    }

}
