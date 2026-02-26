package Tema1;

public class Fraud {
    private String zoneName;
    private String CNP;
    private String name;

    public Fraud(String zoneName, String CNP, String name) {
        this.zoneName = zoneName;
        this.CNP = CNP;
        this.name = name;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
