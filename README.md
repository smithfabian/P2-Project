# P2-109: Faster Database Connection
I had to switch from Mysql Connector-J to Apache Commons so that we could utilise connection pooling.

## Add Apache Commons DPC and Commons Pool to classpath in IntelliJ IDEA

1. In IntelliJ IDEA, right-click on the root folder of your project in the Project tool window and choose **Open Module Settings** or press `F4`.
2. Go to the **Dependencies** tab.
3. Click the **+** button and choose **JARs or directories..**.
4. In P2-Project folder select both JAR files (`commons-dbcp2-2.9.0.jar`,  `commons-pool2-2.11.1.jar` and `commons-logging-1.2.jar`), and click **OK**.
5. Click **Apply** and then **OK** to close the Module Settings window.

## Connect to database in code

I have kept the class interface the same so you still connect via the same method **but(!)** the class is fully static now which mean you no longer initiate an object, instead you call the method directly.

New approach:
```java
import main.app.models.DatabaseConnection;

Connection conn = DatabaseConnection.getConnection();
// query the databse
conn.close();

```
Old approach:
```java
import main.app.models.DatabaseConnection;

DatabaseConnection db = new DatabaseConnection()
Connection conn = db.getConnection();
// query the databse
conn.close();
```