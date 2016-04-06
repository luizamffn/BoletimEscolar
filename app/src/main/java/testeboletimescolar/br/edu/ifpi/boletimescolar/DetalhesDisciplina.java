package testeboletimescolar.br.edu.ifpi.boletimescolar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import DAO.Disciplina2DAO;
import DAO.EscolaDAO;
import DAO.NotaDAO2;
import enums.TipoProva;
import modelo.Disciplina;
import modelo.Escola;

public class DetalhesDisciplina extends AppCompatActivity {
    EscolaDAO escolaDAO = new EscolaDAO(this);
    Disciplina2DAO disciplinaDAO = new Disciplina2DAO(this);
    NotaDAO2 notaDAO = new NotaDAO2(this);
    Disciplina disciplina;
    String idEscola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_disciplina);

        idEscola = getIntent().getStringExtra("idEscola");
        String disc = getIntent().getStringExtra("disciplina");

        disciplina = disciplinaDAO.RetornarDisciplina(Integer.valueOf(idEscola), disc);

        if(disciplina == null){
            finish();
            Toast.makeText(DetalhesDisciplina.this, "Erro contactar o administrador!", Toast.LENGTH_LONG).show();
        }else{
            TextView quantProvas = (TextView) findViewById(R.id.quantProvasExibir);
            TextView nomeDisciplina = (TextView) findViewById(R.id.nomeDisciplina);
            TextView mediaPretendida = (TextView) findViewById(R.id.mediaPretendidaTest);

            quantProvas.setText(""+ disciplina.getQuantiProvas());
            nomeDisciplina.setText(disciplina.getNome());
            mediaPretendida.setText("Media Pretendida: " + disciplina.getMeta());
        }

    }

    public void cadastrarNotas(View v){
        Intent irParaCadastroNotas = new Intent(DetalhesDisciplina.this, CadastroNotas.class);
        irParaCadastroNotas.putExtra("idDisciplina", "" + disciplina.getId());
        irParaCadastroNotas.putExtra("disciplinaNome", "" + disciplina.getNome());
        irParaCadastroNotas.putExtra("tipoProva", ""+ TipoProva.PN);
        startActivity(irParaCadastroNotas);
    }

    public void provaFinal(View v){
        Button b = (Button) findViewById(R.id.provaFinal);
        b.setVisibility(View.GONE);
        Intent irParaCadastroNotas = new Intent(DetalhesDisciplina.this, CadastroNotas.class);
        irParaCadastroNotas.putExtra("idDisciplina", "" + disciplina.getId());
        irParaCadastroNotas.putExtra("disciplinaNome", "" + disciplina.getNome());
        irParaCadastroNotas.putExtra("tipoProva", "" + TipoProva.PR);
        startActivity(irParaCadastroNotas);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Button butao = (Button) findViewById(R.id.butaoNota);
        Button pFinal = (Button) findViewById(R.id.provaFinal);
        TextView media = (TextView) findViewById(R.id.media);
        TextView mediaFinish = (TextView) findViewById(R.id.mediaFinish);
        TextView provaFinal = (TextView) findViewById(R.id.provaFinalTexto);
        TextView obs = (TextView) findViewById(R.id.observacao);
        ListView listaDeNotas = (ListView) findViewById(R.id.list_notas);
        List<String> listaDeValorNotas =  notaDAO.listaValorNotasPorIdDisciplina(disciplina.getId(),""+TipoProva.PN);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaDeValorNotas);
        listaDeNotas.setAdapter(adapter);

        double soma=0, mediaCalculo = 0, mediaFinal = 0;
        int count = 0;

        for (String s : listaDeValorNotas) {
            soma += Double.valueOf(s);
            count++;
        }

        if(count !=0){
            mediaCalculo = soma/count;
            media.setText("Média : " + mediaCalculo);

            if(mediaCalculo == Double.valueOf(disciplina.getMeta())){
                media.setTextColor(Color.YELLOW);
                obs.setText("Parabéns sua media pretendida foi atingida, não perca o ritmo!");
            }else{
                if(mediaCalculo < Double.valueOf(disciplina.getMeta())){
                    media.setTextColor(Color.RED);
                    obs.setText("Você está abaixo da media pretendida, estude mais um pouco!!");
                }else{
                    media.setTextColor(Color.BLUE);
                    obs.setText("Parábens, você esta acima da media que voce planejou. Não perca o foco!");
                }
            }
        }

        if(count == disciplina.getQuantiProvas()){
            butao.setVisibility(View.GONE);
            Escola escola = escolaDAO.RetornarEscolaPorId(Integer.valueOf(idEscola));
            if(mediaCalculo < escola.getMediaEscolar()){
                String notaPR =  notaDAO.notaPR(disciplina.getId(), "" + TipoProva.PR);
                System.out.println("nota PR "+notaPR);
                if(notaPR ==""){
                    pFinal.setVisibility(View.VISIBLE);
                }else{
                    mediaFinish.setVisibility(View.VISIBLE);
                    provaFinal.setText("Prova Final = " + notaPR);
                    mediaFinal = (mediaCalculo + Double.valueOf(""+notaPR))/2;
                    mediaFinish.setText("Média Final: " + mediaFinal);
                    if(mediaFinal >= escola.getMediaEscolarFinal()){
                            obs.setText("Parabéns Você passou!!");
                            mediaFinish.setTextColor(Color.BLUE);
                    }else{
                        obs.setText("Você esta Reprovado na disciplina!!");
                        mediaFinish.setTextColor(Color.RED);
                    }
                    pFinal.setVisibility(View.GONE);
                }
            }else {
                obs.setText("Você passou com media: " + mediaCalculo);
            }
        }
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
