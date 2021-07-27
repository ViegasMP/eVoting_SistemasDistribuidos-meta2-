/**
 * Raul Barbosa 2014-11-07
 */
package hey.model;

import java.util.ArrayList;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Calendar;

import rmiserver.*;
import rmiserver.classes.*;
import rmiserver.multicast.*;

public class eVotingBean {
	private RMIInterface server;
	private ArrayList<String> list;
	private String cc;
	private String url;

	public eVotingBean() {
		try {
			server = (RMIInterface) Naming.lookup("rmi://localhost:7000/eVoting");
		}
		catch(NotBoundException|MalformedURLException|RemoteException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getAllUsers() throws RemoteException {
		return server.getAllUsers();
	}

	public ArrayList<String> getAllElections() throws RemoteException {
		return server.getAllElections();
	}

	public ArrayList<String> getPastElections() throws RemoteException {
		return server.getPastElections();
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public ArrayList<User> getUsers() throws RemoteException {
		return server.getUserList();
	}

	public void setUsername(ArrayList<String> list) {
		this.list = list;
	}

	public Department getDepartment(String dep) throws RemoteException{
		return server.getDepartment(dep);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean register(User user) throws RemoteException {
		return server.register(user);
	}

	public Department findDepartment(String dep) throws RemoteException, NotBoundException, MalformedURLException {
		ArrayList<Department> listaDep;
		while (true) {
			try {
				listaDep = this.server.getDepartmentList();
				break;
			} catch (RemoteException ignored) {
				server = (RMIInterface) Naming.lookup("rmi://localhost:7000/eVoting");
			}
		}
		for (Department department:listaDep) {
			if (department.getName().toLowerCase().equals(dep.toLowerCase()))
				return department;
		}
		return null;
	}

	public boolean AddElection(Election ele) throws RemoteException, NotBoundException, MalformedURLException {
		while (true) {
			try {
				this.server.addElection(ele);
				return true;
			} catch (RemoteException ignored) {
				server = (RMIInterface) Naming.lookup("rmi://localhost:7000/eVoting");
			}
		}

	}

	public ArrayList<Election> getElections() throws RemoteException{
		return this.server.getElectionsList();
	}

	public boolean CreatStudentCouncil(String title, String description, Calendar start, Calendar end, Department department, ArrayList<CandidateList> candidateList) throws RemoteException {
		StudentCouncil election = new StudentCouncil(title, description, start, end, department,candidateList);
		this.server.addElection(election);
		return true;
	}

	public boolean CreatDG(String title, String description, Calendar start, Calendar end, ArrayList<CandidateList> studentCandidateList, ArrayList<CandidateList> teacherCandidateList, ArrayList<CandidateList> employeeCandidateList) throws RemoteException, MalformedURLException, NotBoundException {
		ArrayList<User> listaP;
		while (true) {
			try {
				listaP = this.server.getUserList();
				break;
			} catch (RemoteException ignored) {
				server = (RMIInterface) Naming.lookup("rmi://localhost:7000/eVoting");
			}
		}
		GeneralCouncil election = new GeneralCouncil(title, description, start, end, studentCandidateList, teacherCandidateList, employeeCandidateList, listaP);
		this.server.addElection(election);
		return true;
	}

	public ArrayList<Election> getElectionslist() throws RemoteException{
		return this.server.getElectionsList();
	}

	public Election removeElection(String name) throws RemoteException, MalformedURLException, NotBoundException {
		Election ele;
		ArrayList<Election> lista;
		int index;
		while (true) {
			try {
				lista = this.server.getElectionsList();
				break;
			} catch (RemoteException ignored) {
				server = (RMIInterface) Naming.lookup("rmi://localhost:7000/eVoting");
			}
		}
		for (int i=0;i<lista.size();i++){
			if (name.toLowerCase().equals(lista.get(i).getTitle())){
				index=i;
				ele=lista.get(i);
				this.server.removeElection(index);
				return ele;
			}

		}
		return null;
	}

	public boolean vote(String election, String vote) throws RemoteException{
		return server.voteOnline(election,vote, this.cc);
	}

	public boolean addList(String titulo, String lista){
		try {
			return server.addCandidateList(titulo,lista);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String addFacebook() throws RemoteException {
		this.url=server.addFacebook();
		return url;
	}

	public void authFacebook(String code) throws RemoteException {
		server.authFacebook(code);
	}

	public boolean creaTable(String chosenTable, String chosenElection){
		try {
			ArrayList<MulticastServer> tables = this.server.getVotingTables();
			ArrayList<Election> elections = this.server.getElectionsList();
			Election auxElection=null;

			//get election object by its name
			for (Election ele : elections) {
				if ((ele.getTitle().toUpperCase()).equals(chosenElection.toUpperCase())) {
					auxElection = ele;
				}
			}
			//get department object
			Department auxDepartment = server.getDepartment(chosenTable);

			//if election name and table name are valid
			if(auxElection!=null) {
				//look all current voting tables
				for (MulticastServer table : tables) {
					//if voting table already exists
					if ((table.getDepartment().getName().toUpperCase()).equals(chosenTable.toUpperCase())) {
						//System.out.println("This voting table already exists");
						//look all elections already associated to the table
						for (Election electionTable : table.getElectionsList()) {
							//if they are already associated
							System.out.println(electionTable.getTitle());
							if (electionTable.getTitle().equals(chosenElection)) {
								//System.out.println("This voting table already has this election");
								return false;
							}
						}
						//if chosen table is not associated to the chosen election
						table.addElection(auxElection);
						//System.out.println("This election was added to the pre existing table");
						return true;
					}
				}

				//create new voting table
				MulticastServer newTable = new MulticastServer(auxDepartment);
				System.out.println("table created");
				//add the new voting table to the servers table's list
				server.addTable(newTable);
				System.out.println("table created and added to the server list");
				//add the chosen election to the new table
				newTable.addElection(auxElection);
				System.out.println("election added to the new table");
				return true;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

}
