package testeboletimescolar.br.edu.ifpi.boletimescolar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import DAO.EscolaDAO;
import modelo.Escola;

public class DetalhesEscola extends AppCompatActivity {
    EscolaDAO escolaDAO = new EscolaDAO(this);

    String idEscola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_escola);

        idEscola = getIntent().getStringExtra("idEscola");

        TextView nomeEscola = (TextView) findViewById(R.id.nomeEscola);
        TextView curso = (TextView) findViewById(R.id.cursoEscola);
        TextView mediaEscolar = (TextView) findViewById(R.id.mediaEscolar);
        TextView mediaEscolarFinal = (TextView) findViewById(R.id.mediaEscolarFinal);


        Escola escola = escolaDAO.RetornarEscolaPorId(Integer.valueOf(idEscola));

        nomeEscola.setText(escola.getNome());
        curso.setText(escola.getCurso());
        mediaEscolar.setText(""+escola.getMediaEscolar());
        mediaEscolarFinal.setText(""+escola.getMediaEscolarFinal());
    }

    public void cadastrarDisciplina(View v){
        Intent irParaCadatroDisciplina = new Intent(this, CadastrarDisciplina.class);
        irParaCadatroDisciplina.putExtra("idEscola", idEscola);
        startActivity(irParaCadatroDisciplina);
    }

    public void verDisciplinas(View v){
        Intent irParaListaDisciplina = new Intent(this, ListaDeDisciplinas.class);
        irParaListaDisciplina.putExtra("idEscola", idEscola);
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
