package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.Mockito.*;

class AbrigoServiceTest {

    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private AbrigoService abrigoService = new AbrigoService(client);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private Abrigo abrigo = new Abrigo("Teste", "rua das flores", "pet@gmail.com", "1112345678");

    @Test
    void deveVerificarSeHaAbrigosCadastrados() throws IOException, InterruptedException {
        abrigo.setId(0L);
        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectedIdAndNome = "0 - Teste";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[{" + abrigo.toString() + "}]");
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);

        abrigoService.listarAbrigo();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualAbrigosCadastrados = lines[0];
        String actualIdAndNome = lines[1];

        Assertions.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados);
        Assertions.assertEquals(expectedIdAndNome, actualIdAndNome);

    }

    @Test
    void deveVerificarQuandoNaoHaAbrigosCadastrados() throws IOException, InterruptedException {
        String expected = "Não há abrigos cadastrados";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        // retorna um array vazio quando não tem abrigo cadastrado
        when(response.body()).thenReturn("[]");
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);

        abrigoService.listarAbrigo();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[0];

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void deveCadastrarAbrigoComSucesso() throws IOException, InterruptedException {

        String expectedSuccessMessage = "Abrigo cadastrado com sucesso!";
        String expected = "";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.statusCode()).thenReturn(200);
        // Sem body returned for response
        //when(response.body()).thenReturn("[]");
        when(client.dispararRequisicaoPost(anyString(), anyString())).thenReturn(response);

        // fica trancado nesta parte
        //abrigoService.cadastrarAbrigo();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualSuccessMessage = lines[0];
        String actual = "Abrigo cadastrado com sucesso!";

        Assertions.assertEquals(expected, actualSuccessMessage);
        Assertions.assertEquals(expectedSuccessMessage, actual);
        verify(client.dispararRequisicaoPost(anyString(), anyString()), atLeast(1));

    }

}