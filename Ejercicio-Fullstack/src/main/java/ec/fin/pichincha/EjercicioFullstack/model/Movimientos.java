package ec.fin.pichincha.EjercicioFullstack.model;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "movimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientos.findAll", query = "SELECT m FROM Movimientos m")
    , @NamedQuery(name = "Movimientos.findByMovId", query = "SELECT m FROM Movimientos m WHERE m.movId = :movId")
    , @NamedQuery(name = "Movimientos.findByFecha", query = "SELECT m FROM Movimientos m WHERE m.fecha = :fecha")
    , @NamedQuery(name = "Movimientos.findByTipoMovimiento", query = "SELECT m FROM Movimientos m WHERE m.tipoMovimiento = :tipoMovimiento")
    , @NamedQuery(name = "Movimientos.findByValor", query = "SELECT m FROM Movimientos m WHERE m.valor = :valor")
    , @NamedQuery(name = "Movimientos.findBySaldo", query = "SELECT m FROM Movimientos m WHERE m.saldo = :saldo")})
public class Movimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mov_id")
    private Integer movId;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "saldo")
    private BigDecimal saldo;
    @JoinColumn(name = "cuenta_cuen_id", referencedColumnName = "cuen_id")
    @ManyToOne(optional = false)
    private Cuenta cuentaCuenId;

    public Movimientos() {
    }

    public Movimientos(Integer movId) {
        this.movId = movId;
    }

    public Movimientos(Integer movId, Date fecha, String tipoMovimiento, BigDecimal valor, BigDecimal saldo,Cuenta cuentaCuenId ) {
        this.movId = movId;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
        this.cuentaCuenId = cuentaCuenId;
    }

    public Integer getMovId() {
        return movId;
    }

    public void setMovId(Integer movId) {
        this.movId = movId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Cuenta getCuentaCuenId() {
        return cuentaCuenId;
    }

    public void setCuentaCuenId(Cuenta cuentaCuenId) {
        this.cuentaCuenId = cuentaCuenId;
    }

}
