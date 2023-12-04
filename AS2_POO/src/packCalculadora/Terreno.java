package packCalculadora;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class Terreno extends Calculadora {


    // Atributos

    private final String zona;


    // Getter

    public String getZona() {
        return zona;
    }


    // Construtor

    public Terreno(double valorImovel, double taxaJurosAnual, int prazoFinanciamento, String zona) {
        super(valorImovel, taxaJurosAnual, prazoFinanciamento);
        this.zona = zona;
    }


    // Método de cálculo ( pagamento mensal )

    @Override
    public double pagamentoMensal() {
        return (getValorImovel() / getPrazoFinanciamento()) * (1 + (getTaxaJurosAnual() / 12));
    }


    // Bloco para cálculo (Pagamento Total)

    @Override
    public double pagamentoTotal() {
        double pagamentoTotal = pagamentoMensal() * getPrazoFinanciamento();
        // + 2% sobre o valor total
        return pagamentoTotal * 1.02;
    }


    // Método para salvar os dados em um arquivo de texto

    public void salvarDados(String nomeArquivo) {
        try (FileWriter fileWriter = new FileWriter(nomeArquivo, true);
                PrintWriter save = new PrintWriter(fileWriter)) {
            String terreno1 = "Terreno";                                                                  // Variável para mostrar opção selecionada


            // Bloco para tratar os dados com Decimal Format

            DecimalFormat formate = new DecimalFormat("R$ ###,###,###.00");                        // Criando um objeto para tratar os dados (R$)
            String valorImovel = formate.format(getValorImovel());                                        // Tratando o dado valorImovel
            String valorImovelTotal = formate.format(pagamentoTotal());                                   // Tratando o dado valorImovelTotal


            // Bloco para juntar informação dos dados + os dados

            String tipoFinal = "Tipo de financiamento:  " + terreno1;                                      // Tipo de Financiamento
            String valorImovelFinal = "Valor do imóvel:        " + valorImovel;                            // Valor Imóvel
            String valorTotalFinal = "Valor do Financiamento: " + valorImovelTotal;                        // Valor Imóvel Total
            String jurosFinal = "Valor da Taxa de Juros: " + getTaxaJurosAnual() + " %";                   // Valor Juros
            String prazoFinal = "Prazo de Financiamento: " + getPrazoFinanciamento() + " Anos";            // Prazo Financiamento
            String zonaFinal = "Tipo de Zona: " + getZona();                                               // Zona


            // Bloco de Save

            save.println("-------------------------");
            save.println(tipoFinal);
            save.println(valorImovelFinal);
            save.println(valorTotalFinal);
            save.println(jurosFinal);
            save.println(prazoFinal);
            save.println(zonaFinal);
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


    // Método para "output"

    @Override
    public String toString() {


        // Convertendo os dados com DecimalFormat

        DecimalFormat formate = new DecimalFormat("R$ ###,###,###.00");                    // Criando um objeto para tratar os dados (R$)
        String valorImovel = formate.format(getValorImovel());                                    // Tratando o dado valorImovel
        String valorImovelTotal = formate.format(pagamentoTotal());                               // Tratando o dado valorImovelTotal


        return "Tipo de financiamento: Terreno{" +
                "Valor do imóvel= " + valorImovel +
                ", Valor do Financiamento= " + valorImovelTotal +
                ", Valor da Taxa de Juros= " + getTaxaJurosAnual() + " %" +
                ", Prazo de Financiamento= " + getPrazoFinanciamento() + " anos" +
                ", Zona= " + zona + "}";

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
        } catch (IOException | ClassNotFoundException e) {                                               // Básicamente para caso o arquivo não exista
        }
    }
    }
