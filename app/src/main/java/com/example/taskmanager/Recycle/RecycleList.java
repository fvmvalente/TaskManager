package com.example.taskmanager.Recycle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.ListagemActivity;
import com.example.taskmanager.R;
import com.example.taskmanager.pojo.Tarefas;
import com.example.taskmanager.sqlite.DAO;

import java.util.List;

public class RecycleList extends RecyclerView.Adapter<RecycleList.ViewHolder> {

    private List<Tarefas> listaTarefas;

    private ListagemActivity listagemActivity;

    public RecycleList(ListagemActivity listagemActivity, List<Tarefas> listaTarefas) {
        this.listagemActivity = listagemActivity;
        this.listaTarefas = listaTarefas;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent,false);
        return new ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titulo.setText(listaTarefas.get(position).getTitulo());
        holder.descricao.setText(listaTarefas.get(position).getDescricao());

        excluirTarefa(holder, position);
    }

    private void excluirTarefa(ViewHolder holder, int position){
        holder.excluirTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ID: ", listaTarefas.get(position).getId()+"");
                DAO dao = new DAO(listagemActivity);
                Log.d("EXCLUIR", listaTarefas.get(position).getId()+"");
                dao.deleteTarefas(listaTarefas.get(position).getId());
                listaTarefas.remove(position);
                notifyItemRangeChanged(position, listaTarefas.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaTarefas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView labelTitulo, labelDescricao, titulo, descricao;
        Button excluirTarefa;

        public ViewHolder(View itemView) {
            super(itemView);
            labelTitulo = itemView.findViewById(R.id.labelTitulo);
            labelDescricao = itemView.findViewById(R.id.labelDescricao);
            titulo = itemView.findViewById(R.id.titulo);
            descricao = itemView.findViewById(R.id.descricao);
            excluirTarefa = itemView.findViewById(R.id.excluirTarefa);
        }
    }
}
