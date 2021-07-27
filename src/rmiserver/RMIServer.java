package rmiserver;

import java.io.*;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuthService;
import com.sun.org.apache.xpath.internal.operations.Mult;
import rmiserver.multicast.*;
import rmiserver.classes.*;
import uc.sd.apis.FacebookApi2;

public class RMIServer extends UnicastRemoteObject implements RMIInterface{
    private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/me";
    static private int PORT = 5001;
    static String MULTICAST_ADDRESS = "224.3.2.1";
    private MulticastSocket dSocket;
    private static ArrayList<String> multicastServers = new ArrayList<>();
    private static DatagramSocket nSocket;
    private static boolean run=true;
    private OAuthService service;


    /**
     * Lista de Eleições do sistema
     */
    private ArrayList<Election> electionsList;
    /**
     * Lista de departamentos do sistema
     */
    private ArrayList<Department> departmentsList;
    /**
     * Lista de Pessoas do sistema
     */
    private ArrayList<User> usersList;
    /**
     * Lista de mesas de voto do sistema
     */
    private ArrayList<MulticastServer> votingTablesList;

    public RMIServer() throws RemoteException, SocketException {
        super();
        this.electionsList = new ArrayList<>();
        this.departmentsList = new ArrayList<>();
        this.usersList = new ArrayList<>();
        this.votingTablesList = new ArrayList<>();

        readFiles();

        try{
            dSocket =  new MulticastSocket(5000);
        }
        catch(IOException b){
            System.out.println("Error creating socket");
        }
    }

    synchronized public void electorVote(Election election, Vote vote) {
        for (User user: this.usersList)
            if (user.getIdCardNumber().equals(vote.getVoter().getIdCardNumber()))
                for (Election auxElection : this.electionsList) {
                    if (auxElection.electionEquals(election)) {
                        auxElection.removeVoter(vote);
                        auxElection.addVote(vote);
                    }
                }
        saveFiles(3);
    }

    // ------------------ meta 1 ------------------
    //gestao das listas

    //adiciona a lista
    @Override
    public void addDepartment(Department dep) throws RemoteException {
        this.departmentsList.add(dep);
        saveFiles(2);
    }

    @Override
    public void addElection(Election election) throws RemoteException {
        this.electionsList.add(election);
        saveFiles(3);
    }

    synchronized public void addTable(MulticastServer table){
        votingTablesList.add(table);
        saveFiles(4);
        saveFiles(2);
    }

    //devolve listas

    synchronized public ArrayList<Department> getDepartmentList() {
        return departmentsList;
    }

    public ArrayList<User> getUserList() {
        return usersList;
    }

    synchronized public ArrayList<Election> getElectionsList()  {
        return electionsList;
    }

    synchronized public ArrayList<MulticastServer> getVotingTables(){
        return votingTablesList;
    }

    //identifica elemento da lista

    public Election getElection(String eleicao) throws RemoteException{
        for (Election aux : electionsList)
            if (eleicao.equals(aux.getTitle()))
                return aux;
        return null;
    }

    public Department getDepartment(String dep) throws RemoteException{
        for (Department aux : departmentsList)
            if (dep.equals(aux.getName()))
                return aux;
        return null;
    }

    synchronized public Election chooseElection(User voter, Department dep, int i){
        for (MulticastServer tablesaux : this.votingTablesList)
            if (tablesaux.getDepartment().getName().equals(dep.getName()))
                for (Election aux: tablesaux.getElectionsList())
                    return aux;
        return null;
    }

    synchronized public User indentifyVoter(String idCardNumber) {
        for (User user: this.usersList)
            if (user.getIdCardNumber().equals(idCardNumber))
                return user;
        return null;
    }

    synchronized public ArrayList<Election> identifyElections(User voter, Department dep){
        ArrayList<Election> elections = new ArrayList<>();
        for (MulticastServer tableaux : this.votingTablesList) {
            if ((tableaux.getDepartment().getName().toUpperCase()).equals(dep.getName().toUpperCase())) {
                for (Election aux : tableaux.getElectionsList()) {
                    for (Election election : electionsList) {
                        if (election.getTitle().equals(aux.getTitle())) {
                            elections.add(election);
                        }
                    }
                }
            }
        }
        return elections;
    }

    //remove da lista

    synchronized public void removeElection(int i){
        this.electionsList.remove(i);
        saveFiles(3);
    }

    synchronized public void removeTable(int i){
        this.votingTablesList.remove(i);
        saveFiles(4);
    }

    synchronized public void RemoveUser(int i) throws RemoteException{
        this.usersList.remove(i);
        saveFiles(1);
    }

    synchronized public void RemoveDepartament(int i){
        this.departmentsList.remove(i);
        saveFiles(2);
    }

    // ---------------- fim meta 1 -----------------

    synchronized public boolean manageVotingTable(Department department, int option){
        //abrir mesa de voto
        for (MulticastServer aux : votingTablesList)
            //abrir mesa
            if (option == 1) {
                if ((department.getName().toUpperCase()).equals(aux.getDepartment().getName().toUpperCase())) {
                    aux.setTableState(true);
                    saveFiles(2);
                    saveFiles(4);
                    return true;
                }
            }
            //fechar mesa de voto
            else if (option == 2) {
                if (department.getName().equals(aux.getDepartment().getName()) && aux.getTableState()) {
                    aux.setTableState(false);
                    saveFiles(2);
                    saveFiles(4);
                    return true;
                }
            }
        return false;
    }

    /**
     * Registar user
     */
    @Override
    synchronized public Boolean register(User user) throws RemoteException{
        for (User aux: this.usersList) {
            //print da lista de pessoas ja registadas
            System.out.println(aux.getName());
            //pessoa já registada
            if (user.getIdCardNumber().equals(aux.getIdCardNumber()))
                return false;
        }
        for (Department dep : departmentsList) {
            if ((user.getDepartment().getName()).equals(dep.getName())) {
                //user.addUserToList(usersList, dep);
                usersList.add(user);
                saveFiles(1);
                System.out.println(user.getName());
                return true;
            }
        }
        return false;
    }

    /**
     * returns all the user names
     */
    public boolean vote(String election, String list, String user){
        for (Election ele : electionsList)
            if (election.equals(ele.getTitle())){
                for (User p : ele.getVotersList()){
                    if (user.equals(p.getIdCardNumber())){
                        Vote voto = new Vote(p, new CandidateList(list),null);
                        ele.removeVoter(voto);
                        ele.addVote(voto);
                        return true;
                    }
                }
            }
        return false;
    }

    public ArrayList<String> getAllUsers() {
        System.out.println("Looking up all departments...");
        ArrayList<Department> depList = getDepartmentList();
        ArrayList<String> depNameList = new ArrayList<>();
        for (Department dep : depList) {
            depNameList.add(dep.getName());
        }
        return depNameList;
    }

    public ArrayList<String> getAllElections() {
        System.out.println("Looking up current elections...");
        ArrayList<Election> elections = getElectionsList();
        ArrayList<String> electionNameList = new ArrayList<>();
        for (Election election : elections) {
            // create calendar objects.
            Calendar cal = Calendar.getInstance();
            if(cal.after(election.getStart()) && cal.before(election.getEnd()))
                electionNameList.add(election.getTitle());
        }
        return electionNameList;
    }

    public ArrayList<String> getPastElections() {
        System.out.println("Looking up past elections...");
        ArrayList<Election> elections = getElectionsList();
        ArrayList<String> electionNameList = new ArrayList<>();
        for (Election election : elections) {
            // create calendar objects.
            Calendar cal = Calendar.getInstance();
            if(cal.after(election.getEnd()))
                electionNameList.add(election.getTitle());
        }
        return electionNameList;
    }

    public boolean voteOnline(String election, String candidate, String user){
        User aux=null;
        for(User auxuser: usersList){
            System.out.println(auxuser.getName()+auxuser.getIdCardNumber()+auxuser.getDepartment().getName());
            if(user.equals(auxuser.getIdCardNumber())){
                aux=auxuser;
            }
        }
        for (Election auxelection : electionsList) {
            if (election.equals(auxelection.getTitle())) {
                System.out.println(auxelection.getDepartment().getName()+election);
                if(aux.getDepartment().getName().equals(auxelection.getDepartment().getName())) {
                    Vote vote = new Vote(null, new CandidateList(candidate), null);
                    auxelection.addVote(vote);
                    return true;
                }
                else
                    System.out.println("User does not belong in the department election.");
                return false;
            }
        }
        return false;
    }

    public boolean addCandidateList(String titulo, String lista) throws  RemoteException{
        for (Election aux : electionsList){
            if (aux.getTitle().toUpperCase().equals(titulo.toUpperCase())) {
                aux.addCandidateList(new CandidateList(lista));
                return true;
            }
        }
        return false;
    }

    public String addFacebook() throws RemoteException{

        Token EMPTY_TOKEN = null;
        String apiKey = "516348146069779";
        String apiSecret = "4122fd02b55653dad8dd0e80f84c0cce";

        service = new ServiceBuilder()
                .provider(FacebookApi2.class)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .callback("https://eden.dei.uc.pt/~fmduarte/echo.php") // Do not change this.
                .scope("public_profile")
                .build();
        String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
        System.out.println("Got the Authorization URL!");
        System.out.println("Now go and authorize Scribe here:");
        System.out.println(authorizationUrl);
        return authorizationUrl;
    }

    public void authFacebook(String code) throws RemoteException{
        Token EMPTY_TOKEN = null;
        Verifier verifier = new Verifier(code);
        System.out.println();

        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
        System.out.println("Got the Access Token!");

        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");
        OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
        service.signRequest(accessToken, request);
        Response response = request.send();
        System.out.println("Got it! Lets see what we found...");
        System.out.println();
        System.out.println(response.getCode());
        System.out.println(response.getBody());

        System.out.println("Logged in!");
    }

    public static void main(String args[]) throws RemoteException, SocketException {
        RMIInterface server=new RMIServer();
        try {
            Registry r = LocateRegistry.createRegistry(7000);
            System.out.println(LocateRegistry.getRegistry(7000));
            r.rebind("eVoting", server);
            System.out.println("RMIServer ready.");
        }
        catch (RemoteException e) {
            //backup server
            boolean programFails = true;

                programFails = false;
                try {
                    Thread.sleep(5000);
                    LocateRegistry.createRegistry(7000).rebind("eVoting",server);
                    run=false;
                    System.out.println("Connected! Server Backup assumed");
                    run=true;

                } catch (RemoteException | InterruptedException b) {
                    System.out.println("Main RMI Server working... Waiting for failures");
                    programFails = true;
                }
            }
        }

    // ----------- gestao dos ficheiros ------------
    /**
     * * ler os dados dos ficheiros para o sistema
     */
    public void readFiles() {
        FileInputStream streamIn;
        ObjectInputStream objectinputstream = null;
        try {
            streamIn = new FileInputStream("/Users/ViegasMP/SD/Ficha6_WebProgramming/Hey/ficheiros/pessoas.ser");
            //streamIn = new FileInputStream("/Users/anita/Desktop/Hey/ficheiros/pessoas.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            ArrayList<User> users = (ArrayList<User>) objectinputstream.readObject();
            this.usersList =users;
            streamIn = new FileInputStream("/Users/ViegasMP/SD/Ficha6_WebProgramming/Hey/ficheiros/departamentos.ser");
            //streamIn = new FileInputStream("/Users/anita/Desktop/Hey/ficheiros/departamentos.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            ArrayList<Department> deps = (ArrayList<Department>) objectinputstream.readObject();
            this.departmentsList =deps;
            streamIn = new FileInputStream("/Users/ViegasMP/SD/Ficha6_WebProgramming/Hey/ficheiros/eleicoes.ser");
            //streamIn = new FileInputStream("/Users/anita/Desktop/Hey/ficheiros/eleicoes.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            ArrayList<Election> elections = (ArrayList<Election>) objectinputstream.readObject();
            this.electionsList =elections;
            streamIn = new FileInputStream("/Users/ViegasMP/SD/Ficha6_WebProgramming/Hey/ficheiros/mesas.ser");
            //streamIn = new FileInputStream("/Users/anita/Desktop/Hey/ficheiros/mesas.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            ArrayList<MulticastServer> mesas = (ArrayList<MulticastServer>) objectinputstream.readObject();
            this.votingTablesList= mesas;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectinputstream != null) {
                try {
                    objectinputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * guardar os dados do sistema para ficheiros
     */
    public void saveFiles(int flag){
        ObjectOutputStream oos=null;
        FileOutputStream fout;
        try{
            if (flag == 1){
                fout = new FileOutputStream("/Users/ViegasMP/SD/Ficha6_WebProgramming/Hey/ficheiros/pessoas.ser");
                //fout = new FileOutputStream("/Users/anita/Desktop/Hey/ficheiros/pessoas.ser");
                oos = new ObjectOutputStream(fout);
                oos.writeObject(usersList);
            } else if (flag == 2){
                fout = new FileOutputStream("/Users/ViegasMP/SD/Ficha6_WebProgramming/Hey/ficheiros/departamentos.ser");
                //fout = new FileOutputStream("/Users/anita/Desktop/Hey/ficheiros/departamentos.ser");
                oos = new ObjectOutputStream(fout);
                oos.writeObject(departmentsList);
            } else if (flag == 3){
                fout = new FileOutputStream("/Users/ViegasMP/SD/Ficha6_WebProgramming/Hey/ficheiros/eleicoes.ser");
                //fout = new FileOutputStream("/Users/anita/Desktop/Hey/ficheiros/eleicoes.ser");
                oos = new ObjectOutputStream(fout);
                oos.writeObject(electionsList);
            } else if (flag == 4) {
                fout = new FileOutputStream("/Users/ViegasMP/SD/Ficha6_WebProgramming/Hey/ficheiros/mesas.ser");
                //fout = new FileOutputStream("/Users/anita/Desktop/Hey/ficheiros/mesas.ser");
                oos = new ObjectOutputStream(fout);
                oos.writeObject(votingTablesList);
            }
        }catch (IOException e) {
            System.out.println("IOEXCEPTION");
        }finally {
            if(oos  != null ){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

