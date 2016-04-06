//package DAO;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import modelo.Nota;
//
///**
// * Created by Luiza on 26/03/2016.
// */
//public class NotaDAO extends SQLiteOpenHelper {
//    public NotaDAO(Context context) {
//        super(context, "Notas.db", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE Notas" +
//                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "id_disciplina INTEGER, " +
//                "valor double);";
//        db.execSQL(sql);
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String sql = "DROP TABLE IF EXISTIS Notas";
//        db.execSQL(sql);
//        onCreate(db);
//    }
//
//    public void inserir(Nota notas){
//        ContentValues cv = new ContentValues();
//        cv.put("id_disciplina", notas.getId_disciplina());
//        cv.put("valor", notas.getValor());
//
//        getWritableDatabase().insert("notas", null, cv);
//    }
//
//    public List<Nota> listaNotas(){
//        List<Nota> listaDeNotas = new ArrayList<>();
//        String sql = "select * from Notas";
//        Cursor c = getReadableDatabase().rawQuery(sql, null);
//
//        while (c.moveToNext()){
//            int id = c.getInt(c.getColumnIndex("id"));
//            int id_disciplina = c.getInt(c.getColumnIndex("id_disciplina"));
//            double valor = c.getDouble(c.getColumnIndex("valor"));
//
//            Nota n = new Nota(id_disciplina,valor);
//            n.setId(id);
//
//            listaDeNotas.add(n);
//        }
//
//        return listaDeNotas;
//    }
//
//    public List<String> listaValorNotasPorIdDisciplina(int idMateria){
//        List<String> valores = new ArrayList<>();
//        String sql = "select * from Notas where id_disciplina = " +idMateria;
//        Cursor c = getReadableDatabase().rawQuery(sql, null);
//
//        while (c.moveToNext()){
//            double valor = c.getDouble(c.getColumnIndex("valor"));
//            System.out.println("Valor "+valor);
//
//            valores.add(""+valor);
//        }
//
//        return valores;
//    }
//
//}
