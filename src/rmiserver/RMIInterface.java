package rmiserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import rmiserver.classes.*;
import rmiserver.multicast.*;

public interface RMIInterface extends Remote {
    Boolean register(User user) throws java.rmi.RemoteException;
    public ArrayList<Department> getDepartmentList() throws java.rmi.RemoteException;
    public void addDepartment(Department dep) throws java.rmi.RemoteException;
    public ArrayList<User> getUserList() throws java.rmi.RemoteException;

    ArrayList<Election> getElectionsList()throws java.rmi.RemoteException;
    void addElection(Election election) throws java.rmi.RemoteException;
    void removeElection(int i) throws java.rmi.RemoteException;

    public ArrayList<MulticastServer> getVotingTables() throws java.rmi.RemoteException;
    public void addTable(MulticastServer table) throws java.rmi.RemoteException;
    public void removeTable(int i) throws java.rmi.RemoteException;
    public boolean manageVotingTable(Department department, int option) throws java.rmi.RemoteException;

    public User indentifyVoter(String idCardNumber) throws java.rmi.RemoteException;
    public ArrayList<Election> identifyElections(User voter, Department dep) throws java.rmi.RemoteException;

    public Election chooseElection(User voter, Department dep, int i) throws java.rmi.RemoteException;
    public void electorVote(Election election, Vote vote) throws java.rmi.RemoteException;
    public void RemoveUser(int i) throws java.rmi.RemoteException;


    //-------- meta 2 --------

    public ArrayList<String> getAllUsers() throws RemoteException;
    public ArrayList<String> getAllElections() throws RemoteException;
    public ArrayList<String> getPastElections() throws RemoteException;
    public Department getDepartment(String dep) throws RemoteException;
    public boolean voteOnline(String election, String candidate, String user) throws RemoteException;
    public boolean addCandidateList(String titulo, String lista) throws  RemoteException;
    public String addFacebook() throws RemoteException;
    public void authFacebook(String code) throws RemoteException;

}


