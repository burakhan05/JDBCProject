# JDBCProject

##Installation

Firstly, you must add mysql-connector on your project ==> http://mvnrepository.com/artifact/mysql/mysql-connector-java
Then add package "com.hamzaburakhan.jdbc" on your project and after that you can use it.

##Using
###User Class

```Java
@Table("tablename")
public class User {
	
	private int id;

	@RealName("name")
	private String ad;

	@RealName("lastName")
	private String soyad;

	@Embeded
	private Adress adres;
	
	getter and setters...

```
###Adress Class

```Java
@Table("addresses")
public class User {
	
	@RealName("id")
	private int id;

	@RealName("il")
	private String il;
	
	@RealName("ilçe")
	private String il;
	
	getter and setters...

```
###Insert example
```Java
DatabaseConnection dbconnection = new DatabaseConnection(HOST, DB, USERNAME, PASSWORD, true); // true is encode_utf8
dbconnection.showSqlCode(true);	
User user = new User(20,"New","Record");
dbconnection.insertRow(user);
```

###Select example
```Java
DatabaseConnection dbconnection = new DatabaseConnection(HOST, DB, USERNAME, PASSWORD, true);
String selectSQL = "Select * FROM tabloadi,addresses Where id < ? AND tabloadi.adres_id=addresses.id ";
//Sorgundaki soru işaretleri için parametreleri giriyorsun.Sırasıyla girmen önemli.
//You must enter the parameters for the question marks in your query. It is important to be sequential.
QueryParams paramaters = new QueryParams();
paramaters.addParams(8);
List<User> users = dbconnection.selectRecods(selectSQL, paramaters,User.class);

```
###Update example
```Java
DatabaseConnection dbconnection = new DatabaseConnection(HOST, DB, USERNAME, PASSWORD, true);
String updateSQL = "UPDATE tabloadi SET name=?, lastname=? WHERE id=?";
//Sorgundaki soru işaretleri için parametreleri giriyorsun.Sırasıyla girmen önemli.
//You must enter the parameters for the question marks in your query. It is important to be sequential.
QueryParams params = new QueryParams();
params.addParams("Update");
params.addParams("Example");
params.addParams(6);
dbconnection.update(updateSQL, params);
```

###Delete example
```Java
DatabaseConnection dbconnection = new DatabaseConnection(HOST, DB, USERNAME, PASSWORD, true);
dbconnection.showSqlCode(true);
String deleteSQL = "DELETE FROM tabloadi WHERE id=? AND name=?";
//Sorgundaki soru işaretleri için parametreleri giriyorsun.Sırasıyla girmen önemli.
//You must enter the parameters for the question marks in your query. It is important to be sequential.
QueryParams paramaters = new QueryParams();
paramaters.addParams(20);
paramaters.addParams("New");
int result = dbconnection.delete(deleteSQL, paramaters);
System.out.println(result); // Number of affected rows
```





