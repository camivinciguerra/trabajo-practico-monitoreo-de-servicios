const provinciaSelect = document.getElementById('provincias');
const municipioSelect = document.getElementById('municipios');
const localidadSelect = document.getElementById('localidades');
function cargarProvincias() {
    fetch('/provincias')
        .then(response => response.json())
        .then(provincias => {

            provinciaSelect.innerHTML = '';

            provincias.forEach(provincia => {
                const option = document.createElement('option');
                option.value = provincia.id;
                option.text = provincia.nombre;
                provinciaSelect.appendChild(option);
            });

            cargarMunicipios();
        })
        .catch(error => {
            console.error('Error al obtener datos de provincias:', error);
        });
}

function cargarMunicipios() {
    const idProvincia = provinciaSelect.value;

    fetch(`/municipios/${idProvincia}`)
        .then(response => response.json())
        .then(municipios => {

            municipioSelect.innerHTML = '';
            localidadSelect.innerHTML = '';

            municipios.forEach(municipio => {
                const option = document.createElement('option');
                option.value = municipio.id;
                option.text = municipio.nombre;
                municipioSelect.appendChild(option);
            });

            cargarLocalidades();

        })
        .catch(error => {
            console.error('Error al obtener datos de municipios:', error);
        });
}

function cargarLocalidades() {
    const municipioSeleccionado = municipioSelect.value;

    fetch(`/localidades/${municipioSeleccionado}`)
        .then(response => response.json())
        .then(localidades => {

            localidadSelect.innerHTML = '';

            localidades.forEach(localidad => {
                const option = document.createElement('option');
                option.value = localidad.id;
                option.text = localidad.nombre;
                localidadSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error al obtener datos de localidades:', error);
        });
}

document.addEventListener('DOMContentLoaded', cargarProvincias);
provinciaSelect.addEventListener('change', cargarMunicipios);
municipioSelect.addEventListener('change', cargarLocalidades);
document.addEventListener("DOMContentLoaded", function () {
    // Contador para generar IDs únicos
    let servicioCounter = 1; // Comenzamos desde 1 para el segundo bloque

    // Evento para agregar un nuevo servicio
    document.getElementById("agregar-servicio").addEventListener("click", function () {
        const serviciosContainer = document.getElementById("servicios-container");
        agregarServicio(serviciosContainer);
        // Incrementa la cantidad de servicios cada vez que agregas uno
        document.getElementById("cantidad-servicios").value = servicioCounter;
    });

    // Evento para eliminar el último servicio
    document.getElementById("eliminar-servicio").addEventListener("click", function () {
        const serviciosContainer = document.getElementById("servicios-container");
        eliminarUltimoServicio(serviciosContainer);
        // Decrementa la cantidad de servicios cada vez que eliminas uno
        document.getElementById("cantidad-servicios").value = servicioCounter;
    });

    function agregarServicio(container) {
        // Crear un nuevo bloque de servicio
        const nuevoServicioDiv = document.createElement("div");
        nuevoServicioDiv.classList.add("mb-3");
        nuevoServicioDiv.id = "servicio-" + servicioCounter;

        // Clonar el bloque de servicio original y actualizar IDs y nombres
        const servicioOriginal = document.getElementById("servicio-0");
        const nuevoServicio = servicioOriginal.cloneNode(true);
        nuevoServicio.id = "servicio-" + servicioCounter;

        // Incrementar el contador para generar IDs únicos
        nuevoServicio.querySelectorAll('[name^="servicios_"]').forEach(input => {
            input.name = input.name.replace("_0", "_" + servicioCounter);
        });

        nuevoServicio.querySelectorAll('[name^="tipoServicio_"]').forEach(input => {
            input.name = input.name.replace("_0", "_" + servicioCounter);
        });
        servicioCounter++;


        // Agregar el nuevo bloque de servicio al contenedor
        nuevoServicioDiv.appendChild(nuevoServicio);
        container.appendChild(nuevoServicioDiv);
    }

    function eliminarUltimoServicio(container) {
        const ultimoServicio = container.lastElementChild;
        if (ultimoServicio) {
            container.removeChild(ultimoServicio);
            // Decrementar el contador al eliminar un servicio
            servicioCounter--;
        }
    }

});


