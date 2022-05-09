package ec.fin.pichincha.EjercicioFullstack.logica;

import ec.fin.pichincha.EjercicioFullstack.model.Cuenta;
import ec.fin.pichincha.EjercicioFullstack.model.FrameOut;
import ec.fin.pichincha.EjercicioFullstack.model.Movimientos;
import ec.fin.pichincha.EjercicioFullstack.repository.MovimientosRepository;
import ec.fin.pichincha.EjercicioFullstack.utils.FormatosDatos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class LogicaDeposito {

    @Autowired
    private FormatosDatos formatosDatos;

    private MovimientosRepository movimientosRepository;

    public LogicaDeposito(MovimientosRepository movimientosRepository) {
        this.movimientosRepository = movimientosRepository;
    }

    public FrameOut registrotransaccion(Movimientos movimientos){
        FrameOut frameOut = new FrameOut();
        movimientos.setFecha(formatosDatos.date());

        List <Movimientos>  respMovimientos=  movimientosRepository.findUserByCuenta(movimientos.getCuentaCuenId());
        BigDecimal saldoant = respMovimientos.get(0).getSaldo();
        BigDecimal valordep = movimientos.getValor();
        log.info( "SALDO ANTERIOR " + saldoant + " VALOR " + valordep);

        if (movimientos.getTipoMovimiento().equals("credito")){
            log.info("CREDIO");
            movimientos.setSaldo(saldoant.add(valordep));
            movimientosRepository.save(movimientos);
            frameOut.setObject(movimientosRepository.save(movimientos));
            frameOut.setCode("000");
            frameOut.setMenssaje("TRANSACCION CON EXITO");
            return frameOut;
        }

        if(movimientos.getTipoMovimiento().equals("debito")){
            log.info("DEBITO");
            if(saldoant.compareTo(valordep) == -1 ){
                frameOut.setCode("991");
                frameOut.setMenssaje("SIN SALDO ");
                return frameOut;
            }else {
                movimientos.setSaldo(saldoant.subtract(valordep));
                movimientosRepository.save(movimientos);
                frameOut.setObject(movimientosRepository.save(movimientos));
                frameOut.setCode("000");
                frameOut.setMenssaje("TRANSACCION CON EXITO");
                return frameOut;
            }

        }


    return null;
    }


}
