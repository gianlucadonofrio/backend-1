window.onload = (e) => {
    e.preventDefault();
    getDentist();
}

function getDentist() {
    fetch("/api/dentists", {
        method: "GET",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        }

    })
        .then(res => {
            if (res.status === 200) {
                return res.json();
            } else {
                throw new Error("No dentist created yet");
            }
        })
        .then(data => {

            let html = '';
            data.forEach(dentist => {
                html += `
            <tr>
                <td>${dentist.id}</td>
                <td>${dentist.lastName},  ${dentist.name}</td>
                <td>${dentist.enrollment}</td>  
                <td>
                   <button type="button" class="btn btn-danger" onclick="deleteDentist(${dentist.id})"><span class="material-icons">delete</span></button>
                </td>
            </tr>
        `;
            });

            document.querySelector('#dentist tbody').innerHTML = html;
        })
}

function addDentist() {
    let patient = {
        name: document.getElementById("name").value,
        lastName: document.getElementById("lastName").value,
        enrollment: document.getElementById("enrollment").value,
    }

    fetch('/api/dentists', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(patient)
    })
        .then(res => {
            if (res.status === 200) {
                return res.json();
            } else {
                throw new Error("Error creating dentist");
            }
        })
        .then((res) => {
            location.reload()
        })

}

function deleteDentist(id) {
    if (!confirm('Are you sure you want to delete this dentist?')) {
        return;
    }
    fetch(`/api/dentists/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(() => {
            location.reload()
        })
}

function updateDentist(id) {
    const dentist = {
        name: document.getElementById("name").value,
        lastName: document.getElementById("lastName").value,
        enrollment: document.getElementById("enrollment").value,

    }
    const newPatient = {
        name: dentist.name.value,
        lastName: dentist.lastName.value,
        enrollment: dentist.enrollment.value,

    }

    fetch("/api/dentist/" + id, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newPatient)
    })
        .then(res => res.json())
        .then(() => {
            location.reload();
        })
}