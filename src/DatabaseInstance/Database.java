/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseInstance;

/**
 *
 * @author USER
 */
import Connections.JDBCConnection;
import Contexts.CustomerContext;
import DataModels.Customer;
import DataModels.Room;
import MainMenu.SignIn;
import Contexts.SessionManager;
import DataModels.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

public class Database {
    private Connection connect;
    PreparedStatement pst = null;
    Statement statment = null;
    ResultSet result = null;
    JDBCConnection jdbcConnect = new JDBCConnection();

    public Database() {
        try {
            Class.forName(jdbcConnect.classDriver);
            connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
        } catch (
            ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        try {
            Class.forName(jdbcConnect.classDriver);
            return connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
        } catch (
            ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public SignInResponse getUser(String username, String password) {
        String role = null;
        String status = null;
        String query = "SELECT * FROM employee WHERE username=? AND password=?";
        ArrayList<User> result = new ArrayList<User>();
        
        connect = getConnection();
        try (PreparedStatement pst = connect.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    
                    role = rs.getString("role");
                    status = rs.getString("status");
                    
                    // masukin data user ke sessions manager;
                    User currUser = new User(rs.getString("username"), rs.getString("email"), rs.getString("name"), role, rs.getString("status"), rs.getInt("id"));
                    result.add(currUser);
                    SessionManager.setCurrUser(currUser);
                    return new SignInResponse("success", role, status);
                } else {
                    JOptionPane.showMessageDialog(null, "Username or Password Is Incorrect!");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public DatabaseResultResponse registUser(String email, String name, String username, String password, String role) {
        String query = "insert into employee(email, name, username, password, role, status) values (?, ?, ?, ?, ?, ?)";
        
        try {
            connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
            Class.forName(jdbcConnect.classDriver);
            statment = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            // cek email and username
            pst = connect.prepareStatement("select * from employee where email=? or username=?");
            pst.setString(1, email);
            pst.setString(2, username);
            result = pst.executeQuery();
            if(result.next()) {
                return new DatabaseResultResponse(400, "failed", null);
            };
            
            // Insert new employee
            pst = connect.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, name);
            pst.setString(3, username);
            pst.setString(4, password);
            pst.setString(5, "staff");
            pst.setString(6, "pending");
            pst.executeUpdate();
            
            return new DatabaseResultResponse(200, "success", null);
            
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, e);
            return new DatabaseResultResponse(500, e.getMessage(), null);
        }
    };
    
    public class ambilKamar {
        private Connection connect;

        public ambilKamar(Connection connect) {
            this.connect = connect;
    }

    public ResultSet getKamars() {
        String query = "SELECT * FROM room";
        try {
            PreparedStatement pst = connect.prepareStatement(query);
            return pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
            return null;
        }
    }
    }
    
    public DatabaseResultResponse addRoom(String id, String name, int price, String type) {
      String query = "insert into room values (?, ?, ?, ?)";
      try(PreparedStatement pst = connect.prepareStatement(query)) {
          pst.setString(1, id);
          pst.setString(2, name);
          pst.setInt(3, price);
          pst.setString(4, type);
          
          int rowsAffected = pst.executeUpdate();
          if(rowsAffected > 0) {
              return new DatabaseResultResponse(200, "success", null);
          } else {
              return new DatabaseResultResponse(400, "failed", null);
          }
      } catch (SQLException e) {
          e.printStackTrace();
          return new DatabaseResultResponse(500, e.getMessage(), null);
      }
    };
    
    public DatabaseResultResponse deleteRoom(String roomId) {
        String query = "delete from room where id = ?";
        try(PreparedStatement pst = connect.prepareStatement(query)) {
            pst.setString(1, roomId);
            
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0) {
                return new DatabaseResultResponse(200, "success", null);
            } else {
                return new DatabaseResultResponse(400, "failed", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, e.getMessage(), null);
        }
    };
    
    public DatabaseResultResponse getRooms(String type) {
        String query = "SELECT * from room where type = ?";
        ArrayList<Room> listRoom = new ArrayList<>();
        
        try(Connection connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
            PreparedStatement statment = connect.prepareStatement(query)) {
            statment.setString(1, type);
            
            try(ResultSet result = statment.executeQuery()) {
                while(result.next()) {
                    String id = result.getString("id");
                    String name = result.getString("name");
                    int price = result.getInt("price");
                    String typeRoom = result.getString("type");
                    
                    Room room = new Room(id, name, typeRoom, price);
                    listRoom.add(room);
                }
            };
            
            return new DatabaseResultResponse(200, "success", listRoom);
        } catch(SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, "failed", null);
        }
    };
    
    public DatabaseResultResponse getReservedRooms(String type) {
        String query = "SELECT room.id, room.name, room.price, room.type, reserved_room.status FROM room JOIN reserved_room ON room.id = reserved_room.room_id JOIN payment ON reserved_room.payment_id = payment.id WHERE reserved_room.status != 'completed' AND room.type = ?";
        ArrayList<Room> listRoom = new ArrayList<>();
        
        try(Connection connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
            PreparedStatement statment = connect.prepareStatement(query)) {
            statment.setString(1, type);
            
            try(ResultSet result = statment.executeQuery()) {
                while(result.next()) {
                    String id = result.getString("id");
                    String name = result.getString("name");
                    int price = result.getInt("price");
                    String typeRoom = result.getString("type");
                    
                    Room room = new Room(id, name, typeRoom, price);
                    listRoom.add(room);
                }
            };
            
            return new DatabaseResultResponse(200, "success", listRoom);
        } catch(SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, "failed", null);
        }        
    };
    
    public DatabaseResultResponse getAvailableRooms(String type) {
        String query = "SELECT * FROM room r WHERE r.type = ? AND NOT EXISTS ( SELECT 1 FROM reserved_room rr WHERE rr.room_id = r.id AND rr.status != 'completed')";
        ArrayList<Room> listRoom = new ArrayList<>();
        
        try(Connection connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
            PreparedStatement statment = connect.prepareStatement(query)) {
            statment.setString(1, type);
            
            try(ResultSet result = statment.executeQuery()) {
                while(result.next()) {
                    String id = result.getString("id");
                    String name = result.getString("name");
                    int price = result.getInt("price");
                    String typeRoom = result.getString("type");
                    
                    Room room = new Room(id, name, typeRoom, price);
                    listRoom.add(room);
                }
            };
            
            return new DatabaseResultResponse(200, "success", listRoom);
        } catch(SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, "failed", null);
        }        
    };
    
    public DatabaseResultResponse getStaff(String statusUser) {
        String query = "SELECT id, email, username, name, role, status FROM employee WHERE role = ? AND status = ?";
        ArrayList<User> listStaff = new ArrayList<>();
        
        try(Connection connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
            PreparedStatement statment = connect.prepareStatement(query)) {
            statment.setString(1, "staff");
            statment.setString(2, statusUser);
            
            try(ResultSet result = statment.executeQuery()) {
                while(result.next()) {
                    int id = result.getInt("id");
                    String email = result.getString("email");
                    String username = result.getString("username");
                    String name = result.getString("name");
                    String role = result.getString("role");
                    String status = result.getString("status");
                    
                    User user = new User(username, email, name, role, status, id);
                    listStaff.add(user);
                }
            };
            
            return new DatabaseResultResponse(200, "success", listStaff);
        } catch(SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, "failed", null);
        }
    };
    
    public DatabaseResultResponse deleteStaff(String username) {
        String query = "delete from employee where username = ?";
        try(PreparedStatement pst = connect.prepareStatement(query)) {
            pst.setString(1, username);
            
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0) {
                return new DatabaseResultResponse(200, "success", null);
            } else {
                return new DatabaseResultResponse(400, "failed", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, e.getMessage(), null);
        }
    };
    
    public DatabaseResultResponse approveStaff(String username) {
        String query = "update employee set status = ? where username = ?";
        try(PreparedStatement pst = connect.prepareStatement(query)) {
            pst.setString(1, "approved");
            pst.setString(2, username);
            
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0) {
                return new DatabaseResultResponse(200, "success", null);
            } else {
                return new DatabaseResultResponse(400, "failed", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, e.getMessage(), null);
        }    
    }
    
    public DatabaseResultResponse getCurrentCustomer() {
        String query = "SELECT customer.id, customer.name as name, employee.name as employee_name, reserved_room.room_id,"
                + " room.name as room_name, room.type as room_type, reserved_room.check_in_date, "
                + "reserved_room.check_out_date, reserved_room.status FROM customer JOIN reserved_room ON customer.id = reserved_room.customer_id JOIN room ON reserved_room.room_id = room.id JOIN employee ON customer.employee_id = employee.id WHERE reserved_room.status != 'completed'";
        ArrayList<Customer> listCustomer = new ArrayList<>();
        
        try(Connection connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
            PreparedStatement statment = connect.prepareStatement(query)) {
            
            try(ResultSet result = statment.executeQuery()) {
                while(result.next()) {
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    String employeeName = result.getString("employee_name");
                    String roomId = result.getString("room_id");
                    String roomName = result.getString("room_name");
                    String roomType = result.getString("room_type");
                    String checkInDate = result.getDate("check_in_date").toString();
                    String checkOutDate = result.getDate("check_out_date").toString();
                    String status = result.getString("status");

                    Customer customer = new Customer(id, name, employeeName, roomId, roomName, roomType, checkInDate, checkOutDate, status);
                    listCustomer.add(customer);
                }
            };
            
            return new DatabaseResultResponse(200, "success", listCustomer);
        } catch(SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, "failed", null);
        }
    };    
    
    public DatabaseResultResponse getCheckOutedCustomer() {
        String query = "SELECT customer.id, customer.name as name, customer.email, customer.phone_number, employee.name as employee_name, reserved_room.id as reserved_id, reserved_room.room_id, room.name as room_name, room.type as room_type, reserved_room.check_in_date, reserved_room.check_out_date, reserved_room.status FROM customer JOIN reserved_room ON customer.id = reserved_room.customer_id JOIN room ON reserved_room.room_id = room.id JOIN employee ON customer.employee_id = employee.id WHERE reserved_room.status != 'completed' AND reserved_room.status != 'pending'";
        ArrayList<Customer> listCustomer = new ArrayList<>();
        
        try(Connection connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
            PreparedStatement statment = connect.prepareStatement(query)) {
            
            try(ResultSet result = statment.executeQuery()) {
                while(result.next()) {
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    String email = result.getString("email");
                    String phoneNum = result.getString("phone_number");
                    String employeeName = result.getString("employee_name");
                    String reservedId = result.getString("reserved_id");
                    String roomId = result.getString("room_id");
                    String roomName = result.getString("room_name");
                    String roomType = result.getString("room_type");
                    String checkInDate = result.getDate("check_in_date").toString();
                    String checkOutDate = result.getDate("check_out_date").toString();
                    String status = result.getString("status");

                    Customer customer = new Customer(id, name, email, phoneNum, employeeName, reservedId, roomId, roomName, roomType, checkInDate, checkOutDate, status);
                    listCustomer.add(customer);
                }
            };
            
            return new DatabaseResultResponse(200, "success", listCustomer);
        } catch(SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, "failed", null);
        }
    };    
    
    public DatabaseResultResponse getCustomerHistoryData() {
        String query = "SELECT customer.id, customer.name as name, customer.email, customer.phone_number, employee.name as staff_name, room.id as room_id, room.name as room_name, room.type as room_type, reserved_room.check_in_date, reserved_room.check_out_date, reserved_room.status as status FROM customer JOIN reserved_room ON customer.id = reserved_room.customer_id JOIN room ON reserved_room.room_id = room.id JOIN employee ON customer.employee_id = employee.id WHERE reserved_room.status = 'completed'";
        ArrayList<Customer> listCustomer = new ArrayList<>();
        
        try(Connection connect = DriverManager.getConnection(jdbcConnect.JDBCUrl+jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
            PreparedStatement statment = connect.prepareStatement(query)) {
            
            try(ResultSet result = statment.executeQuery()) {
                while(result.next()) {
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    String email = result.getString("email");
                    String phoneNum = result.getString("phone_number");
                    String employeeName = result.getString("staff_name");
                    String roomId = result.getString("room_id");
                    String roomName = result.getString("room_name");
                    String roomType = result.getString("room_type");
                    String checkInDate = result.getDate("check_in_date").toString();
                    String checkOutDate = result.getDate("check_out_date").toString();
                    String status = result.getString("status");

                    Customer customer = new Customer(id, name, email, phoneNum, employeeName, roomId, roomName, roomType, checkInDate, checkOutDate, status);
                    listCustomer.add(customer);
                }
            };
            
            return new DatabaseResultResponse(200, "success", listCustomer);
        } catch(SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, "failed", null);
        }
    };    
    
    public AddCustomerResponse postCustomer(String name, int employeeId, String email, String phoneNumber) {
        String customerQuery = "INSERT INTO customer (name, employee_id, email, phone_number) VALUES (?, ?, ?, ?)";
        String getIdCustomer = "SELECT LAST_INSERT_ID() AS id";
        
        try (Connection connect = DriverManager.getConnection(jdbcConnect.JDBCUrl + jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
             PreparedStatement customerStatement = connect.prepareStatement(customerQuery);
             PreparedStatement lastInsertIdStatement = connect.prepareStatement(getIdCustomer)) {

            connect.setAutoCommit(false);

            // Set parameter untuk statement insert
            customerStatement.setString(1, name);
            customerStatement.setInt(2, employeeId);
            customerStatement.setString(3, email);
            customerStatement.setString(4, phoneNumber);

            // Eksekusi query INSERT
            customerStatement.executeUpdate();

            // Eksekusi query untuk mendapatkan last inserted ID
            ResultSet resultSet = lastInsertIdStatement.executeQuery();
            int generatedId = 0;
            if (resultSet.next()) {
                generatedId = resultSet.getInt("id");
                new CustomerContext().setId(generatedId);
            }

            connect.commit();

            return new AddCustomerResponse(200, "Customer created successfully with ID: " + generatedId, generatedId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new AddCustomerResponse(500, "Failed to create customer", 0);
        }
    }
    
    public DatabaseResultResponse deleteCustomer(String idCustomer) {
        String query = "delete from customer where id = ?";
        try(PreparedStatement pst = connect.prepareStatement(query)) {
            pst.setString(1, idCustomer);
            
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0) {
                return new DatabaseResultResponse(200, "success", null);
            } else {
                return new DatabaseResultResponse(400, "failed", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, e.getMessage(), null);
        }
    };
    
    public DatabaseResultResponse deleteAllCustomer() {
        String query = "delete from customer";
        try(PreparedStatement pst = connect.prepareStatement(query)) {
            
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0) {
                return new DatabaseResultResponse(200, "success", null);
            } else {
                return new DatabaseResultResponse(400, "failed", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, e.getMessage(), null);
        }
    }
   
   public DatabaseResultResponse postReserved( String paymentId,int customerId, String roomId, 
                                         String checkInDate, String checkOutDate, int dayReserved, double priceTotal, String status) {
    
    String query = "INSERT INTO reserved_room (payment_id, customer_id, room_id, check_in_date, check_out_date, " +
                  "day_reserved, price_total, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connect = DriverManager.getConnection(jdbcConnect.JDBCUrl + jdbcConnect.databaseName, 
                                                            jdbcConnect.databaseUsername, 
                                                            jdbcConnect.databasePassword);
             PreparedStatement stmt = connect.prepareStatement(query)) {

            stmt.setString(1, paymentId);
            stmt.setInt(2, customerId);
            stmt.setString(3, roomId);
            stmt.setString(4, checkInDate);
            stmt.setString(5, checkOutDate);
            stmt.setInt(6, dayReserved);
            stmt.setDouble(7, priceTotal);
            stmt.setString(8, status);

             int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return new DatabaseResultResponse(200, "Reserved room created successfully", null);
            } else {
                return new DatabaseResultResponse(400, "No rows were inserted", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, "Failed to create reserved room", null);
        }
    }

   public DatabaseResultResponse getReserved() {
    String query = "SELECT id, payment_id, customer_id, room_id, check_in_date, check_out_date, day_reserved, price_total, status status FROM reserved_room";
    ArrayList<HashMap<String, Object>> reservedRooms = new ArrayList<>();

    try (Connection connect = DriverManager.getConnection(
            jdbcConnect.JDBCUrl + jdbcConnect.databaseName,
            jdbcConnect.databaseUsername,
            jdbcConnect.databasePassword);
         PreparedStatement statement = connect.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("id", resultSet.getInt("id")); 
            data.put("payment_id", resultSet.getString("payment_id"));
            data.put("customer_id", resultSet.getInt("customer_id"));
            data.put("room_id", resultSet.getString("room_id"));
            data.put("check_in_date", resultSet.getString("check_in_date"));
            data.put("check_out_date", resultSet.getString("check_out_date"));
            data.put("day_reserved", resultSet.getInt("day_reserved"));
            data.put("price_total", resultSet.getDouble("price_total"));
            data.put("status", resultSet.getString("status"));

            reservedRooms.add(data);
        }

        return new DatabaseResultResponse(200, "Success", reservedRooms);
    } catch (SQLException e) {
        e.printStackTrace();
        return new DatabaseResultResponse(500, "Failed to fetch reserved rooms", null);
    }
}


   public DatabaseResultResponse checkOutRoom(String reservedId) {
    String query = "UPDATE reserved_room SET STATUS = 'completed' WHERE id = ?";

    try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, reservedId);
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            return new DatabaseResultResponse(200, "success", null);
        } else {
            return new DatabaseResultResponse(404, "Reserved room not found", null);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return new DatabaseResultResponse(500, "Failed to delete reserved room", null);
    }
}

   
   public DatabaseResultResponse addPayment(String paymentId, double amount,String paymentDate, String paymentMethod, String status) {
        String query = "INSERT INTO payment(id, amount, payment_date, payment_method, status) VALUES (?, ?, ?, ?, ?)";
         try (Connection connect = DriverManager.getConnection(jdbcConnect.JDBCUrl + jdbcConnect.databaseName, jdbcConnect.databaseUsername, jdbcConnect.databasePassword);
            PreparedStatement paymentQuery = connect.prepareStatement(query)) {
            
            //customer statement
            paymentQuery.setString(1, paymentId);
            paymentQuery.setDouble(2, amount);
            paymentQuery.setString(3, paymentDate);
            paymentQuery.setString(4, paymentMethod);
            paymentQuery.setString(5, status);
            paymentQuery.executeUpdate();
            
            new CustomerContext().setPaymentId(paymentId);

            return new DatabaseResultResponse(200, "Payment created", null);
        } catch (SQLException e) {
            e.printStackTrace();
            return new DatabaseResultResponse(500, "Failed to create payment", null);
        }
   }
    
    public void closeConnection() {
        try {
            if (connect != null && !connect.isClosed()) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
