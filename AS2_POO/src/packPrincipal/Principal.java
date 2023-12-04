package packPrincipal;

import packCalculadora.Apartamento;
import packCalculadora.Calculadora;
import packCalculadora.Casa;
import packCalculadora.Terreno;
import packInterface.ExceptionGen;
import packInterface.Interface;

import java.util.ArrayList;




public class Principal {
    public static void main(String[] args) {


        // Bloco pré SWITCH

        Interface useInterface = new Interface();       // Instanciando packInterface.Interface
        useInterface.introducao();                      // Introdução
        int escolha = useInterface.casaApTerreno();     // Variável de escolha do switch


        // Bloco switch


        switch (escolha) {
            case 1 -> {                                                                               // Financiamento CASA


                // Bloco para pedir informações ao usuário

                System.out.println("Selecionado: Casa.");                                         // SOUT mostrando a opção selecionada
                double executeValorImovelCasa = useInterface.interfaceValorImovel();              // Executando Método da interface (ValorImovel)
                int executePrazoFinanciamentoCasa = useInterface.interfacePrazoFinanciamento();   // Executando Método da interface (PrazoFinanciamento)
                double executeTaxaJurosCasa = useInterface.interfaceTaxaJuros();                  // Executando Método da interface (TaxaJuros)
                double executeDesconto = useInterface.interfaceDesconto();                        // Executando Método da interface (Desconto)
                int executeAreaTotal = useInterface.interfaceAreaTotal();                         // Executando Método da interface (areaTotal)
                int executeAreaConntruida = useInterface.interfaceAreaConstruida();               // Executando Método da interface (areaConstruida)


                // Bloco de validação area total > area construída

                do {
                    try {
                        // Validação
                        ExceptionGen.validadeAreasCT(executeAreaConntruida, executeAreaTotal);
                    } catch (ExceptionGen e) {
                        System.out.println(e.getMessage());
                        executeAreaConntruida = useInterface.interfaceAreaConstruida();
                    }
                } while (executeAreaConntruida > executeAreaTotal);


                // Criando um objeto

                Casa useCasa = new Casa(executeValorImovelCasa, executeTaxaJurosCasa, executePrazoFinanciamentoCasa, executeAreaConntruida, executeAreaTotal, executeDesconto);


                // Bloco para salvar/ler TXT

                useCasa.salvarDados("dados_financiamento.txt"); // Salvar em txt
                Casa.lerArquivo("dados_financiamento.txt");     // Ler em txt


                // Bloco de Serialização

                ArrayList<Calculadora> listaCasa = new ArrayList<>(); // Criação do arraylist para serialização
                useCasa.aquecimento(listaCasa);                       // Carregando serializações antigas em listaCasa
                listaCasa.add(useCasa);                               // Adicionando atual serialização a lista
                useCasa.serializar(listaCasa);                        // Salvando o novo array
                useCasa.deserializar();                               // Deserializando e imprimindo
            }
            case 2 -> {                                                                                        // Financiamento Apartamento


                // Bloco para pedir informações ao usuário

                System.out.println("Selecionado: Apartamento.");                                           // SOUT mostrando a opção selecionada
                double executeValorImovelApartamento = useInterface.interfaceValorImovel();                // Executando Método da interface (ValorImovel)
                int executePrazoFinanciamentoApartamento = useInterface.interfacePrazoFinanciamento();     // Executando Método da interface (PrazoFinanciamento)
                double executeTaxaJurosApartamento = useInterface.interfaceTaxaJuros();                    // Executando Método da interface (TaxaJuros)
                int executeGaragem = useInterface.andarGaragem("Garagem(s)");                      // Pedindo o numero de Garagens
                int executeAndar = useInterface.andarGaragem("Andar(s)");                         // Pedindo o número do andar


                // Criando um objeto

                Apartamento useap = new Apartamento(executeValorImovelApartamento, executeTaxaJurosApartamento, executePrazoFinanciamentoApartamento,
                        executeGaragem, executeAndar);


                // Bloco para salvar/ler TXT

                useap.salvarDados("dados_financiamento.txt");
                Apartamento.lerArquivo("dados_financiamento.txt");


                // Bloco de Serialização

                ArrayList<Calculadora> listaAp = new ArrayList<>();   // Criação do arraylist para serialização
                useap.aquecimento(listaAp);                           // Carregando serializações antigas em listaCasa
                listaAp.add(useap);                                   // Adicionando atual serialização a lista
                useap.serializar(listaAp);                            // Salvando o novo array
                useap.deserializar();                                 // Deserializando e imprimindo
            }
            case 3 -> {                                                                                    // Financiamento Terreno


                // Bloco para pedir informações ao usuário

                System.out.println("Selecionado: Terreno");                                            // SOUT mostrando a opção selecionada
                double executeValorImovelTerreno = useInterface.interfaceValorImovel();                // Executando Método da interface (ValorImovel)
                int executePrazoFinanciamentoTerreno = useInterface.interfacePrazoFinanciamento();      // Executando Método da interface (PrazoFinanciamento)
                double executeTaxaJurosTerreno = useInterface.interfaceTaxaJuros();                    // Executando Método da interface (TaxaJuros)
                String executeZona = useInterface.zona();                                              // Executando zona


                // Criando um objeto

                Terreno useterreno = new Terreno(executeValorImovelTerreno, executeTaxaJurosTerreno, executePrazoFinanciamentoTerreno, executeZona);


                // Bloco para salvar e ler dados TXT

                useterreno.salvarDados("dados_financiamento.txt");
                Terreno.lerArquivo("dados_financiamento.txt");


                // Bloco de Serialização

                ArrayList<Calculadora> listaTerreno = new ArrayList<>();                          // Criação do arraylist para serialização
                useterreno.aquecimento(listaTerreno);                                             // Carregando serializações antigas em listaCasa
                listaTerreno.add(useterreno);                                                     // Adiciona atual Serialização a lista
                useterreno.serializar(listaTerreno);                                              // Serializando o novo array
                useterreno.deserializar();                                                        // Deserializando e imprimindo
            }
        }
    }}


