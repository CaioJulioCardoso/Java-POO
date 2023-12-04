package packCalculadora;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Apartamento extends Calculadora {


    // Bloco de Atributos

    private final int numeroVagasGaragem;
    private final int numeroAndar;


    // Métodos Getters

    public int getNumeroVagasGaragem() {
        return numeroVagasGaragem;
    }
    public int getNumeroAndar() {
        return numeroAndar;
    }


    // Construtor

    public Apartamento(double valorImovel, double taxaJurosAnual, int prazoFinanciamento, int numeroVagasGaragem, int numeroAndar) {
        super(valorImovel, taxaJurosAnual, prazoFinanciamento);
        this.numeroVagasGaragem = numeroVagasGaragem;
        this.numeroAndar = numeroAndar;
    }


    // Método de cálculo ( pagamento mensal )

    @Override
    public double pagamentoMensal() {
        double pagamentoMensal = (getValorImovel() / getPrazoFinanciamento()) * (1 + (getTaxaJurosAnual() / 12));
        double taxa = 5.0 / getPrazoFinanciamento();
        return pagamentoMensal * (1 - taxa / 100);
    }


    // Método para pagamentoTotal

    @Override
    public double pagamentoTotal(){
        return pagamentoMensal() * getPrazoFinanciamento();
    }


    // Método para salvar TXT

    public void salvarDados(String nomeArquivo) {
        String apartamento = "Apartamento";                                                       // Variável para mostrar opção selecionada
        try (FileWriter fileWriter = new FileWriter(nomeArquivo, true);
             PrintWriter save = new PrintWriter(fileWriter)) {


            // Bloco para tratar os dados com Decimal Format

            DecimalFormat formate = new DecimalFormat("R$ ###,###,###.00");                // Criando um objeto para tratar os dados (R$)
            String valorImovel = formate.format(getValorImovel());                                // Tratando o dado valorImovel
            String valorImovelTotal = formate.format(pagamentoTotal());                           // Tratando o dado valorImovelTotal


            // Bloco para juntar informação dos dados + os dados

            String tipoFinal = "Tipo de financiamento:  " + apartamento;                         // Tipo de financiamento
            String valorImovelFinal = "Valor do imóvel:        " + valorImovel;                  // Valor Imóvel
            String valorTotalFinal = "Valor do Financiamento: " + valorImovelTotal;              // Valor Imóvel Total
            String jurosFinal = "Valor da Taxa de Juros: " + getTaxaJurosAnual() + "%";          // Valor Juros
            String prazoFinal = "Prazo de Financiamento: " + getPrazoFinanciamento() + " Anos";  // Prazo de Financiamento
            String garagemFinal = "Número de Garagens:     " + getNumeroVagasGaragem();          // Vagas Garagem
            String andarFinal = "Número de Andares:      " +getNumeroAndar();                    // Número do Andar


            // Bloco de Save

            save.println("-------------------------");
            save.println(tipoFinal);
            save.println(valorImovelFinal);
            save.println(valorTotalFinal);
            save.println(jurosFinal);
            save.println(prazoFinal);
            save.println(garagemFinal);
            save.println(andarFinal);
            save.println("-------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Método para ler Arquivo TXT
    public static void lerArquivo(String nomeArquivo) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar a lista já salva
    public void aquecimento(ArrayList<Calculadora> variavelParaLoad){
        try {
            FileInputStream fileIn = new FileInputStream("agoravai.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            variavelParaLoad.clear();
            ArrayList<Calculadora> objetosDesserializados = (ArrayList<Calculadora>) in.readObject();
            variavelParaLoad.addAll(objetosDesserializados);

            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {                   // Básicamente para caso o arquivo não exista
        }
    }


    // Método para serializar

    public void serializar(ArrayList<Calculadora> generico){
        // Serializa o array para um arquivo
        try {
            FileOutputStream fileOut = new FileOutputStream("agoravai.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(generico);
            out.close();
            fileOut.close();
            System.out.println("Objetos serializados com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Método para Deserializar

    public void deserializar(){
        try {
            FileInputStream fileIn = new FileInputStream("agoravai.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Calculadora> listaDesserializada = (ArrayList<Calculadora>) in.readObject();
            in.close();
            fileIn.close();

            // Loop para mostrar Desserilariação
            for (Calculadora t : listaDesserializada) {
                System.out.println("Objeto desserializado: " + t);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para "output"

    @Override
    public String toString() {


        // Convertendo os dados com DecimalFormat

        DecimalFormat formate = new DecimalFormat("R$ ###,###,###.00");               // Criando um objeto para tratar os dados (R$)
        String valorImovel = formate.format(getValorImovel());                               // Tratando o dado valorImovel
        String valorImovelTotal = formate.format(pagamentoTotal());                          // Tratando o dado valorImovelTotal


        // Return

        return "Tipo de financiamento: Apartamento{" +
                "Valor do imóvel= " + valorImovel +
                ", Valor do Financiamento= " + valorImovelTotal +
                ", Valor da Taxa de Juros= " + getTaxaJurosAnual() + " %" +
                ", Prazo de Financiamento= " + getPrazoFinanciamento() + " anos" +
                ", Número deVagas de Garagem=" + getNumeroVagasGaragem() +
                ", Número do Andar=" + getNumeroAndar() +
                '}';
    }
}