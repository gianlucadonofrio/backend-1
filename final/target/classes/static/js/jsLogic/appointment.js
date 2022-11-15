window.onload = (e) => {
    e.preventDefault();
    getAppointments();
}

function getAppointments() {
    fetch("/api/appointments", {
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
                throw new Error("No appointments created yet");
            }
        })
        .then(data => {
            let html = '';
            data.forEach(appointment => {
                html += `
            <tr>
                <td>${appointment.id}</td>
                <td>${appointment.dateAppointment}</td>
                <td>${appointment.patient.lastName},  ${appointment.patient.name} </td>  
              <td>${appointment.dentist.lastName},  ${appointment.dentist.name} </td>  

                <td>
                   <button type="button" class="btn btn-danger" onclick="deleteAppointment(${appointment.id})"><span class="material-icons">delete</span></button>
                </td>
            </tr>
        `;
            });

            document.querySelector('#appointments tbody').innerHTML = html;
        })
}

function addAppointment() {
    let appointment = {
        dateAppointment: document.getElementById("dateAppointment").value,
        patient: {
            id: document.getElementById("patient_id").value,

        },
        dentist: {id: document.getElementById("dentist_id").value}
    }

    fetch('/api/appointments', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(appointment)
    })
        .then(res => {
            if (res.status === 200) {
                return res.json();
            } else {
                throw new Error("Error creating appointment");
            }
        })
        .then((res) => {
            location.reload()
        })

}

function deleteAppointment(id) {
    if (!confirm('Are you sure you want to delete this appointment?')) {
        return;
    }
    fetch(`/api/appointments/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(() => {
            location.reload()
        })
}

function updateAppointment(id) {
    const appointment = {
        dateAppointment: document.getElementById("dateAppointment").value,
        patientId: document.getElementById("patient_id").value,
        dentistId: document.getElementById("dentist_id").value,

    }
    const newAppointment = {
        dateAppointment: appointment.dateAppointment.value,
        patientId: appointment.patientId.value,
        dentistId: appointment.dentistId.value,

    }

    fetch("/api/appointments/" + id, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newAppointment)
    })
        .then(res => res.json())
        .then(() => {
            location.reload();
        })
}