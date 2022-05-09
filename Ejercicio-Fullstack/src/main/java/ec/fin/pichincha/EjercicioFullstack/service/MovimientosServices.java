package ec.fin.pichincha.EjercicioFullstack.service;

import ec.fin.pichincha.EjercicioFullstack.logica.LogicaDeposito;
import ec.fin.pichincha.EjercicioFullstack.model.FrameOut;
import ec.fin.pichincha.EjercicioFullstack.model.Movimientos;
import ec.fin.pichincha.EjercicioFullstack.repository.MovimientosRepository;
import ec.fin.pichincha.EjercicioFullstack.service.interfaceservices.IServicioMovimientos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/movimientos")
public class MovimientosServices implements IServicioMovimientos {

    @Autowired
    private LogicaDeposito logicaDeposito;

    private MovimientosRepository movimientosRepository;

    public MovimientosServices(MovimientosRepository movimientosRepository) {
        this.movimientosRepository = movimientosRepository;
    }

    @Override
    public ResponseEntity<?> crear(Movimientos movimientos) {
        log.info("Ingresa Movimiento");

        return ResponseEntity.ok( logicaDeposito.registrotransaccion(movimientos));
    }

    @Override
    public ResponseEntity<?> listar(Long id) {

        Optional<Movimientos> movimientos = movimientosRepository.findById(Math.toIntExact(id));
        if (movimientos.isPresent()) {
            return ResponseEntity.ok(movimientos.get());
        } else {

            FrameOut frameOut = new FrameOut("111"," no existe id");

            //return ResponseEntity.notFound().build();
            return ResponseEntity.ok(frameOut);
        }
    }

    @Override
    public ResponseEntity<?> eliminar(Movimientos movimientos) {
        return null;
    }
}
