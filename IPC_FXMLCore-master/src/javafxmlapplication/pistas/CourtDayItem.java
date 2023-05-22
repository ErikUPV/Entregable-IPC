/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication.pistas;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author erikb
 */
public class CourtDayItem {
    
    public static final boolean LIBRE = true;
    public static final boolean OCUPADO = false;

    private LocalDate madeForDay;
    private LocalTime fromTime;
    private boolean status;
    
    public CourtDayItem(LocalDate date, LocalTime time, boolean st) {
        madeForDay = date;
        fromTime = time;
        status = st;
    }

    public LocalDate getMadeForDay() {
        return madeForDay;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}

