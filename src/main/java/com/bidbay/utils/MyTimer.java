package com.bidbay.utils;

import java.util.TimerTask;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MyTimer extends TimerTask{
	private Integer horas = 23;
	private Integer minutes = 59;
    private Integer seconds = 59;
    
	@Override
    public void run() {
		 seconds--;

	        if (seconds < 0) {
	            minutes--;
	            seconds = 59;
	        }
	        
	        if(minutes<0) {
	        	horas--;
	        	minutes=59;
	        	seconds=59;
	        }

	        // Actualizar los valores en la página
	        updateTimer(horas,minutes, seconds);

	        // Verificar si se ha alcanzado 0 minutos
	        if (minutes == 0 && seconds == 0) {
	            // Cancelar el temporizador si se ha alcanzado el tiempo deseado
	            cancel();
	        }
    }
	
	 private void updateTimer(Integer horas,Integer minutes, Integer seconds) {
		 ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	        if (requestAttributes != null) {
	            String script = String.format("<script>updateTimer(%d,%d, %d);</script>",horas, minutes, seconds);
	            requestAttributes.getRequest().setAttribute("timerScript", script);
	        }
	    }

}
