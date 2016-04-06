package testeboletimescolar.br.edu.ifpi.boletimescolar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import DAO.Disciplina2DAO;
import modelo.Disciplina;

public class CadastrarDisciplina extends AppCompatActivity {

    Disciplina2DAO disciplinaDAO = new Disciplina2DAO(this);
    String idEscola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_disciplina);

        idEscola = getIntent().getStringExtra("idEscola");
    }

    public void cadastrar(final View view){
//        disciplinaDAO.deletarBD();
        final EditText nome = (EditText) findViewById(R.id.nomeDisciplinaCadastro);
        final EditText mediaPretendia = (EditText) findViewById(R.id.mediaPretendidaCadastro);
        final EditText quantProvas = (EditText) findViewById(R.id.quantProvas);

        if(""+nome.getText() != "" && ""+mediaPretendia.getText() != "" && ""+quantProvas.getText() != ""){
            Disciplina disciplina = new Disciplina(Integer.valueOf(idEscola),""+nome.getText(),
                    Double.valueOf(""+mediaPretendia.getText()), Integer.valueOf("" + quantProvas.getText()));

            List<String> listaDisciplinaDoUsuario = disciplinaDAO.ListaDeNomesDisciplinaPorIdEscola(Integer.valueOf(idEscola));
            if(!listaDisciplinaDoUsuario.contains(""+nome.getText())){
                disciplinaDAO.inserir(disciplina);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Disciplina adicionada \n Deseja adicionar outra");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPretendia.setText("");
                        nome.setText("");
                        quantProvas.setText("");
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
//              Toast.makeText(this, "Matéria inserida com sucesso!!", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this, "Disciplina ja Cadastrada!!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Por favor preencha todos os obrigatórios!", Toast.LENGTH_LONG).show();
        }
    }
}
