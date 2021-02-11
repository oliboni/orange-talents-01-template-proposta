package br.com.zup.proposta.templateproposta.proposta;

import br.com.zup.proposta.templateproposta.endereco.NovoEnderecoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Transactional
class PropostaContollerTest {

    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper jsontMapper;

    @Test
    void deveRetornarBadRequest() throws Exception {
        mockMvc.perform(post("/api/propostas")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarCreated() throws Exception{
        NovaPropostaRequest novaProposta = getNovaPropostaRequest();

        mockMvc.perform(post("/api/propostas")
                            .content(jsontMapper.writeValueAsString(novaProposta))
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location","http://localhost/api/propostas/1"));
//      Deve existir 1 registro no sistema
        assertEquals(1, propostaRepository.findAll().size());
    }

    private NovoEnderecoRequest getNovoEnderecoRequest(){
        return new NovoEnderecoRequest("Br", "SC", "AB", "CD", "AA", 98, "teste");
    }

    private NovaPropostaRequest getNovaPropostaRequest(){
        return new NovaPropostaRequest("025.672.930-19","teste@teste.com","teste",new BigDecimal("2000"),getNovoEnderecoRequest());
    }

}