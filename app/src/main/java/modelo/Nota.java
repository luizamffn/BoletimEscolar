package modelo;

/**
 * Created by Luiza on 23/03/2016.
 */
public class Nota {
    private int id;
    private int id_disciplina;
    private double valor = 0;
    String tipo;

    public Nota(int id_disciplina, double valor, String tipo) {
        this.id_disciplina = id_disciplina;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
