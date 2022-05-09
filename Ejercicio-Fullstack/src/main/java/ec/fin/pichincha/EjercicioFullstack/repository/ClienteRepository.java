package ec.fin.pichincha.EjercicioFullstack.repository;

import ec.fin.pichincha.EjercicioFullstack.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente,Integer>  {



}
