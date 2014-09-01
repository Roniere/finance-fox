/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.modelo;

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
public class Receita implements Serializable {

    @Id
    @SequenceGenerator(sequenceName = "seq_receita", name = "seq_receita", allocationSize = 1, initialValue = 6)
    @GeneratedValue(generator = "seq_receita", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @ManyToOne
    private TipoReceitaDespesa tipoReceitaDespesaCategoria;
    
    @ManyToOne
    private TipoReceitaDespesa tipoReceitaDespesaSubCategoria;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createAt;
    
    @ManyToOne
    private Usuario usuario;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateAt;
    
    @ManyToOne
    private Usuario usuarioUpdate;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataReceita;
    
    @Column(scale= 4, precision=19)
    @DecimalMin( value = "0.00" )
    @Digits( fraction = 4, integer = 15 )
    private BigDecimal valor;

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getDataReceita() {
        return dataReceita;
    }

    public void setDataReceita(Date dataReceita) {
        this.dataReceita = dataReceita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoReceitaDespesa getTipoReceitaDespesaCategoria() {
        return tipoReceitaDespesaCategoria;
    }

    public void setTipoReceitaDespesaCategoria(TipoReceitaDespesa tipoReceitaDespesaCategoria) {
        this.tipoReceitaDespesaCategoria = tipoReceitaDespesaCategoria;
    }

    public TipoReceitaDespesa getTipoReceitaDespesaSubCategoria() {
        return tipoReceitaDespesaSubCategoria;
    }

    public void setTipoReceitaDespesaSubCategoria(TipoReceitaDespesa tipoReceitaDespesaSubCategoria) {
        this.tipoReceitaDespesaSubCategoria = tipoReceitaDespesaSubCategoria;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
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

    @Override
    public String toString() {
        return "Receita{" + "id=" + id + ", tipoReceitaDespesaCategoria=" + tipoReceitaDespesaCategoria + ", tipoReceitaDespesaSubCategoria=" + tipoReceitaDespesaSubCategoria + ", createAt=" + createAt + ", usuario=" + usuario + ", updateAt=" + updateAt + ", usuarioUpdate=" + usuarioUpdate + ", dataReceita=" + dataReceita + ", valor=" + valor + '}';
    }
    
    
    
}
