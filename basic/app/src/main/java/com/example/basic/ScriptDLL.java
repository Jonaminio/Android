package com.example.basic;

public class ScriptDLL {
    public static String getCreateTableChecklist(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE Checklist (");
        sql.append(" ID INTEGER PRIMARY KEY");
        sql.append(" NOT NULL,");
        sql.append(" Description TEXT (200) DEFAULT (''),");
        sql.append(" Active BOOLEAN DEFAULT True");
        sql.append(");");
        return sql.toString();
    }

}
