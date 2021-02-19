package br.com.zup.proposta.templateproposta.config.metrics;

import br.com.zup.proposta.templateproposta.proposta.PropostaContoller;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Metrics {
    private final MeterRegistry meterRegistry;
    private final Collection<String> strings = new ArrayList<>();
    private final Random random = new Random();


    public Metrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        criarGauge();
    }

    public void proposalCounter(){
        Collection<Tag> tags = getTags();

        Counter proposalCounter = this.meterRegistry.counter("proposta_criada", tags);
        proposalCounter.increment();
    }

    public void tempoConsulta(){
        Collection<Tag> tags = getTags();

        Timer timerConsultaProposta = this.meterRegistry.timer("consultar_proposta",tags);
        timerConsultaProposta.record(() -> {
            // Método da sua operação
//            TODO
        });
    }

    private void criarGauge() {
        Collection<Tag> tags = getTags();

        this.meterRegistry.gauge("proposta_gauge", tags, strings, Collection::size);
    }

    private Collection<Tag> getTags() {
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("emissora", "mastercard"));
        tags.add(Tag.of("banco", "Itau"));
        return tags;
    }

    public void removeString() {
        strings.removeIf(Objects::nonNull);
    }

    public void addString() {
        strings.add(UUID.randomUUID().toString());
    }

    @Scheduled(fixedDelay = 1000)
    public void simulandoGauge() {
        double randomNumber = random.nextInt();
        if (randomNumber % 2 == 0) {
            addString();
        } else {
            removeString();
        }
    }
}
