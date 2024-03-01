const loginLinkButton = document.getElementById('boton-crear-usuario');
loginLinkButton.addEventListener('click', () => {
    var nombre = document.getElementById("nombreUsuario").value;
    var mail = document.getElementById("mailUsuario").value;
    var numero = document.getElementById("numero").value;

    // Verificar si los campos están completos
    if (nombre.trim() === "" || mail.trim() === ""  || numero.trim() === "") {
        alert("Por favor, completa todos los campos del formulario.");
    } else {
        document.getElementById("formulario-creacion-usuario").submit();
    }
});
