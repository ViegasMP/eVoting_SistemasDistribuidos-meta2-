package hey.action;

import java.util.Map;
import rmiserver.classes.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import hey.model.eVotingBean;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;


public class LoginAction extends ActionSupport implements SessionAware{
    private Map<String, Object> session;
    private String cc = null;
    private String pass = null;

    public String execute() {

        if(cc!=null && !pass.equals("")) {
            try {
                for (User util : this.getVotingBean().getUsers()) {
                    if (cc.equals(util.getIdCardNumber()) && pass.equals(util.getPassword())) {
                        this.getVotingBean().setCc(cc);
                        this.session.put("cc", cc);
                        this.session.put("loggedin", true);
                        return SUCCESS;
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return ERROR;
    }


    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
