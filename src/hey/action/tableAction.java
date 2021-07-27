package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Map;
import hey.model.eVotingBean;


public class tableAction extends ActionSupport implements SessionAware {
    private Map<String, Object> session;
    private String creatTable=null;
    private String electionTable=null;

    public String execute() {
        if (creatTable!=null && !creatTable.equals("") && electionTable!=null && !electionTable.equals("")) {
            if (this.getVotingBean().creaTable(creatTable, electionTable))
                return SUCCESS;
        }
        return ERROR;
    }

    public String getCreatTable() {
        return creatTable;
    }

    public void setCreatTable(String creatTable) {
        this.creatTable = creatTable;
    }

    public String getElectionTable() {
        return electionTable;
    }

    public void setElectionTable(String electionTable) {
        this.electionTable = electionTable;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public eVotingBean getVotingBean() {
        if(!session.containsKey("votingBean"))
            this.setVotingBean(new eVotingBean());
        return (eVotingBean) session.get("votingBean");
    }

    public void setVotingBean(eVotingBean votingBean) {
        this.session.put("votingBean", votingBean);
    }

}
