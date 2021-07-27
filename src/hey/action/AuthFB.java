package hey.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import hey.model.eVotingBean;
import java.rmi.RemoteException;


public class AuthFB extends ActionSupport implements SessionAware{
    private Map<String, Object> session;

    private String url=null;
    private String code=null;

    public String execute() throws RemoteException {
        this.getVotingBean().authFacebook(code);
        return SUCCESS;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
