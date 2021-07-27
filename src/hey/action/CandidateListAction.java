package hey.action;

import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import hey.model.eVotingBean;


public class CandidateListAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;

    private String lista = null;
    private String eleicao = null;

    public String execute() {
        if (lista!=null && eleicao!=null && !lista.equals("") && !eleicao.equals("")){
            if (this.getVotingBean().addList(eleicao,lista)) {
                return SUCCESS;
            }
        }
        return ERROR;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public String getEleicao() {
        return eleicao;
    }

    public void setEleicao(String eleicao) {
        this.eleicao = eleicao;
    }

    public eVotingBean getVotingBean() {
        if(!session.containsKey("votingBean"))
            this.setVotingBean(new eVotingBean());
        return (eVotingBean) session.get("votingBean");
    }

    public void setVotingBean(eVotingBean votingBean) {
        this.session.put("votingBean", votingBean);
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}