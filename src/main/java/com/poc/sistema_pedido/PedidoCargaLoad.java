// java
package com.poc.sistema_pedido;

import com.poc.sistema_pedido.entity.PedidoEntity;
import com.poc.sistema_pedido.entity.enums.StatusPedido;
import com.poc.sistema_pedido.repository.PedidoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class PedidoCargaLoad implements CommandLineRunner {
    private final PedidoRepository pedidoRepository;

    public PedidoCargaLoad(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<PedidoEntity> pedidos = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            PedidoEntity pedido = new PedidoEntity();
            // Ajuste os nomes dos setters conforme sua entity
            pedido.setMomento(Instant.now());
            pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
            pedido.setEnderecoEntrega("Endereço teste " + i);
            pedido.setValorDesconto(0.0);

            pedidos.add(pedido);
        }

        pedidoRepository.saveAll(pedidos);
    }
}
