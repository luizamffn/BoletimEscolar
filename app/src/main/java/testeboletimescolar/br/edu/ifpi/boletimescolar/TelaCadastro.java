package testeboletimescolar.br.edu.ifpi.boletimescolar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import DAO.UsuarioDAO;
import modelo.Usuario;

public class TelaCadastro extends AppCompatActivity {
    UsuarioDAO usuarioDAO = new UsuarioDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
    }

    public void cadastrar(View view){
        EditText nome = (EditText) findViewById(R.id.nome);
        EditText usuario = (EditText) findViewById(R.id.usuario);
        EditText senha = (EditText) findViewById(R.id.senha);
        System.out.println("nome " + nome.getText());

        Usuario u = new Usuario(""+nome.getText(),""+usuario.getText(),""+senha.getText());

        if(""+nome.getText() != "" && ""+usuario.getText() != "" && ""+senha.getText() !=  ""){
            List<String> usuarios = usuarioDAO.listaNomesUsuarios();
            if(!usuarios.contains(""+usuario.getText())){
                usuarioDAO.inserir(u);
                finish();
                Toast.makeText(TelaCadastro.this, "Usuário Inserido com sucesso! \n\t\t\tDivirta-se! ", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(TelaCadastro.this, "Usuario ja existe!", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(TelaCadastro.this, "Campos incompletos. Por favor preencha todos os campos!", Toast.LENGTH_LONG).show();
        }
    }
}
