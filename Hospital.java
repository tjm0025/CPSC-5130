import java.sql.*;
import java.util.Scanner;


public class Hospital {
    static Connection connection = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/hospitalproject"; //connection string
        String username = "root"; //MySQL username
        String password = "mac2008";   //MySQL password

        connection = DriverManager.getConnection(url, username, password);

        //Scanner definition and utilization
        System.out.println("Enter an integer between 1 and 21 to select a query: ");
        Scanner user = new Scanner(System.in);
        int userInput = user.nextInt();
        switch (userInput) {
            case 1 -> {
                try {
                    String roomIDOcup = "SELECT Room.Room_ID, Admission.admin_date, patients.patient_id, patients.first_name, patients.last_name FROM Room INNER JOIN Admission ON Room.Room_ID = Admission.room_no INNER JOIN Patients ON Admission.patient_id = Patients.patient_id";
                    PreparedStatement preparedStatement = connection.prepareStatement(roomIDOcup);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        StringBuilder query11 = new StringBuilder();
                        for (int i = 1; i <= 5; i++) {
                            query11.append(resultSet.getString(i)).append(":");
                        }
                        System.out.println(query11);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            case 2 -> {
                try {
                    String query_12 = "SELECT Room.Room_ID FROM Room WHERE NOT EXISTS (SELECT * FROM Admission WHERE Admission.room_no = Room.Room_ID)";
                    PreparedStatement preparedStatement12 = connection.prepareStatement(query_12);
                    ResultSet resultSet1 = preparedStatement12.executeQuery();
                    while (resultSet1.next()) {
                        StringBuilder query12 = new StringBuilder();
                        for (int i = 1; i <= 1; i++) {
                            query12.append(resultSet1.getString(i)).append(":");
                        }
                        System.out.println(query12);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 3 -> {
                try {
                    String query_13 = "SELECT Room.Room_ID, Patients.first_name, Patients.last_name, Admission.admin_date FROM Room LEFT JOIN Admission ON Room.Room_ID = Admission.room_no LEFT JOIN Patients ON Admission.patient_id = Patients.patient_id";
                    PreparedStatement preparedStatement13 = connection.prepareStatement(query_13);
                    ResultSet resultSet13 = preparedStatement13.executeQuery();
                    while (resultSet13.next()) {
                        StringBuilder query13 = new StringBuilder();
                        for (int i = 1; i <= 4; i++) {
                            query13.append(resultSet13.getString(i)).append(":");
                        }
                        System.out.println(query13);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 4 -> {
                try {
                    String query_21 = "SELECT * FROM patients";
                    PreparedStatement preparedStatement21 = connection.prepareStatement(query_21);
                    ResultSet resultSet21 = preparedStatement21.executeQuery();
                    while (resultSet21.next()) {
                        StringBuilder query21 = new StringBuilder();
                        for (int i = 1; i <= 6; i++) {
                            query21.append(resultSet21.getString(i)).append(":");
                        }
                        System.out.println(query21);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 5 -> {
                try {
                    String query_22 = "SELECT patients.patient_id, patients.first_name, patients.last_name FROM patients INNER JOIN Admission ON patients.patient_id = Admission.patient_id";
                    PreparedStatement preparedStatement22 = connection.prepareStatement(query_22);
                    ResultSet resultSet22 = preparedStatement22.executeQuery();
                    while (resultSet22.next()) {
                        StringBuilder query22 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query22.append(resultSet22.getString(i)).append(":");
                        }
                        System.out.println(query22);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 6 -> {
                try {

                    String query_23 = "SELECT patients.patient_id, patients.first_name, patients.last_name, admission.dis_date FROM patients INNER JOIN admission ON patients.patient_id = admission.patient_id WHERE admission.dis_date BETWEEN '2022-12-03' AND '2023-05-01'";
                    PreparedStatement preparedStatement23 = connection.prepareStatement(query_23);
                    ResultSet resultSet23 = preparedStatement23.executeQuery();
                    while (resultSet23.next()) {
                        StringBuilder query23 = new StringBuilder();
                        for (int i = 1; i <= 4; i++) {
                            query23.append(resultSet23.getString(i)).append(":");
                        }
                        System.out.println(query23);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 7 -> {
                try {
                    String query_24 = "SELECT patients.patient_id, patients.first_name, patients.last_name, admission.admin_date FROM patients INNER JOIN admission ON patients.patient_id = admission.patient_id WHERE admission.admin_date BETWEEN '2023-01-01' AND '2023-12-31'";
                    PreparedStatement preparedStatement24 = connection.prepareStatement(query_24);
                    ResultSet resultSet24 = preparedStatement24.executeQuery();
                    while (resultSet24.next()) {
                        StringBuilder query24 = new StringBuilder();
                        for (int i = 1; i <= 4; i++) {
                            query24.append(resultSet24.getString(i)).append(":");
                        }
                        System.out.println(query24);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 8 -> {
                try {
                    String query_25 = "SELECT patients.patient_id, diagnoses.diag_id, diagnoses.diag_name FROM patients INNER JOIN admission ON patients.patient_id = admission.patient_id INNER JOIN diagnoses ON admission.diag_id = diagnoses.diag_id WHERE patients.patient_id OR patients.last_name";
                    PreparedStatement preparedStatement25 = connection.prepareStatement(query_25);
                    ResultSet resultSet25 = preparedStatement25.executeQuery();
                    while (resultSet25.next()) {
                        StringBuilder query25 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query25.append(resultSet25.getString(i)).append(":");
                        }
                        System.out.println(query25);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 9 -> {
                try {
                    String query_26 = "SELECT patients.patient_id, treatment.treatment_id, treatment.treat_name, admission.admin_date FROM patients INNER JOIN admission ON patients.patient_id = admission.patient_id INNER JOIN treatment ON admission.treat_id = treatment.treatment_id WHERE patients.patient_id OR patients.last_name ORDER BY admission.admin_date DESC";
                    PreparedStatement preparedStatement26 = connection.prepareStatement(query_26);
                    ResultSet resultSet26 = preparedStatement26.executeQuery();
                    while (resultSet26.next()) {
                        StringBuilder query26 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query26.append(resultSet26.getString(i)).append(":");
                        }
                        System.out.println(query26);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 10 -> {
                try {
                    String query_27 = "SELECT distinct patients.patient_id, patients.first_name, patients.last_name, diagnoses.diag_name, employee.first_name, employee.last_name FROM patients INNER JOIN admission on patients.patient_id = admission.patient_id INNER JOIN diagnoses on admission.diag_id = diagnoses.diag_id INNER JOIN employee on admission.admitdoc_id = employee.employee_id WHERE admission.admin_date <= date_add(admission.dis_date, interval(1 MONTH))";
                    PreparedStatement preparedStatement27 = connection.prepareStatement(query_27);
                    ResultSet resultSet27 = preparedStatement27.executeQuery();
                    while (resultSet27.next()) {
                        StringBuilder query27 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query27.append(resultSet27.getString(i)).append(":");
                        }
                        System.out.println(query27);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 11 -> {
                try {
                    String query_28 = "SELECT patients.patient_id, patients.first_name, patients.last_name, count(*) as total_admissions, AVG(DATEDIFF(admission.dis_date, admission.admin_date)) AS avg_duration, datediff(MAX(admission.admin_date), MIN(admission.admin_date)) AS longest_span, datediff(MIN(CASE WHEN a2.admin_date > admission.admin_date THEN a2.admin_date ELSE NULL END), admission.admin_date) AS shortest_span, AVG(datediff(admission.admin_date, a2.admin_date)) AS avg_span FROM admission JOIN patients ON admission.patient_id = patients.patient_id LEFT JOIN admission a2 ON a2.patient_id = admission.patient_id AND a2.admin_date < admission.admin_date GROUP BY patients.patient_id, patients.first_name, patients.last_name";
                    PreparedStatement preparedStatement28 = connection.prepareStatement(query_28);
                    ResultSet resultSet28 = preparedStatement28.executeQuery();
                    while (resultSet28.next()) {
                        StringBuilder query28 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query28.append(resultSet28.getString(i)).append(":");
                        }
                        System.out.println(query28);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 12 -> {
                try {
                    String query_31 = "SELECT diagnoses.diag_id, diagnoses.diag_name, count(*) as occurences FROM diagnoses JOIN admission ON diagnoses.diag_id = admission.diag_id GROUP BY diagnoses.diag_id, diagnoses.diag_name ORDER BY occurences DESC";
                    PreparedStatement preparedStatement31 = connection.prepareStatement(query_31);
                    ResultSet resultSet31 = preparedStatement31.executeQuery();
                    while (resultSet31.next()) {
                        StringBuilder query31 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query31.append(resultSet31.getString(i)).append(":");
                        }
                        System.out.println(query31);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 13 -> {
                try {
                    String query_32 = "SELECT d.diag_id, d.diag_name, count(*) as OCCURENCES FROM diagnoses d JOIN admission a ON d.diag_id = a.diag_id GROUP BY d.diag_id, d.diag_name ORDER BY occurences DESC";
                    PreparedStatement preparedStatement32 = connection.prepareStatement(query_32);
                    ResultSet resultSet32 = preparedStatement32.executeQuery();
                    while (resultSet32.next()) {
                        StringBuilder query32 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query32.append(resultSet32.getString(i)).append(":");
                        }
                        System.out.println(query32);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 14 -> {
                try {
                    String query_33 = "SELECT treatment.treatment_id, treatment.treat_name, count(*) as occurences FROM treatment JOIN admission ON treatment.treatment_id = admission.treat_id GROUP BY treatment.treatment_id, treatment.treat_name ORDER BY occurences DESC";
                    PreparedStatement preparedStatement33 = connection.prepareStatement(query_33);
                    ResultSet resultSet33 = preparedStatement33.executeQuery();
                    while (resultSet33.next()) {
                        StringBuilder query33 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query33.append(resultSet33.getString(i)).append(":");
                        }
                        System.out.println(query33);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 15 -> {
                try {
                    String query_34 = "SELECT diagnoses.diag_id, diagnoses.diag_name, count(*) AS occurences FROM diagnoses JOIN admission ON diagnoses.diag_id = admission.diag_id GROUP BY diagnoses.diag_id, diagnoses.diag_name HAVING count(DISTINCT admission.patient_id) >= (SELECT COUNT(*) FROM admission GROUP BY patient_id ORDER BY COUNT(*) DESC LIMIT 1)ORDER BY occurences ASC";
                    PreparedStatement preparedStatement34 = connection.prepareStatement(query_34);
                    ResultSet resultSet34 = preparedStatement34.executeQuery();
                    while (resultSet34.next()) {
                        StringBuilder query34 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query34.append(resultSet34.getString(i)).append(":");
                        }
                        System.out.println(query34);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 16 -> {
                try {
                    String query_35 = "SELECT patients.first_name, patients.last_name from patients inner join admission on patients.patient_id = admission.patient_id inner join doctor on admission.doc_id = doctor.employee_id";
                    PreparedStatement preparedStatement35 = connection.prepareStatement(query_35);
                    ResultSet resultSet35 = preparedStatement35.executeQuery();
                    while (resultSet35.next()) {
                        StringBuilder query35 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query35.append(resultSet35.getString(i)).append(":");
                        }
                        System.out.println(query35);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 17 -> {
                try {
                    String query_41 = "SELECT employee.first_name, employee.last_name, employee.emp_type FROM employee ORDER BY last_name ASC, first_name ASC";
                    PreparedStatement preparedStatement41 = connection.prepareStatement(query_41);
                    ResultSet resultSet41 = preparedStatement41.executeQuery();
                    while (resultSet41.next()) {
                        StringBuilder query41 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query41.append(resultSet41.getString(i)).append(":");
                        }
                        System.out.println(query41);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 18 -> {
                try {
                    String query_42 = "SELECT distinct admission.admitdoc_id, admittingdoctor.employee_id, patients.patient_id from admission JOIN admittingdoctor on admission.admitdoc_id = admittingdoctor.employee_id JOIN patients on admission.patient_id = patients.patient_id WHERE patients.patient_id IN (SELECT patient_id FROM admission WHERE admin_date >= DATE_SUB(NOW(), INTERVAL 1 YEAR) GROUP BY patient_id HAVING COUNT(*) >= 4)";
                    PreparedStatement preparedStatement42 = connection.prepareStatement(query_42);
                    ResultSet resultSet42 = preparedStatement42.executeQuery();
                    while (resultSet42.next()) {
                        StringBuilder query42 = new StringBuilder();
                        for (int i = 1; i <= 1; i++) {
                            query42.append(resultSet42.getString(i)).append(":");
                        }
                        System.out.println(query42);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 19 -> {
                try {
                    String query_43 = "SELECT diagnoses.diag_id, diagnoses.diag_name, count(*) as total_occurences from diagnoses join admission on diagnoses.diag_id = admission.diag_id join patients on admission.patient_id = patients.patient_id where admission.admitdoc_id = '201' group by diagnoses.diag_id order by total_occurences DESC";
                    PreparedStatement preparedStatement43 = connection.prepareStatement(query_43);
                    ResultSet resultSet43 = preparedStatement43.executeQuery();
                    while (resultSet43.next()) {
                        StringBuilder query43 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query43.append(resultSet43.getString(i)).append(":");
                        }
                        System.out.println(query43);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 20 -> {
                try {
                    String query_44 = "SELECT treatment.treatment_id, treatment.treat_name, count(*) as total_occurences from treatment join admission on treatment.treatment_id = admission.treat_id join patients on admission.patient_id = patients.patient_id where admission.doc_id = '204' group by treatment.treatment_id order by total_occurences DESC";
                    PreparedStatement preparedStatement44 = connection.prepareStatement(query_44);
                    ResultSet resultSet44 = preparedStatement44.executeQuery();
                    while (resultSet44.next()) {
                        StringBuilder query44 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query44.append(resultSet44.getString(i)).append(":");
                        }
                        System.out.println(query44);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            case 21 -> {
                try {
                    String query_45 = "SELECT employee.first_name, employee.last_name, employee.emp_type from employee where not exists (select distinct admission.patient_id from admission where not exists (select admission.other_id join treatment on admission.treat_id = treatment.treatment_id join employee on admission.doc_id = employee.employee_id join nurse on admission.nurse_id = nurse.nurse_id))";
                    PreparedStatement preparedStatement45 = connection.prepareStatement(query_45);
                    ResultSet resultSet45 = preparedStatement45.executeQuery();
                    while (resultSet45.next()) {
                        StringBuilder query45 = new StringBuilder();
                        for (int i = 1; i <= 3; i++) {
                            query45.append(resultSet45.getString(i)).append(":");
                        }
                        System.out.println(query45);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
