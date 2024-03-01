const loginLinkButton = document.getElementById('boton-carga-masiva')
loginLinkButton.addEventListener('click', () => { //evento -> click//
        var tipoOrganismo = document.getElementById("tipo-organismo").value;
        var inputElement = document.getElementById("formFile");
        var file = inputElement.files[0];
        if (!file) {
            alert("Por favor, carga un archivo.");
        } else {
            document.getElementById("formulario-organismos").submit();
        }
    }
);

