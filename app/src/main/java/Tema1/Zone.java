package Tema1;

import org.checkerframework.checker.units.qual.C;

import java.util.*;

public class Zone {
    private String name;
    int totalVotes;
    private String region;
    private HashMap<String, Voter> voters = new HashMap<>();
    private HashMap<Candidate, Integer> votes = new HashMap<>();

    public Zone() {}

    public Zone (String name, String region) {
        this.name = name;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void addVoter(Voter voter) {
        voters.put(voter.getCNP(), voter);
    }

    public void addVote(Voter voter, Candidate candidate) {
        voter.vote();
        totalVotes++;
        if(voter.getGraceless()) return;
        votes.put(candidate, votes.getOrDefault(candidate, 0) + 1);
    }

    public HashMap<Candidate, Integer> getVotes() {
        return votes;
    }

    public void freeVoters() {
        voters.clear();
    }

    public void freeVotes() {
        votes.clear();
    }

    public void freeVotesCandidate(Candidate candidate) {
        votes.remove(candidate);
    }

    public Voter checkVoter(String CNP) {
        if(voters.containsKey(CNP)){
            return voters.get(CNP);
        }
        return null;
    }

    public void listVoters() {
        if(voters.isEmpty()){
            System.out.println("GOL: Nu sunt votanti in " + name);
            return;
        }

        System.out.println("Votantii din " + name + ":");
        TreeMap<String, Voter> sortedList = new TreeMap<>(voters);
        for(Voter voter : sortedList.values()){
            System.out.println(voter.getName() + " " + voter.getCNP() + " " + voter.getAge());
        }
    }

    public List<Map.Entry<Candidate, Integer>> returnVotes()
    {
        //sortam lista de candidati in functie de voturi/CNP
        List<Map.Entry<Candidate, Integer>> sortedEntries = new ArrayList<>(votes.entrySet());
        sortedEntries.sort((entry1, entry2) -> {
            int voteComparison = entry2.getValue().compareTo(entry1.getValue());
            if (voteComparison != 0) {
                return voteComparison;
            }
            return entry2.getKey().getCNP().compareTo(entry1.getKey().getCNP());
        });
        return sortedEntries;
    }

    public void listVotes() {
        List<Map.Entry<Candidate, Integer>> sortedEntries = returnVotes();
        if(sortedEntries.isEmpty()){
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + name);
            return;
        }

        System.out.println("Raport voturi " + name + ":");
        for(Map.Entry<Candidate, Integer> entry : sortedEntries){
            System.out.println( entry.getKey().getName() + " " + entry.getKey().getCNP() + " - " + entry.getValue());
        }
    }
}

