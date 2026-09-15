$(document).ready(function () {
    loadTyreList();
});

function loadTyreList() {
    $.ajax({
        url: '/tyre',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            if (data && data.length > 0) {
                let innerText = '';

                for (let tyre of data) {
                    // Используем корректное поле tyreManufacturer из DTO
                    let manufacturer = tyre['tyreManufacturer'] !== undefined ? tyre['tyreManufacturer'] : '';

                    innerText += `
                        <tr>
                            <td>${tyre['id']}</td>
                            <td>${tyre['tyreName']}</td>
                            <td>${tyre['tyreProfile']}</td>
                            <td>${tyre['price']}</td>
                            <td>${manufacturer}</td>
                            <td>
                                <button type="button"
                                        class="btn btn-primary"
                                        onclick="editTyre(${tyre['id']})">
                                    EDIT
                                </button>
                            </td>
                            <td>
                                <button type="button"
                                        class="btn btn-danger"
                                        onclick="deleteTyre(${tyre['id']})">
                                    DELETE
                                </button>
                            </td>
                        </tr>
                    `;
                }

                $('#content-of-tyres-table').html(innerText);
            } else {
                $('#content-of-tyres-table').html('');
                $('#table-of-tyres').find('.alert').remove();
                $('#table-of-tyres').prepend(`
                    <div class="alert alert-warning mx-2" role="alert">
                        <span>Tyres not found!</span>
                    </div>
                `);
            }
        },
        error: function () {
            $('#server-response-message-label').html(`
                <div class="alert alert-danger alert-dismissible fade show mx-2" role="alert">
                    <span>List of tyres can not be loaded!</span>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            `);
        }
    });
}

function editTyre(id) {
    $.ajax({
        url: `/tyre/${id}`,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            $('#tyre-form-status').html('Update');

            $('#tyre-id-input').val(data['id']);
            $('#tyre-name-input').val(data['tyreName']);
            $('#tyre-profile-input').val(data['tyreProfile']);
            $('#tyre-price-input').val(data['price']);
            $('#tyre-manufacturer-input').val(data['tyreManufacturer']);
        },
        error: function () {
            $('#server-response-message-label').html(`
                <div class="alert alert-danger alert-dismissible fade show mx-2" role="alert">
                    <span>Tyre data not found or can not be loaded!</span>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            `);
        }
    });
}

function saveTyre() {
    const id = $('#tyre-id-input').val();
    const name = $('#tyre-name-input').val();
    const profile = $('#tyre-profile-input').val();
    const price = $('#tyre-price-input').val();
    const manufacturer = $('#tyre-manufacturer-input').val();

    if (id) {
        const tyreDto = {
            'id': id,
            'tyreName': name,
            'tyreProfile': profile,
            'price': price,
            'tyreManufacturer': manufacturer
        };

        $.ajax({
            url: `/tyre/updatetyre/${tyreDto.id}`,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(tyreDto),
            success: function () {
                clearForm();
                loadTyreList();
                $('#server-response-message-label').html(`
                    <div class="alert alert-success alert-dismissible fade show mx-2" role="alert">
                        <span>Tyre has successfully been updated!</span>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                `);
            },
            error: function () {
                $('#server-response-message-label').html(`
                    <div class="alert alert-danger alert-dismissible fade show mx-2" role="alert">
                        <span>Some errors occurred during updating tyre data!</span>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                `);
            }
        });
    } else {
        const tyreDto = {
            'tyreName': name,
            'tyreProfile': profile,
            'price': price,
            'tyreManufacturer': manufacturer
        };

        $.ajax({
            url: '/tyre/addtyre',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(tyreDto),
            success: function () {
                clearForm();
                loadTyreList();
                $('#server-response-message-label').html(`
                    <div class="alert alert-success alert-dismissible fade show mx-2" role="alert">
                        <span>Tyre has successfully been saved!</span>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                `);
            },
            error: function () {
                $('#server-response-message-label').html(`
                    <div class="alert alert-danger alert-dismissible fade show mx-2" role="alert">
                        <span>Some errors occurred during saving tyre!</span>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                `);
            }
        });
    }
}

function clearForm() {
    $('#tyre-id-input').val('');
    $('#tyre-name-input').val('');
    $('#tyre-profile-input').val('');
    $('#tyre-price-input').val('');
    $('#tyre-manufacturer-input').val('');
    $('#tyre-form-status').html('Add');
}

function deleteTyre(id) {
    $.ajax({
        url: `/tyre/delete/${id}`,
        type: 'DELETE',
        success: function () {
            loadTyreList();

            $('#server-response-message-label').html(`
                <div class="alert alert-success alert-dismissible fade show mx-2" role="alert">
                    <span>Tyre has successfully been deleted!</span>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            `);
        },
        error: function () {
            $('#server-response-message-label').html(`
                <div class="alert alert-danger alert-dismissible fade show mx-2" role="alert">
                    <span>Some errors occurred during deleting tyre!</span>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            `);
        }
    });
}