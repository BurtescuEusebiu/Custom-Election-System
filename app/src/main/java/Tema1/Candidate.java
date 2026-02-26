package Tema1;

public class Candidate {
    public String CNP;
    public int age;
    public String name;

    public Candidate() {}

    public Candidate(String CNP, int age, String Name) {
        this.CNP = CNP;
        this.age = age;
        this.name = Name;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }
}

