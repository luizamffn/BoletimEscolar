package testeboletimescolar.br.edu.ifpi.boletimescolar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DAO.NotaDAO2;
import modelo.Nota;

public class CadastroNotas extends AppCompatActivity {
    NotaDAO2 notaDAO = new NotaDAO2(this);

    String idDisciplina,tipoProva,disciplinaNome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_notas);

        idDisciplina = getIntent().getStringExtra("idDisciplina");
        disciplinaNome = getIntent().getStringExtra("disciplinaNome");
        tipoProva = getIntent().getStringExtra("tipoProva");
        System.out.println("Tipo prova" + tipoProva);

        TextView t = (TextView) findViewById(R.id.nomeDisciplinaNota);
        t.setText(disciplinaNome);
        t.setTextSize(20);
    }

    public void cadastrarNotaConfirmar(View v){
        final EditText valor = (EditText) findViewById(R.id.valorNotaCadastro);

        if(""+valor.getText() != ""){
            Nota nota = new Nota(Integer.valueOf(idDisciplina), Double.valueOf(""+valor.getText()), tipoProva);
            notaDAO.inserir(nota);
            if(tipoProva.equals("PN")){
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage("Nota adicionada \n Deseja adicionar outra?");
//                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        valor.setText("");
//                    }
//                });
//                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//                builder.show();
                finish();
                Toast.makeText(CadastroNotas.this, "Nota adicionada!", Toast.LENGTH_SHORT).show();
            }else{
                finish();
                Toast.makeText(CadastroNotas.this, "Prova Final adicionada!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(CadastroNotas.this, "Preencha o campo nota!", Toast.LENGTH_SHORT).show();
        }

    }
}
