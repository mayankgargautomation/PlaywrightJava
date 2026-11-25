package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseValExtraction {

    public static void main(String[] args) throws SQLException
    {
        DatabaseValExtraction db=new DatabaseValExtraction();
        List<String> allcompanies= db.verifyCompany();
        for(int i=0; i < allcompanies.size(); i++)
        {
            System.out.println(allcompanies.get(i));

        }
    }

    public List<String> verifyCompany() throws SQLException
    {
        //String host="localhost"; String port="3306";
        //Connection conn=DriverManager.getConnection("jdbc:mysql://"+host+ ":" +port+ "/rahulshettyacademy", "root", "root");

        List<String> companies = new ArrayList<>();

        //  It creates a connection between your Java program and MySQL database.
        String databaseUrl="jdbc:mysql://localhost:3306/rahulshettyacademy";
        Connection conn=DriverManager.getConnection(databaseUrl, "root", "root");

        //Creates a Statement object for sendingSQL statements to the database.
        Statement state=conn.createStatement();

       //ResultSet = The result table returned by the database
        String query="select company, count(*)  from Orders group by company having count(*) > 0;";
        ResultSet rs = state.executeQuery(query);

        while(rs.next()) //RS object index require to sit on 1st Index
        {
            companies.add(rs.getString("company"));
        }

        rs.close();
        state.close();
        conn.close();
        return companies;
    }
}
