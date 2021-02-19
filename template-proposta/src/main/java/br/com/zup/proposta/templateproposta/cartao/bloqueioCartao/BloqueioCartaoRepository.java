package br.com.zup.proposta.templateproposta.cartao.bloqueioCartao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloqueioCartaoRepository extends JpaRepository<BloqueioCartao,Long> {
    Optional<BloqueioCartao> findByCartaoId(Long idCartao);
}
