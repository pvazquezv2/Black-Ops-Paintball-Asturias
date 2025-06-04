const modosPorEscenario = {
	"El Bastión del Norte": ["Asalto al castillo", "Rey de la colina"],
	"Lagos de Covadonga": ["Captura la bandera", "Rescate acuático"],
	"La Casona Perdida": ["Combate urbano", "Buscar y destruir"],
	"Selva Astur": ["Supervivencia", "Caza del francotirador"],
	"Playa de Frexulfe": ["Invasión anfibia", "Arena libre"],
	"Fuerza Minera": ["Operación subterránea", "Extracción del tesoro"]
};

const escenarioSelect = document.getElementById("escenarioSelect");
const modoSelect = document.getElementById("modoSelect");

escenarioSelect.addEventListener("change", () => {
	const seleccionado = escenarioSelect.value;
	const modos = modosPorEscenario[seleccionado] || [];


	modoSelect.innerHTML = '<option selected disabled>Selecciona un modo</option>';


	modos.forEach(modo => {
		const option = document.createElement("option");
		option.value = modo;
		option.textContent = modo;
		modoSelect.appendChild(option);
	});
});
