package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.taskmanager.pojo.Tarefas;
import com.example.taskmanager.sqlite.DAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText textoAnotacao, tituloAnotacao;
    Button btGravarTarefa, verLista;

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

        tituloAnotacao = findViewById(R.id.tituloAnotacao);
        textoAnotacao = findViewById(R.id.textoAnotacao);
        btGravarTarefa = findViewById(R.id.btGravarTarefa);

        DAO dao = new DAO(this);

        btGravarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tarefas tarefas = new Tarefas();
                tarefas.setTitulo(tituloAnotacao.getText().toString());
                tarefas.setDescricao(textoAnotacao.getText().toString());
                String resultado = String.valueOf(dao.insertTarefas(tarefas));
                Log.d("STATUS DB: ", resultado);
            }
        });

        verLista = findViewById(R.id.btIrParaLista);
        verLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListagemActivity.class);
                startActivity(intent);
            }
        });


        for (Tarefas tarefa1 : dao.getTarefas()){
            Log.d("TITULO: ", tarefa1.getTitulo().toString());
            Log.d("DESCRICAO: ", tarefa1.getDescricao().toString());
        }
    }
}