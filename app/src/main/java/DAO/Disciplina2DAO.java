package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import modelo.Disciplina;

/**
 * Created by Luiza on 03/04/2016.
 */
public class Disciplina2DAO extends SQLiteOpenHelper {
    public Disciplina2DAO(Context context) {
        super(context,"Disciplina2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Disciplina2" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_escola INTEGER, " +
                "nome varchar(50), " +
                "meta double," +
                "quantProvas int);";
        db.execSQL(sql);

    }

    public void deletarBD() {
        String sql = "DROP TABLE IF EXISTS Disciplina2";
        getReadableDatabase().rawQuery(sql, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS disciplina2";
        db.execSQL(sql);
        onCreate(db);
    }

    public void inserir(Disciplina disciplina){
        ContentValues cv = new ContentValues();
        cv.put("id_escola", disciplina.getId_escola());
        cv.put("nome", disciplina.getNome());
        cv.put("meta", disciplina.getMeta());
        cv.put("quantProvas", disciplina.getQuantiProvas());

        getWritableDatabase().insert("disciplina2", null, cv);
    }

    public void deletar(int idDisciplina){
        getWritableDatabase().delete("Disciplina2 ", "id = " + idDisciplina, null);
    }

//    public List<Disciplina> listaDisciplinas(){
//        List<Disciplina> listaDeDisciplina = new ArrayList<Disciplina>();
//        String sql = "select * from Disciplina2";
//        Cursor c = getReadableDatabase().rawQuery(sql, null);
//
//        while (c.moveToNext()){
//            int id = c.getInt(c.getColumnIndex("id"));
//            int id_usuario = c.getInt(c.getColumnIndex("id_escola"));
//            String nome = c.getString(c.getColumnIndex("nome"));
//            double meta = c.getDouble(c.getColumnIndex("meta"));
//            int quantProvas = c.getInt(c.getColumnIndex("quantProvas"));
//
//            Disciplina d = new Disciplina(id_usuario,nome,meta,quantProvas);
//            d.setId(id);
//
//            listaDeDisciplina.add(d);
//        }
//
//        return listaDeDisciplina;
//    }



    public List<String> ListaDeNomesDisciplinaPorIdEscola(int idescola){
        List<String> listaDeNomeDisciplina = new ArrayList<>();
        String sql = "select * from Disciplina2 where id_escola = " +idescola;
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()){
            String nome = c.getString(c.getColumnIndex("nome"));
            listaDeNomeDisciplina.add(nome);
        }

        return listaDeNomeDisciplina;
    }

    public Disciplina RetornarDisciplina(int idEscola, String nomeDisciplina){
        Disciplina disciplina = null;
        String sql = "select * from Disciplina2 where id_escola = " +idEscola +" and nome like '" + nomeDisciplina+"'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            int id_escola = c.getInt(c.getColumnIndex("id_escola"));
            String nome = c.getString(c.getColumnIndex("nome"));
            double meta = c.getDouble(c.getColumnIndex("meta"));
            int quantProvas = c.getInt(c.getColumnIndex("quantProvas"));

            disciplina = new Disciplina(id_escola,nome,meta,quantProvas);
            disciplina.setId(id);
            return disciplina;
        }

        return disciplina;
    }

}
