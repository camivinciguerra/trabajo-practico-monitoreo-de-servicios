let popup = document.getElementById("popup");

function openPopup(){
    popup.classList.add("open-popup");
}

function closePopup(){
    popup.classList.remove("open-popup")
}

const loginLinkButton = document.getElementById('boton-finalizar-carga')
loginLinkButton.addEventListener('click', () => {
    var nombreUsuario = document.getElementById("username").value;
    var contrasenia = document.getElementById("password").value;
    var contrasenia2 = document.getElementById("contrasenia2").value;
    var contraseniaAux = contrasenia.trim()
    var url = "/create/es-valida/" + contraseniaAux

    if (nombreUsuario.trim() === "" || contrasenia.trim() === "" || contrasenia2.trim() === "") {
        alert("Por favor, completa todos los campos.");
    } else if (contrasenia !== contrasenia2) {
        alert("Las contraseñas no coinciden. Por favor, ingresa la misma contraseña en ambos campos.");
    } else {
        fetch('/usuario/existe/' + nombreUsuario)
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    alert("Nombre de Usuario no disponible.");
                } else {
                    //window.confirm("Su cuenta ha sido creada con éxito!")

                        fetch(url)
                        .then(response => response.json())
                        .then(data => {
                            if (data.validez.trim() === "esValida") {

                                openPopup();

                            } else {
                                document.getElementById("formulario-pwd").submit();
                            }
                        })
                        .catch(error => {
                            console.error("Error al verificar la existencia del nombre de usuario:", error);
                        });
                }
            })
            .catch(error => {
                console.error("Error al verificar la existencia del nombre de usuario:", error);
            });
    }
});
const loginLinkButton2 = document.getElementById('boton-popup')
loginLinkButton2.addEventListener('click', () => {
closePopup();
document.getElementById("formulario-pwd").submit();
/*window.location.replace("http://localhost:8080/inicio")*/
});
