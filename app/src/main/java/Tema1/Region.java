package Tema1;

import java.util.HashMap;

public class Region {
    private int nrVotes;
    private HashMap<Candidate, Integer> votes = new HashMap<>();

    public Region() {}

    public Region(int nrVotes, HashMap<Candidate, Integer> votes) {
        this.nrVotes = nrVotes;
        this.votes = votes;
    }

    public int getNrVotes() {
        return nrVotes;
    }

    public void setNrVotes(int nrVotes) {
        this.nrVotes = nrVotes;
    }

    public HashMap<Candidate, Integer> getVotes() {
        return votes;
    }

    public void setVotes(HashMap<Candidate, Integer> votes) {
        this.votes = votes;
    }


}
