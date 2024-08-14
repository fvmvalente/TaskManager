package com.example.taskmanager.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.taskmanager.pojo.Tarefas;

import java.util.ArrayList;
import java.util.List;

public class DAO extends SQLiteOpenHelper {
    public DAO(@Nullable Context context) {
        super(context, "tb_lista_tarefas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String SQL = "CREATE TABLE tarefas (id INTEGER, titulo TEXT, descricao TEXT);";
        String SQL = "CREATE TABLE tarefas (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, descricao TEXT);";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String SQL = "DROP TABLE tarefas;";
        db.execSQL(SQL);
        onCreate(db);

    }

    public long insertTarefas(Tarefas tarefas){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo",tarefas.getTitulo());
        contentValues.put("descricao", tarefas.getDescricao());
        long newRowId = db.insert("tarefas", null, contentValues);
        db.close();
        tarefas.setId((int) newRowId);
        return newRowId;
    }

    @SuppressLint("Range")
    public List<Tarefas> getTarefas(){
        List<Tarefas> listaTarefas = new ArrayList<Tarefas>();

        SQLiteDatabase db = this.getReadableDatabase();

        String SQL = "SELECT * FROM tarefas";
        Cursor cursor = db.rawQuery(SQL,null,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            do {
                Tarefas tarefas = new Tarefas();
                //tarefas.setId(cursor.getInt(cursor.getColumnIndex("id")));
                tarefas.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                tarefas.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
                tarefas.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                listaTarefas.add(tarefas);

            }while(cursor.moveToNext());
        }
        return listaTarefas;
    }

    public void deleteTarefas(Integer id){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String  whereSQL = "id = ?";
            String[] whereArgs = {id.toString()};
            db.delete("tarefas", whereSQL, whereArgs);
            db.close();
            Log.d("SUCESSO: ", "ID "+id+" DELETADO!");
        } catch (Exception e){
            Log.d("ERROR: ", e.getMessage());
        }
    }
}
