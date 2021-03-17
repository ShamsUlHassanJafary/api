package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;
import model.Sim;

public class SqlUtils {

	public static Connection getConn() throws ClassNotFoundException, SQLException {
		String userName = "root";
		String password = "khosa";
		String conString = "jdbc:mysql://localhost:3306/telecommunication";
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection(conString, userName, password);

		return con;

	}

	public static boolean createCustomer(Connection con, Customer ct) throws SQLException {
		boolean status = false;
		String query = "insert into dbtCustomer(ct_fName, ct_lName, ct_gender, ct_address) values(?,?,?,?)";

		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, ct.getFirstname());
		pst.setString(2, ct.getLastName());
		pst.setString(3, ct.getGender());
		pst.setString(4, ct.getAddress());
		

		int rows = pst.executeUpdate();
		if (rows > 0) {
			status = true;
		} else {
			status = false;
		}
		return status;

	}

	public static boolean createSim(Connection con, Sim sim) throws SQLException {

		boolean status = false;
		String query = "insert into dbtSim(cellNo) values(?)";

		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, sim.getCellNo());

		int rows = pst.executeUpdate();
		if (rows > 0) {
			status = true;
		} else {
			status = false;
		}
		return status;
	}

	public static ArrayList<Sim> listOfSim(Connection con) throws SQLException {

		ArrayList<Sim> sList = new ArrayList<>();

		String query = "SELECT * FROM dbtSim";

		PreparedStatement pst = con.prepareStatement(query);

		ResultSet rs = pst.executeQuery();

		while (rs.next()) {

			Sim sim = new Sim();

			int simId = rs.getInt("simId");

			sim.setSimId(simId);

			String cellNo = rs.getString("cellNo");
			sim.setCellNo(cellNo);

			sList.add(sim);
		}
		return sList;
	}

	public static int getCustomerId(String name) throws ClassNotFoundException, SQLException {
		Connection con = getConn();
		int customerId = 0;

		String query = "SELECT ct_Id FROM dbtcustomer where ct_fName ='" + name + "'";
		System.out.println(query);
		PreparedStatement pst = con.prepareStatement(query);

		ResultSet rs = pst.executeQuery();

		while (rs.next()) {

			customerId = rs.getInt("ct_Id");
		}
		
		return customerId;
	}

	public static boolean linktoSim(int customerId, String cellNo) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		String query = "UPDATE dbtsim SET ct_Id = ? WHERE cellNo =" + cellNo;
		conn = getConn();
		PreparedStatement pst = conn.prepareStatement(query);

		pst.setLong(1, customerId);

		int rows = pst.executeUpdate();
		if (rows > 0) {
			return true;

		} else {
			return false;
		}

	}

	public static ArrayList<Sim> getSimsOfCustomer(int id) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = getConn();

		ArrayList<Sim> sList = new ArrayList<>();

		String query = "SELECT * FROM dbtSim where ct_Id=" + id;

		PreparedStatement pst = conn.prepareStatement(query);

		ResultSet rs = pst.executeQuery();

		while (rs.next()) {

			Sim sim = new Sim();

			int simId = rs.getInt("simId");

			sim.setSimId(simId);

			String cellNo = rs.getString("cellNo");
			sim.setCellNo(cellNo);

			String customerId = rs.getString("ct_Id");
			sim.setCustomerId(customerId);

			sList.add(sim);
		}
		return sList;
	}
}
