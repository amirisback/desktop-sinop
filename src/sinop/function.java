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

import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Faisal Amir
 */
public class function {
    
    private char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
    private StringBuilder stringBuilder = new StringBuilder();
    private Random random = new Random();
    private String output, nilai_waktu, waktuNoDetik;
    private Date now = new Date();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private String outputDate = simpleDateFormat.format(now);
    int jamReal, menitReal, detik;
    String nilai_jam, nilai_menit, nilai_detik;



    public void setNilai_waktu(String nilai_waktu) {
        this.nilai_waktu = nilai_waktu;
    }

    public String getDateToday() {
        return outputDate;
    }

    public Date getNow() {
        return now;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }
    
    public char[] getChars() {
        return chars;
    }

    public void setChars(char[] chars) {
        this.chars = chars;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public String getRandomChar() {
        for (int lenght = 0; lenght < 5; lenght++) {
            Character character = chars[random.nextInt(chars.length)];
            stringBuilder.append(character);
        }
        output = stringBuilder.toString();
        stringBuilder.delete(0, 5);
        return output;
    }
    
    public void cariWaktu(){
        Calendar cal = new GregorianCalendar();     
        jamReal = cal.get(Calendar.HOUR_OF_DAY);  //24 jam
        menitReal = cal.get(Calendar.MINUTE);
        detik = cal.get(Calendar.SECOND); 
            String nol_jam = "", nol_menit = "",nol_detik = "";
            if(jamReal <= 9) 
                nol_jam= "0";
            if(menitReal <= 9) 
                nol_menit= "0";
            if(detik <= 9) 
                nol_detik= "0"; 
        //berhasil
        nilai_jam = nol_jam + Integer.toString(jamReal);
        nilai_menit = nol_menit + Integer.toString(menitReal);
        nilai_detik = nol_detik + Integer.toString(detik);
    }
    
    public void getTime(JLabel txt_time){
        Thread p = new Thread(){
            public void run(){
                for(;;){
                    cariWaktu();
                    txt_time.setText(nilai_jam + " : " + nilai_menit + " : " + nilai_detik);
                    try {
                        sleep(1000);
                    }catch (InterruptedException ex){
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        p.start();
    }
    
    public void setTextDate(JLabel txt_date){
        txt_date.setText(outputDate);
    }
    
    
    public String getNilai_waktu() {
        nilai_waktu = nilai_jam + ":" + nilai_menit + ":" + nilai_detik;
        return nilai_waktu;
    }

    public String getWaktuNoDetik() {
        cariWaktu();
        waktuNoDetik = nilai_jam + " : " + nilai_menit;
        return waktuNoDetik;
    }
}