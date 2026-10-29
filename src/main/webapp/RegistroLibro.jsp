<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Registro Libro Elitec</title>

	<!-- Solo bootstrap.css: grid, reboot y utilities ya vienen incluidos -->
	<link href="css/bootstrap.css" rel="stylesheet">

	<!-- jQuery SIEMPRE antes que bootstrap.bundle.js -->
	<script src="js/jquery-4.0.0.min.js" type="text/javascript"></script>
	<!-- Solo UNO: bundle incluye Popper, que bootstrap.js necesita -->
	<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
</head>
<body class="bg-light">
	<div class="container py-4">
		<div class="card shadow-sm">
			<div class="card-header bg-primary text-white">
				<h1 class="h4 mb-0">Registro de Libro</h1>
			</div>
			<div class="card-body">
				<!-- Aquí se inserta el mensaje de éxito/error -->
				<div id="mensaje"></div>

				<form id="formLibro" method="post" novalidate>
					<div class="row g-3">
						<div class="col-md-3">
							<label for="registro" class="form-label">Registro</label>
							<input type="text" class="form-control" id="registro" name="registro"
								placeholder="Ingrese el registro" maxlength="30" required>
							<div class="invalid-feedback">Ingrese el registro</div>
						</div>
						<div class="col-md-9">
							<label for="titulo" class="form-label">Título</label>
							<input type="text" class="form-control" id="titulo" name="titulo"
								placeholder="Ingrese el título" maxlength="30" required>
							<div class="invalid-feedback">Ingrese el título</div>
						</div>
						<div class="col-md-4">
							<label for="pais" class="form-label">País</label>
							<input type="text" class="form-control" id="pais" name="pais"
								placeholder="Ingrese el país" maxlength="30" required>
							<div class="invalid-feedback">Ingrese el país</div>
						</div>
						<div class="col-md-4">
							<label for="autor" class="form-label">Autor</label>
							<input type="text" class="form-control" id="autor" name="autor"
								placeholder="Ingrese el autor" maxlength="30" required>
							<div class="invalid-feedback">Ingrese el autor</div>
						</div>
						<div class="col-md-4">
							<label for="fecCreacion" class="form-label">Fecha de Creación</label>
							<input type="date" class="form-control" id="fecCreacion" name="fecCreacion" required>
							<div class="invalid-feedback">Ingrese la Fecha de Creación</div>
						</div>
					</div>
					<div class="row mt-4">
						<div class="col text-center">
							<button type="submit" class="btn btn-primary px-5" id="btnRegistrar">
								<span id="btnTexto">Registrar</span>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		// Escuchamos el SUBMIT del formulario, no el click del botón.
		// Así también funciona si el usuario presiona Enter en un campo.
		$("#formLibro").on("submit", function (e) {
			e.preventDefault();
			console.log("submit del formulario");

			let form = this;

			// Validación nativa + estilos de Bootstrap
			if (!form.checkValidity()) {
				$(form).addClass("was-validated");
				return;
			}

			let $btn = $("#btnRegistrar");

			$.ajax({
				url: "registraLibroAlias",
				type: "POST",
				data: $(form).serialize(),
				beforeSend: function () {
					// Evita dobles envíos y da feedback visual
					$btn.prop("disabled", true).addClass("disabled");
					$("#btnTexto").text("Registrando...");
				},
				success: function (response) {
					console.log("response >>> ", response);

					// Soportamos respuesta JSON o texto plano del servidor
					let mensaje = (response && response.mensajeSalida)
						? response.mensajeSalida
						: "Libro registrado correctamente";

					// Limpiar formulario y validaciones
					form.reset();
					$(form).removeClass("was-validated");

					// Mensaje con fadeIn, dura 3 segundos
					$("#mensaje").hide().html(
						'<div class="alert alert-success alert-dismissible fade show" role="alert">'
						+ mensaje
						+ '<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
						+ '</div>'
					).fadeIn(300);

					setTimeout(function () {
						$("#mensaje .alert").fadeOut(300, function () {
							$(this).remove();
						});
					}, 3000);
				},
				error: function (xhr, status, error) {
					console.error("Error al registrar:", error);
					$("#mensaje").hide().html(
						'<div class="alert alert-danger alert-dismissible fade show" role="alert">'
						+ "Ocurrió un error al registrar el libro."
						+ '<button type="button" class="btn-close" data-bs-dismiss="alert"></button>'
						+ '</div>'
					).fadeIn(300);
				},
				complete: function () {
					// Rehabilitar el botón siempre, éxito o error
					$btn.prop("disabled", false).removeClass("disabled");
					$("#btnTexto").text("Registrar");
				}
			});
		});
	</script>
</body>
</html>