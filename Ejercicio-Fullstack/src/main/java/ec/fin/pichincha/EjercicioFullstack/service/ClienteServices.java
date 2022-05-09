package ec.fin.pichincha.EjercicioFullstack.service;

import ec.fin.pichincha.EjercicioFullstack.model.Cliente;
import ec.fin.pichincha.EjercicioFullstack.model.FrameOut;
import ec.fin.pichincha.EjercicioFullstack.model.Persona;
import ec.fin.pichincha.EjercicioFullstack.repository.ClienteRepository;
import ec.fin.pichincha.EjercicioFullstack.repository.PersonaRepository;
import ec.fin.pichincha.EjercicioFullstack.service.interfaceservices.IServiceCliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/api/clientes")
public class ClienteServices implements IServiceCliente {

    private ClienteRepository clienteRepository;
    private PersonaRepository personaRepository;

    public ClienteServices(ClienteRepository clienteRepository, PersonaRepository personaRepository) {
        this.clienteRepository = clienteRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public ResponseEntity<?> crear(Cliente cliente) {

        try {
            Persona persona = cliente.getPersonaPerId();
            cliente.setPersonaPerId(personaRepository.save(persona));
            clienteRepository.save(cliente);
            return ResponseEntity.ok(cliente);
        }catch (Exception e){
            log.info(e.getLocalizedMessage());
            FrameOut frameOut = new FrameOut("999", e.getLocalizedMessage());
            return ResponseEntity.ok(frameOut);
        }

    }

    @Override
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    @Override
    public ResponseEntity<?> eliminar(Cliente cliente) {
        clienteRepository.deleteAllById(Collections.singleton(cliente.getClieId()));
        return null;
    }

    @Override
    public ResponseEntity<?> actualizar(Cliente cliente) {
       try {

           if (cliente.getClieId() == null) {
               log.warn("NO EXISTE EL TIPO DE LIBRO");
               return ResponseEntity.badRequest().build();
           }
           Persona persona = cliente.getPersonaPerId();
           cliente.setPersonaPerId(personaRepository.save(persona));
           return ResponseEntity.ok(clienteRepository.save(cliente));

       }catch (Exception e ){
           FrameOut frameOut = new FrameOut("999", e.getLocalizedMessage());
           return ResponseEntity.ok(frameOut);
       }

    }
}
