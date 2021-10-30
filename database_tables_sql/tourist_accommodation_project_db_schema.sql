--db_schema

create type accommodationTypeEnum as enum('hotel', 'guesthouse', 'apartment', 'cottage');
create type numberOfStarsEnum as enum('none', '1', '2', '3', '4', '5');
create type billingCategoryEnum as enum('perPerson', 'perUnit');
create type boardTypeEnum as enum('none', 'breakfast', 'half-board', 'full-board');

create table touristAccommodation(
touristAccommodationID BIGSERIAL PRIMARY KEY,
touristAccommodationName VARCHAR(255),
phone VARCHAR(255),
email VARCHAR(60),
fax VARCHAR(255),
website VARCHAR(255),
adress VARCHAR(255),
accommodationType accommodationTypeEnum,
numberOfStars numberOfStarsEnum
);

create table socialMediaAccount(
    accountname VARCHAR(255),
    plattform VARCHAR(255),
    touristAccommodationID INTEGER references touristAccommodation(touristAccommodationID),
    CONSTRAINT socialMediaAccountKEY primary key(accountname, plattform)
);

create table guest(
    guestID BIGSERIAL PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    title VARCHAR(255),
    dateOfBirth DATE,
    email VARCHAR(60),
    phone VARCHAR(255),
    adress VARCHAR(255)
);

create table touristAccommodationHasGuest(
    touristAccommodationID INTEGER references touristAccommodation(touristAccommodationID),
    guestID INTEGER references guest(guestID),
    CONSTRAINT touristAccommodationHasGuestKEY primary key(touristAccommodationID, guestID)
);

create table booking(
    bookingID BIGSERIAL PRIMARY KEY,
    numAdults INTEGER,
    numChildren INTEGER,
    boardType boardTypeEnum,
    guestID INTEGER references guest(guestID)
);

create table travelsWith(
    guestID INTEGER references guest(guestID),
    bookingID INTEGER references booking(bookingID),
    CONSTRAINT travelsWithKEY primary key(guestID, bookingID)
);

create table category(
    categoryName VARCHAR(255) PRIMARY KEY NOT NULL,
    categoryDescription VARCHAR(255),
    numBeds INTEGER,
    floorAreaInM2 INTEGER
);

create table entity(
    roomID BIGSERIAL PRIMARY KEY,
    entityName VARCHAR(255),
    categoryName VARCHAR(255) references category(categoryName),
    touristAccommodationID INTEGER references touristAccommodation(touristAccommodationID)
);

create table priceRecording(
    recordingID BIGSERIAL PRIMARY KEY,
    price FLOAT,
    billingCategory billingCategoryEnum,
    dateFrom DATE,
    dateTo DATE,
    boardType boardTypeEnum
);

create table isOffered(
    recordingID INTEGER references priceRecording(recordingID),
    touristAccommodationID INTEGER references touristAccommodation(touristAccommodationID),
    categoryName VARCHAR(255) references category(categoryName),
    CONSTRAINT isOfferedKEY primary key(recordingID, touristAccommodationID, categoryName)
);

create table isBooked(
    bookingID INTEGER references booking(bookingID),
    roomID INTEGER references entity(roomID),
    dateFrom DATE,
    dateTo DATE,
    CONSTRAINT isBookedKEY primary key(bookingID, roomID)
);
