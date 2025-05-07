package br.com.alura.conversordemoedas.modelos;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConversorDeMoedas {


    private static final String API_KEY = "fa0a9bd0d60cae3b012849137024ee30c75e55acc1d7abadca02826911257939";
    private static final String BASE_URL = "https://economia.awesomeapi.com.br/json/last/";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            exibirCabecalho();

            System.out.println("Seja Bem-Vindo(a) ao Conversor De Moedas\n");
            System.out.println("1) Dólar => Real");
            System.out.println("2) Euro => Dólar");
            System.out.println("3) Libra Esterlina => Euro");
            System.out.println("4) Iene => Dólar");
            System.out.println("5) Franco Suiço => Euro");
            System.out.println("6) Dólar Canadense => Real");
            System.out.println("7) Dólar Autraliano => Dólar");
            System.out.println("8) Dólar Neozelandês => Euro");
            System.out.println("9) Renminbi => Dólar");
            System.out.println("10) Rublo Russo => Euro");
            System.out.println("11) Rupia Indiana => Dólar");
            System.out.println("12) Real => Peso Argentino");
            System.out.println("13) Peso Mexicano => Dólar");
            System.out.println("14) Peso Chileno => Euro");
            System.out.println("15) Peso Colombiano => Dólar");
            System.out.println("16) Peso Uruguaio => Real");
            System.out.println("17) Dólar De Cingapura => Dólar");
            System.out.println("18) Dólar de Hong Kong => Euro");
            System.out.println("19) Coroa Dinamarquesa => Dólar");
            System.out.println("20) Coroa Norueguesa => Euro");
            System.out.println("21) Coroa Sueca => Dólar");
            System.out.println("22) Lira Turca => Euro");
            System.out.println("23) Dirham Dos Emirados Árabes Unidos => Dólar");
            System.out.println("24) Dinar Do Kuwait => Euro");
            System.out.println("25) Riyal Saudita => Dólar");
            System.out.println("26) Rand Da África Do Sul => Euro");
            System.out.println("27) Shekel Israelense => Dólar");
            System.out.println("28) Baht Tailandês => Euro");
            System.out.println("29) Rúpia Do Paquistão => Dólar");
            System.out.println("30) Ringgit Malaio => Euro");
            System.out.println("31) Real => Dólar");
            System.out.println("32) Real => Euro");
            System.out.println("33) Dólar => Euro");
            System.out.println("34) Sair");
            System.out.println("**********************************************");
            System.out.print("Escolha uma opção válida: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Por favor, insira um número entre 1 e 34.\n");
                scanner.next();
                continue;
            }

            opcao = scanner.nextInt();

            if (opcao == 34) {
                System.out.println("Programa finalizado.");
                break;
            }

            if (opcao < 1 || opcao > 34) {
                System.out.println("Opção inválida. Por favor, escolha entre 1 e 34.\n");
                continue;
            }

            System.out.print("Digite o valor que deseja converter: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Valor inválido. Por favor, insira um número decimal.\n");
                scanner.next();
                continue;
            }

            double valor = scanner.nextDouble();

            switch (opcao) {
                case 1 -> converterMoeda("USD-BRL", valor);
                case 2 -> converterMoeda("EUR-USD", valor);
                case 3 -> converterMoeda("GBP-EUR", valor);
                case 4 -> converterMoeda("JPY-USD", valor);
                case 5 -> converterMoeda("CHF-EUR", valor);
                case 6 -> converterMoeda("CAD-BRL", valor);
                case 7 -> converterMoeda("AUD-USD", valor);
                case 8 -> converterMoeda("NZD-EUR", valor);
                case 9 -> converterMoeda("CNY-USD", valor);
                case 10 -> converterMoeda("RUB-EUR", valor);
                case 11 -> converterMoeda("INR-USD", valor);
                case 12 -> converterMoeda("BRL-ARS", valor);
                case 13 -> converterMoeda("MXN-USD", valor);
                case 14 -> converterMoeda("CLP-EUR", valor);
                case 15 -> converterMoeda("COP-USD", valor);
                case 16 -> converterMoeda("UYU-BRL", valor);
                case 17 -> converterMoeda("SGD-USD", valor);
                case 18 -> converterMoeda("HKD-EUR", valor);
                case 19 -> converterMoeda("DKK-USD", valor);
                case 20 -> converterMoeda("NOK-EUR", valor);
                case 21 -> converterMoeda("SEK-USD", valor);
                case 22 -> converterMoeda("TRY-EUR", valor);
                case 23 -> converterMoeda("AED-USD", valor);
                case 24 -> converterMoeda("KWD-EUR", valor);
                case 25 -> converterMoeda("SAR-USD", valor);
                case 26 -> converterMoeda("ZAR-EUR", valor);
                case 27 -> converterMoeda("ILS-USD", valor);
                case 28 -> converterMoeda("THB-EUR", valor);
                case 29 -> converterMoeda("PKR-USD", valor);
                case 30 -> converterMoeda("MYR-EUR", valor);
                case 31 -> converterMoeda("BRL-USD", valor);
                case 32 -> converterMoeda("BRL-EUR", valor);
                case 33 -> converterMoeda("USD-EUR", valor);
                default -> System.out.println("Opção inválida, tente novamente.");
            }

            System.out.println();
        }
    }

    public static void converterMoeda(String moedas, double valor) {
        try {
            String urlStr = BASE_URL + moedas + "?token=" + API_KEY;

            URL url = new URL(urlStr);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            Scanner respostaScanner = new Scanner(conexao.getInputStream());
            StringBuilder jsonBuilder = new StringBuilder();

            while (respostaScanner.hasNext()) {
                jsonBuilder.append(respostaScanner.nextLine());
            }

            respostaScanner.close();

            JsonObject json = JsonParser.parseString(jsonBuilder.toString()).getAsJsonObject();
            JsonObject cotacao = json.getAsJsonObject(moedas.replace("-", ""));

            double cotacaoMoeda = cotacao.get("bid").getAsDouble();
            double resultado = valor * cotacaoMoeda;

            System.out.printf(">> %.2f %s = %.2f %s%n", valor, moedas.split("-")[0], resultado, moedas.split("-")[1]);
        } catch (Exception e) {
            System.out.println("Erro ao realizar conversão: " + e.getMessage());
        }
    }

    private static void exibirCabecalho() {
        System.out.println("**********************************************");
        System.out.println("*    Meu Projeto Conversor De Moedas BR     *");
        System.out.println("**********************************************");
    }
}
