package entities;

public class Fruta {
    private String nome;
    private double precoUnitario;
    private int quantidade;

    public Fruta() {

    }

    public Fruta(String nome, double precoUnitario, int quantidade) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Nome = '" + getNome() + '\'' +
                ", Preço Unitário = " + String.format("%.2f", getPrecoUnitario()) +
                ", Quantidade = " + getQuantidade();
    }

    public double getTotal(int quantidadeComprada) {
        return precoUnitario * quantidadeComprada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
