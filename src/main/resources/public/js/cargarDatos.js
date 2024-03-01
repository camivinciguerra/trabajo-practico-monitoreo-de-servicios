const loginLinkButton = document.getElementById('boton-alta-usuario');
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


loginLinkButton.addEventListener('click', () => {
     const serviciosSelect = document.getElementById("servicios");
     const opcionesSeleccionadas = serviciosSelect.selectedOptions;
     const serviciosSeleccionados = [];

     for (const opcion of opcionesSeleccionadas) {
         serviciosSeleccionados.push(opcion.value);
     }
     const entidadesSelect = document.getElementById("entidades");
     const opcionesSeleccionadasEntidades = entidadesSelect.selectedOptions;
     const entidadesSeleccionadas = [];
        for (const opcion of opcionesSeleccionadasEntidades) {
            entidadesSeleccionadas.push(opcion.value);
        }
     var estrategia = document.getElementById("estrategia").value;
     var localidad =  document.getElementById("localidades").value;
     var municipio = document.getElementById("municipios").value;
     var provincia = document.getElementById("provincias").value;

    // Verificar si los campos están completos
    if (serviciosSeleccionados.length === 0 || entidadesSeleccionadas.length === 0 || estrategia=== ""  || provincia === "") {
        alert("Por favor, completa todos los campos del formulario.");
    } else {
        document.getElementById("formulario-alta-usuario").submit();
    }
});