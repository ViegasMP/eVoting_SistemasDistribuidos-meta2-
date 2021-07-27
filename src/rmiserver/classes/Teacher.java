package rmiserver.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Teacher extends User implements Serializable {

    public Teacher(String name, String phoneNumber, String address, String password, Department department, String idCardNumber, String expirationDate) {
        super(name, phoneNumber, address, password, department, idCardNumber, expirationDate);
    }

    public void addUserToList(ArrayList<User> list, Department dep){
        list.add(this);
    }

    public void addGeneralVoter(GeneralCouncil generalCounsel) {

    }

    public void removeGeneralVoter(GeneralCouncil generalCounsel, Vote vote) {

    }

    public void addGeneralVote(GeneralCouncil generalCounsel, Vote vote) {

    }

    public ArrayList<CandidateList> getGeneralCandidateList(GeneralCouncil generalCounsel) {
        return null;
    }


    public String toString() {
        return "Teacher{}";
    }

}
