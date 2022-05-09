package ec.fin.pichincha.EjercicioFullstack.repository;

import ec.fin.pichincha.EjercicioFullstack.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

}
