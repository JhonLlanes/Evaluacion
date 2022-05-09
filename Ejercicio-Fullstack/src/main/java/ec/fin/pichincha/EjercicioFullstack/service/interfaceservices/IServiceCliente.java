package ec.fin.pichincha.EjercicioFullstack.service.interfaceservices;

import ec.fin.pichincha.EjercicioFullstack.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

public interface IServiceCliente {
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Cliente cliente);

    @GetMapping("/listar")
    public ResponseEntity<?> listar();

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminar(@RequestBody Cliente cliente);

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody Cliente cliente);
}
