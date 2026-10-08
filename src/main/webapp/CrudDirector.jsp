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
	<script src="js/datatables.js" type="text/javascript"></script>
	<script src="js/sweetalert2@11.js" type="text/javascript"></script>
	
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap-grid.css" rel="stylesheet">
	<link href="css/bootstrap-reboot.css" rel="stylesheet">
	<link href="css/bootstrap-utilities.css" rel="stylesheet">
	<link href="css/datatables.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>Crud de Director</h1>
		<div class="row" style="margin-top: 2%;">
			<div class="col-3">
					<label for="nombre">Nombre</label> 
			</div>
			<div class="col-3">
					<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el Nombre" maxlength="30">
			</div>
			<div class="col-3">
                    <button class="btn btn-primary" id="btnBuscar"style="width: 200px">Buscar</button>
            </div>       
            <div class="col-3">
    				<button class="btn btn-primary" type="button" style="width: 200px" onclick="abrirModal()">Registra</button>
    		</div>	 
		</div>
		
		<div class="row" style="margin-top: 2%;">
            <div class="col-12">
                <table class="table table-striped" id="id_table">
                    <thead>
                        <tr><th>Código</th>
                            <th>Nombre</th>
                            <th>Email</th>
                            <th>Tipo</th>
                            <th>Estado</th>
                           	<th></th>
							<th></th>
							<th></th>
                        </tr>
                    </thead>
                    <tbody >
 
                    </tbody>
                </table>
          </div>	
		</div>
	</div>
	
		<!-- 
			 ------------- INICIO MODAL DE REGISTRO -------------------- 
		-->
		
		<div class="modal fade" id="id_div_modal_registra" tabindex="-1" aria-hidden="true">
		    <div class="modal-dialog modal-lg">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title">Registro de Director</h5>
		                <button type="button" class="btn-close" data-bs-dismiss="modal"  aria-label="Cerrar"></button>
		            </div>
		            <div class="modal-body">
		                <form id="id_form_registra">
		                    <input type="hidden" name="metodo" value="registra">
		                    <div class="row mb-3">
		                        <label for="id_reg_nombre" class="col-md-3 col-form-label">Nombre</label>
		                        <div class="col-md-9">
		                            <input class="form-control" id="id_reg_nombre" name="nombre" placeholder="Ingrese el Nombre" type="text" maxlength="100">
		                        </div>
		                    </div>
		                    <div class="row mb-3">
		                        <label for="id_reg_email" class="col-md-3 col-form-label">Email</label>
		                        <div class="col-md-9">
		                            <input class="form-control" id="id_reg_email" name="email" placeholder="Ingrese el Email" type="email">
		                        </div>
		                    </div>
		                    <div class="row mb-3">
		                        <label for="id_reg_tipo" class="col-md-3 col-form-label">Tipo</label>
		                        <div class="col-md-9">
		                            <select class="form-control" id="id_reg_tipo" name="tipo" >
										<option>[Seleccione]</option>
									</select>
		                        </div>
		                    </div>
		
		                    <div class="text-center">
		                        <button type="button" id="id_btn_registra"  class="btn btn-primary me-2">Registrar</button>
		                    </div>
		                </form>
		            </div>
		        </div>
		    </div>
		</div>
		<!-- 
			 ------------- FIN MODAL DE REGISTRO -------------------- 
		-->
		
		<!-- 
			 ------------- INICIO MODAL DE ACTUALIZAR -------------------- 
		-->
			<div class="modal fade" id="id_div_modal_actualiza" tabindex="-1" aria-hidden="true">
		    <div class="modal-dialog modal-lg">
		        <div class="modal-content">
		
		            <div class="modal-header">
		                <h5 class="modal-title">Actualiza Director</h5>
		                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
		            </div>
		
		            <div class="modal-body">
		                <form id="id_form_actualiza">
		                    <input type="hidden" name="metodo" value="actualiza">
		                    <input type="hidden" name="id" id="id">
		
		                    <div class="row mb-3">
		                        <label for="id_act_nombre" class="col-md-3 col-form-label"> Nombre </label>
		                        <div class="col-md-9">
		                            <input class="form-control" id="id_act_nombre" name="nombre" placeholder="Ingrese el Nombre" type="text"maxlength="100">
		                        </div>
		                    </div>
		                    <div class="row mb-3">
		                        <label for="id_act_email" class="col-md-3 col-form-label"> Email </label>
		                        <div class="col-md-9">
		                            <input class="form-control" id="id_act_email" name="email" placeholder="Ingrese el EMAIL"  type="text" >
		                        </div>
		                    </div>
							<div class="row mb-3">
								<label for="id_act_tipo" class="col-md-3 col-form-label"> Tipo </label>
								<div class="col-md-9">
									<select class="form-select" id="id_act_tipo" name="tipo"></select>
								</div>
							</div>			
		                    <div class="row mb-3">
		                        <label for="id_act_estado" class="col-md-3 col-form-label"> Estado </label>
		                        <div class="col-md-9">
		                            <select class="form-select" id="id_act_estado" name="estado">
		                                <option value="">[Seleccione]</option>
		                                <option value="1">Activo</option>
		                                <option value="0">Inactivo</option>
		                            </select>
		                        </div>
		                    </div>
		                    <div class="text-center">
		                        <button type="button" id="id_btn_actualiza" class="btn btn-primary me-2"> Actualizar </button>
		                    </div>
		                </form>
		            </div>
		        </div>
		    </div>
		</div>
		<!-- 
			 ------------- INICIO MODAL DE ACTUALIZAR -------------------- 
-->
	<script type="text/javascript">
	
		 $(document).ready(function () {
				$.ajax({
				url: 'cargaComboTipoAlias', // URL del servlet para obtener categorías
				type: 'GET',
				success: function (data) {
					console.log('Categorías cargadas:', data);
					var comboBox = $('#id_reg_tipo');
					data.forEach(function (obj) {
						comboBox.append('<option value="' + obj.idTipo + '">' + obj.descripcion + '</option>');
					});
					var comboBox2 = $('#id_act_tipo');
					data.forEach(function (obj) {
						comboBox2.append('<option value="' + obj.idTipo + '">' + obj.descripcion + '</option>');
					});
				},
				error: function (xhr, status, error) {
					console.error('Error al cargar categorías:', error);
				}
			});
		});
		 
		$("#btnBuscar").click(function(e) {
			var nombre = $("#nombre").val();
			$.ajax({
				url: "crudDirectorAlias",
				type: "GET",
				data: {metodo: "listaPorNombre", nombre: nombre},
				success: function (response) {
					console.log(">>> response: " , response);
					agregarGrilla(response);
				},
				error: function () {
					alert("Error al buscar libros por título.");
				}
			});
			
			
		});
	
		function agregarGrilla(lista){
			 $('#id_table').DataTable().clear();
			 $('#id_table').DataTable().destroy();
			 $('#id_table').DataTable({
					data: lista,
					language: IDIOMA,
					searching: true,
					ordering: true,
					processing: true,
					pageLength: 10,
					lengthChange: true,
					info:true,
					scrollY: 305,
			        scroller: {
			            loadingIndicator: true
			        },
					columns:[
						{data: "idDirector",className:'text-center'},
						{data: "nombre",className:'text-center'},
						{data: "email",className:'text-center'},
						{data: "tipo.descripcion", className:'text-center'},
						{data: function(row, type, val, meta){
							return row.estado == 1 ? "Activo" : "Inactivo";  
						},className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-info btn-sm" onClick="verFormularioActualiza(\'' + meta.row +'\');">Editar</button>';  
						},className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-warning btn-sm" onClick="eliminacionLogica(\'' + row.idDirector +'\');" >E.Lógica</button>';
						},className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-danger btn-sm"  onClick="eliminacionFisica(\'' + row.idDirector +'\');" >E.Física</button>';
						},className:'text-center'},
					]                                     
			    });
		}

			var IDIOMA = {
				processing:"procesando...",
			    lengthMenu: "_MENU_ Registros por p&aacute;gina",
			    zeroRecords: "No existen registros",
			    info: "P&aacute;gina _PAGE_ de _PAGES_",
			    infoEmpty: "Sin registros",
			    infoFiltered: "(Filtro de _MAX_ registros)",
			    search: "Buscar:",
			    paginate: {
			        "first":      "Primero",
			        "last":       "Last",
			        "next":       "Siguiente",
			        "previous":   "Anterior"
			    }
			};
	
			function eliminacionLogica(id){
				console.log("eliminacionLogica ==> ", id);
				 $.ajax({
			          type: "POST",
			          url: "crudDirectorAlias", 
			          data: {"metodo":"eliminacionLogica", "idDirector":id},
			          success: function(data){
			        	  console.log("data ==> ", data);
			        	  agregarGrilla(data);
			          },
			          error: function(){
			        	  Swal.fire({title: 'Error',text: "Error al porcesar", icon: 'error'});
			          }
			    });
			}	
	
			function eliminacionFisica(id){
			 	Swal.fire({
			        title: '¿Está seguro?',
			        text: 'El director será eliminado permanentemente.',
			        icon: 'warning',
			        showCancelButton: true,
			        confirmButtonText: 'Sí, eliminar',
			        cancelButtonText: 'Cancelar'
			    }).then((result) => {
			        if (result.isConfirmed) {
				        	$.ajax({
				  	          type: "POST",
				  	          url: "crudDirectorAlias", 
				  	          data: {"metodo": "eliminacionFisica", "idDirector":id},
				  	          success: function(data){
				  	        	agregarGrilla(data);
				  	          },
				  	          error: function(){
				  	        	Swal.fire({title: 'Error',text: "Error al porcesar", icon: 'error'});
				  	          }
				  	        });
			        }

			    });
		}  
			
			function abrirModal() {
			    const modal = new bootstrap.Modal( $('#id_div_modal_registra') );
			    modal.show();
			}	        
				     	
			
		$("#id_btn_registra").click(function(e) {
			console.log("click en registrar");		
	        $.ajax({
				url: 'crudDirectorAlias',
				type: 'POST',
				data: $('#id_form_registra').serialize(),
				success: function (data) {
					 agregarGrilla(data);
					 const modal = bootstrap.Modal.getInstance(  $('#id_div_modal_registra') );
		        	 modal.hide();
				},
				error: function (xhr, status, error) {
					// Manejar errores aquí
					console.error('Error al registrar :', error);
				}
			});
		});
		
		function verFormularioActualiza(indiceGrilla){
			// Obtener la fila seleccionada
		    const objGrilla = $('#id_table').DataTable().row(indiceGrilla).data();
			console.log(objGrilla);
			
		    // Cargar datos en el formulario
		    $("#id").val(objGrilla.idDirector);
		    $("#id_act_nombre").val(objGrilla.nombre);
		    $("#id_act_email").val(objGrilla.email);
		    $("#id_act_estado").val(objGrilla.estado);
		    $("#id_act_tipo").val(objGrilla.tipo.idTipo);
		    
		    const modal = new bootstrap.Modal( $('#id_div_modal_actualiza') );
		    modal.show();
		    
		}
		
		
		$("#id_btn_actualiza").click(function(e) {
			console.log("click en actualizar");		
	        $.ajax({
				url: 'crudDirectorAlias',
				type: 'POST',
				data: $('#id_form_actualiza').serialize(),
				success: function (data) {
					 agregarGrilla(data);
					 const modal = bootstrap.Modal.getInstance(  $('#id_div_modal_actualiza') );
		        	 modal.hide();
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