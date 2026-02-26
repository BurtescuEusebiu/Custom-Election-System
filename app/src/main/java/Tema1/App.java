package Tema1;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class App {
    private final Scanner scanner;

    public App(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public void run() {
        boolean finish = false;
        HashMap<String, Election> elections = new HashMap<>();

        while (!finish) {
            int commandNr = scanner.nextInt();
            scanner.nextLine();
            switch (commandNr) {
                case 0:
                    createElections(elections);
                    break;
                case 1:
                    startElections(elections);
                    break;
                case 2:
                    addZone(elections);
                    break;
                case 3:
                    removeZone(elections);
                    break;
                case 4:
                    addCandidate(elections);
                    break;
                case 5:
                    removeCandidate(elections);
                    break;
                case 6:
                    addVoter(elections);
                    break;
                case 7:
                    listCandidates(elections);
                    break;
                case 8:
                    listVoters(elections);
                    break;
                case 9:
                    vote(elections);
                    break;
                case 10:
                    stopElection(elections);
                    break;
                case 11:
                    ratioZone(elections);
                    break;
                case 12:
                    ratioNation(elections);
                    break;
                case 13:
                    analyzeZone(elections);
                    break;
                case 14:
                    analyzeNation(elections);
                    break;
                case 15:
                    reportFrauds(elections);
                    break;
                case 16:
                    deleteElection(elections);
                    break;
                case 17:
                    listElections(elections);
                    break;
                case 18:
                    finish = true;
                    break;
                default:
                    incorrectInput();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }

    public void createElections(HashMap<String, Election> elections) {
        String id = scanner.next();
        String name = scanner.nextLine().trim();

        if (elections.containsKey(id)) {
            System.out.println("EROARE: Deja exista alegeri cu id " + id);
            return;
        }

        Election newElection = new Election(id, name);
        elections.put(id, newElection);
        System.out.println("S-au creat alegerile " + name);
    }

    public void startElections(HashMap<String, Election> elections) {
        String id = scanner.next();

        if (elections.containsKey(id)) {
            Election currentElection = elections.get(id);

            if (currentElection.getState().equals(Election.Status.NOT_STARTED)) {
                currentElection.start();
                System.out.println("Au pornit alegerile " + currentElection.getName());
            } else {
                System.out.println("EROARE: Alegerile deja au inceput");
            }
        } else {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
        }
    }

    public void addZone(HashMap<String, Election> election) {
        String id = scanner.next();
        String name = scanner.next();
        String region = scanner.next();

        Election electionCurr = election.get(id);
        if(electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }


        if (!electionCurr.getState().equals(Election.Status.RUNNING)) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }

        Zone checkZone = electionCurr.checkZone(name);
        if (checkZone != null) {
            System.out.println("EROARE: Deja exista o circumscriptie cu numele " + name);
            return;
        }

        Zone newZone = new Zone(name, region);
        electionCurr.addZone(newZone);
        System.out.println("S-a adaugat circumscriptia " + name);
    }

    public void removeZone(HashMap<String, Election> election) {
        String id = scanner.next();
        String name = scanner.next();

        Election electionCurr = election.get(id);
        if(electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if (!electionCurr.getState().equals(Election.Status.RUNNING)) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }

        Zone checkZone = electionCurr.checkZone(name);
        if (checkZone != null) {
            Zone zoneDelete = electionCurr.getZone(name);
            zoneDelete.freeVoters();
            zoneDelete.freeVotes();
            electionCurr.removeZone(name);
            System.out.println("S-a sters circumscriptia " + name);
        } else {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + name);
        }
    }

    public void addCandidate(HashMap<String, Election> election) {
        String id = scanner.next();
        String CNP = scanner.next();
        int age = scanner.nextInt();
        String name = scanner.nextLine().trim();

        if (CNP.length() != 13) {
            System.out.println("EROARE: CNP invalid");
            return;
        }

        Election electionCurr = election.get(id);

        if (electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if (!electionCurr.getState().equals(Election.Status.RUNNING)) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }

        String checkCNP = electionCurr.checkCNP(CNP);
        if (checkCNP != null) {
            System.out.println("EROARE: Candidatul " + checkCNP + " are deja acelasi CNP");
            return;
        }

        if (age < 35) {
            System.out.println("EROARE: Varsta invalida");
            return;
        }

        Candidate candidateNew = new Candidate(CNP, age, name);
        electionCurr.addCandidate(candidateNew);
        System.out.println("S-a adaugat candidatul " + name);
    }

    public void removeCandidate(HashMap<String, Election> election) {
        String id = scanner.next();
        String CNP = scanner.next();
        Election electionCurr = election.get(id);
        if (electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if (!electionCurr.getState().equals(Election.Status.RUNNING)) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }

        Candidate candidateToDelete = electionCurr.getCandidate(CNP);
        if (candidateToDelete == null) {
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + CNP);
            return;
        }

        System.out.println("S-a sters candidatul " + candidateToDelete.getName());
        electionCurr.removeCandidate(CNP);
    }

    public void addVoter(HashMap<String, Election> election) {
        String id = scanner.next();
        String zoneName = scanner.next();
        String CNP = scanner.next();
        int age = scanner.nextInt();
        String gracelessIn = scanner.next();
        boolean graceless = gracelessIn.equals("da");
        String name = scanner.nextLine().trim();

        Election electionCurr = election.get(id);
        if (electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if (!electionCurr.getState().equals(Election.Status.RUNNING)) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }

        if (CNP.length() != 13) {
            System.out.println("EROARE: CNP invalid");
            return;
        }

        if (age < 18) {
            System.out.println("EROARE: Varsta invalida");
            return;
        }

        Zone zoneCheck = electionCurr.checkZone(zoneName);
        if (zoneCheck == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + zoneName);
            return;
        }

        Voter checkVoter = electionCurr.checkVoter(zoneName, CNP);
        if (checkVoter != null) {
            System.out.println("EROARE: Votantul " + checkVoter.getName() + " are deja acelasi CNP");
            return;
        }

        Voter voter = new Voter(CNP, age, graceless, name);
        electionCurr.addVoter(zoneName, voter);
        System.out.println("S-a adaugat votantul " + name);
    }

    public void listCandidates(HashMap<String, Election> election) {
        String id = scanner.next();
        Election electionCurr = election.get(id);
        if(electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if (electionCurr.getState().equals(Election.Status.NOT_STARTED)) {
            System.out.println("EROARE: Inca nu au inceput alegerile");
            return;
        }

        electionCurr.listCandidates();

    }

    public void listVoters(HashMap<String, Election> election) {
        String id = scanner.next();
        String zoneName = scanner.next();
        Election electionCurr = election.get(id);
        if(electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if (electionCurr.getState().equals(Election.Status.NOT_STARTED)) {
            System.out.println("EROARE: Inca nu au inceput alegerile");
            return;
        }

        Zone zoneCheck = electionCurr.checkZone(zoneName);
        if (zoneCheck == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + zoneName);
            return;
        }

        zoneCheck.listVoters();
    }

    public void vote(HashMap<String, Election> election) {
        String id = scanner.next();
        String zoneName = scanner.next();
        String CNPVoter = scanner.next();
        String CNPCandidate = scanner.next();
        Election electionCurr = election.get(id);
        if(electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if(!electionCurr.getState().equals(Election.Status.RUNNING)) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }

        Zone zoneCheck = electionCurr.checkZone(zoneName);
        if (zoneCheck == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + zoneName);
            return;
        }

        Candidate checkCandidate = electionCurr.getCandidate(CNPCandidate);
        if(checkCandidate == null) {
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + CNPCandidate);
            return;
        }

        Voter checkVoted = electionCurr.checkVoter(zoneName, CNPVoter);
        Voter voterCheck = zoneCheck.checkVoter(CNPVoter);
        if(checkVoted == null) {
            System.out.println("FRAUDA: Votantul cu CNP-ul " + CNPVoter + " a incercat sa comita o frauda. Votul a fost anulat");
            Fraud fraud = new Fraud(zoneName,CNPVoter,electionCurr.searchCNP(CNPVoter));
            electionCurr.addFraud(fraud);
            return;
        }

        if(voterCheck == null) {
            System.out.println("FRAUDA: Votantul cu CNP-ul " + CNPVoter + " a incercat sa comita o frauda. Votul a fost anulat");
            Fraud fraud = new Fraud(zoneName,CNPVoter, electionCurr.searchCNP(CNPVoter));
            electionCurr.addFraud(fraud);
            return;
        }

        if(voterCheck.getVoted()) {
            System.out.println("FRAUDA: Votantul cu CNP-ul " + CNPVoter + " a incercat sa comita o frauda. Votul a fost anulat");
            Fraud fraud = new Fraud(zoneName,CNPVoter,voterCheck.getName());
            electionCurr.addFraud(fraud);
            return;
        }

        System.out.println(voterCheck.getName() + " a votat pentru " + checkCandidate.getName());
        zoneCheck.addVote(checkVoted,checkCandidate);

    }

    public void stopElection(HashMap<String, Election> election) {
        String id = scanner.next();

        Election electionCurr = election.get(id);
        if(electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if(!electionCurr.getState().equals(Election.Status.RUNNING)) {
            System.out.println("EROARE: Nu este perioada de votare");
        }

        System.out.println("S-au terminat alegerile " + electionCurr.getName());
        electionCurr.end();
    }

    public void ratioZone(HashMap<String, Election> election) {
        String id = scanner.next();
        String zoneName = scanner.next();
        Election electionCurr = election.get(id);
        if(electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if(!electionCurr.getState().equals(Election.Status.ENDED)) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }

        Zone zoneCheck = electionCurr.checkZone(zoneName);
        if(zoneCheck == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + zoneName);
            return;
        }
        zoneCheck.listVotes();


    }

    public void ratioNation(HashMap<String, Election> election) {
        String id = scanner.next();
        Election electionCurr = election.get(id);
        if(electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if(!electionCurr.getState().equals(Election.Status.ENDED)) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }

        electionCurr.listVotes();
    }

    public void analyzeZone(HashMap<String, Election> election) {
        String id = scanner.next();
        String zoneName = scanner.next();
        Election electionCurr = election.get(id);
        if(electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if(!electionCurr.getState().equals(Election.Status.ENDED)) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }

        Zone zoneCheck = electionCurr.checkZone(zoneName);
        if(zoneCheck == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + zoneName);
            return;
        }

        Analysis analysis = new Analysis(electionCurr);
        analysis.analyzeZone(zoneCheck);

    }

    public void analyzeNation(HashMap<String, Election> election) {
        String id = scanner.next();
        Election electionCurr = election.get(id);
        if(electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if(!electionCurr.getState().equals(Election.Status.ENDED)) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }

        Analysis analysis = new Analysis(electionCurr);
        analysis.analyzeNation();
    }

    public void reportFrauds(HashMap<String, Election> election) {
        String id = scanner.next();
        Election electionCurr = election.get(id);
        if(electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if(!electionCurr.getState().equals(Election.Status.ENDED)) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }
        electionCurr.listFrauds();

    }

    public void deleteElection(HashMap<String, Election> election) {
        String id = scanner.next();
        Election electionCurr = election.get(id);
        if(electionCurr == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        System.out.println("S-au sters alegerile " + electionCurr.getName());
        election.remove(electionCurr.getId());
    }

    public void listElections(HashMap<String, Election> election) {
        if(election.isEmpty()) {
            System.out.println("GOL: Nu sunt alegeri");
            return;
        }

        System.out.println("Alegeri:");
        for(Map.Entry<String, Election> entry : election.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getName());
        }
    }

    public void incorrectInput() {
        System.out.println("EROARE: Comanda incorecta/necunscuta!");
    }
}
