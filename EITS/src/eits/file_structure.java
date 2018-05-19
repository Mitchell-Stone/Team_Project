
package eits;


public class file_structure {
    
    /*
    
    Right, so, this is the guideline on how to keep stuff organized.
    
    EITS
    
    -eits
    
        eits.java // main app logic
    
    -eits.db
    
        db_util.java //stores the connection methods
        db_type.java //enumerator storing database types for quicck switch between database
    
    -eits.login
    
        loginController.java // methods related to login
    
    -eits.security
    
        securityController.java // methods related to hashing and security
    
    -eits.administrator
    
        adminController.java // methods related to admin
    
    -eits.caseWorker
    
        caseWorkerController.java // methods related to caseWorkers
    
    -eits.student
    
        studentController.java // methods related to students
    
    -eits.beans // this package contains all the necessary beans for data exchange (set/get and variables)
                // each bean is a class 
    
        login.java
        admin.java
        caseWorker.java
        student.java
    
    NAMING CONVENTIONS
    
    every variable, method and class should be named with camelCase---> ex. firstName
    
    methods should be descriptive of their purpose as to make them less confusing
    
    classes should have a name as general as possible
    
    variables can have a generic name, as they should only be used as locally as possible.
    using global variables should be done only in case of things determining actions or tiers.
    
    methods should only have beans as arguments, this was we can use the sets ang gets to pull/push 
    data to and from them. (of course every case is specific)
    
    DATABASE
    
    queries should be kept as general as possible by using placeholders and question marks (which are placeholders)
    
    EX. String sql = "SELECT * FROM $tablename WHERE $columnname = ?";
        
        String query = sql.replace("$tablename", bean.getTable()).replace("$columnname", bean.getColumn());
    
    In this example a query has two placeholders and a question mark.
    The placeholders will be replaced before the try/catch block with the replace method.
    
        stmt.setInt(1, bean.getInteger());
    
    Question marks are instead replaced with this method. (Only works with PreparedStatement objects)
    
    Remember that question marks can only be used as values, never as table or column names.
    
    In the database if two columns describe the same thing they should have the same name.
    
    add your suggestions below.
    
    
    */
    
}
