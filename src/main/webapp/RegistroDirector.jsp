<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registro Director Elitec</title>
	<script src="js/bootstrap.js" type="text/javascript"></script>
	<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
	<script src="js/bootstrap.esm.js" type="text/javascript"></script>
	<script src="js/jquery-4.0.0.min.js" type="text/javascript"></script>
	
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap-grid.css" rel="stylesheet">
	<link href="css/bootstrap-reboot.css" rel="stylesheet">
	<link href="css/bootstrap-utilities.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>Registro de Director</h1>
		<form id="form" method="post" novalidate >
			<div class="row" style="margin-top: 2%;">
				<div class="col-4">
					<label for="registro">Nombre</label> 
					<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre" maxlength="30" required>
					<div class="invalid-feedback">Ingrese el nombre</div>
				</div>
				<div class="col-4">
					<label for="titulo">Email</label> 
					<input type="email" class="form-control" id="email" name="email" placeholder="Ingrese el email" maxlength="30" required>
					<div class="invalid-feedback">Ingrese el email</div>
				</div>
				<div class="col-4">
					<label for="tipo">Tipo</label> 
					<select class="form-control" id="tipo" name="tipo" >
						<option>[Seleccione]</option>
					</select>
				</div>
			</div>
			<div class="row justify-content-center" style="margin-top: 2%">
				<button class="btn btn-primary" id="btnRegistrar"style="width: 200px">Registrar</button>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		  $(document).ready(function () {
				$.ajax({
				url: 'cargaComboTipo', // URL del servlet para obtener categorías
				type: 'GET',
				success: function (data) {
					console.log('Categorías cargadas:', data);
					var comboBox = $('#tipo');
					data.forEach(function (obj) {
						comboBox.append('<option value="' + obj.idTipo + '">' + obj.descripcion + '</option>');
					});
				},
				error: function (xhr, status, error) {
					console.error('Error al cargar categorías:', error);
				}
			});
		});
		
	
		$("#btnRegistrar").click(function(e) {
			console.log("click en registrar");		
			e.preventDefault(); //Evita que el formulario se envíe automáticamente
	
			
			let form = $('#form')[0];
	        if (form.checkValidity() === false) {
	            $(form).addClass('was-validated');
	            return;
	        }
	
	     
	        $.ajax({
				url: 'registroDirectorAlias',
				type: 'POST',
				data: $(form).serialize(),
				success: function (response) {
					
					console.log('response >>> '+ response);
					//limpiar el formulario
					$('#form')[0].reset();
					
					//limpiar las validaciones
					$('#form').removeClass('was-validated');
					
					//enviar un mensaje de éxito al usuario en forma de div que dure 3 segundos
					$('#form').prepend('<div class="alert alert-success" role="alert">'+ response.mensajeSalida +'</div>');
					setTimeout(function () {
						$('.alert').remove();
					}, 3000);
				},
				error: function (xhr, status, error) {
					// Manejar errores aquí
					console.error('Error al registrar :', error);
				}
			});
		});
	</script>
</body>
</html>