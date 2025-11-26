package aplicacao;

import entidades.*;
import gui.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ACMETech {
    // Cria as estruturas de dados - Árvores para serem ordenadas crescentemente e com código diferente
    private TreeSet<Fornecedor> fornecedores = new TreeSet<>();
    private TreeSet<Tecnologia> tecnologias = new TreeSet<>();
    private TreeSet<Comprador> compradores = new TreeSet<>();
    private TreeSet<Venda> vendas = new TreeSet<>();

    public ACMETech() {
        inicializar();
    }

    public Set<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public Set<Comprador> getCompradores() {
        return compradores;
    }

    public Set<Tecnologia> getTecnologias() {
        return tecnologias;
    }

    public Set<Venda> getVendas() {
        return vendas;
    }

    public void inicializar() {
        lerArquivoParticipantes("PARTICIPANTESENTRADA.CSV");
        lerArquivoTecnologias("TECNOLOGIASENTRADA.CSV");
        lerArquivoVendas("VENDASENTRADA.CSV");
    }


    private void lerArquivoParticipantes(String arquivo) {
        Path path = Paths.get("resources", arquivo);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            reader.readLine();
            String linha;
            while ((linha = reader.readLine()) != null) {
                Scanner sc = new Scanner(linha).useDelimiter(";");
                long cod = sc.nextLong();
                String nome = sc.next();
                int tipoParticipante = sc.nextInt();
                if (tipoParticipante == 1) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date fundacao = sdf.parse(sc.next());
                    Area area = Area.valueOf(sc.next());
                    cadastrarFornecedor(cod, nome, fundacao, area);
                }
                if (tipoParticipante == 2) {
                    String pais = sc.next();
                    String email = sc.next();
                    cadastrarComprador(cod, nome, pais, email);
                }
            }
        } catch (IOException e) {
            System.err.format("Erro ao ler arquivo %s: %s%n", arquivo, e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void lerArquivoTecnologias(String arquivo) {
        Path path = Paths.get("resources", arquivo);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            reader.readLine();
            String linha;
            while ((linha = reader.readLine()) != null) {
                Scanner sc = new Scanner(linha).useDelimiter(";");
                long id = sc.nextLong();
                String modelo = sc.next().trim();
                String descricao = sc.next().trim();
                double valorBase = Double.parseDouble(sc.next().trim());
                double peso = Double.parseDouble(sc.next().trim());
                double temperatura = Double.parseDouble(sc.next().trim());
                long cod = Long.parseLong(sc.next().trim());
                cadastrarTecnologia(id, modelo, descricao, valorBase, peso, temperatura, cod);
            }
        } catch (IOException e) {
            System.err.format("Erro ao ler arquivo %s: %s%n", arquivo, e);
        }
    }

    private void lerArquivoVendas(String arquivo) {
        Path path = Paths.get("resources", arquivo);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            reader.readLine();
            String linha;
            while ((linha = reader.readLine()) != null) {
                Scanner sc = new Scanner(linha).useDelimiter(";");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                long num = sc.nextLong();
                Date data = sdf.parse(sc.next());
                long cod = sc.nextLong();
                long id = sc.nextLong();
                cadastrarVenda(num, data, cod, id);
            }
        } catch (IOException e) {
            System.err.format("Erro ao ler arquivo %s: %s%n", arquivo, e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean cadastrarFornecedor(long codigo, String nome, Date fundacao, Area area) {
        Fornecedor novoFornecedor = new Fornecedor(codigo, nome, fundacao, area);
        return fornecedores.add(novoFornecedor);
    }

    public boolean cadastrarComprador(long cod, String nome, String pais, String email) {
        Comprador novoComprador = new Comprador(cod, nome, pais, email);
        return compradores.add(novoComprador);
    }

    public boolean cadastrarTecnologia(long id, String modelo, String descricao, double valorBase, double peso, double temperatura, long cod) {
        Fornecedor fornecedor = null;
        for (Fornecedor f : fornecedores) {
            if (f.getCod() == cod) {
                fornecedor = f;
            }
        }
        Tecnologia novaTecnologia = new Tecnologia(id, modelo, descricao, valorBase, peso, temperatura, fornecedor);
        return tecnologias.add(novaTecnologia);
    }

    public boolean cadastrarTecnologia(Tecnologia novaTecnologia) {
        return tecnologias.add(novaTecnologia);
    }

    public boolean cadastrarVenda(long num, Date data, long cod, long id) {
        Tecnologia tecnologia = tecnologias.stream().filter(t -> t.getId() == id).findFirst().orElseThrow();
        Comprador comprador = compradores.stream().filter(c -> c.getCod() == cod).findFirst().orElseThrow();
        Venda v = new Venda(num, data, tecnologia, comprador, calculaQuantidadeVendas(cod));
        return vendas.add(v);
    }

    public long calculaQuantidadeVendas(long cod) {
        return vendas.stream().filter(v -> v.getComprador().getCod() == cod).count();
    }

    public String gerarRelatorio(Relatorio relatorio) {
        StringBuilder stringRelatorio = new StringBuilder();
        switch (relatorio) {
            case FORNECEDOR -> {
                stringRelatorio.append("Relatório de Fornecedores:\n");
                stringRelatorio.append("Cod || Nome || Fundação || Area\n");
                for (Fornecedor f : fornecedores) {
                    stringRelatorio.append(f.geraDescricao());
                    stringRelatorio.append("\n");
                }
            }
            case COMPRADOR -> {
                stringRelatorio.append("Relatório de Compradores:\n");
                stringRelatorio.append("Cod || Nome || País || Email\n");
                for (Comprador c : compradores) {
                    stringRelatorio.append(c.geraDescricao());
                    stringRelatorio.append("\n");
                }
            }
            case VENDA -> {
                stringRelatorio.append("Relatório de Vendas:\n");
                stringRelatorio.append(String.format(
                        "%s||%s||%s||%s||%s||%s||%s||%s||%s||%s||%s||%s||%s||%s",
                        " Num ",
                        " Data ",
                        " Modelo Tecnologia ",
                        " Descrição ",
                        " Valor Base ",
                        " Peso ",
                        " Temperatura ",
                        " Fornecedor ",
                        " Comprador Cod ",
                        " Nome ",
                        " País ",
                        " Email ",
                        " Valor Final ",
                        " Quantidade de Vendas\n"));
                for (Venda v : vendas) {
                    stringRelatorio.append(v.geraDescricao());
                    stringRelatorio.append("\n");
                }
            }
            case TECNOLOGIA -> {
                stringRelatorio.append("Relatório de Tecnologia:\n");
                stringRelatorio.append(" Modelo || Descrição || Valor Base || Peso || Temperatura || Fornecedor \n");
                for (Tecnologia t : tecnologias) {
                    stringRelatorio.append(t.geraDescricao());
                    stringRelatorio.append("\n");
                }
            }
        }
        return stringRelatorio.toString();
    }

    public void executar() {
        new TelaInicialGUI(this).setVisible(true);
    }


    private void carregarFornecedores(String json) throws Exception {
        String bloco = json.split("\"fornecedores\"")[1].split("]")[0];
        String[] objetos = bloco.split("\\{");

        for (String obj : objetos) {
            if (!obj.contains("\"cod\"")) continue;


            long codigo = Long.parseLong(obj.split("\"cod\":")[1].split(",")[0].trim());
            String nome = obj.split("\"nome\":")[1].split("\"")[1];
            String dataStr = obj.split("\"fundacao\":")[1].split("\"")[1];
            String areaStr = obj.split("\"area\":")[1].split("\"")[1];

            Date data = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
            Area area = Area.valueOf(areaStr);

            fornecedores.add(new Fornecedor(codigo, nome, data, area));
        }
    }

    private void carregarTecnologias(String json) throws Exception {
        String bloco = json.split("\"tecnologias\"")[1].split("]")[0];
        String[] objetos = bloco.split("\\{");

        for (String obj : objetos) {
            if (!obj.contains("\"id\"")) continue;

            long id = Long.parseLong(obj.split("\"id\":")[1].split(",")[0].trim());
            String modelo = obj.split("\"modelo\":")[1].split("\"")[1];
            String descricao = obj.split("\"descricao\":")[1].split("\"")[1];
            double valorBase = Double.parseDouble(obj.split("\"valorBase\":")[1].split(",|}")[0].trim());
            double peso = Double.parseDouble(obj.split("\"peso\":")[1].split(",|}")[0].trim());
            double temperatura = Double.parseDouble(obj.split("\"temperatura\":")[1].split(",|}")[0].trim());
            long fornecedorCod = Long.parseLong(obj.split("\"fornecedor\":")[1].split(",|}")[0].trim());

            Fornecedor fornecedor = fornecedores.stream().filter(f -> f.getCod() == fornecedorCod).findFirst().orElseThrow();

            tecnologias.add(new Tecnologia(id, modelo, descricao, valorBase, peso, temperatura, fornecedor));
        }
    }

    private void carregarCompradores(String json) throws Exception {
        String bloco = json.split("\"compradores\"")[1].split("]")[0];
        String[] objetos = bloco.split("\\{");

        for (String obj : objetos) {
            if (!obj.contains("\"cod\"")) continue;

            long cod = Long.parseLong(obj.split("\"cod\":")[1].split(",")[0].trim());
            String nome = obj.split("\"nome\":")[1].split("\"")[1];
            String pais = obj.split("\"pais\":")[1].split("\"")[1];
            String email = obj.split("\"email\":")[1].split("\"")[1];

            compradores.add(new Comprador(cod, nome, pais, email));
        }
    }

    private void carregarVendas(String json) throws Exception {
        String bloco = json.split("\"vendas\"")[1].split("]")[0];
        String[] objetos = bloco.split("\\{");

        for (String obj : objetos) {
            if (!obj.contains("\"num\"")) continue;

            long cod = Long.parseLong(obj.split("\"num\":")[1].split(",")[0].trim());
            String dataStr = obj.split("\"data\":")[1].split("\"")[1];
            long tecnologia = Long.parseLong(obj.split("\"tecnologia\":")[1].split(",|}")[0].trim());
            long comprador = Long.parseLong(obj.split("\"comprador\":")[1].split(",|}")[0].trim());

            Date data = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dataStr);

            Tecnologia tecnologiaObj = tecnologias.stream().filter(t -> t.getId() == tecnologia).findFirst().orElseThrow();
            Comprador compradorObj = compradores.stream().filter(c -> c.getCod() == comprador).findFirst().orElseThrow();
            vendas.add(new Venda(cod, data, tecnologiaObj, compradorObj, calculaQuantidadeVendas(comprador)));
        }
    }

    public boolean salvarJSON(String nomeArquivo) {
        try {
            Path path = Paths.get("resources");
            Path arquivo = path.resolve(nomeArquivo + ".json");
            PrintWriter out = new PrintWriter(arquivo.toFile());

            out.println("{");

            out.println("\"fornecedores\": [");
            int i = 0;
            for (Fornecedor f : fornecedores) {
                out.println("  {");
                out.println("   \"cod\": " + f.getCod() + ",");
                out.println("   \"nome\": \"" + f.getNome() + "\",");
                out.println("   \"fundacao\": \"" + new java.text.SimpleDateFormat("dd/MM/yyyy").format(f.getFundacao()) + "\",");
                out.println("   \"area\": \"" + f.getArea().name() + "\"");
                out.print("  }");
                if (++i < fornecedores.size()) out.println(",");
                else out.println();
            }
            out.println("],");

            out.println("\"tecnologias\": [");
            i = 0;
            for (Tecnologia t : tecnologias) {
                out.println("  {");
                out.println("   \"id\": " + t.getId() + ",");
                out.println("   \"modelo\": \"" + t.getModelo() + "\",");
                out.println("   \"descricao\": \"" + t.getDescricao() + "\",");
                out.println("   \"valorBase\": " + t.getValorBase() + ",");
                out.println("   \"peso\": " + t.getPeso() + ",");
                out.println("   \"temperatura\": " + t.getTemperatura() + ",");
                out.println("   \"fornecedor\": " + t.getFornecedor().getCod());
                out.print("  }");
                if (++i < tecnologias.size()) out.println(",");
                else out.println();
            }
            out.println("],");

            out.println("\"compradores\": [");
            i = 0;
            for (Comprador c : compradores) {
                out.println("  {");
                out.println("   \"cod\": " + c.getCod() + ",");
                out.println("   \"nome\": \"" + c.getNome() + "\",");
                out.println("   \"pais\": \"" + c.getPais() + "\",");
                out.println("   \"email\": \"" + c.getEmail() + "\"");
                out.print("  }");
                if (++i < compradores.size()) {
                    out.println(",");
                } else {
                    out.println();
                }
            }
            out.println("],");

            out.println("\"vendas\": [");
            i = 0;
            for (Venda v : vendas) {
                out.println("  {");
                out.println("   \"num\": " + v.getNum() + ",");
                out.println("   \"data\": \"" + new java.text.SimpleDateFormat("dd/MM/yyyy").format(v.getData()) + "\",");
                out.println("   \"valorFinal\": " + v.getValorFinal() + ",");
                out.println("   \"tecnologia\": " + v.getTecnologia().getId() + ",");
                out.println("   \"comprador\": " + v.getComprador().getCod() + ",");
                out.println("   \"quantidadeVendas\": " + calculaQuantidadeVendas(v.getComprador().getCod()));
                out.print("  }");
                if (++i < vendas.size()) out.println(",");
                else out.println();
            }
            out.println("]");

            out.println("}");

            out.close();
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar JSON: " + e.getMessage());
            return false;
        }
    }

    public boolean carregarJSON(String nomeArquivo) {
        try {
            String conteudo = new String(java.nio.file.Files.readAllBytes(
                    java.nio.file.Paths.get("resources/" + nomeArquivo + ".json")
            ));

            fornecedores.clear();
            tecnologias.clear();
            compradores.clear();
            vendas.clear();

            carregarFornecedores(conteudo);
            carregarTecnologias(conteudo);
            carregarCompradores(conteudo);
            carregarVendas(conteudo);

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao carregar JSON: " + e.getMessage());
            return false;
        }
    }

    public boolean removerVenda(int numeroVenda) {
        return vendas.removeIf(v -> v.getNum() == numeroVenda);
    }

    public Comprador buscarComprador(int codigo) {
        return compradores.stream().filter(c -> c.getCod() == codigo).findFirst().orElse(null);
    }

    public boolean alterarComprador(long cod, String nome, String pais, String email) {
        Comprador comprador = compradores.stream().filter(c -> c.getCod() == cod).findFirst().orElse(null);
        try {
            comprador.setNome(nome);
            comprador.setPais(pais);
            comprador.setEmail(email);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String consultarTecnologiaMaiorValor() {
        if (tecnologias.isEmpty()) {
            return "Nenhuma tecnologia cadastrada.";
        }
        double maiorValor = tecnologias.stream().mapToDouble(Tecnologia::getValorBase).max().orElse(0);
        List<Tecnologia> tecnologiasMaiorValor = tecnologias.stream().filter(t -> t.getValorBase() == maiorValor).toList();
        StringBuilder sb = new StringBuilder();
        sb.append("Tecnologia(s) com maior(es) valor(es) base:\n");
        tecnologiasMaiorValor.forEach(t -> sb.append(t.geraDescricao()).append("\n"));
        return sb.toString();
    }

    public String consultarFornecedorMaiorTecnologias() {
        if (tecnologias.isEmpty()) {
            return "Nenhuma tecnologia cadastrada.";
        }

        Map<Fornecedor, Long> contagem = tecnologias.stream()
                .collect(Collectors.groupingBy(Tecnologia::getFornecedor, Collectors.counting()));

        long maiorQuantidade = contagem.values().stream()
                .mapToLong(Long::longValue)
                .max()
                .orElse(0);

        List<Fornecedor> fornecedoresMaior = contagem.entrySet().stream()
                .filter(e -> e.getValue() == maiorQuantidade)
                .map(Map.Entry::getKey)
                .toList();

        StringBuilder sb = new StringBuilder();
        sb.append("Fornecedor(es) com maior número de tecnologias (")
                .append(maiorQuantidade)
                .append("):\n");

        fornecedoresMaior.forEach(f -> sb.append(f.geraDescricao() + "\n - Quantidade:" + maiorQuantidade + "\n").append("\n"));

        return sb.toString();
    }

    public String consultarCompradorMaiorVendas() {
        if (vendas.isEmpty()) {
            return "Erro: Não há vendas cadastradas.";
        }

        if (compradores.isEmpty()) {
            return "Erro: Não há compradores cadastrados.";
        }

        Map<Comprador, Integer> contador = new HashMap<>();

        for (Venda v : vendas) {
            Comprador c = v.getComprador();
            if (c != null) {
                contador.put(c, contador.getOrDefault(c, 0) + 1);
            }
        }

        if (contador.isEmpty()) {
            return "Erro: Nenhum comprador está associado a vendas.";
        }

        int maior = contador.values().stream().max(Integer::compareTo).orElse(0);

        if (maior == 0) {
            return "Nenhum comprador possui vendas registradas.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Comprador(es) com maior número de vendas:\n");

        for (Map.Entry<Comprador, Integer> entry : contador.entrySet()) {
            if (entry.getValue() == maior) {
                Comprador c = entry.getKey();
                sb.append(c.geraDescricao() + "\n - Quantidade: " + maior).append("\n");
            }
        }
        return sb.toString();
    }

    public String consultarVendaMaiorValor() {
        if (vendas.isEmpty()) {
            return "Erro: Não há vendas cadastradas.";
        }

        double maiorValor = 0;

        for (Venda v : vendas) {
            double valorTecnologia = v.getTecnologia().getValorBase();
            if (valorTecnologia >= maiorValor) {
                maiorValor = valorTecnologia;
            }
        }

        if (maiorValor == 0) {
            return "Erro: Não foi possível identificar o maior valor de venda.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Venda(s) com maior valor:\n");
        sb.append("Maior valor encontrado: ").append(maiorValor).append("\n");

        for (Venda v : vendas) {
            if (Double.compare(v.getTecnologia().getValorBase(), maiorValor) == 0) {
                sb.append(String.format(
                        "%s||%s||%s||%s||%s||%s||%s||%s||%s||%s||%s||%s||%s||%s",
                        " Num ",
                        " Data ",
                        " Modelo Tecnologia ",
                        " Descrição ",
                        " Valor Base ",
                        " Peso ",
                        " Temperatura ",
                        " Fornecedor ",
                        " Comprador Cod ",
                        " Nome ",
                        " País ",
                        " Email ",
                        " Valor Final ",
                        " Quantidade de Vendas\n"));
                sb.append(v.geraDescricao()).append("\n");
            }
        }
        return sb.toString();
    }

}
