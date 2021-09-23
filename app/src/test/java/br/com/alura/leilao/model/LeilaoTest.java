package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    @Test
    public void getDescricao() {
        //criar cenário de teste
        Leilao console = new Leilao("Console");

        //executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        //testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void getMaiorLance() {
        //criando cenário
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Alex"), 200.0));

        //executando método que queremos testar
        double maiorLanceDevolvido = console.getMaiorLance();

        //testando se o retorno é o mesmo que era esperado
        //como é uma comparação entre double, precisamos usar o terceiro parâmetro "delta"
        //delta padrão = 0.0001
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);

    }
}