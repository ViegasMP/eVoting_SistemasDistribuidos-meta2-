package hey.action;

import rmiserver.classes.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import hey.model.eVotingBean;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class LoadElectionsAction extends ActionSupport implements SessionAware {
    private Map<String, Object> session;
    private String election = null;
    private String local = null;

    public String execute(){

        if(election!=null && !election.equals("")){
            try {
                for(Election ele:this.getVotingBean().getElections()) {
                    if(election.equals(ele.getTitle())){
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String ini = sdf.format(ele.getStart().getTime());
                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String fim = sdf.format(ele.getEnd().getTime());
                        local="title:\t\t\t"+ele.getTitle()+"\ndescription:\t"+ele.getDescription()+"\nbegins:\t\t"+ini
                                +"\nends:\t\t"+fim;
                        return SUCCESS;
                    }
                }

            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return SUCCESS;
        }

        return ERROR;
    }

    public String getElection() {
        return election;
    }

    public void setElection(String election) {
        this.election = election;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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
