/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication.pistas;

import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.Member;

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
    private BooleanProperty statusProperty;
    private Member user;
    private boolean oldForDay;
    private BooleanProperty oldForDayProperty;
    
    public CourtDayItem(LocalDate date, LocalTime time, boolean st) {
        madeForDay = date;
        fromTime = time;
        status = st;
        statusProperty = new SimpleBooleanProperty(status);
        user = null;
        oldForDay = false;
        oldForDayProperty = new SimpleBooleanProperty(oldForDay);
    }

    public void setOldForDay(boolean oldForDay) {
        this.oldForDay = oldForDay;
    }

    public boolean isOldForDay() {
        return oldForDay;
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
    
    public BooleanProperty statusProperty() {
        return statusProperty;
    }
    
    public BooleanProperty oldForDayProperty() {
        return oldForDayProperty;
    }
    
    
    public void setUser(Member member) {
        user = member;
    }
    
    public Member getUser(){
        return user;
    }
    
    
}

