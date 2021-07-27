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



public class AltGeneralCEAction extends ActionSupport implements SessionAware{
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;

    private String title=null;
    private String description=null;
    private String dateini=null;
    private String datend=null;
    private String name=null;



    public String execute() throws RemoteException, MalformedURLException, NotBoundException {
        Election newElection;
        if (name != null && !name.equals("")) {
            try {
                for (Election ele : this.getVotingBean().getElectionslist()) {
                    if (name.toLowerCase().equals(ele.getTitle())) {

                        newElection = this.getVotingBean().removeElection(name);
                        Calendar start = Calendar.getInstance();
                        SimpleDateFormat ini = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
                        Calendar end = Calendar.getInstance();
                        SimpleDateFormat fim = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
                        try {
                            start.setTime(ini.parse(dateini));
                            end.setTime(fim.parse(datend));
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return ERROR;
                        }
                        ele.setDescription(description);
                        ele.setTitle(title);
                        ele.setStart(start);
                        ele.setEnd(end);
                        this.getVotingBean().AddElection(ele);
                        return SUCCESS;
                    }
                }

            }
            catch (RemoteException e){
                e.printStackTrace();
            }
        }
        return ERROR;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateini() {
        return dateini;
    }

    public void setDateini(String dateini) {
        this.dateini = dateini;
    }

    public String getDatend() {
        return datend;
    }

    public void setDatend(String datend) {
        this.datend = datend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
