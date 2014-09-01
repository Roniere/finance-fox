/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.modelo;

import br.com.foxinline.generico.EntidadeGenerica;
import br.com.foxinline.enums.TipoDocumento;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author roniere
 */
@Entity
public class Fornecedor  extends EntidadeGenerica implements Serializable {

    private String nome;
    private String responsavel;
    private String cnpjcpf;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    
    @OneToMany(cascade= CascadeType.ALL)
    private List<ContaBancaria> contaBancarias;

    private Boolean efetivo;

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Boolean getEfetivo() {
        return efetivo;
    }

    public void setEfetivo(Boolean efetivo) {
        this.efetivo = efetivo;
    }
    
    public String getCnpjcpf() {
        return cnpjcpf;
    }

    public void setCnpjcpf(String cnpjcpf) {
        this.cnpjcpf = cnpjcpf;
    }

    public List<ContaBancaria> getContaBancarias() {
        return contaBancarias;
    }

    public void setContaBancarias(List<ContaBancaria> contaBancarias) {
        this.contaBancarias = contaBancarias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    
    
    
}
