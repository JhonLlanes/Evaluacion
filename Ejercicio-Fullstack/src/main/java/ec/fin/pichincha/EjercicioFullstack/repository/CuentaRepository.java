package ec.fin.pichincha.EjercicioFullstack.repository;

import ec.fin.pichincha.EjercicioFullstack.model.Cuenta;
import ec.fin.pichincha.EjercicioFullstack.model.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    @Query("SELECT u FROM Cuenta u WHERE u.numeroCuenta = :cuenta")
    Cuenta findByCuentaforNumero(
            @Param("cuenta") String numero);

}
