package br.com.foxinline.generico;

import br.com.foxinline.modelo.Fornecedor;
import br.com.foxinline.modelo.Secretaria;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.history.HistoryPolicy;
import org.eclipse.persistence.sessions.Session;

/**
 * Classe de auditoria, deve ser declarada no persistence.xml na propriedade
 * eclipselink.session.customizer;
 * @author roniere
 */
public class Auditoria implements SessionCustomizer {

    private static final String DATA_INICIAL = "data_inicial";
    private static final String DATA_FINAL = "data_final";
    protected List<Class<?>> classesAuditadas = new ArrayList<Class<?>>();

    public Auditoria() {
        setClassesAuditadas();
    }

    /**
     * Adiciona as classes na lista de classes auditadas;
     */
    private void setClassesAuditadas() {
        classesAuditadas.add(Secretaria.class);
        classesAuditadas.add(Fornecedor.class);
    }

    @SuppressWarnings("unchecked")
    public void customize(Session session) throws Exception {
        for (Class<?> clazz : classesAuditadas) {
            ClassDescriptor sourceDescriptor = session.getDescriptor(clazz);
            customize(sourceDescriptor);
        }
    }

    /**
     * Verifica se houveram alterações e salva as datas de alterações na 
     * tabela historico da classe de referencia
     * @param sourceDescriptor
     * @throws Exception 
     */
    private void customize(ClassDescriptor sourceDescriptor) throws Exception {

        String sourceTableName = (String) sourceDescriptor.getTableNames().get(
                sourceDescriptor.getTableNames().size() - 1);
        String historyTableName = sourceTableName + "_hist";

        HistoryPolicy policy = new HistoryPolicy();
        policy.addHistoryTableName(sourceTableName, historyTableName);
        policy.addStartFieldName(DATA_INICIAL);
        policy.addEndFieldName(DATA_FINAL);
        sourceDescriptor.setHistoryPolicy(policy);
    }
}
