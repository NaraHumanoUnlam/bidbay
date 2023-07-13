function Timer(fecha) {
  var fechaObjetivo = new Date(fecha);

  var pantalla = document.getElementById('reloj');
  var fechaActual = new Date();

  var diff = Math.floor((fechaObjetivo - fechaActual) / (1000 * 60 * 60 * 24)); // Diferencia en días
  var horas = Math.floor((fechaObjetivo - fechaActual) / (1000 * 60 * 60)) % 24; // Diferencia en horas
  var minutos = Math.floor((fechaObjetivo - fechaActual) / (1000 * 60)) % 60; // Diferencia en minutos
  var segundos = Math.floor((fechaObjetivo - fechaActual) / 1000) % 60; // Diferencia en segundos

  if (pantalla) {
    const timer = setInterval(() => {
      pantalla.innerText = "Faltan: " + diff + " días " +
        (horas < 10 ? "0" + horas : horas) + " horas " +
        (minutos < 10 ? "0" + minutos : minutos) + " minutos " +
        (segundos < 10 ? "0" + segundos : segundos) + " segundos";

      if (segundos <= 0) {
        minutos--;
        segundos = 59;
      }
      if (minutos < 0) {
        horas--;
        minutos = 59;
      }
      if (horas < 0) {
        diff--;
        horas = 23;
      }
      if (diff < 0) {
        clearInterval(timer);
        pantalla.innerText = "La fecha ha pasado";
      }
      segundos--;
    }, 1000);
  }
}

window.addEventListener('load', () => {
  var fechaHTML = document.getElementById('fecha'); // Aquí debes establecer el elemento HTML que contiene la fecha objetivo
  var fechaObjetivo = fechaHTML.innerText;

  Timer(fechaObjetivo);
});
