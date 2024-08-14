package com.example.taskmanager;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.Recycle.RecycleList;
import com.example.taskmanager.pojo.Tarefas;
import com.example.taskmanager.sqlite.DAO;

public class ListagemActivity extends AppCompatActivity {

    private RecycleList recycleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listagem);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        gerarLista();
    }

    public void gerarLista(){
        DAO dao = new DAO(this);

        recycleList = new RecycleList(this, dao.getTarefas());
        RecyclerView recyclerView = findViewById(R.id.listaTarefas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycleList);

        //Teste de tarefas na lista
//        for (Tarefas tarefa1 : dao.getTarefas()){
//            Log.d("TITULO: ", tarefa1.getTitulo());
//            Log.d("DESCRICAO: ", tarefa1.getDescricao());
//        }
    }
}