package entities;

public class Fruta extends Produto {
    public Fruta(String nome, double precoUnitario, int quantidade) {
        super(nome, precoUnitario, quantidade);
    }

    @Override
    public double getTotal(int quantidadeComprada) {
        return getPrecoUnitario() * quantidadeComprada;
    }

    public double calcularDiferencaPrecoPorcentagem(double precoAntigo) {
        double diferenca = precoAntigo - getPrecoUnitario();
        return Math.abs((diferenca / precoAntigo) * 100);
    }
}
