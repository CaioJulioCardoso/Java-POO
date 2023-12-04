package packCalculadora;
import packInterface.ExceptionGen;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Casa extends Calculadora {


    // Bloco de Atributos

    private final double areaConstruida;
    private final double areaTotal;
    private final double desconto;


    // Métodos Getters

    public double getAreaConstruida() {
        return areaConstruida;
    }
    public double getAreaTotal() {
        return areaTotal;
    }
    public double getDesconto() {
        return desconto;
    }


    // Construtor

    public Casa(double valorImovel, double taxaJurosAnual, int prazoFinanciamento, double areaConstruida, double areaTotal, double desconto) {
        super(valorImovel, taxaJurosAnual, prazoFinanciamento);
        this.areaConstruida = areaConstruida;
        this.areaTotal = areaTotal;
        this.desconto = desconto;
    }


    // Método de cálculo ( pagamento mensal )

    @Override
    public double pagamentoMensal() {

        double pagamentoMensal = (getValorImovel() / getPrazoFinanciamento()) * (1 + (getTaxaJurosAnual() / 12));
        double jurosMensal = pagamentoMensal * (getTaxaJurosAnual()/12);

        try{
            ExceptionGen.DescontoMaiorDoQueJurosException(getDesconto(), jurosMensal);                              // Validação semana 7
            pagamentoMensal -= getDesconto();                                                                       // Aplicar desconto de no máximo R$ 100 por parcela
            return pagamentoMensal;

        } catch (ExceptionGen e){
            throw new ExceptionGen(e.getMessage());
        }
    }


    // Bloco para cálculo (Pagamento Total)
    @Override
    public double pagamentoTotal(){
        double pagamentoMensalComDesconto = pagamentoMensal();                          // Chama o método que calcula o pagamento mensal
        double pagamentoTotal = pagamentoMensalComDesconto * getPrazoFinanciamento();   // Calcula o pagamento total com base no pagamento mensal com desconto
        return pagamentoTotal;
    }


    // Método para salvar os dados em um arquivo de texto

    public void salvarDados(String nomeArquivo) {
        try (FileWriter fileWriter = new FileWriter(nomeArquivo, true);
             PrintWriter save = new PrintWriter(fileWriter)) {
            String casa = "Casa";                                                            // Variável para mostrar opção selecionada


            // Bloco para tratar os dados com Decimal Format

            DecimalFormat formate = new DecimalFormat("R$ ###,###,###.00");           // Criando um objeto para tratar os dados (R$)
            DecimalFormat formateArea = new DecimalFormat("###,##0 m²");              // Criando um objeto para tratar os dados (m²)
            String valorImovel = formate.format(getValorImovel());                           // Tratando o dado valorImovel
            String valorImovelTotal = formate.format(pagamentoTotal());                      // Tratando o dado valorImovelTotal
            String areaTotalAdq = formateArea.format(getAreaTotal());                        // Tratando o dado areaTotal
            String areaConstruidaAdq = formateArea.format(getAreaConstruida());              // Tratando o dado areaConstruida
            String descontoAqd = formate.format(getDesconto());                              // Tratando o dado valorDesconto


            // Bloco para juntar informação dos dados + os dados

            String tipoFinal = "Tipo de financiamento:  " + casa;                                    // Tipo de Financiamento
            String valorImovelFinal = "Valor do imóvel:        " + valorImovel;                      // Valor do Imóvel
            String valorTotalFinal = "Valor do Financiamento: " + valorImovelTotal;                  // Valor Total do Imóvel
            String jurosFinal = "Valor da Taxa de Juros: " + getTaxaJurosAnual() + "%";              // Valor do Juros
            String prazoFinal = "Prazo de Financiamento: " + getPrazoFinanciamento() + " Anos";      // Prazo de Financiamento
            String areaTotalFinal = "Área Total:             " + areaTotalAdq;                       // Área Total
            String areaConstruidaFinal = "Área Construída:        " + areaConstruidaAdq;             // Área Construída
            String descontoFinal = "Desconto por parcela:   " + descontoAqd;                         // Desconto


            // Bloco de Save

            save.println("-------------------------");
            save.println(tipoFinal);
            save.println(valorImovelFinal);
            save.println(valorTotalFinal);
            save.println(jurosFinal);
            save.println(prazoFinal);
            save.println(areaTotalFinal);
            save.println(areaConstruidaFinal);
            save.println(descontoFinal);
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
        } catch (IOException | ClassNotFoundException e) {                               // Básicamente para caso o arquivo não exista
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


    // Método para "output"

    @Override
    public String toString() {


        // Convertendo os dados com DecimalFormat

        DecimalFormat formate = new DecimalFormat("R$ ###,###,###.00");                  // Criando um objeto para tratar os dados (R$)
        String valorImovel = formate.format(getValorImovel());                                  // Tratando o dado valorImovel
        String valorImovelTotal = formate.format(pagamentoTotal());                             // Tratando o dado valorImovelTotal
        String desconto = formate.format(getDesconto());                                        // Tratando o dado desconto




        return "Tipo de financiamento: Casa{" +
                "Valor do imóvel= " + valorImovel +
                ", Valor do Financiamento= " + valorImovelTotal +
                ", Valor da Taxa de Juros= " + getTaxaJurosAnual() + " %" +
                ", Prazo de Financiamento= " + getPrazoFinanciamento() + " anos" +
                ", Área Construida=" + getAreaConstruida() + "m²" +
                ", Área Total=" + getAreaTotal() + "m²"+
                ", Desconto por Parcela=" + desconto +
                '}';
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
    }