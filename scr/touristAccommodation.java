import enums.*;

import java.sql.*;

public class touristAccommodation {

    // Attributes
    private Integer touristAccommodationID;
    private String touristAccommodationName;
    private String phone;
    private String email;
    private String fax;
    private String website;
    private String adress;
    private accommodationTypeEnum accommodationType;
    private numberOfStarsEnum numberOfStars;

    // Connection und ResultSet
    private Connection connection;
    private ResultSet resultSet;

    // Prepared Statements
    // add statements
    private PreparedStatement addTouristAccommodationStmt;
    private PreparedStatement addGuestToTouristAccommodationStmt;
    private PreparedStatement addEntityStmt;
    // delete statements
    private PreparedStatement deleteTouristAccommodationStmt;
    private PreparedStatement deleteGuestFromTouristAccommodationStmt;
    private PreparedStatement deleteEntityStmt;
    // update statements
    private PreparedStatement updateTouristAccommodationNameStmt;
    private PreparedStatement updatePhoneStmt;
    private PreparedStatement updateEmailStmt;
    private PreparedStatement updateFaxStmt;
    private PreparedStatement updateWebsiteStmt;
    private PreparedStatement updateAdressStmt;
    private PreparedStatement updateAccommodationTypeStmt;
    private PreparedStatement updateNumberOfStarsStmt;
    // contains statements
    private PreparedStatement containsTouristAccommodationIDStmt;
    private PreparedStatement containsTouristAccommodationAttributesStmt;
    private PreparedStatement containsTouristAccommodationHasGuestStmt;
    private PreparedStatement containsEntityStmt;
    private PreparedStatement containsEntityIDStmt;
    // Statement for getting the touristAccommodationID (which is automatically generated in the database)
    private PreparedStatement getTouristAccommodationIDStmt;
    // Statement for getting roomID from entity
    private PreparedStatement getEntityIDStmt;


    // Getter and Setter

    /**
     * Gets the ID of the tourist accommodation from the database.
     * The ID is generated automatically when a tourist accommodation is added to the database.
     * @return the ID of the tourist accommodation
     */
    public Integer getTouristAccommodationID() {
        if (this.touristAccommodationID == null && containsTouristAccommodationAttributes(this.touristAccommodationName, this.phone, this.email, this.fax, this.website, this.adress, this.accommodationType, this.numberOfStars)) {
            fetchTouristAccommodationID(this.touristAccommodationName, this.phone, this.email, this.fax, this.website, this.adress, this.accommodationType, this.numberOfStars);
            return this.touristAccommodationID;
        }
        return touristAccommodationID;
    }

    /**
     * Fetches the tourist accommodation ID from the database
     * @param touristAccommodationName
     * @param phone
     * @param email
     * @param fax
     * @param website
     * @param adress
     * @param accommodationType
     * @param numberOfStars
     */
    private void fetchTouristAccommodationID(String touristAccommodationName, String phone, String email, String fax, String website, String adress, accommodationTypeEnum accommodationType, numberOfStarsEnum numberOfStars) {
        try {
            this.getTouristAccommodationIDStmt.setString(1, touristAccommodationName);
            this.getTouristAccommodationIDStmt.setString(2, phone);
            this.getTouristAccommodationIDStmt.setString(3, email);
            this.getTouristAccommodationIDStmt.setString(4, fax);
            this.getTouristAccommodationIDStmt.setString(5, website);
            this.getTouristAccommodationIDStmt.setString(6, adress);
            this.getTouristAccommodationIDStmt.setString(7, String.valueOf(accommodationType));
            this.getTouristAccommodationIDStmt.setString(8, numberOfStars.getNumVal());

            ResultSet rs = getTouristAccommodationIDStmt.executeQuery();
            rs.next();
            this.touristAccommodationID = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches the room ID of a specifiy entity of a tourist accommodation
     * @param entityName
     * @param categoryName
     * @param touristAccommodationID
     * @return the room ID
     */

    public int fetchRoomID(String entityName, String categoryName, int touristAccommodationID) {
        int roomID = -1;
        try {
            this.getEntityIDStmt.setString(1, entityName);
            this.getEntityIDStmt.setString(2, categoryName);
            this.getEntityIDStmt.setInt(3, touristAccommodationID);

            ResultSet rs = getEntityIDStmt.executeQuery();
            rs.next();
            roomID = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomID;
    }


    public String getTouristAccommodationName() {
        return touristAccommodationName;
    }

    public void setTouristAccommodationName(String touristAccommodationName) {
        this.touristAccommodationName = touristAccommodationName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public accommodationTypeEnum getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(accommodationTypeEnum accommodationType) {
        this.accommodationType = accommodationType;
    }

    public numberOfStarsEnum getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(numberOfStarsEnum numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    /**
     * Constructor
     * @param conn
     * @param touristAccommodationName
     * @param phone
     * @param email
     * @param fax
     * @param website
     * @param adress
     * @param accommodationType
     * @param numberOfStars
     */

    public touristAccommodation(Connection conn, String touristAccommodationName, String phone, String email, String fax, String website, String adress, accommodationTypeEnum accommodationType, numberOfStarsEnum numberOfStars) {
        this.connection = conn;
        this.touristAccommodationName = touristAccommodationName;
        this.phone = phone;
        this.email = email;
        this.fax = fax;
        this.website = website;
        this.adress = adress;
        this.accommodationType = accommodationType;
        this.numberOfStars = numberOfStars;

        try {
            this.addTouristAccommodationStmt = this.connection.prepareStatement("INSERT INTO create table touristAccommodation(touristAccommodationName, phone, email, fax, website, adress, accommodationType, numberOfStars) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CAST(? AS accommodationTypeEnum), CAST(? AS numberOfStarsEnum));");
            this.addGuestToTouristAccommodationStmt = this.connection.prepareStatement("INSERT INTO touristAccommodationHasGuest(touristAccommodationID, guestID) VALUES (?, ?);");
            this.addEntityStmt = this.connection.prepareStatement("INSERT INTO entity(entityName, categoryName, touristAccommodationID) VALUES (?, ?, ?);");

            this.deleteTouristAccommodationStmt = this.connection.prepareStatement("DELETE FROM touristAccommodation WHERE touristAccommodationID = ?;");
            this.deleteGuestFromTouristAccommodationStmt = this.connection.prepareStatement("DELETE FROM touristAccommodationHasGuest WHERE touristAccommodationID = ? AND guestID = ?;");
            this.deleteEntityStmt = this.connection.prepareStatement("DELETE FROM entity WHERE roomID = ?;");

            this.updateTouristAccommodationNameStmt = this.connection.prepareStatement("UPDATE touristAccommodation SET touristAccommodationName = ? WHERE touristAccommodationID = ?;");
            this.updatePhoneStmt = this.connection.prepareStatement("UPDATE touristAccommodation SET phone = ? WHERE touristAccommodationID = ?;");
            this.updateEmailStmt = this.connection.prepareStatement("UPDATE touristAccommodation SET email = ? WHERE touristAccommodationID = ?;");
            this.updateFaxStmt = this.connection.prepareStatement("UPDATE touristAccommodation SET fax = ? WHERE touristAccommodationID = ?;");
            this.updateWebsiteStmt = this.connection.prepareStatement("UPDATE touristAccommodation SET website = ? WHERE touristAccommodationID = ?;");
            this.updateAdressStmt = this.connection.prepareStatement("UPDATE touristAccommodation SET adress = ? WHERE touristAccommodationID = ?;");
            this.updateAccommodationTypeStmt = this.connection.prepareStatement("UPDATE touristAccommodation SET accommodationType = CAST(? AS accommodationTypeEnum) WHERE touristAccommodationID = ?;");
            this.updateNumberOfStarsStmt = this.connection.prepareStatement("UPDATE touristAccommodation SET numberOfStars = CAST(? AS numberOfStarsEnum) WHERE touristAccommodationID = ?;");

            this.containsTouristAccommodationIDStmt = this.connection.prepareStatement("SELECT * FROM touristAccommodation WHERE touristAccommodationID = ?;");
            this.containsTouristAccommodationAttributesStmt = this.connection.prepareStatement("SELECT * FROM touristAccommodation WHERE touristAccommodationName = ? AND phone = ? AND email = ? AND fax = ? AND website = ? AND adress = ? AND accommodationType = CAST(? AS accommodationTypeEnum) AND numberOfStars = CAST(? AS numberOfStarsEnum);");
            this.containsTouristAccommodationHasGuestStmt = this.connection.prepareStatement("SELECT * FROM touristAccommodationHasGuest WHERE touristAccommodationID = ? AND guestID = ?;");

            this.containsEntityStmt = this.connection.prepareStatement("SELECT * FROM entity WHERE entityName = ? AND categoryName = ? AND touristAccommodationID = ?;");
            this.containsEntityIDStmt = this.connection.prepareStatement("SELECT * FROM entity WHERE roomID = ?;");

            this.getEntityIDStmt = this.connection.prepareStatement("SELECT roomID FROM entity WHERE entityName = ? AND categoryName = ? AND touristAccommodationID = ?;");
            this.getTouristAccommodationIDStmt = this.connection.prepareStatement("SELECT touristAccommodationID FROM touristAccommodation WHERE touristAccommodationName = ? AND phone = ? AND email = ? AND fax = ? AND website = ? AND adress = ? AND accommodationType = CAST(? AS accommodationTypeEnum) AND numberOfStars = CAST(? AS numberOfStarsEnum);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    /************************ Start of contains-methods *******************************/



    /**
     * Checks if a tourist accommodation with specific given attributes is contained in the databaes
     * @param touristAccommodationName
     * @param phone
     * @param email
     * @param fax
     * @param website
     * @param adress
     * @param accommodationType
     * @param numberOfStars
     * @return true if the tourist accommodation is contained in the database, else return false
     */
    private boolean containsTouristAccommodationAttributes(String touristAccommodationName, String phone, String email, String fax, String website, String adress, accommodationTypeEnum accommodationType, numberOfStarsEnum numberOfStars) {
        try {
            this.containsTouristAccommodationAttributesStmt.setString(1, touristAccommodationName);
            this.containsTouristAccommodationAttributesStmt.setString(2, phone);
            this.containsTouristAccommodationAttributesStmt.setString(3, email);
            this.containsTouristAccommodationAttributesStmt.setString(4, fax);
            this.containsTouristAccommodationAttributesStmt.setString(5, website);
            this.containsTouristAccommodationAttributesStmt.setString(6, adress);
            this.containsTouristAccommodationAttributesStmt.setString(7, String.valueOf(accommodationType));
            this.containsTouristAccommodationAttributesStmt.setString(8, numberOfStars.getNumVal());

            ResultSet rs = containsTouristAccommodationAttributesStmt.executeQuery();

            if(rs.next()) {
                rs.close();
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }







}
