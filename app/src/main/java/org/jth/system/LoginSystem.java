package org.jth.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;

import org.jth.security.Authenticate;
import org.jth.security.AuthenticateImpl;

public class LoginSystem {
	
	/**
	 * A command line interface demonstrating how to login to the system
	 * 
	 * @param argv - command line arguments
	 * @throws NumberFormatException - if UTF-8 encoding is not supported
	 * @throws IOException - if bufferedreader cannot read information
	 * @throws NoSuchAlgorithmException - if the SHA-256 algorithm is not found
	 */
	public static void loginCLI() throws NumberFormatException, IOException, NoSuchAlgorithmException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome. Please choose an option:");
		System.out.println("1: login");
		int option = Integer.parseInt(br.readLine());
		if (option == 1) {
			System.out.print("UserID: ");
			String userId = br.readLine();
			Authenticate userAuthentication = new AuthenticateImpl(userId);
			System.out.print("Password: ");
			String password = br.readLine();
			if (userAuthentication.authenticate(password)) {
				System.out.println("Logged in successfully");
			} else {
				System.out.println("Failed to login. Please check your userID and password");
			}
		} else {
			System.out.println("Please choose a valid option");
		}	
	}

}
