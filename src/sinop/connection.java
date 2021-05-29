/*
 * 
 * FrogoBox Inc License
 * ------------------------------------------
 * 
 * Copyright (C) 2018      
 * All rights reserved
 * 
 * Name     : Muhammad Faisal Amir
 * E-mail   : f.miir117@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * 
 * id.amirisback.frogobox
 * 
 */
package sinop;

import java.io.File; //import library file

//import library dari pluggin library JDBC MySQL--------------------------------
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
//------------------------------------------------------------------------------

/**
 *
 * @author Faisal Amir
 */
public class connection {
    //Deklarasi data path hasil program ----------------------------------------
    private String Folder_Data = "C:/sinop";
    private String Folder_Notepad = Folder_Data + "/Data_Notepad";
    private String Folder_Excel = Folder_Data + "/Data_Excel";
    private String Folder_Notulensi = Folder_Excel +"/Data_Notulensi";
    private String Folder_Sidang = Folder_Excel + "/Data_Sidang";
    //--------------------------------------------------------------------------
    
    
    //Deklarasi operasi CRUD Database-------------------------------------------
    private PreparedStatement preStatement;
    private Statement statement;
    private Connection connect;
    private ResultSet resultSet;
    //--------------------------------------------------------------------------
    
    //Koneksi SQLite------------------------------------------------------------
    private String driver = "com.mysql.jdbc.Driver";
    private String Nama_database = "sinop.db";
    private String url = Folder_Data + "/" + Nama_database;
    private String url_db = "jdbc:sqlite:" + url;
    private String sql_table_data_sidang;
    private String sql_table_data_notulensi;
    //--------------------------------------------------------------------------
    
    public connection() {
    }

    public PreparedStatement getPreStatement() {
        return preStatement;
    }

    public void setPreStatement(PreparedStatement preStatement) {
        this.preStatement = preStatement;
    }
    
    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl_db() {
        return url_db;
    }

    public void setUrl_db(String url_db) {
        this.url_db = url_db;
    }

    
    public String getFolder_Data() {
        return Folder_Data;
    }

    public void setFolder_Data(String Folder_Data) {
        this.Folder_Data = Folder_Data;
    }

    public String getFolder_Notulensi() {
        return Folder_Notulensi;
    }

    public void setFolder_Notulensi(String Folder_Notulensi) {
        this.Folder_Notulensi = Folder_Notulensi;
    }

    public String getFolder_Excel() {
        return Folder_Excel;
    }

    public void setFolder_Excel(String Folder_Excel) {
        this.Folder_Excel = Folder_Excel;
    }

    public String getFolder_Notepad() {
        return Folder_Notepad;
    }

    public void setFolder_Notepad(String Folder_Notepad) {
        this.Folder_Notepad = Folder_Notepad;
    }

    public String getFolder_Sidang() {
        return Folder_Sidang;
    }

    public void setFolder_Sidang(String Folder_Sidang) {
        this.Folder_Sidang = Folder_Sidang;
    }
    
    
    

    public String getNama_database() {
        return Nama_database;
    }

    public void setNama_database(String Nama_database) {
        this.Nama_database = Nama_database;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSql_table_data_sidang() {
        return sql_table_data_sidang;
    }

    public void setSql_table_data_sidang(String sql_table_data_sidang) {
        this.sql_table_data_sidang = sql_table_data_sidang;
    }

    public String getSql_table_data_notulensi() {
        return sql_table_data_notulensi;
    }

    public void setSql_table_data_notulensi(String sql_table_data_notulensi) {
        this.sql_table_data_notulensi = sql_table_data_notulensi;
    }

    //Create DataBase SQLite----------------------------------------------------
    public void createAllTable(){
        sql_table_data_sidang = "CREATE TABLE data_sidang (\n" +
                                "    id_sidang      VARCHAR (12) PRIMARY KEY,\n" +
                                "    jenis_sidang   VARCHAR (30),\n" +
                                "    waktu_sidang   VARCHAR (15),\n" +
                                "    nama_sidang    VARCHAR (25),\n" +
                                "    tempat_sidang  VARCHAR (25),\n" +
                                "    presidium_1    VARCHAR (30),\n" +
                                "    presidium_2    VARCHAR (30),\n" +
                                "    presidium_3    VARCHAR (30),\n" + 
                                "    tgl_mulai      VARCHAR (30),\n" +
                                "    tgl_selesai    VARCHAR (30), \n" +
                                "    status         VARCHAR (30) \n" +
                                ");";
        
        sql_table_data_notulensi = "CREATE TABLE data_notulensi (\n" +
                                "    id_notulensi VARCHAR (10) PRIMARY KEY,\n" +
                                "    tanggal      VARCHAR (25),\n" +
                                "    waktu        VARCHAR (15),\n" +
                                "    nama         VARCHAR (25),\n" +
                                "    interupsi    VARCHAR (30),\n" +
                                "    redaksi      TEXT (3000),\n" +
                                "    id_sidang    VARCHAR (10) REFERENCES data_sidang (id_sidang) ON DELETE CASCADE\n" +
                                "                                                                 ON UPDATE CASCADE\n" +
                                ");";
        try {
            statement = connect.createStatement();
            //Create Table data_sidang
            statement.execute(sql_table_data_sidang);
            //Create Table data_notulensi
            statement.execute(sql_table_data_notulensi);
        } catch (SQLException e) {
        }

    }   
    //--------------------------------------------------------------------------
    
    public void ConnectToDB(){
        try{
            File folder = new File(Folder_Data);
            if (!folder.exists()) {
                folder.mkdir();
            }
            //Source Code Koneksi SQLite----------------------------------------
            connect = DriverManager.getConnection(url_db);
            DatabaseMetaData meta = connect.getMetaData();
            //------------------------------------------------------------------
        }catch (Exception e){
            System.out.println(e);
        }
    }
        
    public void dataSinop() {
        try {
            //Membuat Folder Data------------------------------------------------

            File folder_notepad = new File(Folder_Notepad);
            if (!folder_notepad.exists()) {
                folder_notepad.mkdir();
            }
            
            File folder_excel = new File(Folder_Excel);
            if (!folder_excel.exists()) {
                folder_excel.mkdir();
            }
            
            File folder_notulensi = new File(Folder_Notulensi);
            if (!folder_notulensi.exists()) {
                folder_notulensi.mkdir();
            }
            
            File folder_sidang = new File(Folder_Sidang);
            if (!folder_sidang.exists()) {
                folder_sidang.mkdir();
            }

            //------------------------------------------------------------------
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
   
}
