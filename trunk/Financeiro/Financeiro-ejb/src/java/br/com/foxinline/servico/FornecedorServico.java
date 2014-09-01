/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.servico;

import br.com.foxinline.generico.ServicoGenerico;
import br.com.foxinline.modelo.Fornecedor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author roniere
 */
@Stateless
public class FornecedorServico extends ServicoGenerico<Fornecedor> {

    public FornecedorServico() {
        super(Fornecedor.class);
    }

    public List<Fornecedor> pesquisa(Fornecedor fornecedor) {
        String str = "SELECT f FROM Fornecedor f WHERE ";

        if (fornecedor.getNome() != null && !fornecedor.getNome().isEmpty()) {
            str += " lower(f.nome) like lower(:nome) and ";
        }
        if (fornecedor.getCnpjcpf() != null && !fornecedor.getCnpjcpf().isEmpty()) {
            str += " lower(f.cnpjcpf) like lower(:cnpjcpf) and ";
        }
        str += "1 = 1 ORDER BY f.nome ASC";
        
        Query query = getEntityManager().createQuery(str);

        if (fornecedor.getNome() != null && !fornecedor.getNome().isEmpty()) {
            query.setParameter("nome", "%"+fornecedor.getNome()+"%");
        }
        if (fornecedor.getCnpjcpf() != null && !fornecedor.getCnpjcpf().isEmpty()) {
            query.setParameter("cnpjcpf", "%"+fornecedor.getCnpjcpf()+"%");
        }

        return query.getResultList();
    }
}
