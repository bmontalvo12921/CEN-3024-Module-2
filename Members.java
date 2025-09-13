/**
*Sets up the attributes for Members
 */

public class Members {
    private String id;
    private String name;
    private String address;
    private Double fine;

    /**
     * Member paramters
     *@param id 7 digits
     *@param name
     *@param address
     *@param fine
     */
    public Members(String id, String name, String address, double fine){
        this.id = id;
        this.name = name;
        this.address = address;
        this.fine = fine;
    }
    /**
    *Getters and Setters
     */
    public String getId() { return id; }
    public String setid(String id) { this.id = id; return this.id; }
    public String getName() { return name; }
    public String setname(String name) { this.name = name; return this.name; }
    public String getAddress() { return address; }
    public String setaddress(String address) { this.address = address; return this.address; }
    public Double getFine() { return fine; }
    public Double setfine(Double fine) { this.fine = fine; return this.fine; }

    /**
     *
     * @return String with ID, Name, Address and Fine
     */
    @Override
    public String toString() {
        return id + "-"+ name +"-"+ address +"-"+ fine;
    }
}
