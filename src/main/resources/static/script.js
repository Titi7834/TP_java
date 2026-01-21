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