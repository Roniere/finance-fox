/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author roniere
 */
@Entity
public class ContaBancaria  implements Serializable {

    @Id
    @SequenceGenerator(sequenceName = "seq_contabancaria", name = "seq_contabancaria", allocationSize = 1, initialValue = 6)
    @GeneratedValue(generator = "seq_contabancaria", strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Banco banco;
    private String conta;
    private String agencia;
    private String operacao;

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
    
    
    
    
}
