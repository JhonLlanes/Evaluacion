package ec.fin.pichincha.EjercicioFullstack.logica;

import ec.fin.pichincha.EjercicioFullstack.model.FrameOut;
import ec.fin.pichincha.EjercicioFullstack.model.Movimientos;
import ec.fin.pichincha.EjercicioFullstack.repository.CuentaRepository;
import ec.fin.pichincha.EjercicioFullstack.repository.MovimientosRepository;
import ec.fin.pichincha.EjercicioFullstack.utils.FormatosDatos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class LogicaReporte {

    @Autowired
    private FormatosDatos formatosDatos;

    private MovimientosRepository movimientosRepository;
    private CuentaRepository cuentaRepository;

    public LogicaReporte(MovimientosRepository movimientosRepository, CuentaRepository cuentaRepository) {
        this.movimientosRepository = movimientosRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public FrameOut reporteRangoFechas(Map<String,String> data){

        log.info("TRENSACCIONES DE UNA CUENTA");
        FrameOut frameOut = new FrameOut();
        Date inicio;
        Date fin;
        try {
            inicio =new SimpleDateFormat("yyyy-MM-dd").parse(data.get("fechainicio"));
            fin =new SimpleDateFormat("yyyy-MM-dd").parse(data.get("fechafin"));


        List<Movimientos> respData = movimientosRepository.reporteMovimientos(inicio,fin,cuentaRepository.findByCuentaforNumero(data.get("cuenta")));
        List<Map<String,String>> respFin = new ArrayList<>();
        for (Movimientos movimientos:respData) {
            Map<String, String> datares = new HashMap<>();
            datares.put("fecha", String.valueOf(movimientos.getFecha()));
            datares.put("tipoMovimiento", movimientos.getTipoMovimiento());
            datares.put("valor", movimientos.getTipoMovimiento().equals("debito") ? "-"+movimientos.getValor() : String.valueOf(movimientos.getValor()));
            datares.put("saldo", String.valueOf(movimientos.getSaldo()));
            respFin.add(datares);
        }
        frameOut.setObject(respFin);
        return frameOut;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


    public FrameOut reporteRangoFechasCuentas(Map<String,String> data){
        log.info("TRENSACCIONES DE VARIAS CUENTAS + RENGO DE FECHAS");

        FrameOut frameOut = new FrameOut();
        Date inicio;
        Date fin;
        try {
            inicio =new SimpleDateFormat("yyyy-MM-dd").parse(data.get("fechainicio"));
            fin =new SimpleDateFormat("yyyy-MM-dd").parse(data.get("fechafin"));


        List<Movimientos> respData = movimientosRepository.reporteMovimientosCliente(inicio,fin);
        List<Map<String,String>> respFin = new ArrayList<>();
        for (Movimientos movimientos:respData) {
            Map<String, String> datares = new HashMap<>();
            datares.put("fecha", String.valueOf(movimientos.getFecha()));
            datares.put("cliente", movimientos.getCuentaCuenId().getClienteClieId().getPersonaPerId().getNombre());
            datares.put("numerocuenta", movimientos.getCuentaCuenId().getNumeroCuenta());
            datares.put("saldoinicial", movimientos.getTipoMovimiento().equals("debito") ?  String.valueOf(movimientos.getSaldo().add(movimientos.getValor())):String.valueOf(movimientos.getSaldo().subtract(movimientos.getValor())));
            datares.put("estado", movimientos.getCuentaCuenId().getClienteClieId().getEstado());
            datares.put("movimiento", movimientos.getTipoMovimiento().equals("debito") ? "-"+movimientos.getValor() : String.valueOf(movimientos.getValor()));
            datares.put("saldodisponible", String.valueOf(movimientos.getSaldo()));
            datares.put("tipo", movimientos.getTipoMovimiento());
            respFin.add(datares);
        }
        frameOut.setObject(respFin);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return frameOut;
    }

}
