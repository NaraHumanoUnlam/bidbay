/**
 *  Validaciones de la pagina en javascript
 */

 function mostrarPopUpNotificacion() {

			let popupNotificacion = document.getElementById('conteiner-notificacion');
			popupNotificacion.classList.toggle('hidden');
			
		}
		
		function goBack() {
		    window.history.go(-1);
		}
		
		function validarCompra() {
            var idVendedor = event.target.getAttribute("data-id-vendedor");
            var idUsuario = event.target.getAttribute("data-id-usuario");
            var compra = event.target.getAttribute("data-id-compra");
            console.log(compra);
            console.log(idVendedor);
            console.log(idUsuario);
            if(idUsuario == null || idUsuario == undefined){
                indicacionLogeo();
            } else if (idVendedor != idUsuario) {
                window.location.href = "/carrito/form/" + compra;
            } else {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'No podés comprar tus propios productos',
                showCloseButton: true,
                confirmButtonColor: '#348214',
                confirmButtonText: 'Entendido',
                cancelButtonText: '<i class="fa fa-thumbs-down"></i>'
            })
        }}
		
		function validarAccion(element){
			var href = element.dataset.href;
			var idUsuario = event.target.getAttribute("data-id-usuario");
			if(idUsuario == null || idUsuario == undefined){
				indicacionLogeo();
			} else{
				window.location.href = href;
			}
		}
		
		function indicacionLogeo(){
				Swal.fire({
	                icon: 'error',
	                title: 'Oops',
	                text: 'Necesitas una cuenta para hacer eso',
	                showCloseButton: false,
	                confirmButtonColor: '#348214',
	                confirmButtonText: 'Continuar',
	                cancelButtonText: '<i class="fa fa-thumbs-down"></i>'
	            }).then(function() {
	                window.location.href = "/login";
	            });
		}
		
		function confirmacionBorrado(element){
			var href = element.dataset.href;
			var idProd = event.target.getAttribute("data-id-producto");
			Swal.fire({
				  title: 'Estas seguro de borrar este producto?',
				  text: 'Esta acción no se puede deshacer',
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonText: 'Borrar',
				  cancelButtonText: 'Cancelar',
				}).then((result) => {
				  /* Read more about isConfirmed, isDenied below */
				  if (result.isConfirmed) {
				    Swal.fire('Borrado!', '', 'Se borro el producto').then(() => {
		                window.location.href = href + idProd;
		            });
				  }})
		}
		
		function confirmacionBorradoReview(element){
			var href = element.dataset.href;
			var idReview = event.target.getAttribute("data-id-review");
			Swal.fire({
				  title: 'Estas seguro de borrar esta review?',
				  text: 'Esta acción no se puede deshacer',
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonText: 'Borrar',
				  cancelButtonText: 'Cancelar',
				}).then((result) => {
				  /* Read more about isConfirmed, isDenied below */
				  if (result.isConfirmed) {
				    Swal.fire('Borrado!', '', 'Se borro la review').then(() => {
		                window.location.href = href + idReview;
		            });
				  }})
		}