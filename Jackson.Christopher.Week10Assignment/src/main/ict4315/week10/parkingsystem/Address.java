package week10.parkingsystem;

public class Address {
    private String streetAddress1;
    private String streetAddress2 = "";
    private String city;
    private String state;
    private String zipcode;

    /**
     *
     * @param streetAddress1
     * @param streetAddress2
     * @param city
     * @param state
     * @param zipcode
     */
    public Address (String streetAddress1,String streetAddress2,
                    String city,String state,String zipcode){
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    /**
     *
     * @return
     */
    public String getStreetAddress1() {
        return streetAddress1;
    }

    /**
     *
     * @param streetAddress1
     */
    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    /**
     *
     * @return
     */
    public String getStreetAddress2() {
        return streetAddress2;
    }

    /**
     *
     * @param streetAddress2
     */
    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     *
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}