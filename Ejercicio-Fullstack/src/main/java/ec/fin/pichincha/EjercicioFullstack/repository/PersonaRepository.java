package ec.fin.pichincha.EjercicioFullstack.repository;

import ec.fin.pichincha.EjercicioFullstack.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {
}
