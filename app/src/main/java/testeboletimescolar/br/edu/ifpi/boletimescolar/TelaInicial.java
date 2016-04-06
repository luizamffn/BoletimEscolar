package testeboletimescolar.br.edu.ifpi.boletimescolar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import DAO.UsuarioDAO;
import modelo.Usuario;

public class TelaInicial extends AppCompatActivity {

    UsuarioDAO usuarioDAO = new UsuarioDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
    }


    public void cadastro(View v){
        Intent irParaCadatro = new Intent(this, TelaCadastro.class);
        startActivity(irParaCadatro);
    }

    public void entrar(View v){
        EditText usuario = (EditText) findViewById(R.id.usuario);
        EditText senha = (EditText) findViewById(R.id.senha);

        List<String> nomesUsuarios = usuarioDAO.listaNomesUsuarios();

//        List<Usuario> listaUsuarios = usuarioDAO.listaUsuarios();
//
//        //Lista so com usuarios
//        List<String> listaUsu= new ArrayList<String>();
//        for (Usuario u : listaUsuarios){
//            System.out.println(u.getId() + "-" + u.getUsuario()+"-" +u.getSenha());
//            listaUsu.add(u.getUsuario());
//        }

        if(""+usuario.getText() != "" & ""+senha.getText() !=""){
            if(nomesUsuarios.contains(""+usuario.getText())) {
                Usuario u = usuarioDAO.retornarUsuario("" + usuario.getText());

                if(u.getSenha().equals(""+senha.getText())){
                    Intent irParaBemVindo = new Intent(this, BemVindo.class);
                    irParaBemVindo.putExtra("MENSAGEM",""+u.getNome());
                    irParaBemVindo.putExtra("idUsuario",""+u.getId());
                    startActivity(irParaBemVindo);
                }else{
                    Toast.makeText(TelaInicial.this, "Senha Incorreta!", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(TelaInicial.this, "Usuario Não cadastrado", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(TelaInicial.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }


    }

}
