package Service;

import DAO.Reposetry.EmployeeReposetry;

import java.sql.SQLException;

public class EmployeService {
  private EmployeeReposetry employeeReposetry;
  public EmployeService() throws SQLException {
     employeeReposetry = new EmployeeReposetry();
  }

  public void  ajoutModificationService(){


  }

}
