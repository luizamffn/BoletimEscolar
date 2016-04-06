package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import modelo.Nota;

/**
 * Created by Luiza on 26/03/2016.
 */
public class NotaDAO2 extends SQLiteOpenHelper {
    public NotaDAO2(Context context) {
        super(context, "Nota.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Nota" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_disciplina INTEGER, " +
                "valor double," +
                "tipo varchar(2));";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTIS Nota";
        db.execSQL(sql);
        onCreate(db);
    }

    public void inserir(Nota notas){
        ContentValues cv = new ContentValues();
        cv.put("id_disciplina", notas.getId_disciplina());
        cv.put("valor", notas.getValor());
        cv.put("tipo", notas.getTipo());

        getWritableDatabase().insert("nota", null, cv);
    }

//    public List<Nota> listaNotas(){
//        List<Nota> listaDeNotas = new ArrayList<>();
//        String sql = "select * from Nota";
//        Cursor c = getReadableDatabase().rawQuery(sql, null);
//
//        while (c.moveToNext()){
//            int id = c.getInt(c.getColumnIndex("id"));
//            int id_disciplina = c.getInt(c.getColumnIndex("id_disciplina"));
//            double valor = c.getDouble(c.getColumnIndex("valor"));
//            String tipo = c.getString(c.getColumnIndex("tipo"));
//
//
//            Nota n = new Nota(id_disciplina,valor,tipo);
//            n.setId(id);
//
//            listaDeNotas.add(n);
//        }
//
//        return listaDeNotas;
//    }

    public List<String> listaValorNotasPorIdDisciplina(int idMateria, String tipo){
        List<String> valores = new ArrayList<>();
        String sql = "select * from Nota where id_disciplina = " +idMateria + " and tipo = '" + tipo + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()){
            double valor = c.getDouble(c.getColumnIndex("valor"));

            valores.add(""+valor);
        }

        return valores;
    }

    public String notaPR(int idMateria, String tipo){
        String valor = "";
        String sql = "select * from Nota where id_disciplina = " +idMateria + " and tipo = '" + tipo + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()){
            double v = c.getDouble(c.getColumnIndex("valor"));

            valor =""+v;
        }

        return valor;
    }

}
