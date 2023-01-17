package com.ahmetdayi.ticketapp.entity.converter;

import com.ahmetdayi.ticketapp.core.constant.Constant;
import com.ahmetdayi.ticketapp.core.exception.WrongStatueException;
import com.ahmetdayi.ticketapp.entity.Statue;
import org.springframework.stereotype.Component;

@Component
public class StatueConverter {

    public Statue convert(String from){
        if (from.equalsIgnoreCase("CANCELED")){
            return Statue.CANCELED;
        } else if (from.equalsIgnoreCase("DELAYED")) {
            return Statue.DELAYED;
        } else if (from.equalsIgnoreCase("BOUGHT")) {
            return Statue.BOUGHT;
        }else {
            throw new WrongStatueException(Constant.WRONG_STATUE);
        }
    }
}
