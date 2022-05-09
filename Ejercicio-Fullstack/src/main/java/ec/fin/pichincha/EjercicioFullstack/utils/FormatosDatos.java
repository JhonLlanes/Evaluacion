package ec.fin.pichincha.EjercicioFullstack.utils;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class FormatosDatos {
    private static DecimalFormat df2 = new DecimalFormat("#,##");

    public double getformatodouble(String costo){
        double val = Double.parseDouble(costo);
        df2.format(val);
        return val;
    }

    public  String datatimeservidor(){
        LocalDateTime myObj = LocalDateTime.now();
        return ""+myObj;
    }

    public Date date(){
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        return date;
    }

}
