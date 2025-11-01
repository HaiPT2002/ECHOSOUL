// ================= DROPDOWN =================
function toggleDropdown() {
    document.getElementById("dropdown").classList.toggle("show");
}

// Chốt sự kiện cho toàn bộ cửa sổ
window.addEventListener('click', e => {
    // Dropdown
    if (!e.target.closest('.user-menu')) {
        document.getElementById("dropdown")?.classList.remove("show");
    }

    // Modal
    if (e.target === modal) modal.style.display = "none";
});

// ================= INLINE EDIT =================
const editBtn = document.getElementById('editBtn');
const saveBtn = document.getElementById('saveBtn');
const cancelBtn = document.getElementById('cancelBtn');

editBtn.addEventListener('click', () => {
    document.querySelectorAll('.editable').forEach(span => span.style.display = 'none');
    document.querySelectorAll('input').forEach(input => input.style.display = 'inline-block');
    editBtn.style.display = 'none';
    saveBtn.style.display = 'inline-block';
    cancelBtn.style.display = 'inline-block';
});

cancelBtn.addEventListener('click', () => {
    document.querySelectorAll('.editable').forEach(span => span.style.display = 'inline');
    document.querySelectorAll('input').forEach(input => input.style.display = 'none');
    editBtn.style.display = 'inline-block';
    saveBtn.style.display = 'none';
    cancelBtn.style.display = 'none';
});

saveBtn.addEventListener('click', () => {
    const username = document.getElementById('nameInput').value;
    const zodiac = document.getElementById('zodiacInput').value;
    const hobbies = document.getElementById('hobbiesInput').value;
    const stone = document.getElementById('stoneInput').value;

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    fetch('/profile/edit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken
        },
        body: JSON.stringify({username, zodiac, hobbies, stone })
    }).then(res => {
        if(res.ok) location.reload();
        else alert("Cập nhật thất bại");
    });
});

// ================= AVATAR MODAL =================
const modal = document.getElementById("avatarModal");
const avatarBtn = document.getElementById("avatarBtn");
const closeModal = modal?.querySelector(".close");

avatarBtn.onclick = () => modal.style.display = "flex";
closeModal?.addEventListener('click', () => modal.style.display = "none");

// ================= REGISTER =======================
function validateForm() {
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const errorMsg = document.getElementById("passwordError");

    if (password !== confirmPassword) {
        errorMsg.style.display = "block";
        return false; // chặn form gửi đi
    } else {
        errorMsg.style.display = "none";
        return true;
    }
}
