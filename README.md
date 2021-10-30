# tourist accommodation project

Content
1. project description
2. repository content
3. how to run the project

## 1. project description

This project represents a database application for a fictitious regional booking platform. 

Every tourist accommodation has a name, adress, contact data (phone, email, fax, website), accommodation type (hotel, guesthouse, apartment, cottage) and possible social media accounts. If the tourist accommodation is a hotel, its number of stars is also known.

A tourist accommodation offers one or multiple categories. Every category has a name, description, number of beds and its floor area in squaremeters. 
A tourist accommodation has entities. Each entity belongs to exact one category and has a name (like room 23). This name is only per tourist accommodation unique. 

For each category the prices must be recorded. For hotels and guesthouses, the prices apply per person and night as well as per board type. For apartments and cottages the prices apply per entity and night. 

A tourist accommodation has a list of its guests' data. The guests' data contains a guests' first name, last name, date of birth, adress, phone and email. 
A guest can book a stay in an entity of a tourist accommodation. Every booking must contain an arrival date and a departure date, the booker and possible fellow travellers.
Only one booking per entity and night is allowed. 

## 2. repository content

This repository contains:
- database schema (sql) for creating the database
- UML diagram 
- code implemented in java for running the application 

## 3. how to run the project
