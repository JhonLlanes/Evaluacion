package ec.fin.pichincha.EjercicioFullstack.service.interfaceservices;

import ec.fin.pichincha.EjercicioFullstack.model.Cuenta;
import ec.fin.pichincha.EjercicioFullstack.model.Movimientos;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RequestMapping("/default")
public interface IServicioMovimientos  {


    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Movimientos movimientos);

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listar(@PathVariable Long id);

    @GetMapping("/reporte/{data}")
    public ResponseEntity<?> reporteFechas(@PathVariable String data, HttpServletResponse response);

    @GetMapping("/reporte2/{data}")
    public ResponseEntity<?> reporteFechasCliente(@PathVariable String data, HttpServletResponse response);

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminar(@RequestBody Movimientos movimientos);
}
