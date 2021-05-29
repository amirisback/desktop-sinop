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

import java.awt.Desktop;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Faisal Amir
 */
public class crud {
    
    connection conn = new connection();
    private File file;
    private String DataNotulensi, sdg_id, sdg_jenis, sdg_waktu, sdg_nama, 
            sdg_tempat, sdg_pres1, sdg_pres2, sdg_pres3, sdg_tgl_mulai, sdg_tgl_selesai, 
            sdg_status;
    private String ntl_id, ntl_tanggal, ntl_waktu, ntl_nama, ntl_interupsi, ntl_redaksi;
    private String sql = null;
        
    public crud(){
        conn.ConnectToDB();
    }
    
    public void CreateTable(String[] daftarColumn, JTable jTable, DefaultTableModel list){
        jTable.setModel(list);
        for (int i = 0; i < daftarColumn.length+1; i++) {
            if(i==0) {
                list.addColumn("No.");
            } else {
                list.addColumn(daftarColumn[i-1]);
            }
        }
    }
    
    
    // SQL Data Sidang -----------------------------------------------------------------
    public void InsertDataSidang(String idSidang, String jenis, String waktu,
            String namaSidang, String tempat, String presidium1, String presidium2, 
            String presidium3, String tgl_skrng, String tgl_selesai, String status){
        try {
            sql = "INSERT INTO data_sidang(id_sidang, jenis_sidang, "
                        + "waktu_sidang, nama_sidang, tempat_sidang, presidium_1, "
                        + "presidium_2, presidium_3, tgl_mulai, tgl_selesai, status)"
                        + " VALUES ('"+idSidang+"','"+jenis+"','"+waktu+"','"+namaSidang+"','"
                        + ""+tempat+"','"+presidium1+"','"+presidium2+"',"
                        + "'"+presidium3+"','"+tgl_skrng+"','"+tgl_selesai+"','"+status+"')";
            conn.setPreStatement(conn.getConnect().prepareStatement(sql));
            conn.getPreStatement().execute();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conn.getPreStatement().close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }        
    }
    
    public void DeleteDataSidang(Object id){
        try {
            sql = "DELETE FROM data_sidang WHERE id_sidang = '"+id+"'";
            conn.setPreStatement(conn.getConnect().prepareStatement(sql));
            conn.getPreStatement().executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);   
        } finally {
            try {
                conn.getPreStatement().close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    
    public void ReadDataSidang(DefaultTableModel list){
        try {
            list.getDataVector().removeAllElements();
            list.fireTableDataChanged();
            int i = 1;
            sql = "SELECT * FROM data_sidang";
            conn.setStatement(conn.getConnect().createStatement());
            conn.setResultSet(conn.getStatement().executeQuery(sql));
            while (conn.getResultSet().next()){
                Object[] data_sidang = new Object[7];
                data_sidang[0] = i + ".";
                data_sidang[1] = conn.getResultSet().getString("id_sidang");
                data_sidang[2] = conn.getResultSet().getString("jenis_sidang");
                data_sidang[3] = conn.getResultSet().getString("nama_sidang");
                data_sidang[4] = conn.getResultSet().getString("tgl_mulai");
                data_sidang[5] = conn.getResultSet().getString("tgl_selesai");
                data_sidang[6] = conn.getResultSet().getString("status");
                list.addRow(data_sidang);
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    public void PendingSidang(String Status, Object id){
        try {
            sql = "UPDATE data_sidang SET status = '"+Status+"' WHERE id_sidang = '"+id+"'";
            conn.setPreStatement(conn.getConnect().prepareStatement(sql));
            conn.getPreStatement().executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    //---------------------------------------------------------------------------
    
    public void StartSidang(Object id, JLabel txt_jenis, JLabel txt_sidang,
                JLabel txt_pres1, JLabel txt_pres2, JLabel txt_pres3, JLabel txt_tempat){
        try {
            int i = 1;
            sql = "SELECT * FROM data_sidang WHERE id_sidang = '"+id+"'";
            conn.setStatement(conn.getConnect().createStatement());
            conn.setResultSet(conn.getStatement().executeQuery(sql));
            while (conn.getResultSet().next()){
                txt_jenis.setText(conn.getResultSet().getString("jenis_sidang"));
                txt_sidang.setText(conn.getResultSet().getString("nama_sidang"));
                txt_pres1.setText(conn.getResultSet().getString("presidium_1"));
                txt_pres2.setText(conn.getResultSet().getString("presidium_2"));
                txt_pres3.setText(conn.getResultSet().getString("presidium_3"));
                txt_tempat.setText("Tempat : " + conn.getResultSet().getString("tempat_sidang"));
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        } 
        
    }
    
        
        public void insertDataNotulensi(String id_notulensi, String tanggal,
                String waktu, String nama, String interupsi, String redaksi,
                Object id_sidang){
            try {
                sql = "INSERT INTO data_notulensi(id_notulensi, tanggal, waktu,"
                        + " nama, interupsi, redaksi, id_sidang) VALUES "
                        + "('"+id_notulensi+"','"+tanggal+"','"+waktu+"',"
                        + "'"+nama+"','"+interupsi+"','"+redaksi+"',"
                        + "'"+id_sidang+"')";
                conn.setPreStatement(conn.getConnect().prepareStatement(sql));
                conn.getPreStatement().execute();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                try {
                    conn.getPreStatement().close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        
        public void readDataNotulensi(DefaultTableModel list, Object id){
            try {
                list.getDataVector().removeAllElements();
                list.fireTableDataChanged();
                int i = 1;
                sql = "SELECT * FROM data_notulensi where id_sidang = '"+id+"'";
                conn.setStatement(conn.getConnect().createStatement());
                conn.setResultSet(conn.getStatement().executeQuery(sql));
                while (conn.getResultSet().next()){
                    Object[] data_notulensi = new Object[7];
                    data_notulensi[0] = i + ".";
                    data_notulensi[1] = conn.getResultSet().getString("id_notulensi");
                    data_notulensi[2] = conn.getResultSet().getString("waktu");
                    data_notulensi[3] = conn.getResultSet().getString("tanggal");
                    data_notulensi[4] = conn.getResultSet().getString("interupsi");
                    data_notulensi[5] = conn.getResultSet().getString("nama");
                    data_notulensi[6] = conn.getResultSet().getString("redaksi");
                    list.addRow(data_notulensi);
                    i++;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        
        }
        
    public void deleteDataNotulensi(Object id, DefaultTableModel list, int row, Object id_sidang){
        try {
            sql = "DELETE FROM data_notulensi WHERE id_notulensi = '"+id+"'";
            conn.setPreStatement(conn.getConnect().prepareStatement(sql));
            conn.getPreStatement().executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);   
        }
        list.removeRow(row);
        list.fireTableDataChanged();
        readDataNotulensi(list, id_sidang);
        
    }
        
        
        
    private void getNamaTxt(Object id_sidang){
        try {
            sql = "SELECT * FROM data_sidang where id_sidang = '"+id_sidang+"'";
            conn.setStatement(conn.getConnect().createStatement());
            conn.setResultSet(conn.getStatement().executeQuery(sql));
            while (conn.getResultSet().next()){
                DataNotulensi = conn.getFolder_Notepad() + "/Notulen - " + conn.getResultSet().getString("tgl_mulai") + " - "+ conn.getResultSet().getString("nama_sidang") + ".txt";
                sdg_id = conn.getResultSet().getString("id_sidang");
                sdg_jenis = conn.getResultSet().getString("jenis_sidang");
                sdg_nama = conn.getResultSet().getString("nama_sidang");
                sdg_pres1 = conn.getResultSet().getString("presidium_1");
                sdg_pres2 = conn.getResultSet().getString("presidium_2");
                sdg_pres3 = conn.getResultSet().getString("presidium_3");
                sdg_tempat = conn.getResultSet().getString("tempat_sidang");
                sdg_status = conn.getResultSet().getString("status");
                sdg_tgl_mulai = conn.getResultSet().getString("tgl_mulai");
                sdg_tgl_selesai = conn.getResultSet().getString("tgl_selesai");
                sdg_waktu = conn.getResultSet().getString("waktu_sidang");
            }
        } catch (SQLException e) {
        }
    }
    
    
    
    
        public void writeDataNotulensi(BufferedWriter buffer, Object id) throws IOException{
            try {
                int i = 1;
                sql = "SELECT * FROM data_notulensi where id_sidang = '"+id+"'";
                conn.setStatement(conn.getConnect().createStatement());
                conn.setResultSet(conn.getStatement().executeQuery(sql));
                while (conn.getResultSet().next()){
                    ntl_id = conn.getResultSet().getString("id_notulensi");
                    ntl_waktu = conn.getResultSet().getString("waktu");
                    ntl_interupsi = conn.getResultSet().getString("interupsi");
                    ntl_nama = conn.getResultSet().getString("nama");
                    ntl_tanggal = conn.getResultSet().getString("tanggal");
                    ntl_redaksi = conn.getResultSet().getString("redaksi");
                    buffer.write("(" +ntl_waktu + ") - (" + ntl_tanggal + ") - " + "("+ntl_interupsi + "/" + ntl_nama + ") \t:" );buffer.newLine();
                    buffer.write(ntl_redaksi);buffer.newLine();buffer.newLine();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        
        }
    
    
    
    public void writeText(Object id_sidang){
        getNamaTxt(id_sidang);
        BufferedWriter bw = null;
	FileWriter fw = null;
            try {
                
                File file = new File(DataNotulensi);
                // if file doesnt exists, then create it
                if (!file.exists()) {
                        file.createNewFile();
                }
                // true = append file
                //fw = new FileWriter(file.getAbsoluteFile(), true);
                fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                
                bw.write("================== NOTULENSI SIDANG ==================");bw.newLine();
                bw.write("Id Sidang \t: " + sdg_id);bw.newLine();
                bw.write("Tanggal \t: " + sdg_tgl_mulai);bw.newLine();
                bw.write("Jenis Sidang \t: " + sdg_jenis);bw.newLine();
                bw.write("Sidang \t\t: " + sdg_nama);bw.newLine();
                bw.write("Waktu \t\t: " + sdg_waktu);bw.newLine();
                bw.write("Tempat Sidang \t: " + sdg_tempat);bw.newLine();
                bw.write("Presidium 1 \t: " + sdg_pres1);bw.newLine();
                bw.write("Presidium 2 \t: " + sdg_pres2);bw.newLine();
                bw.write("Presidium 3 \t: " + sdg_pres3);bw.newLine();
                bw.write("======================================================");bw.newLine();bw.newLine();
                writeDataNotulensi(bw, id_sidang);bw.newLine();
                bw.write("------------------------------------------------------");bw.newLine();
                bw.write("Created By SINOP - Copyright 2018 All Right Reserved");
            } catch (IOException e) {
            } finally {
                try {
                    if (bw != null)
                            bw.close();
                    if (fw != null)
                            fw.close();
                    Desktop.getDesktop().open(new File(DataNotulensi));
                } catch (IOException ex) {
                }
            }

	}
}
        
