package br.com.felipemira.web.out.feing.controller;

import br.com.felipemira.application.core.domain.model.TransactionBacen;
import br.com.felipemira.application.core.ports.out.BacenPort;
import br.com.felipemira.common.mappers.GenericConvert;
import br.com.felipemira.web.out.dto.request.TransactionBacenRequest;
import br.com.felipemira.web.out.feing.clients.TransactBacenFeing;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Map;

@Named
public class BacenController implements BacenPort {

    private final TransactBacenFeing transactBacenFeing;

    private final RabbitTemplate rabbitTemplate;

    private static final String queueName = "bacen-queue";

    @Inject
    public BacenController(TransactBacenFeing transactBacenFeing, RabbitTemplate rabbitTemplate){
        this.transactBacenFeing = transactBacenFeing;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void postTransactionBacen(TransactionBacen transactionBacen) {
        Gson gson = new Gson();
        try{
            String json = gson.toJson(GenericConvert.convertModelMapper(transactionBacen, TransactionBacenRequest.class));
            Map<String, String> headers = Map.of("Content-Type", "application/json");
            transactBacenFeing.postTransact(json, headers);
        }catch(Exception ex){
            rabbitTemplate.convertAndSend(queueName, gson.toJson(transactionBacen));
        }
    }
}
