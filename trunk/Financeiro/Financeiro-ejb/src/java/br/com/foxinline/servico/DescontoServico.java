/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.servico;

import br.com.foxinline.generico.ServicoGenerico;
import br.com.foxinline.modelo.Desconto;
import javax.ejb.Stateless;

/**
 *
 * @author roniere
 */
@Stateless
public class DescontoServico  extends ServicoGenerico<Desconto> {

    public DescontoServico() {
        super(Desconto.class);
    }
    
    
}
