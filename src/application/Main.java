package application;

import entities.Fruta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Fruta> frutas = new ArrayList<>();
        List<Fruta> compras = new ArrayList<>();
        List<Integer> quantCompra = new ArrayList<>();

        frutas.add(new Fruta("Abacaxi", 2.5, 7));
        frutas.add(new Fruta("Goiaba", 1.25, 12));
        frutas.add(new Fruta("Uva", 2, 10));
        frutas.add(new Fruta("Manga", 1.5, 8));
        frutas.add(new Fruta("Banana", 0.5, 14));

        boolean go = true;
        int opc, index, quantidade, frutaEscolhida;
        String nome;
        double precoUnitario;
        double valorTotal = 0.0;
        char continuar, simOuNao;
        do {
            System.out.println("Bem vindo ao sistema de loja de frutas!");
            System.out.println("0. Fechar programa");
            System.out.println("1. Manipular sistema");
            System.out.println("2. Ir às compras");
            System.out.print("Resposta: ");
            opc = scanner.nextInt();
            scanner.nextLine();
            switch (opc) {
                case 0:
                    System.out.println("Fechando programa...");
                    go = false;
                    break;
                case 1:
                    do {
                        System.out.println("0. Voltar");
                        System.out.println("1. Adicionar fruta");
                        System.out.println("2. Editar fruta");
                        System.out.println("3. Remover fruta");
                        System.out.print("Resposta: ");
                        opc = scanner.nextInt();
                        scanner.nextLine();
                        switch (opc) {
                            case 0:
                                break;
                            case 1:
                                System.out.print("Nome da fruta: ");
                                nome = scanner.nextLine();
                                nome = capitalize(nome);
                                do {
                                    System.out.print("Preço unitário (R$): ");
                                    precoUnitario = scanner.nextDouble();
                                    if (precoUnitario < 0) {
                                        System.out.println("O preço não pode ser negativo!");
                                    }
                                } while (precoUnitario < 0);
                                do {
                                    System.out.print("Digite a quantidade de " + nome + ": ");
                                    quantidade = scanner.nextInt();
                                    if (quantidade < 0) {
                                        System.out.println("Digite uma quantidade válida!");
                                    }
                                } while (quantidade < 0);
                                frutas.add(new Fruta(nome, precoUnitario, quantidade));
                                System.out.println(frutas.getLast().getNome() + " criada com sucesso!");
                                break;
                            case 2:
                                if (frutas.isEmpty()) {
                                    System.out.println("A lista está vazia!");
                                    break;
                                }

                                do {
                                    listarFrutas(frutas);
                                    System.out.println("Digite o número da fruta");
                                    System.out.print("Resposta: ");
                                    index = scanner.nextInt();
                                    scanner.nextLine();
                                    if (index < 1 || index > frutas.size()) {
                                        System.out.println("Fruta não encontrada!");
                                    }
                                } while (index < 1 || index > frutas.size());
                                System.out.println("Editar atributos de " + frutas.get(index - 1).getNome());
                                System.out.println("0. Voltar");
                                System.out.println("1. Nome");
                                System.out.println("2. Preço Unitário");
                                System.out.println("3. Quantidade");
                                System.out.print("Resposta: ");
                                opc = scanner.nextInt();
                                scanner.nextLine();
                                switch (opc) {
                                    case 0:
                                        break;
                                    case 1:
                                        String nomeAntigo = frutas.get(index - 1).getNome();
                                        System.out.print("Novo nome de " + frutas.get(index - 1).getNome() + ": ");
                                        nome = scanner.nextLine();
                                        nome = capitalize(nome);
                                        frutas.get(index - 1).setNome(nome);
                                        System.out.println("Nome alterado de "
                                                + nomeAntigo +
                                                " para "
                                                + frutas.get(index - 1).getNome());
                                        break;
                                    case 2:
                                        double precoUnitarioAntigo = frutas.get(index - 1).getPrecoUnitario();
                                        do {
                                            System.out.println("Preço unitário antigo: R$"
                                                    + String.format("%.2f", frutas.get(index - 1).getPrecoUnitario()));
                                            System.out.print("Novo preço unitário (R$): ");
                                            precoUnitario = scanner.nextDouble();
                                            if (precoUnitario < 0) {
                                                System.out.println("O preço unitário não pode ser negativa!");
                                            }
                                        } while (precoUnitario < 0);
                                        frutas.get(index - 1).setPrecoUnitario(precoUnitario);
                                        System.out.println("Preço unitário alterado de R$"
                                                + String.format("%.2f", precoUnitarioAntigo) +
                                                " para R$"
                                                + String.format("%.2f", frutas.get(index - 1).getPrecoUnitario()));
                                        double porcentagemDiferenca = frutas.get(index - 1).calcularDiferencaPrecoPorcentagem(precoUnitarioAntigo);
                                        System.out.println("O preço " + ((frutas.get(index - 1).getPrecoUnitario() > precoUnitarioAntigo) ? "aumentou " : "diminuiu ") + String.format("%.2f", porcentagemDiferenca) + "%");
                                        break;
                                    case 3:
                                        int quantidadeAntiga = frutas.get(index - 1).getQuantidade();
                                        do {
                                            System.out.println("Quantidade de "
                                                    + frutas.get(index - 1).getNome() +
                                                    ": "
                                                    + frutas.get(index - 1).getQuantidade());
                                            System.out.print("Nova quantidade: ");
                                            quantidade = scanner.nextInt();
                                            scanner.nextLine();
                                            if (quantidade > 1000) {
                                                quantidade = 1000;
                                            }

                                            if (quantidade < 1) {
                                                System.out.println("Digite uma quantidade válida!");
                                            }
                                        } while (quantidade < 1);
                                        frutas.get(index - 1).setQuantidade(quantidade);
                                        System.out.println("Quantidade alterada de "
                                                + quantidadeAntiga +
                                                " para "
                                                + frutas.get(index - 1).getQuantidade());
                                        break;
                                    default:
                                        System.out.println("Digite uma opção válida!");
                                        break;
                                }
                                break;
                            case 3:
                                if (frutas.isEmpty()) {
                                    System.out.println("A lista está vazia!");
                                    break;
                                }

                                do {
                                    System.out.println("0. Voltar");
                                    listarFrutas(frutas);
                                    System.out.println("Qual o índice que deseja remover?");
                                    System.out.print("Resposta: ");
                                    index = scanner.nextInt();
                                    scanner.nextLine();
                                    if (index == 0) {
                                        break;
                                    }

                                    if (index < 1 || index > frutas.size()) {
                                        System.out.println("Fruta não encontrada!");
                                    }

                                } while (index < 1 || index > frutas.size());
                                System.out.println("Tem certeza que deseja remover " + frutas.get(index - 1).getNome() + "?");
                                System.out.print("Resposta [S/N]: ");
                                simOuNao = scanner.next().toUpperCase().charAt(0);
                                if (simOuNao == 'S') {
                                    System.out.println(frutas.get(index - 1).getNome() + " removida com sucesso!");
                                    frutas.remove(index - 1);
                                }
                                break;
                        }

                    } while (opc != 0);
                    break;
                case 2:
                    if (frutas.isEmpty()) {
                        System.out.println("A lista está vazia!");
                        break;
                    }

                    double valorCompra;
                    int quantidadeComprada;

                    while (true) {
                        exibirMenuCompras(frutas);
                        System.out.print("Resposta: ");

                        if (!scanner.hasNextInt()) {
                            System.out.println("Digite um número válido!");
                            scanner.next();
                            continue;
                        }

                        frutaEscolhida = scanner.nextInt();
                        scanner.nextLine();

                        if (frutaEscolhida == 0) {
                            break;
                        }

                        if (frutaEscolhida < 1 || frutaEscolhida > frutas.size()) {
                            System.out.println("Escolha entre 1 e " + frutas.size() + "!");
                            continue;
                        }

                        if (frutas.get(frutaEscolhida - 1).getQuantidade() == 0) {
                            System.out.println("Não tem estoque para " + frutas.get(frutaEscolhida - 1).getNome());
                            continue;
                        }

                        do {
                            System.out.print("Digite a quantidade: ");
                            quantidadeComprada = scanner.nextInt();
                            scanner.nextLine();
                            if (quantidadeComprada > frutas.get(frutaEscolhida - 1).getQuantidade()) {
                                quantidadeComprada = frutas.get(frutaEscolhida - 1).getQuantidade();
                            }
                        } while (quantidadeComprada > frutas.get(frutaEscolhida - 1).getQuantidade());

                        System.out.println("Pedido: ");
                        valorCompra = frutas.get(frutaEscolhida - 1).getTotal(quantidadeComprada);
                        System.out.println(quantidadeComprada + " " + frutas.get(frutaEscolhida - 1).getNome() +
                                ((quantidadeComprada == 1) ? "" : "s") +
                                " por R$" + String.format("%.2f", valorCompra));

                        frutas.get(frutaEscolhida - 1).setQuantidade(frutas.get(frutaEscolhida - 1).getQuantidade() - quantidadeComprada);

                        boolean frutaJaComprada = false;
                        for (int i = 0; i < compras.size(); i++) {
                            if (compras.get(i).getNome().equals(frutas.get(frutaEscolhida - 1).getNome())) {
                                quantCompra.set(i, quantCompra.get(i) + quantidadeComprada);
                                frutaJaComprada = true;
                                break;
                            }
                        }

                        if (!frutaJaComprada) {
                            compras.add(frutas.get(frutaEscolhida - 1));
                            quantCompra.add(quantidadeComprada);
                        }

                        System.out.print("Continuar comprando? [S/N]: ");
                        continuar = scanner.next().toUpperCase().charAt(0);

                        if (continuar != 'S') {
                            break;
                        }
                    }
                    if (compras.isEmpty()) {
                        System.out.println("Nenhuma compra foi realizada.");
                    } else {
                        exibirNF(compras, quantCompra, valorTotal);
                    }
                    break;
            }
        } while(go);
        scanner.close();
    }

    public static void listarFrutas(ArrayList<Fruta> frutas) {
        int contador = 1;
        for (Fruta f : frutas) {
            System.out.println(contador + ". " + f.getNome());
            contador++;
        }
    }

    public static void exibirNF(List<Fruta> compras, List<Integer> quantidadeComprada, double valorTotal) {
        System.out.println("+----+------------+------------+------+------------+");
        System.out.printf("| %-2s | %-10s | %-10s | %-4s | %-10s |\n", "ID", "Produto", "Preço Unit", "Qtd", "Total");
        System.out.println("+----+------------+------------+------+------------+");

        for (int i = 0; i < compras.size(); i++) {
            Fruta f = compras.get(i);
            double totalItem = f.getTotal(quantidadeComprada.get(i));
            valorTotal += totalItem;
            System.out.printf("| %-2d | %-10s | R$ %-7.2f | %-4d | R$ %-7.2f |\n",
                    (i + 1),
                    f.getNome(),
                    f.getPrecoUnitario(),
                    quantidadeComprada.get(i),
                    totalItem);
        }

        System.out.println("+----+------------+------------+------+------------+");
        System.out.printf("| %-35s | R$ %-7.2f |\n", "TOTAL GERAL", valorTotal);
        System.out.println("+-------------------------------------+------------+");

        salvarNFArquivo(compras, quantidadeComprada, valorTotal);
    }

    public static void salvarNFArquivo(List<Fruta> compras, List<Integer> quantidadeComprada, double valorTotal) {
        String nomeArquivo = "nf_frutas.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("+----+------------+------------+------+------------+\n");
            writer.write(String.format("| %-2s | %-10s | %-10s | %-4s | %-10s |\n",
                    "ID",
                    "Produto",
                    "Preço Unit",
                    "Qtd",
                    "Total"));

            writer.write("+----+------------+------------+------+------------+\n");

            for (int i = 0; i < compras.size(); i++) {
                Fruta f = compras.get(i);
                double totalItem = f.getTotal(quantidadeComprada.get(i));
                writer.write(String.format("| %-2d | %-10s | R$ %-7.2f | %-4d | R$ %-7.2f |\n",
                        (i + 1), f.getNome(), f.getPrecoUnitario(), quantidadeComprada.get(i), totalItem));
            }

            writer.write("+----+------------+------------+------+------------+\n");
            writer.write(String.format("| %-35s | R$ %-7.2f |\n", "TOTAL GERAL", valorTotal));
            writer.write("+-------------------------------------+------------+\n");

            System.out.println("Nota fiscal salva em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public static void exibirMenuCompras(ArrayList<Fruta> frutas) {
        System.out.println("0. Voltar");
        System.out.println("+----+---------+------------+------+");
        System.out.printf("| %-2s | %-7s | %-10s | %-4s |\n", "ID", "Nome", "Preço Unit", "Qtd");
        System.out.println("+----+---------+------------+------+");
        for (int i = 0; i < frutas.size(); i++) {
            Fruta fruta = frutas.get(i);
            System.out.printf("| %-2d | %-7s | R$ %-7.2f | %-4d |\n",
                    (i + 1),
                    fruta.getNome(),
                    fruta.getPrecoUnitario(),
                    fruta.getQuantidade());
        }
        System.out.println("+----+---------+------------+------+");
    }

    public static String capitalize(String nome) {
        return nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
    }
}