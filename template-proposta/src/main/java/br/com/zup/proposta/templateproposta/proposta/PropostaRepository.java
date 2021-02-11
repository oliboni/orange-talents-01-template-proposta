package br.com.zup.proposta.templateproposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);

    @Query("select p from Proposta p where p.estadoProposta = 'ELEGIVEL' and p.numeroCartao is null")
    List<Proposta> findAllElegivelCartaoisNull();

}
