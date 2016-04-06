package modelo;

/**
 * Created by Luiza on 23/03/2016.
 */
public class Disciplina {
    private int id;
    private int id_escola;
    private String nome;
    private double Meta;
    private int quantiProvas;
//    private List<Nota> Notas = new ArrayList<>();

    public Disciplina(int id_escola, String nome, double meta, int quantiProvas) {
        this.id_escola = id_escola;
        this.nome = nome;
        Meta = meta;
        this.quantiProvas = quantiProvas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_escola() {
        return id_escola;
    }

    public void setId_escola(int id_escola) {
        this.id_escola = id_escola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMeta() {
        return Meta;
    }

    public void setMeta(double meta) {
        Meta = meta;
    }

    public int getQuantiProvas() {
        return quantiProvas;
    }

    public void setQuantiProvas(int quantiProvas) {
        this.quantiProvas = quantiProvas;
    }


}
