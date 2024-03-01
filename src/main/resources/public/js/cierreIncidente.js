const loginLinkButton = document.getElementById('boton-apertura-incidente')
const comunidadSelect = document.getElementById('comunidades');
const establecimientoSelect = document.getElementById('establecimientos');

function cargarComunidades() {
    fetch('/comunidades/usuario')
        .then(response => response.json())
        .then(comunidades => {

            comunidadSelect.innerHTML = '';
            establecimientoSelect.innerHTML = '';

            comunidades.forEach(comunidad => {
                const option = document.createElement('option');
                option.value = comunidad.id;
                option.text = comunidad.nombre;
                comunidadSelect.appendChild(option);
            });

            cargarEstablecimientos();
        })
        .catch(error => {
            console.error('Error al obtener datos de comunidades:', error);
        });
}

function cargarEstablecimientos() {
    const idComunidad = comunidadSelect.value;

    fetch(`/establecimiento/comunidad/${idComunidad}`)
        .then(response => response.json())
        .then(establecimientos => {

            establecimientoSelect.innerHTML = '';

            establecimientos.forEach(establecimiento => {
                const option = document.createElement('option');
                option.value = establecimiento.id;
                option.text = establecimiento.nombre;
                establecimientoSelect.appendChild(option);
            });


        })
        .catch(error => {
            console.error('Error al obtener datos de establecimientos:', error);
        });
}

document.addEventListener('DOMContentLoaded', cargarComunidades);
comunidadSelect.addEventListener('change', cargarEstablecimientos);
loginLinkButton.addEventListener('click', () => { //evento -> click
    var establecimientos = document.getElementById("establecimientos").value;

    // Verificar si los campos están completos
    if (establecimientos.trim() === "" || establecimientos === "Seleccione un establecimiento") {
        alert("Por favor, seleccione un establecimiento.");
    } else {
        document.getElementById("formulario-incidentes").submit();
    }
})