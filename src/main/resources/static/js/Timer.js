/**
 *  Timer --- para subasta 
 */


function Timer(fecha) {
	let dias = 0;
	
	var fechaHora = new Date(fecha);
	
	var year = fechaHora.getFullYear();
	var month = fechaHora.getMonth() + 1; // Los meses comienzan desde 0, por lo que se agrega 1
	var day = fechaHora.getDate();
	var hour = fechaHora.getHours();
	var minute = fechaHora.getMinutes();
	var second = fechaHora.getSeconds();
	
	console.log("Año: " + year);
	console.log("Mes: " + month);
	console.log("Día: " + day);
	console.log("Hora: " + hour);
	console.log("Minuto: " + minute);
	console.log("Segundo: " + second);
	let pantalla = document.getElementById('reloj');
	let fechaActual = new Date();
	var diff = day - fechaActual.getDate();
	diff < 0 ? 0 : diff;
	console.log("diferencia: " + diff);
	dias = diff;
	var horas = hour -fechaActual.getHours();
	let segundos=second - fechaActual.getSeconds();
	let minutos=minute - fechaActual.getMinutes();
	
	
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
				horas=23;
				minutos = 59;
				segundos = 59;
			}
			
		}, 1000);
		//agregar logica para agregar el pago al ofertante
	}
}

window.addEventListener('load', () => {
	var fechaHTML = document.getElementById('fecha');  // Aquí debes establecer la hora objetivo
	console.log("mi fecha: " + fechaHTML.innerText);
	let fechaObjetivo = fechaHTML.innerText;

	Timer(fechaObjetivo);
})







