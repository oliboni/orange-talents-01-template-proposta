package br.com.zup.proposta.templateproposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);

    @Query("select p from Proposta p LEFT JOIN p.cartao c " +
           "where p.estadoProposta = 'ELEGIVEL' and c is null")
//           " limit 100" +
//           " for update skip locked")//for update(lock nas linnhas selecionadas) @Lock(LockModeType.PESSIMISTIC_WRITE)
                                     //skip locked não pega as linhas com lock
                                    //Para skip, usa-se @QueryHints({})
    List<Proposta> findTop100AllElegivelCartaoisNull();

}
