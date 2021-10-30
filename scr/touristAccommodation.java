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


}
