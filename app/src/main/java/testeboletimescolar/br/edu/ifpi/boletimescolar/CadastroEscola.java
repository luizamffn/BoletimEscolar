package testeboletimescolar.br.edu.ifpi.boletimescolar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import DAO.EscolaDAO;
import modelo.Escola;

public class CadastroEscola extends AppCompatActivity {
    EscolaDAO escolaDAO = new EscolaDAO(this);
    String idUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_escola);

        idUsu = getIntent().getStringExtra("idUsuario");
    }

    public void cadastrar(final View view){
        final EditText nome = (EditText) findViewById(R.id.nomeEscolaCadastro);
        final EditText curso = (EditText) findViewById(R.id.cursoEscolaCadastro);
        final EditText mediaEscolar = (EditText) findViewById(R.id.mediaEscolarCadastro);
        final EditText mediaEscolarFinal = (EditText) findViewById(R.id.mediaEscolarFinalCadastro);

        if(""+nome.getText() != "" && ""+ curso.getText() != "" &&
                ""+mediaEscolar.getText() != "" && ""+mediaEscolarFinal.getText() != ""){

            Escola escola = new Escola(Integer.valueOf(idUsu),""+nome.getText(), ""+ curso.getText(),
                    Double.valueOf(""+mediaEscolar.getText()), Double.valueOf("" +mediaEscolarFinal.getText()));

            List<String> listaEscolaUsuario = escolaDAO.listaDeNomesEscolaPorIdUsuario(Integer.valueOf(idUsu));

            if(!listaEscolaUsuario.contains(""+nome.getText())){
                escolaDAO.inserir(escola);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Escola adicionada \n Deseja adicionar outra?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaEscolarFinal.setText("");
                        mediaEscolar.setText("");
                        nome.setText("");
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
            }else{
                Toast.makeText(this, "Escola ja Cadastrada!!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Por favor preencha todos os obrigatórios!", Toast.LENGTH_LONG).show();
        }
    }
}
