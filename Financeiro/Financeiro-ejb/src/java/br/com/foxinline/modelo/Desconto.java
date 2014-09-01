/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author roniere
 */
@Entity
public class Desconto  implements Serializable {

    @Id
    @SequenceGenerator(sequenceName = "seq_desconto", name = "seq_desconto", allocationSize = 1, initialValue = 6)
    @GeneratedValue(generator = "seq_desconto", strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private TipoDesconto tipoDesconto;
    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDesconto getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDesconto tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    
    
}
