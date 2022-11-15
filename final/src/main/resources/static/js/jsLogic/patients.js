window.onload = (e) => {
    e.preventDefault();
    getPatients();
}

function getPatients() {
    fetch("/api/patients", {
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
                throw new Error("No patients created yet");
            }
        })
        .then(data => {

            let html = '';
            data.forEach(patient => {
                html += `
            <tr>
                <td>${patient.id}</td>
                <td>${patient.lastName},  ${patient.name}</td>
                <td>${patient.admissionDate}</td>
                <td>${patient.dni}</td>
                <td>${patient.address.street} ${patient.address.number}, ${patient.address.location}, ${patient.address.province}</td>
                <td>
       
                   <button type="button" class="btn btn-danger" onclick="deletePatient(${patient.id})"><span class="material-icons">delete</span></button>
                </td>
            </tr>
        `;
            });

            document.querySelector('#patients tbody').innerHTML = html;
        })
}

function addPatients() {
    let patient = {
        name: document.getElementById("name").value,
        lastName: document.getElementById("lastName").value,
        admissionDate: document.getElementById("admissionDate").value,
        dni: document.getElementById("dni").value,
        address: {
            street: document.getElementById("street").value,
            number: document.getElementById("number").value,
            location: document.getElementById("location").value,
            province: document.getElementById("province").value,
        }
    }

    fetch('/api/patients', {
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
                throw new Error("Error creating patient");
            }
        })
        .then((res) => {
            location.reload()
        })

}

function deletePatient(id) {
    if (!confirm('¿Está seguro de eliminar el paciente?')) {
        return;
    }
    fetch(`/api/patients/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(() => {
            location.reload()
        })
}

function updatePatient(id) {
    const patient = {
        name: document.getElementById("name"),
        lastName: document.getElementById("lastName"),
        admissionDate: document.getElementById("admissionDate"),
        dni: document.getElementById("dni"),
        address: {
            street: document.getElementById("street"),
            number: document.getElementById("number"),
            location: document.getElementById("location"),
            province: document.getElementById("province"),
        }
    }
    const newPatient = {
        name: patient.name.value,
        lastName: patient.lastName.value,
        admissionDate: patient.admissionDate.value,
        dni: patient.dni.value,
        address: {
            street: patient.address.street.value,
            number: patient.address.number.value,
            location: patient.address.location.value,
            province: patient.address.province.value,
        }
    }

    fetch("/api/patients/" + id, {
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
