/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.modelo;

import br.com.foxinline.enums.TipoFinanceiro;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

/**
 *
 * @author roniere
 */
@Entity
public class Planejamento implements Serializable {

    @Id
    @SequenceGenerator(sequenceName = "seq_planejamento", name = "seq_planejamento", allocationSize = 1, initialValue = 6)
    @GeneratedValue(generator = "seq_planejamento", strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * RECEITA/DESPESA
     */
    @Enumerated(EnumType.STRING)
    private TipoFinanceiro tipoFinanceiro;
    @ManyToOne
    private Secretaria secretaria;
    @OneToOne
    private TipoReceitaDespesa tipoReceitaDespesa;
    @OneToOne
    private TipoReceitaDespesa tipoReceitaDespesaSub;
    @Column(scale= 4, precision=19)
    @DecimalMin( value = "0.00" )
    @Digits( fraction = 4, integer = 15 )
    private BigDecimal valor;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date competencia;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createat;
    @OneToOne
    private Usuario usuario;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateat;
    @OneToOne
    private Usuario usuarioUpdate;

    public Date getCompetencia() {
        return competencia;
    }

    public void setCompetencia(Date competencia) {
        this.competencia = competencia;
    }

    public Date getCreateat() {
        return createat;
    }

    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Secretaria getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(Secretaria secretaria) {
        this.secretaria = secretaria;
    }

    public TipoFinanceiro getTipoFinanceiro() {
        return tipoFinanceiro;
    }

    public void setTipoFinanceiro(TipoFinanceiro tipoFinanceiro) {
        this.tipoFinanceiro = tipoFinanceiro;
    }

    public TipoReceitaDespesa getTipoReceitaDespesa() {
        return tipoReceitaDespesa;
    }

    public void setTipoReceitaDespesa(TipoReceitaDespesa tipoReceitaDespesa) {
        this.tipoReceitaDespesa = tipoReceitaDespesa;
    }

    public TipoReceitaDespesa getTipoReceitaDespesaSub() {
        return tipoReceitaDespesaSub;
    }

    public void setTipoReceitaDespesaSub(TipoReceitaDespesa tipoReceitaDespesaSub) {
        this.tipoReceitaDespesaSub = tipoReceitaDespesaSub;
    }

    public Date getUpdateat() {
        return updateat;
    }

    public void setUpdateat(Date updateat) {
        this.updateat = updateat;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioUpdate() {
        return usuarioUpdate;
    }

    public void setUsuarioUpdate(Usuario usuarioUpdate) {
        this.usuarioUpdate = usuarioUpdate;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    
}