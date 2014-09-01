/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.modelo;

import br.com.foxinline.enums.EstadoDespesa;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

/**
 *
 * @author roniere
 */
@Entity
public class Despesa  implements Serializable {

    @Id
    @SequenceGenerator(sequenceName = "seq_despesa", name = "seq_despesa", allocationSize = 1, initialValue = 6)
    @GeneratedValue(generator = "seq_despesa", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @OneToOne
    private Fornecedor fornecedor;
    private String objeto;
    
    @Column(scale= 4, precision=19)
    @DecimalMin( value = "0.00" )
    @Digits( fraction = 4, integer = 15 )
    private BigDecimal valorBruto;
    
    @OneToOne
    private Secretaria secretaria;
    
    @OneToMany(cascade= CascadeType.ALL)
    private List<Desconto> descontos;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAto;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createAt;
    
    @OneToOne
    private Usuario usuario;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateAt;
    
    @OneToOne
    private Usuario usuarioAtualizou;
    
    @OneToOne
    private TipoReceitaDespesa tipoReceitaDespesa;
    
    
    @ManyToOne
    private TipoReceitaDespesa tipoReceitaDespesaSub;
    
    /**
     * CONTRATO
     */
    private String processo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataProcesso;
    
    private String empenho;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEmpenho;
    
    private String fonte;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataPagamento;
    
    @Enumerated(EnumType.STRING)
    private EstadoDespesa estadoDespesa;
    
    private Boolean status;

    public TipoReceitaDespesa getTipoReceitaDespesaSub() {
        return tipoReceitaDespesaSub;
    }

    public void setTipoReceitaDespesaSub(TipoReceitaDespesa tipoReceitaDespesaSub) {
        this.tipoReceitaDespesaSub = tipoReceitaDespesaSub;
    }

    
    public EstadoDespesa getEstadoDespesa() {
        return estadoDespesa;
    }

    public void setEstadoDespesa(EstadoDespesa estadoDespesa) {
        this.estadoDespesa = estadoDespesa;
    }
    
    
    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getDataAto() {
        return dataAto;
    }

    public void setDataAto(Date dataAto) {
        this.dataAto = dataAto;
    }

    public Date getDataEmpenho() {
        return dataEmpenho;
    }

    public void setDataEmpenho(Date dataEmpenho) {
        this.dataEmpenho = dataEmpenho;
    }

    public Date getDataProcesso() {
        return dataProcesso;
    }

    public void setDataProcesso(Date dataProcesso) {
        this.dataProcesso = dataProcesso;
    }

    public List<Desconto> getDescontos() {
        return descontos;
    }

    public void setDescontos(List<Desconto> descontos) {
        this.descontos = descontos;
    }

    public String getEmpenho() {
        return empenho;
    }

    public void setEmpenho(String empenho) {
        this.empenho = empenho;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getProcesso() {
        return processo;
    }

    public void setProcesso(String processo) {
        this.processo = processo;
    }

    public Secretaria getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(Secretaria secretaria) {
        this.secretaria = secretaria;
    }

    public TipoReceitaDespesa getTipoReceitaDespesa() {
        return tipoReceitaDespesa;
    }

    public void setTipoReceitaDespesa(TipoReceitaDespesa tipoReceitaDespesa) {
        this.tipoReceitaDespesa = tipoReceitaDespesa;
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

    public Usuario getUsuarioAtualizou() {
        return usuarioAtualizou;
    }

    public void setUsuarioAtualizou(Usuario usuarioAtualizou) {
        this.usuarioAtualizou = usuarioAtualizou;
    }

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }

    
    
}
