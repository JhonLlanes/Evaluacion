package ec.fin.pichincha.EjercicioFullstack.service.interfaceservices;

import ec.fin.pichincha.EjercicioFullstack.model.Cuenta;
import ec.fin.pichincha.EjercicioFullstack.model.Movimientos;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/default")
public interface IServicioMovimientos  {


    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Movimientos movimientos);

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listar(@PathVariable Long id);

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminar(@RequestBody Movimientos movimientos);
}
