package br.com.zup.proposta.templateproposta.cartao.carteira;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    Optional<Carteira> findByCarteiraDigitalAndCartaoId(CarteiraDigital carteiraDigital, Long id);
}
