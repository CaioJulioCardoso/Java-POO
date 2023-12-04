package packInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interface {

    // Constantes para limites
    private static final double MAX_VALOR_IMOVEL = 2.0E8; // 200.000.000,00
    private static final int MAX_PRAZO_FINANCIAMENTO = 50;
    private static final double MAX_TAXA_JUROS = 400.00;
    private static final double MIN_VALOR_IMOVEL = 5000.00;
    private static final double MAX_VALOR_DESCONTO = 100.00;


    // Introdução do programa

    public void introducao(){
        System.out.println(" Bem vindo ao Programa Otimizado de Operações em Mercado de Empreendimentos Seguros e Sustentaveis !");
        System.out.println(" Esse programa Calculará o financiamento de um imóvel.");
    }


    // Menu de seleção

    public int casaApTerreno() {
        int decisao;

        do {
            try {
                Scanner teclado = new Scanner(System.in);
                System.out.println("Você deseja financiar Casa (1), Apartamento (2) ou Terreno (3) ?");
                decisao = teclado.nextInt();


                ExceptionGen.validarMenu(decisao);                                                          // Validação para verificar se o número é 1, 2 ou 3

                break;
            } catch (InputMismatchException | ExceptionGen e) {
                System.out.println("Erro: Entrada Inválida! Digite uma opção válida ");
            }
        } while (true);

        // Return
        return decisao;
    }


    // Método para pedir o valor do imóvel
    public double interfaceValorImovel(){


        double retornoImovel; // iniciando a variavel de retorno

        do {
            try {
                Scanner teclado = new Scanner(System.in);
                System.out.println("Digite o valor do imóvel a ser parcelado: ");
                retornoImovel = teclado.nextDouble();

                // Validações
                ExceptionGen.validarValorMuitoAltoDinheiro(retornoImovel, MAX_VALOR_IMOVEL);  // Número muito alto
                ExceptionGen.validarValorMuitoBaixo(retornoImovel, MIN_VALOR_IMOVEL);         // Número muito baixo
                ExceptionGen.validarCasasDecimais(retornoImovel);                             // Número com até duas casas decimais
                ExceptionGen.validarNegativo(retornoImovel);                                  // Número negativo

                // Retorno
                return retornoImovel;

            } catch (InputMismatchException e){                                               // Erro de entrada sem ser número
                System.out.println("Entrada inválida! Digite um valor válido!");
            } catch (ExceptionGen e){                                                         // Erros personalizados
                System.out.println("Erro: " + e.getMessage());
            }
        } while (true);


    }


    // Método para pedir o prazo do financiamento

    public int interfacePrazoFinanciamento() {


        int retornoPrazoFinanciamento; // iniciando a variavel de retorno

        do {
            try {
                // Iniciando Scanner
                Scanner teclado = new Scanner(System.in);

                // Output
                System.out.println("Digite o prazo do financiamento em anos: ");

                // Input
                retornoPrazoFinanciamento = teclado.nextInt();

                // Validações
                ExceptionGen.validarNegativo(retornoPrazoFinanciamento);
                ExceptionGen.validarZero(retornoPrazoFinanciamento);
                ExceptionGen.validarValorMuitoAltoAnos(retornoPrazoFinanciamento, MAX_PRAZO_FINANCIAMENTO);

                // Retorno
                return retornoPrazoFinanciamento;

            } catch (InputMismatchException e) {                                     // Erro de entrada sem ser número
                System.out.println("Entrada inválida! Digite um número inteiro válido!");
            } catch (ExceptionGen e) {                                               // Erros personalizados
                System.out.println(e.getMessage());
            }
        }while (true) ;
    }

    // Método para pedir a taxa de juros

    public double interfaceTaxaJuros(){
        double retornoTaxaJuros; // Iniciando a variável de retorno

        do {
            try {
                // Iniciando Scanner
                Scanner teclado = new Scanner(System.in);

                // Output
                System.out.println("Digite a taxa de juros anual: ");

                // Input
                retornoTaxaJuros = teclado.nextDouble();

                // Validações

                ExceptionGen.validarValorMuitoAltoJuros(retornoTaxaJuros,MAX_TAXA_JUROS);
                ExceptionGen.validarZero(retornoTaxaJuros);
                ExceptionGen.validarNegativo(retornoTaxaJuros);
                ExceptionGen.validarCasasDecimais(retornoTaxaJuros);

                // Retorno
                return retornoTaxaJuros;

            } catch (InputMismatchException e){                                             // Erro de entrada sem ser número
                System.out.println("Entrada inválida! Digite um número válido!");
            } catch (ExceptionGen e){                                                       // Erros personalizados
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    // Método para coletar a área construída do usuário
    public int interfaceAreaConstruida() {
        int retornoAreaConstruida; // variavel de retorno

        do {
            try{
                // Scanner
                Scanner teclado = new Scanner(System.in);

                // Output
                System.out.println("Digite a área construída (Em m²): ");

                // Input
                retornoAreaConstruida = teclado.nextInt();

                // Validações

                ExceptionGen.validarNegativo(retornoAreaConstruida);
                ExceptionGen.validarZero(retornoAreaConstruida);
                ExceptionGen.validarExcessoAreas(retornoAreaConstruida);

                // Retorno
                return retornoAreaConstruida;
            }catch (InputMismatchException e){                                            // Erro de entrada sem ser número
                System.out.println("Entrada inválida! Digite um número inteiro válido!");
            }catch (ExceptionGen e){                                                      // Erros personalizados
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    // Método para coletar a área total do usuário
    public int interfaceAreaTotal() {
        int retornoAreaTotal; // variavel de retorno

        do {
            try{
                // Scanner
                Scanner teclado = new Scanner(System.in);

                // Output
                System.out.println("Digite a área total do terreno (em m²): ");

                // Input
                retornoAreaTotal = teclado.nextInt();

                // Validações
                ExceptionGen.validarNegativo(retornoAreaTotal);
                ExceptionGen.validarZero(retornoAreaTotal);
                ExceptionGen.validarExcessoAreas(retornoAreaTotal);

                // Retorno
                return retornoAreaTotal;
            }catch (InputMismatchException e){                                                 // Erro de entrada sem ser número
                System.out.println("Entrada inválida! Digite um número inteiro válido!");
            }catch (ExceptionGen e){                                                           // Erros personalizados
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    // Método para coletar o desconto do usuário
    public double interfaceDesconto() {
        double retornoDesconto;

        do{
            try{
                // Scanner
                Scanner teclado = new Scanner(System.in);

                // OUTPUT
                System.out.println("Digite o valor de desconto (Deverá ser menos que juros mensal e de 100 Reais)");

                // INPUT
                retornoDesconto = teclado.nextDouble();

                // Validações

                ExceptionGen.validarNegativo(retornoDesconto);
                ExceptionGen.validarZero(retornoDesconto);
                ExceptionGen.validarValorMuitoAltoDinheiro(retornoDesconto,MAX_VALOR_DESCONTO);
                ExceptionGen.validarCasasDecimais(retornoDesconto);

                // Retorno
                return retornoDesconto;
            } catch (InputMismatchException e){                                            // Erro de entrada sem ser número
                System.out.println("Entrada inválida! Digite um valor válido!");
            } catch (ExceptionGen e){                                                     // Erros personalizados
                System.out.println(e.getMessage());
            }
        } while (true);
    }


    // Método unificado para Andar e Garagem
    public int andarGaragem(String escolha){             // escolha andar ou garagem

        int retornoEscolha;

        do{
            try{
                // Scanner
                Scanner teclado = new Scanner(System.in);

                // OUTPUT
                System.out.println("Qual é o número de: [" + escolha + "] do imóvel ?");

                // INPUT
                retornoEscolha = teclado.nextInt();

                // Validações

                ExceptionGen.validarNegativo(retornoEscolha);
                ExceptionGen.validarZero(retornoEscolha);
                ExceptionGen.excessoGaragemAndar(retornoEscolha);

                // Retorno
                return retornoEscolha;

            } catch (InputMismatchException e) {                                       // Erro de entrada sem ser número
                System.out.println("Entrada inválida! Digite um valor válido!");
            } catch (ExceptionGen e) {                                                 // Erros personalizados
                System.out.println(e.getMessage());
            }
        }while (true);

    }


    // Método Pegar a variável zona
    public String zona(){
        int retornozona;

        do{
            try{
                // Scanner
                Scanner teclado = new Scanner(System.in);

                // OUTPUT
                System.out.println("Seleciona em qual zona o imóvel é localizado:");
                System.out.println("(1) Residencial (2) Comercial (3) Rural");

                // INPUT
                retornozona = teclado.nextInt();

                // Validações
                ExceptionGen.validarMenu(retornozona);

                // Retorno
                switch (retornozona) {
                    case 1 -> {
                        return "Residencial";
                    }
                    case 2 -> {
                        return "Comercial";
                    }
                    case 3 -> {
                        return "Rural";
                    }
                }

            } catch (InputMismatchException e){                                                // Erro de entrada sem ser número
                System.out.println("Entrada inválida! Digite um valor válido!");
            } catch (ExceptionGen e) {                                                         // Erros personalizados
                System.out.println(e.getMessage());
            }
        }while (true);
    }
}


