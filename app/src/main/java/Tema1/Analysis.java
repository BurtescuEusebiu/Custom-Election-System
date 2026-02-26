package Tema1;

import java.sql.SQLOutput;
import java.util.*;

public class Analysis {
    Election election;

    public Analysis() {}

    public Analysis(Election election) {
        this.election = election;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public void analyzeZone(Zone zone) {
        List<Map.Entry<Candidate, Integer>> sortedEntries = zone.returnVotes();
        if(sortedEntries.isEmpty()) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + zone.getName());
            return;
        }
        //Afisam
        double percentageNation = (double) zone.getTotalVotes() /election.totalVotes()*100;
        double percentageLocal  = (double) sortedEntries.get(0).getValue()/zone.getTotalVotes()*100;
        System.out.println("In " + zone.getName() + " au fost " + zone.getTotalVotes()
                + " voturi din " + election.totalVotes() + ". Adica " + (int)percentageNation
                + "%. Cele mai multe voturi au fost stranse de " + sortedEntries.get(0).getKey().getCNP() + " "
                + sortedEntries.get(0).getKey().getName() + ". Acestea constituie " + (int)percentageLocal
                + "% din voturile circumscriptiei.");
    }

    public void analyzeNation() {
        HashMap<String,Region> regions = new HashMap<>();
        //Vom parcurge toate zonele si vom updata datele corespunzatoare fiecarei regiuni
        for(Map.Entry<String,Zone> entry : election.getZones().entrySet()) {
            String zoneName = entry.getKey();
            Zone zone = entry.getValue();
            String regionName = zone.getRegion();
            Region region = regions.get(regionName);
            if (region == null) {
                region = new Region();
                regions.put(regionName, region);
            }

            region.setNrVotes(region.getNrVotes() + zone.getTotalVotes());
            HashMap<Candidate, Integer> zoneVotes = zone.getVotes();
            for (Map.Entry<Candidate, Integer> voteEntry : zoneVotes.entrySet()) {
                Candidate candidate = voteEntry.getKey();
                int votes = voteEntry.getValue();
                HashMap<Candidate, Integer> regionVotes = region.getVotes();
                regionVotes.put(candidate, regionVotes.getOrDefault(candidate, 0) + votes);
            }
        }
        //Sortam alfabetic regiunile
        List<Map.Entry<String, Region>> sortedEntries = new ArrayList<>(regions.entrySet());
        sortedEntries.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));
        LinkedHashMap<String, Region> sortedRegions = new LinkedHashMap<>();
        for (Map.Entry<String, Region> entry : sortedEntries) {
            sortedRegions.put(entry.getKey(), entry.getValue());
        }
        regions = sortedRegions;

        if(sortedRegions.isEmpty()) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
            return;
        }

        System.out.println("In Romania au fost " + election.totalVotes() + " voturi.");
        for(Map.Entry<String, Region> entry : sortedRegions.entrySet()) {
            //Sortam lista de candidati
            HashMap<Candidate, Integer> candidateVotes = entry.getValue().getVotes();
            List<Map.Entry<Candidate, Integer>> sortedCandidateEntries = new ArrayList<>(candidateVotes.entrySet());
            sortedCandidateEntries.sort((entry1, entry2) -> {
                int voteComparison = entry2.getValue().compareTo(entry1.getValue());
                if (voteComparison != 0) {
                    return voteComparison;
                }
                return entry2.getKey().getCNP().compareTo(entry1.getKey().getCNP());
            });

            if(sortedCandidateEntries.isEmpty()) {
                System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
                return;
            }
            //Afisam
            double percentageNation = (double) entry.getValue().getNrVotes()/election.totalVotes() * 100;
            double percentageLocal = (double) sortedCandidateEntries.get(0).getValue()/ entry.getValue().getNrVotes() * 100;
            System.out.println("In " + entry.getKey() + " au fost " + entry.getValue().getNrVotes() + " voturi din "
            + election.totalVotes() + ". Adica " + (int)percentageNation + "%. Cele mai multe voturi au fost stranse de "
            + sortedCandidateEntries.get(0).getKey().getCNP() + " " + sortedCandidateEntries.get(0).getKey().getName()
            + ". Acestea constituie " + (int)percentageLocal + "% din voturile regiunii.");
        }

    }
}
