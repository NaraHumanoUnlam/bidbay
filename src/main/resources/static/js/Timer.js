/**
 *  Timer --- para subasta 
 */


function Timer(fecha, horaFin) {
	let dias = 0;
	
	var partesHora = horaFin.split(":");
	
	let pantalla = document.getElementById('reloj');
	let fechaActual = new Date();
	var fechaInicio = fechaActual;
	var fechaFin = fecha;
	var diff = fechaInicio.getTime() - fechaFin.getTime();
	dias = Math.round(diff/(1000*60*60*24*30),0) < 0 ? 0 : Math.round(diff/(1000*60*60*24*30),0);
	var horasActuales = fechaActual.getHours() == 00? 24 :fechaActual.getHours() ;
	var minutosActuales = fechaActual.getMinutes();
	var segundosActuales = fechaActual.getSeconds();
	let segundos=segundosActuales;
	let horas=horasActuales - (partesHora[0]+24);
	let minutos=minutosActuales - partesHora[1];
	
	
	if (pantalla) {
		const timer = setInterval(() => {
			pantalla.innerText = "Fantan: " + dias + " días " + (horas < 10 ? "0" + horas : horas) + " horas " +
				(minutos < 10 ? "0" + minutos : minutos) + " minutos " +
				(segundos < 10 ? "0" + segundos : segundos) + " segundos ";
			segundos--;
			if(segundos<=0){
				minutos--;
				segundos =59;
			}
			if (minutos <= 0) {
				horas--;
				minutos=59;
				segundos = 59;
				
			}
			if (horas <= 0) {
				dias < 0 ? 0 : dias--;
				console.log("dias: " + dias);
				horas=23;
				minutos = 59;
				segundos = 59;
			}
			
		}, 1000);
		//agregar logica para agregar el pago al ofertante
	}
}

window.addEventListener('load', () => {
	var fechaHTML = document.getElementById('fecha'); // Aquí debes establecer la fecha objetivo
	var hora = document.getElementById('hora'); // Aquí debes establecer la hora objetivo
	
	let fechaObjetivo = fechaHTML.innerText;
	let horaObjetivo = hora.innerText;
	var partesFecha = fechaObjetivo.split('-');
	var fecha = new Date(partesFecha[0], partesFecha[1], partesFecha[2]);

	Timer(fecha, horaObjetivo);
})







