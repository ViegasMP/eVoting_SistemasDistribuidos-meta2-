package hey.action;

import rmiserver.classes.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import hey.model.eVotingBean;


public class VoteAction extends ActionSupport implements SessionAware{
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;

    private String election = null;
    private String candidatos = null;
    private String vote = null;


    public String execute() throws Exception {
        String cc=getVotingBean().getCc();
        if(election!=null && !election.equals("")) {
            try {
                for (Election ele : this.getVotingBean().getElections()) {
                    if (election.equals(ele.getTitle())) {
                        StringBuffer sb = new StringBuffer();

                        for (String s : ele.getCandidateNamesList()) {
                            sb.append(s);
                            sb.append("\n");
                        }
                        candidatos = sb.toString();
                        if(ele.getCandidateNamesList().contains(vote)) {
                            if(this.getVotingBean().vote(election,vote));
                                return SUCCESS;
                        }
                    }
                }
                return LOGIN;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return ERROR;
    }

    public String getElection() {
        return election;
    }

    public void setElection(String election) {
        this.election = election;
    }

    public String getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(String candidatos) {
        this.candidatos = candidatos;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
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
