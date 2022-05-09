package ec.fin.pichincha.EjercicioFullstack.service.interfaceservices;


import ec.fin.pichincha.EjercicioFullstack.model.Cliente;
import ec.fin.pichincha.EjercicioFullstack.model.Cuenta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
public interface IServicioCuenta {

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Cuenta cuenta);

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listar(@PathVariable Long id);

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminar(@RequestBody Cuenta cuenta);

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody Cuenta cuenta);

}
