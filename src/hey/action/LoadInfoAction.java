/**
 * Raul Barbosa 2014-11-07
 */
package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Map;
import hey.model.eVotingBean;

public class LoadInfoAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 4L;
	private Map<String, Object> session;
	private String username = "lala";
	private ArrayList<String> list = null;

	@Override
	public String execute() {
		// any username is accepted without confirmation (should check using RMI)

		if(this.username != null) {
			this.getVotingBean().setUsername(this.list);
			return SUCCESS;
		}
		else
			return ERROR;
	}
	
	public void setUsername(ArrayList<String> list) {
		this.list = list; // will you sanitize this input? maybe use a prepared statement?
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
