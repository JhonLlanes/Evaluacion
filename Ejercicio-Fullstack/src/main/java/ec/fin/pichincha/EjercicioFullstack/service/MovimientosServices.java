package ec.fin.pichincha.EjercicioFullstack.service;

import ec.fin.pichincha.EjercicioFullstack.logica.LogicaDeposito;
import ec.fin.pichincha.EjercicioFullstack.logica.LogicaReporte;
import ec.fin.pichincha.EjercicioFullstack.model.FrameOut;
import ec.fin.pichincha.EjercicioFullstack.model.Movimientos;
import ec.fin.pichincha.EjercicioFullstack.repository.MovimientosRepository;
import ec.fin.pichincha.EjercicioFullstack.service.interfaceservices.IServicioMovimientos;
import ec.fin.pichincha.EjercicioFullstack.utils.UserPDFExporter;
import ec.fin.pichincha.EjercicioFullstack.utils.UserPDFExporterCuentas;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/movimientos")
public class MovimientosServices implements IServicioMovimientos {

    @Autowired
    private LogicaDeposito logicaDeposito;

    @Autowired
    private LogicaReporte logicaReporte;

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
    public ResponseEntity<?> reporteFechas(String data, HttpServletResponse response)  {

        String[] parts = data.split(":");
        Map<String, String> map = new HashMap<>();
        map.put("cuenta", parts[0]);
        map.put("fechainicio", parts[1]);
        map.put("fechafin", parts[2]);
//        logicaReporte.reporteRangoFechas(map);

        FrameOut frame = logicaReporte.reporteRangoFechas(map);

        UserPDFExporter pdfExporter = new UserPDFExporter((List<Map<String, String>>) frame.getObject());
        try {
            pdfExporter.export(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return ResponseEntity.ok(frame);
    }

    @Override
    public ResponseEntity<?> reporteFechasCliente(String data, HttpServletResponse response) {
        String[] parts = data.split(":");
        Map<String, String> map = new HashMap<>();
        map.put("fechainicio", parts[0]);
        map.put("fechafin", parts[1]);

        FrameOut frame = logicaReporte.reporteRangoFechasCuentas(map);

        if(parts[2].equals("true")){

            UserPDFExporterCuentas pdfExporter = new UserPDFExporterCuentas((List<Map<String, String>>) frame.getObject());
            try {
                pdfExporter.export(response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        return ResponseEntity.ok(frame);
    }

    @Override
    public ResponseEntity<?> eliminar(Movimientos movimientos) {
        return null;
    }
}
