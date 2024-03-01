
$(document).ready(function() {
    $('#example').DataTable({
        "pagingType": "full_numbers",  // Tipo de paginación
        "lengthMenu": [10, 25, 50, 100],  // Opciones de cantidad de filas por página
        "pageLength": 10,  // Cantidad de filas por página por defecto
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.25/i18n/Spanish.json" // Ruta al archivo de idioma
        },
        "ordering": false
    });
});