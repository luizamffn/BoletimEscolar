package modelo;

/**
 * Created by Luiza on 01/04/2016.
 */
public class Escola {
    private int id;
    private int id_usuario;
    private String nome;
    private String curso;
    private double MediaEscolar = 0;
    private double MediaEscolarFinal = 0;
//    private List<Disciplina> Notas = new ArrayList<>();


    public Escola(int id_usuario, String nome, String curso, double mediaEscolar, double mediaEscolarFinal) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.curso = curso;
        MediaEscolar = mediaEscolar;
        MediaEscolarFinal = mediaEscolarFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public double getMediaEscolar() {
        return MediaEscolar;
    }

    public void setMediaEscolar(double mediaEscolar) {
        MediaEscolar = mediaEscolar;
    }

    public double getMediaEscolarFinal() {
        return MediaEscolarFinal;
    }

    public void setMediaEscolarFinal(double mediaEscolarFinal) {
        MediaEscolarFinal = mediaEscolarFinal;
    }
}
