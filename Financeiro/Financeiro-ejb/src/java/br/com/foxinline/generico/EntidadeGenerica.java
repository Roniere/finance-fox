/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.generico;

import br.com.foxinline.modelo.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Classe genérica na qual todas as entidades do projeto devem herdar, 
 * mantém o controle de modificação e o versionamento
 * @author roniere
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class EntidadeGenerica implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    /**
     * Quando false, o objeto encontra-se excluido/cancelado. 
     * Caso contrário encontra-se ativo
     */
    private Boolean ativo;
    /**
     * Controle de versionamento da entidade, faz o controle de alterações
     * concorrentes
     */
    @Version
    private int versao;
    /**
     * Timestamp da persistencia do objeto
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataCriacao;
    /**
     * Timestamp da alteração do objeto
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
    /**
     * Usuário do sistema que persistiu o objeto
     */
    @OneToOne
    private Usuario usuarioCriacao;
    /**
     * Usuário do sistema que atualizou o objeto
     */
    @OneToOne
    private Usuario usuarioAtualizacao;

    /**
     * Construtor padrão, no qual inicializa {@link ativo} como true
     */
    public EntidadeGenerica() {
        this.ativo = true;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getUsuarioAtualizacao() {
        return usuarioAtualizacao;
    }

    public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
        this.usuarioAtualizacao = usuarioAtualizacao;
    }

    public Usuario getUsuarioCriacao() {
        return usuarioCriacao;
    }

    public void setUsuarioCriacao(Usuario usuarioCriacao) {
        this.usuarioCriacao = usuarioCriacao;
    }
    
}
