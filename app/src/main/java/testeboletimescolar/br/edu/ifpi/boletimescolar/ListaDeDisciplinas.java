package testeboletimescolar.br.edu.ifpi.boletimescolar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import DAO.Disciplina2DAO;
import modelo.Disciplina;

public class ListaDeDisciplinas extends AppCompatActivity {

    Disciplina2DAO disciplinaDAO = new Disciplina2DAO(this);

    String idEscola,nomeMateria;
    ListView listDisciplinas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_disciplinas);

        //setando o id dousuario
        idEscola = getIntent().getStringExtra("idEscola");

        listDisciplinas = (ListView) findViewById(R.id.list_Disciplinas);
        registerForContextMenu(listDisciplinas);

        listDisciplinas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                nomeMateria = "" + parent.getItemAtPosition(position);
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem informacoes =  menu.add("Informações");
        informacoes.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent irParaDetalhesDisciplina = new Intent(ListaDeDisciplinas.this, DetalhesDisciplina.class);
                irParaDetalhesDisciplina.putExtra("idEscola", "" + idEscola);
                irParaDetalhesDisciplina.putExtra("disciplina", nomeMateria);
                startActivity(irParaDetalhesDisciplina);
                return false;
            }
        });

        MenuItem excluir =  menu.add("Excluir");
        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                try {
                    Disciplina disciplina = disciplinaDAO.RetornarDisciplina(Integer.valueOf(idEscola), nomeMateria);
                    disciplinaDAO.deletar(disciplina.getId());
                    update();
                    Toast.makeText(ListaDeDisciplinas.this, "Disciplina deletada com sucesso!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ListaDeDisciplinas.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });
    }

    public void update(){
        List<String> disciplinas = disciplinaDAO.ListaDeNomesDisciplinaPorIdEscola(Integer.valueOf(idEscola));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, disciplinas);

        listDisciplinas.setAdapter(adapter);

    }
    @Override
    protected void onResume() {
        super.onResume();
        update();

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
