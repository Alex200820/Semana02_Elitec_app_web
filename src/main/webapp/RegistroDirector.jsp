
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Registro Director Elitec</title>

    <!-- LIBRERÍAS JAVASCRIPT -->
    <script src="js/jquery-4.0.0.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.bundle.js" type="text/javascript"></script>

    <!-- ARCHIVOS CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-grid.css" rel="stylesheet">
    <link href="css/bootstrap-reboot.css" rel="stylesheet">
    <link href="css/bootstrap-utilities.css" rel="stylesheet">

</head>

<body>
    <div class="container mt-5">
        <div class="card shadow border-0">
            <!-- ENCABEZADO -->
            <div class="card-header bg-primary text-white p-4">
                <h2 class="mb-1">Registro de Director</h2>
                <p class="mb-0">
                    Complete los datos para registrar un nuevo director.
                </p>
            </div>
            <!-- CUERPO -->
            <div class="card-body p-4">
                <form id="form" method="post" novalidate>
                    <h5 class="text-primary mb-4">
                        Información del Director
                    </h5>
                    <div class="row g-4">
                        <!-- NOMBRE -->
                        <div class="col-md-6">
                            <label for="nombre" class="form-label fw-bold">
                                Nombre
                            </label>
                            <input type="text"
                                class="form-control"
                                id="nombre"
                                name="nombre"
                                placeholder="Ingrese el nombre"
                                maxlength="30"
                                required>
                            <div class="invalid-feedback">
                                Ingrese el nombre.
                            </div>
                        </div>
                        <!-- EMAIL -->
                        <div class="col-md-6">
                            <label for="email" class="form-label fw-bold">
                                Correo electrónico
                            </label>
                            <input type="email"
                                class="form-control"
                                id="email"
                                name="email"
                                placeholder="ejemplo@correo.com"
                                maxlength="30"
                                required>
                            <div class="invalid-feedback">
                                Ingrese un correo electrónico válido.
                            </div>
                        </div>
                        <!-- TIPO -->
                        <div class="col-md-6">

                            <label for="tipo" class="form-label fw-bold">
                                Tipo de Director
                            </label>

                            <select class="form-select"
                                id="tipo"
                                name="tipo"
                                required>

                                <option value="">[Seleccione]</option>

                            </select>

                            <div class="invalid-feedback">
                                Seleccione un tipo.
                            </div>
                        </div>
                    </div>
                    <!-- BOTONES -->
                    <div class="row mt-5">
                        <div class="col-12 d-flex justify-content-center gap-3">
                            <button type="reset"
                                class="btn btn-outline-secondary px-4"
                                id="btnLimpiar">
                                Limpiar
                            </button>
                            <button type="button"
                                class="btn btn-primary px-4"
                                id="btnRegistrar">
                                Registrar
                            </button>

                        </div>
                    </div>
                </form>
            </div>

            <!-- PIE -->
            <div class="card-footer bg-light text-muted text-center">
                Sistema de Registro Elitec
            </div>

        </div>

    </div>


    <script type="text/javascript">

        $(document).ready(function () {

            // CARGAR COMBO TIPO
            $.ajax({

                url: 'cargaComboTipo',
                type: 'GET',

                success: function (data) {

                    console.log('Tipos cargados:', data);

                    var comboBox = $('#tipo');

                    data.forEach(function (obj) {

                        comboBox.append(
                            $('<option>', {
                                value: obj.idTipo,
                                text: obj.descripcion
                            })
                        );

                    });

                },

                error: function (xhr, status, error) {

                    console.error('Error al cargar tipos:', error);

                }

            });


            // REGISTRAR DIRECTOR
            $("#btnRegistrar").click(function (e) {

                console.log("Click en registrar");

                let form = $('#form')[0];

                // VALIDAR FORMULARIO
                if (form.checkValidity() === false) {

                    $(form).addClass('was-validated');

                    return;

                }

                $.ajax({

                    url: 'registroDirectorAlias',
                    type: 'POST',
                    data: $(form).serialize(),

                    success: function (response) {

                        console.log('response >>> ', response);

                        // LIMPIAR FORMULARIO
                        form.reset();

                        // LIMPIAR VALIDACIONES
                        $(form).removeClass('was-validated');

                        // ELIMINAR ALERTAS ANTERIORES
                        $('#form .alert').remove();

                        // MOSTRAR MENSAJE DE ÉXITO
                        $('#form').prepend(
                            '<div class="alert alert-success mt-3" role="alert">'
                            + response.mensajeSalida +
                            '</div>'
                        );

                        // ELIMINAR MENSAJE DESPUÉS DE 3 SEGUNDOS
                        setTimeout(function () {

                            $('#form .alert').remove();

                        }, 3000);

                    },

                    error: function (xhr, status, error) {

                        console.error('Error al registrar:', error);

                        alert('Error al registrar el director.');

                    }

                });

            });


            // LIMPIAR VALIDACIONES
            $('#btnLimpiar').click(function () {

                $('#form').removeClass('was-validated');

                $('#form .alert').remove();

            });

        });

    </script>

</body>
</html>