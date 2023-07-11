/**
 *  Timer --- para subasta 
 */


function Timer(fecha, horaFin) {
	let dias = 0;
	let centesimos = 99;
	console.log(horas);
	let pantalla = document.getElementById('reloj');
	let fechaActual = new Date();;
	var fechaInicio = fecha;
	var fechaFin = fechaActual;
	var diff = fechaFin.getTime() - fechaInicio.getTime();
	dias = diff / (1000 * 60 * 60 * 24);
	var horasActuales = fechaActual.getHours();
	var minutosActuales = fechaActual.getMinutes();
	var segundosActuales = fechaActual.getSeconds();
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
	}
}

window.addEventListener('load', () => {
	var fechaObjetivo = '12/07/2023'; // Aquí debes establecer la fecha objetivo
	var horaObjetivo = "23:59:59"; // Aquí debes establecer la hora objetivo

	var partesFecha = fechaObjetivo.split('/');
	var fecha = new Date(partesFecha[2], partesFecha[1] - 1, partesFecha[0]);

	Timer(fecha, horaObjetivo);
})







