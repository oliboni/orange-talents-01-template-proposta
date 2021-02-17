package br.com.zup.proposta.templateproposta.biometria;

import br.com.zup.proposta.templateproposta.cartao.Cartao;
import br.com.zup.proposta.templateproposta.endereco.Endereco;
import br.com.zup.proposta.templateproposta.proposta.Proposta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.lang.annotation.Documented;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Transactional
class BiometriaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper jsontMapper;
    @Autowired
    private BiometriaRepository biometriaRepository;

    @Test
    @DisplayName("CriaCartaoRetorna200")
    void criaBiometriaRetorna201() throws Exception {
        Cartao cartao = getNovoCartao(getNovaProposta("091.232.700-63"));
        NovaBiometria novaBiometria = new NovaBiometria("Um9kcmlnbyBPbGlib25p");

        mockMvc.perform(post("/api/biometrias/{idCartao)",cartao.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsontMapper.writeValueAsString(novaBiometria)))
                .andDo(print())
                .andExpect(status().isCreated());

        List<Biometria> biometriaCadastrada = biometriaRepository.findAll();

        assertEquals(1,biometriaCadastrada.size());
        assertEquals("Um9kcmlnbyBPbGlib25p", biometriaCadastrada.get(0).getBiometria());
        assertEquals(cartao.getId(), biometriaCadastrada.get(0).getCartao().getId());
    }

    @Test
    @DisplayName("NaoCriaCartaoRetorna404")
    void naoCriaBiometriaRetorna404() throws Exception{
        NovaBiometria novaBiometria = new NovaBiometria("Um9kcmlnbyBPbGlib25p");

        mockMvc.perform(post("/api/biometrias/{idCartao)",10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsontMapper.writeValueAsString(novaBiometria)))
                .andDo(print())
                .andExpect(status().isNotFound());

        assertEquals(0,biometriaRepository.findAll().size());
    }

    @Test
    @DisplayName("NaoCriaCartaoRetorna400")
    void naoCriaBiometriaRetorna400() throws Exception{
        Cartao cartao = getNovoCartao(getNovaProposta("091.232.700-63"));
        NovaBiometria novaBiometria = new NovaBiometria("Um 9kcmlnbyBPbGlib25p ");

        mockMvc.perform(post("/api/biometrias/{idCartao)",cartao.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsontMapper.writeValueAsString(novaBiometria)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        assertEquals(0,biometriaRepository.findAll().size());
    }

    private Cartao getNovoCartao(Proposta proposta){
        return new Cartao("numeroCArd","Titular", LocalDateTime.now(),new BigDecimal("2000"),proposta);
    }

    private Endereco getNovoEndereco(){
        return new Endereco("Br", "SC", "AB", "CD", "AA", 98, "teste");
    }

    private Proposta getNovaProposta(String documento){
        return new Proposta(documento,"teste@teste.com","teste",new BigDecimal("2000"),getNovoEndereco());
    }
}