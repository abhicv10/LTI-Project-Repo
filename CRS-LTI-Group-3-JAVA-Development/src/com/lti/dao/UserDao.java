
/**
 * 
 */
package com.lti.dao;

import com.lti.exception.InvalidUserException;
import com.lti.exception.UserAlreadyExistException;

import java.util.ArrayList;
import com.lti.bean.Login;

/**
 * @author 10710133
 *
 */
public interface UserDao {

    /**
    * Method to check if the username already exist
    * @param username- username of the user
    * @return- returns true if the username already exist

     */

    public boolean isUsernameAlreadyTaken(String username);

    /**
    * Method to create a new user
    * @param login- login details of the user
    * @throws UserAlreadyExistException

     */

    public void createNewUser(Login login) throws UserAlreadyExistException ;

     /**
    * Method to validates the user
    * @param username- username of the user
    * @param password- password of the user
    * @param role- role of the user
    * @throws InvalidUserException
    * @return returns false if it's an invalid user

     */

    public boolean validateUser(String username, String password, String role) throws InvalidUserException;

      /**
    * Method to get the user ID of the user
    * @param username- username of the user
    * @param password- password of the user
    * @param role- role of the user
    * @throws InvalidUserException
    * @return returns the user ID

     */


    public int getUserID(String username, String password, String role) throws InvalidUserException;

    /**
    * Method to get all the user login details
    * @return returns a list of the login details of the particular user
    
     */

    public ArrayList<Login> getAllUserLoginDetails();

    /**
    * Method to update the password of the user
    * @param username- username of the user
    * @param newPassword- the updates password
     */

    public void updatePassword(String username, String newPassword);










    

}
