package packCalculadora;
import java.io.*;

public abstract class Calculadora implements Serializable {


    // Boa prática de serialização
    @Serial
    private static final long serialVersionUID = 1L;

    // Atributos

    private final double valorImovel;
    private final double taxaJurosAnual;
    private final int prazoFinanciamento;


    // Métodos Getters

    public double getValorImovel() {
        return valorImovel;
    }
    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }
    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }


    // Método Construtor
    public Calculadora(double valorImovel, double taxaJurosAnual, int prazoFinanciamento) {
        this.valorImovel = valorImovel;
        this.taxaJurosAnual = taxaJurosAnual;
        this.prazoFinanciamento = prazoFinanciamento;
    }


    // Método ABSTRATOS (PAGAMENTOMENSAL, PAGAMENTOTOTAL, STATUS)

    public abstract double pagamentoMensal();
    public abstract double pagamentoTotal();


    // Método da classe mãe de "output"
    @Override
    public String toString() {
        return "Calculadora{" +
                "valorImovel=" + this.valorImovel +
                ", taxaJurosAnual=" + this.taxaJurosAnual +
                ", prazoFinanciamento=" + this.prazoFinanciamento +
                '}';
    }

}


