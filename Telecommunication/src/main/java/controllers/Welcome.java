package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Dao.SqlUtils;
import model.Customer;
import model.Sim;

@RestController
public class Welcome {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		model.addAttribute("message", "Welcome on Board");
		return "index";
	}
//
//	@RequestMapping(value = "/createCustomer", method = RequestMethod.GET)
//	public String createCustomer() {
//
//		return "create";
//	}

	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public String createCustomer(String firstname, String lastName, String gender, String address) {

		Customer ct = new Customer();
		ct.setFirstname(firstname);
		ct.setLastName(lastName);
		ct.setAddress(gender);
		ct.setAddress(address);
		
		
//		ct.setDateOfBirth("01/01/1900");
		Connection con = null;
		boolean status = false;
		try {
			con = SqlUtils.getConn();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			status = SqlUtils.createCustomer(con, ct);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = "";
		if (status == true) {
			message = "Customer inserted successfully ";
		} else {
			message = "We are sorry this operration can not be completed";
		}

		return message;
	}

	@RequestMapping(value = "/createSim", method = RequestMethod.POST)
	public String createSim(String cellNo) {

		Sim sim = new Sim();
		sim.setCellNo(cellNo);

//		ct.setDateOfBirth("01/01/1900");
		Connection con = null;
		boolean status = false;
		try {
			con = SqlUtils.getConn();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			status = SqlUtils.createSim(con, sim);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = "";
		if (status == true) {
			message = "Customer inserted successfully ";
		} else {
			message = "We are sorry this operration can not be completed";
		}
		return message;
	}

	@RequestMapping(value = "/listSim", method = RequestMethod.GET)
	public String listOfSim() {

		ArrayList<String> list = new ArrayList<String>();
		Connection con = null;

		try {
			con = SqlUtils.getConn();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		ArrayList<Sim> simList = new ArrayList<>();

		try {
			simList = SqlUtils.listOfSim(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < simList.size(); i++) {
			
			Sim sim = simList.get(i);
			String cellNo = sim.getCellNo();
			System.out.println(cellNo);
		}

		return "success";
	}

	@RequestMapping(value = "/linkcustomer", method = RequestMethod.POST)
	public String linkSimToCustomer(String customerName, String cellNo) {
		boolean bol = false;
		String message = "";
		int id = 0;
		try {
			id = SqlUtils.getCustomerId(customerName);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			bol = SqlUtils.linktoSim(id, cellNo);
			if (bol == true) {
				message = "You Customer linked to Sim";
			} else {
				message = "Sorry Customer can't linked with Sim ";
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return message;

	}

	@RequestMapping(value = "/RetrieveCustomerSims", method = RequestMethod.POST)
	public void RetrieveCustomerSims(String customerName) throws ClassNotFoundException, SQLException {
		ArrayList<Sim> listOfSim = new ArrayList<>();

		int id = 0;
		try {
			id = SqlUtils.getCustomerId(customerName);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		listOfSim = SqlUtils.getSimsOfCustomer(id);
		for (int i = 0; i < listOfSim.size(); i++) {

			Sim sim = listOfSim.get(i);
			String cellNo = sim.getCellNo();
			String customerId = sim.getCustomerId();
			System.out.println(cellNo);
			System.out.println(customerId);
		}

	}
}
