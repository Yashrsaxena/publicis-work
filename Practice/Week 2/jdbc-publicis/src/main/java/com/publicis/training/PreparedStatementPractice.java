package com.publicis.training;

import java.sql.*;
import java.util.Scanner;

/**
 * This class connects to the database:publicis-training
 *
 */
public class PreparedStatementPractice
{
    Connection dbConnection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String db_query;
    public static final String URLTOCONNECT = "jdbc:mysql://localhost:3306/publicis-training";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static void main( String[] args ) throws SQLException {
        PreparedStatementPractice reference = new PreparedStatementPractice();
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
            int flag = reference.showParticularStudent(id);
            if(flag==1) {
                System.out.println("Enter new Name, Domain and City in Same Order");
                reference.updateExistingDetails(new Student(id, scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
            }
            else {
            }
        }
        else if(option==4) {
            System.out.println("Enter Student ID:");
            int id = scanner.nextInt();
            scanner.nextLine();
            int flag = reference.showParticularStudent(id);
            if(flag==1)
            {reference.deleteById(id);}
            else{
            }
        }
    }

    void connectToDatabase() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(URLTOCONNECT, USERNAME, PASSWORD);
            System.out.println("Successfully Connected to Database...");
        }catch (ClassNotFoundException ce){
            System.out.println("Database can't be load");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void showDetails() throws SQLException {
        db_query = "select * from students";
        resultSet = preparedStatement.executeQuery(db_query);
        while(resultSet.next()){
            System.out.println(new Student(resultSet.getInt("ID"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
        }
    }

    int showParticularStudent(int id) throws SQLException {
        int flag;
        db_query = "select * from students where ID=?";
        preparedStatement = dbConnection.prepareStatement(db_query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
//        System.out.println(resultSet.getInt("ID"));

        if(resultSet.next()==false) {
            System.out.println("Record Not Found");
            return 0;
        }
        else {
            System.out.println(new Student(resultSet.getInt("ID"), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            return 1;
        }
    }

    void insertNewStudent(Student student) throws SQLException{
        db_query = "INSERT INTO `students`(`ID`, `Name`, `Domain`, `City`) VALUES (?, ?, ?, ?)";
        preparedStatement = dbConnection.prepareStatement(db_query);
        preparedStatement.setInt(1, student.id());
        preparedStatement.setString(2, student.name());
        preparedStatement.setString(3, student.domain());
        preparedStatement.setString(4, student.city());
        if(preparedStatement.executeUpdate()>0)
            System.out.println("Inserted the details with ID: "+student.id());
    }
    void updateExistingDetails(Student student) throws SQLException {
        db_query = "UPDATE `students` SET `Name` = ?,`Domain`=?,`City`=? WHERE ID=?";
        preparedStatement = dbConnection.prepareStatement(db_query);
        preparedStatement.setInt(4, student.id());
        preparedStatement.setString(1, student.name());
        preparedStatement.setString(2, student.domain());
        preparedStatement.setString(3, student.city());
        if(preparedStatement.executeUpdate()>0)
            System.out.println("Updated the details for ID: "+student.id());
    }
    void deleteById(Integer id) throws SQLException{
        db_query = "delete from students where ID=?";
        preparedStatement = dbConnection.prepareStatement(db_query);
        preparedStatement.setInt(1, id);
        if(preparedStatement.executeUpdate()>0)
            System.out.println("Deleted the details for ID: "+id);
    }
}
