package hey.action;

import java.util.Map;
import rmiserver.classes.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import hey.model.eVotingBean;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;


public class LoginFB extends ActionSupport implements SessionAware{
    private Map<String, Object> session;

    private String authUrl=null;

    public String execute() throws RemoteException {
        authUrl=this.getVotingBean().addFacebook();
        System.out.println(authUrl);
        if(authUrl != null)
            return SUCCESS;
        else
            return ERROR;
    }


    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
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
