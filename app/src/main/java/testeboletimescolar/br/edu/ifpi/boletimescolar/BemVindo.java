package testeboletimescolar.br.edu.ifpi.boletimescolar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class BemVindo extends AppCompatActivity{

    String idUsu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        String msg = getIntent().getStringExtra("MENSAGEM");
        idUsu = getIntent().getStringExtra("idUsuario");
        System.out.println(idUsu);

        TextView usuario = (TextView) findViewById(R.id.bemVindo);
        usuario.setText("Bem Vindo(a) " + msg);
        usuario.setTextSize(30);

    }

    public void cadastrarEscola(View v){
        Intent irParaCadatroEscola = new Intent(this, CadastroEscola.class);
        irParaCadatroEscola.putExtra("idUsuario", idUsu);
        startActivity(irParaCadatroEscola);
    }

    public void verEscolas(View v){
        Intent irParaListaDisciplina = new Intent(this, ListaDeEscolas.class);
        irParaListaDisciplina.putExtra("idUsuario", idUsu);
        startActivity(irParaListaDisciplina);
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sair){
            finish();
            Intent irParaInicio = new Intent(this, TelaInicial.class);
            startActivity(irParaInicio);
        }
        return super.onOptionsItemSelected(item);
    }

}
