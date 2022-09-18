async function fillingTable () {
    const getUsers = await fetch("api/admin");
    await getUsers.json()
        .then(json => {
            const usersTable = document.getElementById("users_tbody");
            usersTable.innerHTML = "";

            json.forEach(({id, name, lastName, email, age, password, roles}) => {
                const tr = document.createElement("tr");

                let roleString = "";

                roles.forEach(role => roleString += ` ${role}`);
                tr.innerHTML = `<td> ${id} </td>
                                <td> ${name} </td>
                                <td> ${lastName} </td>
                                <td> ${email} </td>
                                <td> ${age} </td>
                                <td> ${roleString} </td>
                                <td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#edit_modal"
                                            data-id=${id} data-name=${name} data-lastname=${lastName} 
                                            data-age=${age} data-email=${email} data-roles="${roles}"
                                            data-password=${password}> Edit </button>
                                </td>
                                <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete_modal"
                                            data-id=${id} data-name=${name} data-lastname=${lastName} 
                                            data-age=${age} data-email=${email} data-roles="${roles}"
                                            data-password=${password}> Delete </button>
                                </td>`;

                usersTable.append(tr);
            })
        })
        .catch(error => alert("Ошибка HTTP: " + error));
}

//TODO создание нового пользователя
document.getElementById("create_form")
    .addEventListener("submit", event => {
    event.preventDefault();

    fetch("api/admin", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            name: $('[name="firstName"]').val(),
            lastName: $('[name="lastName"]').val(),
            age: $('[name="age"]').val(),
            email: $('[name="username"]').val(),
            password: $('[name="password"]').val(),
            roles: $('[name="roles_string"]').val()
        })
    })
        .then(() => {
            fillingTable();
            show("user_table", "new_user");

        })
        .catch(error => alert(error));
});

//TODO модальное окно редактированиея
$("#edit_modal").on("show.bs.modal", event => {
    const button = $(event.relatedTarget);

    $("#id_edit").val(button.data("id"));
    $("#name_edit").val(button.data("name")) ;
    $("#lastname_edit").val(button.data("lastname"));
    $("#age_edit").val(button.data("age"));
    $("#username_edit").val(button.data("email"));
    $("#password_edit").val(button.data("password"));

    button.data("roles")
        .split(",")
        .forEach(element => {
            if (element === "ADMIN") {
                document.getElementById("role_admin").selected = true;
            }
            if (element === "USER") {
                document.getElementById("role_user").selected = true;
            }
    })
})
    .on("hide.bs.modal", event => {
        document.getElementById("role_admin").selected = false;
        document.getElementById("role_user").selected = false;
});

document.getElementById("form_edit")
    .addEventListener("submit", event => {
        event.preventDefault();
        fetch("api/admin", {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                id: $('[name="idEdit"]').val(),
                name: $('[name="firstNameEdit"]').val(),
                lastName: $('[name="lastNameEdit"]').val(),
                age: $('[name="ageEdit"]').val(),
                email: $('[name="usernameEdit"]').val(),
                password: $('[name="passwordEdit"]').val(),
                roles: $('[name="roles_stringEdit"]').val()
            })
        })
            .then(() => fillingTable())
            .catch(error => alert(error));


        $("#edit_modal").modal("hide");
    })

//TODO модальное окно удаления
$("#delete_modal").on("show.bs.modal", event => {
    const button = $(event.relatedTarget);

    $("#id_delete").val(button.data("id"));
    $("#name_delete").val(button.data("name")) ;
    $("#lastname_delete").val(button.data("lastname"));
    $("#age_delete").val(button.data("age"));
    $("#username_delete").val(button.data("email"));
    $("#password_delete").val(button.data("password"));

    button.data("roles")
        .split(",")
        .forEach(element => {
            if (element === "ADMIN") {
                document.getElementById("role_admin_delete").selected = true;
            }
            if (element === "USER") {
                document.getElementById("role_user_delete").selected = true;
            }
        })
})
    .on("hide.bs.modal", event => {
        document.getElementById("role_admin_delete").selected = false;
        document.getElementById("role_user_delete").selected = false;
    });

document.getElementById("delete_modal")
    .addEventListener("submit", event => {
        event.preventDefault();

        fetch(`api/admin/${$('[name="idDelete"]').val()}`,{
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            }
        })
            .then(() => fillingTable())
            .catch(error => alert(error));
        $("#delete_modal").modal("hide");
    })