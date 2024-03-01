const loginLinkButton = document.getElementById('boton-ingreso')
loginLinkButton.addEventListener('click', () => { //evento -> click
      var nombre = document.getElementById("nombreUsuario").value;
      var contrasenia = document.getElementById("contrasena").value;

                // Verificar si los campos están completos
      if (nombre.trim() === "" || contrasenia.trim() === "") {
          alert("Por favor, completa todos los campos del formulario.");
      } else {
          document.getElementById("formulario-login").submit();
          //console.log(nombre);
      }
})

