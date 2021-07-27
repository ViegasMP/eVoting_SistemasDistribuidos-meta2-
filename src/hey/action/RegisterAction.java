/**
 * Raul Barbosa 2014-11-07
 */
package hey.action;

import rmiserver.classes.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.util.Map;
import hey.model.eVotingBean;

public class RegisterAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;

    private String name = null;
    private String phoneNumber = null;
    private String address = null;
    private String password = null;
    private String department = null;
    private String idCardNumber = null;
    private String expirationDate = null;
    private String occupation = null;

    @Override
    public String execute() throws RemoteException {
        if (name != null && phoneNumber != null && address != null && password != null && department!= null && idCardNumber != null && expirationDate != null) {
            Department dep = this.getVotingBean().getDepartment(department);
            if (dep != null) {
                User novapessoa;
                switch (occupation) {
                    case "student":
                        novapessoa = new Student(name, phoneNumber, address, password, dep, idCardNumber, expirationDate);
                        break;
                    case "teacher":
                        novapessoa = new Teacher(name, phoneNumber, address, password, dep, idCardNumber, expirationDate);
                        break;
                    case "employee":
                        novapessoa = new Employee(name, phoneNumber, address, password, dep, idCardNumber, expirationDate);
                        break;
                    default:
                        return ERROR;
                }
                if (this.getVotingBean().register(novapessoa))
                    return SUCCESS;

            } else
                return ERROR;
        }
        return ERROR;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getIdCardNumber() { return idCardNumber; }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}

