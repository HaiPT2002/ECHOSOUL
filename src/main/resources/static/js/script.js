function toggleDropdown() {
    document.getElementById("dropdown").classList.toggle("show");
}

window.onclick = function(e) {
    if (!e.target.closest('.user-menu')) {
        document.getElementById("dropdown")?.classList.remove("show");
    }
};
