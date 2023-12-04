// Tomei a liberdade de criar uma classe para exceções personalizadas

package packInterface;
import java.text.NumberFormat; // Sintaxe melhor que DecimalFormat
import java.util.Locale;

public class ExceptionGen extends RuntimeException {

    // Construtor
    public ExceptionGen(String mensagem) {
        super(mensagem);
    }

    // Exception para validar número negativo (Genérico)
    public static <T extends Number> void validarNegativo(T numero) {
        if (numero.doubleValue() < 0) {
            throw new ExceptionGen("O número não pode ser negativo... Tente novamente!");
        }
    }

    // Exception para validar 2 casas decimais
    public static void validarCasasDecimais(double numero) {
        double numeroArredondado = Math.round(numero * Math.pow(10, 2)) / Math.pow(10, 2);
        if (numero != numeroArredondado) {
            throw new ExceptionGen("O número não pode ter mais de " + 2 + " casas decimais.");
        }
    }

    // Exception para zero(Genérico)
    public static <Y extends Number> void validarZero(Y numero) {
        if (numero.doubleValue() == 0) {
            throw new ExceptionGen("O número não pode ser zero... Tente novamente!");
        }
    }

    // Exception para o Menu
    public static void validarMenu(int escolha){
        if (escolha < 1 || escolha > 3) {
            throw new ExceptionGen("Valor inválido! Digite apenas números de 1 a 3");
        }
    }

    // Exception para um input muito alto (Dinheiro)
    public static void validarValorMuitoAltoDinheiro(double valor, double limite) {
        if (valor > limite) {
            NumberFormat useReal = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
            String limiteFormatado = useReal.format(limite);
            throw new ExceptionGen("Valor muito alto! O limite é " + limiteFormatado);
        }
    }

    // Exception para um input muito alto (Anos)
    public static void validarValorMuitoAltoAnos(int valor, int limite){
        if (valor > limite) {
            throw new ExceptionGen("Valor muito alto! O limite é : " + limite + " anos");
        }
    }

    // Exception para um input muito alto (Juros)

    public static void validarValorMuitoAltoJuros(double valor, double limite){
        if (valor > limite) {
            throw new ExceptionGen("Valor muito alto! O limite é : " + limite + " %");
        }
    }

    // Exception para um input muito baixo (Dinheiro)
    public static void validarValorMuitoBaixo(double valor, double limite) {
        if (valor < limite) {
            NumberFormat useReal = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
            String limiteFormatado = useReal.format(limite);
            throw new ExceptionGen("Valor muito baixo! O valor deve ser maior que " + limiteFormatado);
        }
    }

    // Exception para caso area Construida > area Total
    public static void validadeAreasCT(int contruida, int total){
        if (contruida > total) {
            throw new ExceptionGen("A área total não pode ser menor que a área contruida. Tente novamente ...");
        }
    }

    // Exception Atividade Formativa Semana 7

    public static void DescontoMaiorDoQueJurosException(double desconto, double jurosMensal) {
        if (desconto > jurosMensal){
            throw new ExceptionGen("ERRO: O valor do desconto é maior que o de juros mensal.");
        }
    }

    // Exception para muitos andares / garagens

    public static void excessoGaragemAndar (int andarGaragem){
        if (andarGaragem > 15){
            throw new ExceptionGen("O Número de andares e/ou garagens não pode ultrapassar de 15. Tente novamente...");
        }
    }

    // Exception unificada para um valor muito alto da area (Construída || TOTAL)
    public static void validarExcessoAreas (int area){
        if (area > 2000){
            throw new ExceptionGen("Desculpe, este programa está configurado para aceitar valores de área de até 2000 m². Tente novamente...");
        }
    }

}


