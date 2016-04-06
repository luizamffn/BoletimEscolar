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

import DAO.EscolaDAO;
import modelo.Escola;

public class ListaDeEscolas extends AppCompatActivity {

    EscolaDAO escolaDAO = new EscolaDAO(this);
    String idUsu,nomeEscola;
    Escola escola;
    ListView listEscolas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_escolas);

        idUsu = getIntent().getStringExtra("idUsuario");

        listEscolas = (ListView) findViewById(R.id.list_Escola);
        registerForContextMenu(listEscolas);

        listEscolas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                nomeEscola = "" + parent.getItemAtPosition(position);
                escola = escolaDAO.RetornarEscolaDoIsuario(Integer.valueOf(idUsu), nomeEscola);
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
                Intent irParaDetalhesEscola = new Intent(ListaDeEscolas.this, DetalhesEscola.class);
                irParaDetalhesEscola.putExtra("idEscola", "" + escola.getId());
                startActivity(irParaDetalhesEscola);
                return false;
            }
        });
        MenuItem excluir =  menu.add("Excluir");
        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                try {
                    escolaDAO.deletar(escola);
                    update();
                    Toast.makeText(ListaDeEscolas.this, "Disciplina deletada com sucesso!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ListaDeEscolas.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    public void update(){
        List<String> escolas = escolaDAO.listaDeNomesEscolaPorIdUsuario(Integer.valueOf(idUsu));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, escolas);

        listEscolas.setAdapter(adapter);
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
