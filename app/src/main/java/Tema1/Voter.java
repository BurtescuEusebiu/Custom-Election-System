package Tema1;

public class Voter {
    private String CNP;
    private int age;
    private boolean graceless;
    private boolean voted;
    private String name;

    public Voter() {}

    public Voter(String CNP, int age, boolean graceless, String name) {
        this.CNP = CNP;
        this.age = age;
        this.voted = false;
        this.graceless = graceless;
        this.name = name;
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

    public boolean getGraceless() {
        return graceless;
    }

    public void setGraceless(boolean graceless) {
        this.graceless = graceless;
    }

    public boolean getVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void vote() {
        voted = true;
    }
}
