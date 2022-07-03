package com.publicis.training;

import java.sql.*;
import java.util.Scanner;

/**
 * This class connects to the database:publicis-training
 *
 */
public class DBOperations
{
    Connection dbConnection;
    Statement theStatement;
    ResultSet resultSet;
    String db_query;
    public static final String URLTOCONNECT = "jdbc:mysql://localhost:3306/publicis-training";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static void main( String[] args ) throws SQLException {
        DBOperations reference = new DBOperations();
        reference.connectToDatabase();
        Scanner scanner = new Scanner(System.in);
        System.out.print("1 : Shows Table\n2 : Insert Values\n3 : Update a Particular Record By Id\n4 : Delete a Particular Record By Id\n");
        int option = scanner.nextInt();
        if(option==1){
            System.out.println("Student Details");
            reference.showDetails();
        }
        else if(option==2){
            System.out.println("Enter new ID, Name, Domain and City in Same Order");
            int id = scanner.nextInt();
            scanner.nextLine();
            reference.insertNewStudent(new Student(id, scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
        }
        else if(option==3) {
            System.out.println("Enter Student ID to be updated");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter new Name, Domain and City in Same Order");
            reference.updateExistingDetails(new Student(id, scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
        }
        else if(option==4) {
            System.out.println("Enter Student ID:");
            reference.deleteById(scanner.nextInt());
        }
    }

    void connectToDatabase() {
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(URLTOCONNECT, USERNAME, PASSWORD);
        theStatement = dbConnection.createStatement();
            System.out.println("Successfully Connected to Database...");
        }catch (ClassNotFoundException ce){
            System.out.println("Database can't be load");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void showDetails() throws SQLException {
        db_query = "select * from students";
        resultSet = theStatement.executeQuery(db_query);
        while(resultSet.next()){
            System.out.println(new Student(resultSet.getInt("ID"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
        }
        }

    void insertNewStudent(Student student) throws SQLException{
        db_query = "INSERT INTO `students`(`ID`, `Name`, `Domain`, `City`) VALUES ('"+student.id()+"','"+student.name()+"','"+student.domain()+"','"+student.city()+"')";
        if(theStatement.executeUpdate(db_query)>0)
            System.out.println("Inserted the details with ID: "+student.id());
    }
    void updateExistingDetails(Student student) throws SQLException {
        db_query = "UPDATE `students` SET `Name` = '"+student.name()+"',`Domain`='"+student.domain()+"',`City`='"+student.city()+"' WHERE ID="+student.id();
        if(theStatement.executeUpdate(db_query)>0)
            System.out.println("Updated the details for ID: "+student.id());
    }
    void deleteById(Integer id) throws SQLException{
        db_query = "delete from students where ID="+id;
        if(theStatement.executeUpdate(db_query)>0)
            System.out.println("Deleted the details for ID: "+id);
    }
}
