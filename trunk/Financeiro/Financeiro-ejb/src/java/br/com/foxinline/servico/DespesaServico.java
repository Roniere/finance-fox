/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.servico;

import br.com.foxinline.generico.ServicoGenerico;
import br.com.foxinline.enums.EstadoDespesa;
import br.com.foxinline.modelo.Despesa;
import br.com.foxinline.modelo.Secretaria;
import br.com.foxinline.modelo.TipoReceitaDespesa;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author roniere
 */
@Stateless
public class DespesaServico extends ServicoGenerico<Despesa> implements Serializable {

    public DespesaServico() {
        super(Despesa.class);
    }

    public List<Despesa> despesasPorEstado(EstadoDespesa estadoDespesa) {
        String str = "SELECT d FROM Despesa d WHERE d.estadoDespesa = :estadoDespesa ORDER BY d.id ASC;";
        Query query = getEntityManager().createQuery(str);
        query.setParameter("estadoDespesa", estadoDespesa);
        return (List<Despesa>) query.getResultList();
    }

    public List<Despesa> pesquisarDespesa(Despesa despesa) {
        String sql = "SELECT d FROM Despesa d ";

        if (despesa.getFornecedor() != null) {
            sql += " JOIN d.fornecedor f";
        }

        if (despesa.getTipoReceitaDespesa() != null) {
            sql += " JOIN d.tipoReceitaDespesa trd";
        }

        if (despesa.getSecretaria() != null) {
            sql += " JOIN d.secretaria sec";
        }

        sql += " WHERE ";

        if (despesa.getFornecedor() != null) {
            sql += " f.id = :fornecedor and ";
        }

        if (despesa.getTipoReceitaDespesa() != null) {
            sql += " trd.id = :tiporeceitadespesa and ";
        }

        if (despesa.getSecretaria() != null) {
            sql += " sec.id = :secretaria and ";
        }

        if (despesa.getProcesso() != null && !despesa.getProcesso().isEmpty()) {
            sql += " d.processo like :processo and ";
        }
        if (despesa.getDataProcesso() != null) {
            sql += " d.dataProcesso = :dataprocesso and ";
        }
        if (despesa.getEmpenho() != null && !despesa.getEmpenho().isEmpty()) {
            sql += " d.empenho = :empenho and ";
        }
        if (despesa.getDataEmpenho() != null) {
            sql += " d.dataempenho = :dataempenho and ";
        }
        if (despesa.getDataPagamento() != null) {
            sql += " d.dataPagamento = :pagamento and ";
        }

        sql += " 1 = 1";
        Query query = getEntityManager().createQuery(sql);

        if (despesa.getFornecedor() != null) {
            query.setParameter("fornecedor", despesa.getFornecedor().getId());
        }

        if (despesa.getTipoReceitaDespesa() != null) {
            query.setParameter("tiporeceitadespesa", despesa.getTipoReceitaDespesa().getId());
        }

        if (despesa.getSecretaria() != null) {
            query.setParameter("secretaria", despesa.getSecretaria().getId());
        }

        if (despesa.getDataPagamento() != null) {
            query.setParameter("pagamento", despesa.getDataPagamento());
        }
        if (despesa.getProcesso() != null && !despesa.getProcesso().isEmpty()) {
            query.setParameter("processo", despesa.getProcesso());
        }
        if (despesa.getDataProcesso() != null) {
            query.setParameter("dataprocesso", despesa.getDataProcesso());
        }
        if (despesa.getEmpenho() != null && !despesa.getEmpenho().isEmpty()) {
            query.setParameter("empenho", despesa.getEmpenho());
        }
        if (despesa.getDataEmpenho() != null) {
            query.setParameter("dataempenho", despesa.getDataEmpenho());
        }
        System.err.println(sql);
        return (List<Despesa>) query.getResultList();
    }
    
    
    public BigDecimal getValorDespesaPorDias(Date dataInicial, Date dataFinal, TipoReceitaDespesa tipoReceitaDespesa,
            TipoReceitaDespesa tipoReceitaDespesaSub, Secretaria secretaria) {

        String sql = "SELECT SUM(d.valorBruto) FROM Despesa d";

        if (tipoReceitaDespesa != null) {
            sql += " JOIN d.tipoReceitaDespesa tipo ";
        }

        if (tipoReceitaDespesaSub != null) {
            sql += " JOIN d.tipoReceitaDespesaSub tiposub ";
        }
        
        if(secretaria != null ){
            sql += " JOIN d.secretaria s";
        }

        sql += " WHERE ";

        if (tipoReceitaDespesa != null) {
            sql += "tipo.id = :tipo and ";
        }

        if (tipoReceitaDespesaSub != null) {
            sql += " tiposub.id = :tiposub and ";
        }
        
        if(secretaria != null ){
            sql += " s.id = :secretaria and ";
        }

        sql += " d.dataPagamento BETWEEN :datainicial AND :datafinal";

        Query query = getEntityManager().createQuery(sql);
        if (tipoReceitaDespesa != null) {
            query.setParameter("tipo", tipoReceitaDespesa.getId());
        }
        query.setParameter("datainicial", dataInicial);
        query.setParameter("datafinal", dataFinal);

        if (tipoReceitaDespesaSub != null) {
            query.setParameter("tiposub", tipoReceitaDespesaSub.getId());
        }
        if(secretaria != null ){
            query.setParameter("secretaria", secretaria.getId());
        }


        return (BigDecimal) (query.getSingleResult() == null ? BigDecimal.ZERO : query.getSingleResult());
    }
}
