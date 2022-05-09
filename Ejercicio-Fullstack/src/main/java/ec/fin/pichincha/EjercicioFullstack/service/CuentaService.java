package ec.fin.pichincha.EjercicioFullstack.service;

import ec.fin.pichincha.EjercicioFullstack.model.*;
import ec.fin.pichincha.EjercicioFullstack.repository.CuentaRepository;
import ec.fin.pichincha.EjercicioFullstack.repository.MovimientosRepository;
import ec.fin.pichincha.EjercicioFullstack.service.interfaceservices.IServicioCuenta;
import ec.fin.pichincha.EjercicioFullstack.utils.FormatosDatos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/cuenta")
public class CuentaService implements IServicioCuenta {

    @Autowired
    private FormatosDatos formatosDatos;

    private CuentaRepository cuentaRepository;
    private MovimientosRepository movimientosRepository;


    public CuentaService(CuentaRepository cuentaRepository, MovimientosRepository movimientosRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientosRepository = movimientosRepository;
    }

    @Override
    public ResponseEntity<?> crear(Cuenta cuenta) {
        try {
            Cuenta cuenta1 = cuentaRepository.save(cuenta);
            BigDecimal inicio = cuenta1.getSaldoInicial();
            Movimientos movimientos = new Movimientos( null,  formatosDatos.date(),"credito", inicio, inicio,cuenta1 );
            movimientosRepository.save(movimientos);
            return ResponseEntity.ok(cuenta1);
        }catch (Exception e){
            log.info(e.getLocalizedMessage());
            FrameOut frameOut = new FrameOut("999", e.getLocalizedMessage());
            return ResponseEntity.ok(frameOut);
        }
    }

    @Override
    public ResponseEntity<?> listar(Long id) {

        Optional<Cuenta> cuenta = cuentaRepository.findById(Math.toIntExact(id));


        if (cuenta.isPresent()) {
            return ResponseEntity.ok(cuenta.get());
        } else {

            FrameOut frameOut = new FrameOut("111"," no existe id");

            //return ResponseEntity.notFound().build();
            return ResponseEntity.ok(frameOut);
        }


    }

    @Override
    public ResponseEntity<?> eliminar(Cuenta cuenta) {
        return null;
    }

    @Override
    public ResponseEntity<?> actualizar(Cuenta cuenta) {
        return null;
    }
}
