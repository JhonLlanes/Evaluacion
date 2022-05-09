package ec.fin.pichincha.EjercicioFullstack.service;

import ec.fin.pichincha.EjercicioFullstack.model.Cliente;
import ec.fin.pichincha.EjercicioFullstack.model.Persona;
import ec.fin.pichincha.EjercicioFullstack.repository.ClienteRepository;
import ec.fin.pichincha.EjercicioFullstack.repository.PersonaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CuentaServicetest {

    private ClienteRepository clienteRepository;

    private PersonaRepository personaRepository;

    public CuentaServicetest(ClienteRepository clienteRepository, PersonaRepository personaRepository) {
        this.clienteRepository = clienteRepository;
        this.personaRepository = personaRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<?> create() {
        try {
            Persona persona = new Persona(null,"jllanes","M",34,"091104587081","0990868789");
            Cliente cliente = new Cliente(null,"123","true", personaRepository.save(persona));
            log.info("INGRESA");
            return ResponseEntity.ok( clienteRepository.save(cliente));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("0001");
        }
    }

    @GetMapping("/cliente")
    public List<Cliente> findall(){
        try{
            return clienteRepository.findAll();
        }catch (Exception e) {
          log.info(e.getLocalizedMessage());
        }
        return null;
    }
}
