package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    public static final Leilao SONHO = new Leilao("sonho");

    //poderia ter criado um atributo de classe do tipo Leilao e utilizado ele pra todos testes
    //cada vez que rodamos um teste, ele cria nova instância da classe, sendo assim um teste não afeta outro teste
    //se fosse uma instância estática, daria erro pois seria utilizada a mesma referência para todos os testes


    @Test
    public void getDescricao_QuandoRecebeDescricao_DevolveDescricao() {
        //criar cenário de teste
        Leilao console = new Leilao("Console");

        //executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        //testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void getMaiorLance_QuandoRecebeApenasUmLance() {
        //criando cenário
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Alex"), 200.0));

        //executando método que queremos testar
        double maiorLanceDevolvido = console.getMaiorLance();

        //testando se o retorno é o mesmo que era esperado
        //como é uma comparação entre double, precisamos usar o terceiro parâmetro "delta"
        //delta padrão = 0.0001
        assertEquals(200.0, maiorLanceDevolvido, DELTA);

    }

    @Test
    public void getMaiorLance_RecebeValoresEmOrdemCrescente_DevolveMaiorLance() {
        Leilao videogame = new Leilao("videogame");
        videogame.propoe(new Lance(new Usuario("Tília"), 100.0));
        videogame.propoe(new Lance(new Usuario("Rapha"), 130.0));
        videogame.propoe(new Lance(new Usuario("Dude"), 135.0));
        double maiorValorDevolvido = videogame.getMaiorLance();
        assertEquals(135.0, maiorValorDevolvido, DELTA);
    }

    //teste obsoleto pois o teste que verifica se é permitido dar lances menores que o maiorLance cobre o mesmo cenário
//    @Test
//    public void deve_DevolverMaiorLance_QuandoRecebeLancesEmOrdemDecrescente() {
//        Leilao bolo = new Leilao("bolo de chocolate");
//        bolo.propoe(new Lance(new Usuario("Livia"), 15.0));
//        bolo.propoe(new Lance(new Usuario("Tilia"), 10.0));
//        double maiorValorDevolvido = bolo.getMaiorLance();
//        assertEquals(15.0, maiorValorDevolvido, DELTA);
//    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeUnicoLance() {
        Leilao brigadeiro = new Leilao("brigadeiro");
        brigadeiro.propoe(new Lance(new Usuario("Tilia"), 5.0));
        double menorLanceDevolvido = brigadeiro.getMenorLance();
        assertEquals(5.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeLancesEmOrdemCrescente() {
        Leilao cheesecake = new Leilao("cheesecake");
        cheesecake.propoe(new Lance(new Usuario("Alê"), 500.0));
        cheesecake.propoe(new Lance(new Usuario("Genja"), 600.0));
        double valorMenorLanceCheesecake = cheesecake.getMenorLance();
        assertEquals(500.0, valorMenorLanceCheesecake, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeLancesEmOrdemDecrescente() {
        Leilao cheesecake = new Leilao("cheesecake");
        cheesecake.propoe(new Lance(new Usuario("Alê"), 500.0));
        cheesecake.propoe(new Lance(new Usuario("Genja"), 400.0));
        double valorMenorLanceCheesecake = cheesecake.getMenorLance();
        assertEquals(400.0, valorMenorLanceCheesecake, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeTresLances() {
        Leilao cheesecake = new Leilao("cheesecake");
        cheesecake.propoe(new Lance(new Usuario("Fofa"), 500.0));
        cheesecake.propoe(new Lance(new Usuario("Genja"), 450.0));
        cheesecake.propoe(new Lance(new Usuario("Genja"), 480.0));

        List<Lance> tresMaioresLancesDevolvidos = cheesecake.tresMaioresLances();

        //verificando se devolve 3 números
        assertEquals(3, tresMaioresLancesDevolvidos.size());

        //verificando o valor que se encontra em cada índice da lista levando em considereção que deve ser em ordem decrescente
        assertEquals(500.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(480.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(450.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);

    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances() {
        Leilao cheesecake = new Leilao("cheesecake");
        List<Lance> lancesDevolvidos = cheesecake.tresMaioresLances();
        assertEquals(0, lancesDevolvidos.size(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance() {
        Leilao videogame = new Leilao("videogame");
        videogame.propoe(new Lance(new Usuario("Tília"), 100.0));

        List<Lance> lancesDevolvidos = videogame.tresMaioresLances();
        assertEquals(1, lancesDevolvidos.size());
        assertEquals(100.0, lancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasDoisLances() {
        Leilao bolo = new Leilao("bolo de chocolate");
        bolo.propoe(new Lance(new Usuario("Livia"), 15.0));
        bolo.propoe(new Lance(new Usuario("Tilia"), 10.0));

        List<Lance> lancesDevolvidos = bolo.tresMaioresLances();
        assertEquals(2, lancesDevolvidos.size());
        assertEquals(15.0, lancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(10.0, lancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances() {
        Leilao bolo = new Leilao("bolo de chocolate");
        bolo.propoe(new Lance(new Usuario("Livia"), 15.0));
        bolo.propoe(new Lance(new Usuario("Tilia"), 10.0));
        bolo.propoe(new Lance(new Usuario("Dude"), 12.0));
        bolo.propoe(new Lance(new Usuario("Rapha"), 17.0));
        bolo.propoe(new Lance(new Usuario("Milaide"), 18.0));

        final List<Lance> lancesDevolvidos = bolo.tresMaioresLances();

        assertEquals(3, lancesDevolvidos.size());
        assertEquals(18.0, lancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(17.0, lancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(15.0, lancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTemLances() {
        double maiorLanceDevolvido = SONHO.getMaiorLance();
        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTemLances() {
        double menorLanceDevolvido = SONHO.getMenorLance();
        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void naoDeve_AdcionarLance_QuandoForMenorQueMaiorLance() {
        SONHO.propoe(new Lance(new Usuario("Milaide"), 38.0));
        SONHO.propoe(new Lance(new Usuario("Due"), 28.0));

        int quantidadeDevolvida = SONHO.quantidadeLances();
        assertEquals(1, quantidadeDevolvida);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance(){
        Usuario Milaide = new Usuario("Milaide");
        SONHO.propoe(new Lance(Milaide, 38.0));
        SONHO.propoe(new Lance(new Usuario("Milaide"), 38.0));

        int quantidadeDeLances = SONHO.quantidadeLances();

        assertEquals(1, quantidadeDeLances);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoUsuarioDerCincoLances(){
        SONHO.propoe(new Lance(new Usuario("Milaide"), 100.0));
        SONHO.propoe(new Lance(new Usuario("Junio"), 200.0));
        SONHO.propoe(new Lance(new Usuario("Milaide"), 300.0));
        SONHO.propoe(new Lance(new Usuario("Junio"), 400.0));
        SONHO.propoe(new Lance(new Usuario("Milaide"), 500.0));
        SONHO.propoe(new Lance(new Usuario("Junio"), 600.0));
        SONHO.propoe(new Lance(new Usuario("Milaide"), 700.0));
        SONHO.propoe(new Lance(new Usuario("Junio"), 800.0));
        SONHO.propoe(new Lance(new Usuario("Milaide"), 900.0));
        SONHO.propoe(new Lance(new Usuario("Junio"), 1000.0));
        SONHO.propoe(new Lance(new Usuario("Milaide"), 1100.0));
        SONHO.propoe(new Lance(new Usuario("Junio"), 1200.0));

        int quantidadeDeLances = SONHO.quantidadeLances();

        assertEquals(10, quantidadeDeLances);


    }

}
