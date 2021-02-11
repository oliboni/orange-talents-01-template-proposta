package br.com.zup.proposta.templateproposta.api.consulta;

import br.com.zup.proposta.templateproposta.proposta.EstadoProposta;

public enum AnaliseSolicitacao {
    SEM_RESTRICAO(EstadoProposta.ELEGIVEL), COM_RESTRICAO(EstadoProposta.NAO_ELEGIVEL);

    private EstadoProposta estadoProposta;

    AnaliseSolicitacao(EstadoProposta estadoProposta) {
        this.estadoProposta = estadoProposta;
    }

    public EstadoProposta getEstadoProposta() {
        return estadoProposta;
    }
}
