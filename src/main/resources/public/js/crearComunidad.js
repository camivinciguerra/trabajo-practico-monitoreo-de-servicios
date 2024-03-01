const loginLinkButton = document.getElementById('boton-alta-usuario');
loginLinkButton.addEventListener('click', () => {
    const serviciosSelect = document.getElementById("servicios");
    const opcionesSeleccionadas = serviciosSelect.selectedOptions;
    const serviciosSeleccionados = [];

    for (const opcion of opcionesSeleccionadas) {
        serviciosSeleccionados.push(opcion.value);
    }
    var nombre = document.getElementById("nombre").value;

    // Verificar si los campos están completos
    if (serviciosSeleccionados.length === 0 || nombre === "") {
        alert("Por favor, completa todos los campos del formulario.");
    } else {
        document.getElementById("formulario-alta-usuario").submit();
    }
});