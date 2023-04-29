To run this project, you will need to reference
the Project.pdf file for the Hospital Database project.

The user is to input an integer between 1 and 21 to
call the corresponding query.

````java
    System.out.println("Enter an integer between 1 and 20 to select a query: ");
    Scanner user = new Scanner(System.in);
    int userInput = user.nextInt();
````
For example, the user selects integer 4, and this 
corresponds to Query 2.1 :

```java
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
        
```    
Which returns the following: 

101:Tom:Gulley:M:Ashley H:Aetna:
102:Tim:Jackson:M:Bryan K:BCBS:
103:Amanda:Alvarez:F:Tim A:United:
104:Tammy:Lee:F:Yong Kim:Aetna:
105:Shaq:Jones:M:Parker Jones:BCBS:
106:Allison:Thompson:F:Hanna S.:United:
107:Keiko:Hashimoto:F:Yuki Tsunoda:Humana:
108:Will:Smith:M:Jaden Smith:Humana: