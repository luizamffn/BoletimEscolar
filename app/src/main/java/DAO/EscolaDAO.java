package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import modelo.Escola;

/**
 * Created by Luiza on 01/04/2016.
 */
public class EscolaDAO extends SQLiteOpenHelper {
    public EscolaDAO(Context context) {
        super(context, "Escola.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Escola" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_usuario INTEGER, " +
                "nome varchar(50), " +
                "curso varchar(50), " +
                "mediaEscolar doube," +
                "mediaEscolarFinal double);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTIS Escola";
        db.execSQL(sql);
        onCreate(db);
    }

    public void inserir(Escola escola){
        ContentValues cv = new ContentValues();
        cv.put("id_usuario", escola.getId_usuario());
        cv.put("nome", escola.getNome());
        cv.put("curso", escola.getCurso());
        cv.put("mediaEscolar", escola.getMediaEscolar());
        cv.put("mediaEscolarFinal", escola.getMediaEscolarFinal());

        getWritableDatabase().insert("escola", null, cv);
    }

    public void deletar(Escola escola){
        System.out.println("IdEscolaDelte  " + escola.getId());
        getWritableDatabase().delete("Escola ", "id = " + escola.getId(), null);
    }

    public Escola RetornarEscolaPorId(int idEscola){
        Escola escola = null;
        String sql = "select * from Escola where id = " +idEscola;
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            int id_usuario = c.getInt(c.getColumnIndex("id_usuario"));
            String nome = c.getString(c.getColumnIndex("nome"));
            String curso = c.getString(c.getColumnIndex("curso"));
            double mediaEscola = c.getDouble(c.getColumnIndex("mediaEscolar"));
            double mediaEscolaFinal = c.getDouble(c.getColumnIndex("mediaEscolarFinal"));

            escola = new Escola(id_usuario,nome,curso,mediaEscola,mediaEscolaFinal);
            escola.setId(id);
        }

        return escola;
    }

    public List<String> listaDeNomesEscolaPorIdUsuario(int idUsuario){
        List<String> listaDeNomeEscola = new ArrayList<>();
        String sql = "select * from Escola where id_usuario = " +idUsuario;
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()){
            String nome = c.getString(c.getColumnIndex("nome"));
            listaDeNomeEscola.add(nome);
        }

        return listaDeNomeEscola;
    }

    public Escola RetornarEscolaDoIsuario(int idUsuario , String nomeEscola){
        Escola escola = null;
        String sql = "select * from Escola where id_usuario = " +idUsuario +" and nome like '" + nomeEscola+"'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            int id_usuario = c.getInt(c.getColumnIndex("id_usuario"));
            String nome = c.getString(c.getColumnIndex("nome"));
            String curso = c.getString(c.getColumnIndex("curso"));
            double mediaEscola = c.getDouble(c.getColumnIndex("mediaEscolar"));
            double mediaEscolaFinal = c.getDouble(c.getColumnIndex("mediaEscolarFinal"));

            escola = new Escola(id_usuario,nome,curso,mediaEscola,mediaEscolaFinal);
            escola.setId(id);
        }

        return escola;
    }
}
