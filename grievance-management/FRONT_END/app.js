document.getElementById("registerForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const user = {
        userName: document.getElementById("userName").value,
        userEmail: document.getElementById("userEmail").value,
        userPassword: document.getElementById("userPassword").value
    };

    fetch('/api/users/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
    .then(response => response.json())
    .then(data => {
        console.log('User registered:', data);
    })
    .catch(error => console.error('Error:', error));
});

document.getElementById("grievanceForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const grievance = {
        description: document.getElementById("description").value,
        grievanceType: document.getElementById("grievanceType").value
    };

    fetch('/api/grievances/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(grievance)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Grievance submitted:', data);
    })
    .catch(error => console.error('Error:', error));
});
