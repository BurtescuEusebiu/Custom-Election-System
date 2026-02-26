package Tema1;

import com.sun.source.tree.Tree;

import java.util.*;

public class Election {
    public enum Status {
        NOT_STARTED,
        RUNNING,
        ENDED
    }

    private String id;
    private String name;
    private Status status;
    private HashMap<String, Candidate> candidates = new HashMap<>();
    private HashMap<String, Zone> zones = new HashMap<>();
    private Stack<Fraud> frauds = new Stack<>();

    public Election() {}

    public Election(String id, String name) {
        this.name = name;
        this.id = id;
        this.status = Status.NOT_STARTED;
    }

    public Stack getFrauds() {
        return frauds;
    }

    public void setFrauds(Stack frauds) {
        this.frauds = frauds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getState() {
        return status;
    }

    public void setState(Status status) {
        this.status = status;
    }

    public void addZone(Zone zone) {
        zones.put(zone.getName(), zone);
    }
    
    public Zone getZone(String id) {
        return zones.get(id);
    }

    public HashMap<String, Zone> getZones(){
        return zones;
    }

    public void start(){
        status = Status.RUNNING;
    }

    public void end(){
        status = Status.ENDED;
    }

    public Zone checkZone(String id) {
        return zones.get(id);
    }
    
    public void removeZone(String id) {
        zones.remove(id);
    }
    
    public void addCandidate(Candidate candidate) { candidates.put(candidate.getCNP(), candidate);}
    public String checkCNP(String CNP) {
        if(candidates.containsKey(CNP)) return candidates.get(CNP).getName();
        return null;
    }
    
    public Candidate getCandidate(String CNP) {
        return candidates.get(CNP);
    }

    public void removeCandidate(String CNP) {
        Candidate candidateToDelete = candidates.get(CNP);
        for (Map.Entry<String, Zone> entry : zones.entrySet()) {
            entry.getValue().freeVotesCandidate(candidateToDelete);
            candidates.remove(CNP);
        }
    }

    public void addVoter(String zoneName,Voter voter) {
        Zone zoneCurr = zones.get(zoneName);
        zoneCurr.addVoter(voter);
    }

    public Voter checkVoter(String zoneName, String CNP){
        Zone zoneCurr = zones.get(zoneName);
        return zoneCurr.checkVoter(CNP);
    }

    public int totalVotes(){
        int totalVotes = 0;
        for (Zone zone : zones.values()) {
            totalVotes+=zone.totalVotes;
        }
        return totalVotes;
    }

    public void listCandidates(){
        if(candidates.isEmpty()){
            System.out.println("GOL: Nu sunt candidati");
            return;
        }
        System.out.println("Candidatii:");
        TreeMap<String, Candidate> sortedList = new TreeMap<>(candidates);
        for (Map.Entry<String, Candidate> entry : sortedList.entrySet()) {
            System.out.println(entry.getValue().name + " " + entry.getValue().getCNP() + " " + entry.getValue().getAge());
        }
    }

    public void listVotes() {
        //Calcuam numarul de voturi pentru fiecare candidat la nivel national
        HashMap<Candidate, Integer> totalVotes = new HashMap<>();
        for(Zone zone : zones.values()) {
            HashMap<Candidate, Integer> zoneVotes = zone.getVotes();
            for (Map.Entry<Candidate, Integer> entry : zoneVotes.entrySet()) {
                Candidate candidate = entry.getKey();
                int voteCount = entry.getValue();
                totalVotes.merge(candidate, voteCount, Integer::sum);
            }
        }
        List<Map.Entry<Candidate, Integer>> sortedEntries = new ArrayList<>(totalVotes.entrySet());
        sortedEntries.sort((entry1, entry2) -> {
            int voteComparison = entry1.getValue().compareTo(entry2.getValue());
            //aparent la tara se afiseaza crescator in teste
            if (voteComparison != 0) {
                return voteComparison;
            }
            return entry2.getKey().getCNP().compareTo(entry1.getKey().getCNP());
        });

        if (sortedEntries.isEmpty()) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
            return;
        }

        System.out.println("Raport voturi Romania:");
        for (Map.Entry<Candidate, Integer> entry : sortedEntries) {
            System.out.println(entry.getKey().getName() + " " + entry.getKey().getCNP() + " - " + entry.getValue());
        }
    }

    public String searchCNP(String CNP) {
        for(Zone zone : zones.values()) {
            Voter voter = zone.checkVoter(CNP);
            if(voter != null) return voter.getName();
        }
        return null;
    }

    public void addFraud(Fraud fraud){
        frauds.push(fraud);
    }

    public void listFrauds(){
        if(frauds.isEmpty()){
            System.out.println("GOL: Romanii sunt cinstiti");
            return;
        }
        System.out.println("Fraude comise:");
        for(Fraud entry : frauds){
            System.out.println("In " + entry.getZoneName() + ": " + entry.getCNP() + " " + entry.getName());
        }
    }
}
