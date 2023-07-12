/**
 *  Timer --- para subasta 
 */


function Timer(fecha, horaFin) {
	let dias = 0;
	let centesimos = 99;
	console.log(horas);
	let pantalla = document.getElementById('reloj');
	let fechaActual = new Date();
	
	console.log("fecha actual: " + fechaActual + "fecha: " + fecha);
	var fechaInicio = fecha;
	var fechaFin = fechaActual;
	var diff = fechaInicio.getTime() - fechaFin.getTime();
	dias = diff/(1000*60*60*24*31);
	var horasActuales = fechaActual.getHours();
	var minutosActuales = fechaActual.getMinutes();
	var segundosActuales = fechaActual.getSeconds();
	console.log(segundosActuales);
	var partesHora = horaFin.split(":");
	var horas = partesHora[0];
	var minutos = partesHora[1];
	var segundos = partesHora[2];
	horas = horas-horasActuales;
	minutos= minutos-minutosActuales;
	segundos=segundos-segundosActuales;
	if (pantalla) {

		const timer = setInterval(() => {
			pantalla.innerText = "Fantan: " + Math.round(dias, 0) + " días " + (horas < 10 ? "0" + horas : horas) + " horas " +
				(minutos < 10 ? "0" + minutos : minutos) + " minutos " +
				(segundos < 10 ? "0" + segundos : segundos) + " segundos ";
			centesimos--;
			if (centesimos < 0) {
				segundos--;
				centesimos = 99;
			}

			if (segundos < 0) {
				minutos--;
				segundos = 59;
				centesimos = 99;
			}

			if (minutos < 0) {
				horas--;
				minutos = 59;
				segundos = 59;
				centesimos = 99;
			}


			if (horas < 0) {
				if (dias > 0) {
					dias--;
					horas = 23;
					minutos = 59;
					segundos = 59;
					centesimos = 99;
				}
			}
		}, 10);
		//agregar logica para agregar el pago al ofertante
	}
}

window.addEventListener('load', () => {
	var fechaHTML = document.getElementById('fecha'); // Aquí debes establecer la fecha objetivo
	var hora = document.getElementById('hora'); ; // Aquí debes establecer la hora objetivo
	
	let fechaObjetivo = fechaHTML.innerText;
	let horaObjetivo = hora.innerText+":59";
	var partesFecha = fechaObjetivo.split('-');
	var fecha = new Date(partesFecha[0], partesFecha[1], partesFecha[2]);

	Timer(fecha, horaObjetivo);
})







