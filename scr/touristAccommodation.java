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
    private PreparedStatement getIDStmt;
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
            this.getIDStmt.setString(1, touristAccommodationName);
            this.getIDStmt.setString(2, phone);
            this.getIDStmt.setString(3, email);
            this.getIDStmt.setString(4, fax);
            this.getIDStmt.setString(5, website);
            this.getIDStmt.setString(6, adress);
            this.getIDStmt.setString(7, String.valueOf(accommodationType));
            this.getIDStmt.setString(8, numberOfStars.getNumVal());

            ResultSet rs = getIDStmt.executeQuery();
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
