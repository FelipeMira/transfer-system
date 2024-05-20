package br.com.felipemira.bacen.controller;

import br.com.felipemira.bacen.model.TransferenciaBacenRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/bacen/transferencia")
public class BacenController {


    @PostMapping
    public ResponseEntity<?> post(@RequestBody TransferenciaBacenRequest transferenciaBacenRequest) {

        if(isNull(transferenciaBacenRequest)){
            System.out.println("API MOCK para envio ao BACEN, objeto enviado nulo.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            System.out.printf("API MOCK para envio ao BACEN, objeto enviado: idDebito %s, idCredito %s, valor %s \n",
                    transferenciaBacenRequest.getIdDebito(),
                    transferenciaBacenRequest.getIdCredito(),
                    transferenciaBacenRequest.getValor());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
