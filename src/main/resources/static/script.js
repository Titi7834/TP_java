function confirmDelete(id) {
    if (!confirm("Voulez-vous vraiment supprimer ce produit ?")) {
        return;
    }

    Toastify({
        text: "Produit supprimé",
        duration: 3000,
        gravity: "top",
        position: "right",
        backgroundColor: "#4CAF50"
    }).showToast();

    // délai pour voir le toast avant redirection
    setTimeout(() => {
        window.location.href = "/supprimer/" + id;
    }, 800);
}

// Récupération des éléments
const searchInput = document.getElementById("searchNom");
const filterSelect = document.getElementById("filterType");
const table = document.getElementById("tableProduits");
const tbody = table.querySelector("tbody");
const headers = table.querySelectorAll("th[data-column]");

// --- FONCTION DE FILTRAGE ---
function filterTable() {
    const searchValue = searchInput.value.toLowerCase();
    const typeValue = filterSelect.value;

    Array.from(tbody.rows).forEach(row => {
        const nom = row.cells[1].textContent.toLowerCase();
        const type = row.cells[2].textContent;

        const matchesSearch = nom.includes(searchValue);
        const matchesType = typeValue === "" || type === typeValue;

        row.style.display = (matchesSearch && matchesType) ? "" : "none";
    });
}

// Événements recherche et filtre
searchInput.addEventListener("input", filterTable);
filterSelect.addEventListener("change", filterTable);

// --- FONCTION DE TRI ---
headers.forEach(header => {
    header.addEventListener("click", () => {
        const column = header.getAttribute("data-column");
        const index = Array.from(header.parentNode.children).indexOf(header);
        const rows = Array.from(tbody.rows);

        // Déterminer ordre
        const ascending = !header.classList.contains("asc");

        rows.sort((a, b) => {
            let valA = a.cells[index].textContent;
            let valB = b.cells[index].textContent;

            if (column === "nom") {
                return ascending ? valA.localeCompare(valB) : valB.localeCompare(valA);
            } else {
                return ascending ? parseFloat(valA) - parseFloat(valB) : parseFloat(valB) - parseFloat(valA);
            }
        });

        rows.forEach(row => tbody.appendChild(row));

        // Mise à jour des classes pour afficher flèche
        headers.forEach(h => h.classList.remove("asc", "desc"));
        header.classList.toggle("asc", ascending);
        header.classList.toggle("desc", !ascending);
    });
});
