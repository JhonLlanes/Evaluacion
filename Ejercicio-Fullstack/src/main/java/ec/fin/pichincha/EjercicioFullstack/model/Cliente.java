package ec.fin.pichincha.EjercicioFullstack.model;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author John
 */
@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByClieId", query = "SELECT c FROM Cliente c WHERE c.clieId = :clieId")
    , @NamedQuery(name = "Cliente.findByContrasena", query = "SELECT c FROM Cliente c WHERE c.contrasena = :contrasena")
    , @NamedQuery(name = "Cliente.findByEstado", query = "SELECT c FROM Cliente c WHERE c.estado = :estado")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clie_id")
    private Integer clieId;
    @Basic(optional = false)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "persona_per_id", referencedColumnName = "per_id")
    @ManyToOne(optional = false)
    private Persona personaPerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteClieId")
    private List<Cuenta> cuentaList;

    public Cliente() {
    }

    public Cliente(Integer clieId) {
        this.clieId = clieId;
    }

    public Cliente(Integer clieId, String contrasena, String estado) {
        this.clieId = clieId;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public Integer getClieId() {
        return clieId;
    }

    public void setClieId(Integer clieId) {
        this.clieId = clieId;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Persona getPersonaPerId() {
        return personaPerId;
    }

    public void setPersonaPerId(Persona personaPerId) {
        this.personaPerId = personaPerId;
    }


    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    public Cliente(Integer clieId, String contrasena, String estado, Persona personaPerId) {
        this.clieId = clieId;
        this.contrasena = contrasena;
        this.estado = estado;
        this.personaPerId = personaPerId;

    }
}
