/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.servico;

import br.com.foxinline.generico.ServicoGenerico;
import br.com.foxinline.modelo.TipoDesconto;
import javax.ejb.Stateless;

/**
 *
 * @author roniere
 */
@Stateless
public class TipoDescontoServico extends ServicoGenerico<TipoDesconto>{

    public TipoDescontoServico() {
        super(TipoDesconto.class);
    }
    
}
