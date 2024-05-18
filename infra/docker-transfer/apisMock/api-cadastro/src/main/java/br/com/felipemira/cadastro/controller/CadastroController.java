package br.com.felipemira.cadastro.controller;

import br.com.felipemira.cadastro.domain.Correntista;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/cadastros")
public class CadastroController {

    private final Map<Integer, Correntista> cadastro = new HashMap<>();

    public CadastroController() {
        cadastro.put(1, new Correntista(1, "Fernando"));
        cadastro.put(2, new Correntista(2, "Rebeca"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Correntista> get(@PathVariable Integer id) {
        var correntista = cadastro.get(id);

        if(isNull(correntista)){
            System.out.println("API MOCK para correntista nao encontrou o cliente de id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            System.out.println("API MOCK para correntista encontrou o cliente de id: " + id);
            return ResponseEntity.ok(correntista);
        }
    }
}
