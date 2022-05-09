package ec.fin.pichincha.EjercicioFullstack.repository;

import ec.fin.pichincha.EjercicioFullstack.model.Cuenta;
import ec.fin.pichincha.EjercicioFullstack.model.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Integer> {

    @Query("SELECT u FROM Movimientos u WHERE u.cuentaCuenId = ?1")
    List<Movimientos> findMovimientosBytransaccion(Cuenta cuenta);

    @Query("SELECT u FROM Movimientos u WHERE u.cuentaCuenId = :cuenta ORDER BY u.movId DESC")
    List<Movimientos> findUserByCuenta(
            @Param("cuenta") Cuenta cuenta);

    @Query("SELECT u FROM Movimientos u WHERE u.cuentaCuenId = :cuenta and u.fecha BETWEEN :startDate AND :endDate")
    List<Movimientos> reporteMovimientos(@Param("startDate") Date startDate,
                                         @Param("endDate") Date endDate,
                                         @Param("cuenta") Cuenta cuenta);


    @Query("SELECT u FROM Movimientos u WHERE u.fecha BETWEEN :startDate AND :endDate")
    List<Movimientos> reporteMovimientosCliente(@Param("startDate") Date startDate,
                                         @Param("endDate") Date endDate);
}
