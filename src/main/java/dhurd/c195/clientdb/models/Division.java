package dhurd.c195.clientdb.models;

public class Division {
    private int divisionID;
    private String div;
    private int countryID;

    public Division (int divisionID, String div, int countryID){
        this.divisionID = divisionID;
        this.div = div;
        this.countryID = countryID;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    @Override
    public String toString(){
        return (div);
    }
}
