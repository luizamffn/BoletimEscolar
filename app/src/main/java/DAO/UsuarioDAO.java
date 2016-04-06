package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

/**
 * Created by Luiza on 23/03/2016.
 */
public class UsuarioDAO extends SQLiteOpenHelper {

    public UsuarioDAO(Context context) {
        super(context, "Usuario.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Usuario" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome varchar(50), " +
                "usuario varchar(50), " +
                "senha varchar(50));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTIS Usuario";
        db.execSQL(sql);
        onCreate(db);
    }

    public void inserir(Usuario usuario){
        ContentValues cv = new ContentValues();
        cv.put("nome", usuario.getNome());
        cv.put("usuario", usuario.getUsuario());
        cv.put("senha", usuario.getSenha());

        getWritableDatabase().insert("usuario", null, cv);
    }

    public void deletar(Usuario usuario){
        String[] args = {""+usuario.getId()};
        getWritableDatabase().delete("Usuario ", "id = ?", args);
    }

    public List<String> listaNomesUsuarios(){
        List<String> listaDeNomesUsuarios = new ArrayList<>();
        String sql = "select * from Usuario;";
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()){
            String usuarioNome = c.getString(c.getColumnIndex("usuario"));

            listaDeNomesUsuarios.add(usuarioNome);
        }

        return listaDeNomesUsuarios;
    }

    public Usuario retornarUsuario(String usuario){
        String sql = "select * from Usuario where usuario like '" + usuario+"'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        Usuario u = null;
        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            String usuarioNome = c.getString(c.getColumnIndex("usuario"));
            String senha = c.getString(c.getColumnIndex("senha"));

            u = new Usuario(nome,usuarioNome,senha);
            u.setId(id);
        }
        return u;
    }

}
