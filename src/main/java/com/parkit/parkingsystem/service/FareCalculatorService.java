package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;
public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }
        
        double reduction = 1;
        
       
        if(ticket.isAlreadyCame()) {
        	reduction = 0.05;
        }
        
        
        long inHour = ticket.getInTime().getTime()/60000;
        long outHour = ticket.getOutTime().getTime()/60000;
        System.out.println("inHour  :" + inHour);
        System.out.println("  outHour  :" + outHour);
        double durationMinutes = (outHour - inHour);
        System.out.println("durationMinutes  :"+durationMinutes);
        double duration = durationMinutes/60;
        System.out.println("duration prima  :"+ duration);
        
        //verify se duration maggio to 30 Minut return 0
        if(duration <= 0.5) {
        	duration =0;
        }
        System.out.println("duration  dopo :" +duration );
        
     
        	switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                ticket.setPrice(reduction*duration * Fare.CAR_RATE_PER_HOUR);
                break;
            }
            case BIKE: {
                ticket.setPrice(reduction*duration * Fare.BIKE_RATE_PER_HOUR);
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
         }
  
    }
}

