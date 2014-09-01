/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.modelo;

import br.com.foxinline.enums.TipoFinanceiro;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author roniere
 */
@Entity
public class TipoReceitaDespesa implements Serializable {
    @OneToOne(mappedBy = "tipoReceitaDespesaSub")
    private Planejamento planejamento;

    @Id
    @SequenceGenerator(sequenceName = "seq_tiporeceitadespesa", name = "seq_tiporeceitadespesa", allocationSize = 1)
    @GeneratedValue(generator = "seq_tiporeceitadespesa", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoFinanceiro tipoFinanceiro;
    @OneToMany
    private List<TipoReceitaDespesa> tipoReceitaDespesas;
    
    private Boolean principal; 
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createAt;
    
    @OneToOne
    private Usuario usuario;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateAt;
    
    @OneToOne
    private Usuario usuarioUpdate;

    
    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
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

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoFinanceiro getTipoFinanceiro() {
        return tipoFinanceiro;
    }

    public void setTipoFinanceiro(TipoFinanceiro tipoFinanceiro) {
        this.tipoFinanceiro = tipoFinanceiro;
    }

    public List<TipoReceitaDespesa> getTipoReceitaDespesas() {
        return tipoReceitaDespesas;
    }

    public void setTipoReceitaDespesas(List<TipoReceitaDespesa> tipoReceitaDespesas) {
        this.tipoReceitaDespesas = tipoReceitaDespesas;
    }

    @Override
    public String toString() {
        return "TipoReceitaDespesa{" + "id=" + id + ", nome=" + nome + ", tipoFinanceiro=" + tipoFinanceiro + ", tipoReceitaDespesas=" + tipoReceitaDespesas + ", principal=" + principal + ", createAt=" + createAt + ", usuario=" + usuario + ", updateAt=" + updateAt + ", usuarioUpdate=" + usuarioUpdate + '}';
    }
    
    
}
