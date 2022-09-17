async function getAuthentication () {
    const getUsers = await fetch("api/user");
    return await getUsers.json()
        .catch(error => alert("Ошибка HTTP: " + error));
}

function fillHeader(data) {
    data.then(d => {
        let roleString = "";

        d.roles.forEach(role => roleString += ` ${role}`);

        document.getElementById("user_authentication").innerHTML = `
            <b class="navbar-brand" > ${d.email} </b> with roles:
            <span class="navbar-brand"> ${roleString} </span>`;
    })

}

function fillUserAuth(data) {
    data.then(d => {
        const usersTable = document.getElementById("user_info")

        const tr = document.createElement("tr");

        let roleString = "";

        d.roles.forEach(role => roleString += ` ${role}`);
        tr.innerHTML = ` <td> ${d.id} </td>
                         <td> ${d.name} </td>
                         <td> ${d.lastName} </td>
                         <td> ${d.email} </td>
                         <td> ${d.age} </td>
                         <td> ${roleString} </td>`;

        usersTable.append(tr);
    })
}