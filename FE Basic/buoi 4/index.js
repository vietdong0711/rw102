var idUpdate = -1; // id cần update

// hiên thị danh sách account
loadTable();

// hiện icon theo giá trị của theme trong localStorage
//  = light hoặc chưa tồn tại       icon mặt trời
//  = dark                          icon mặt trăng
var theme = localStorage.getItem("theme");

if (theme === "dark") {
    $("#themeId").append(
        '<i class="fa fa-moon-o" style="font-size: 24px"></i>',
    );
    $("html").attr("data-theme", "dark");
} else {
    $("#themeId").append('<i class="fa fa-sun-o" style="font-size: 24px"></i>');
    $("html").attr("data-theme", "light");
}

// jqclick
$("#themeId").click(function (e) {
    var currentTheme = localStorage.getItem("theme");
    if (currentTheme === "light" || currentTheme == null) {
        // thay đổi css cho html để đổi màu theo theme
        $("html").attr("data-theme", "dark");
        // update gtri cho theme trong localStorage
        localStorage.setItem("theme", "dark");

        // đổi icon ở màn hình
        $("#themeId").empty();
        $("#themeId").append(
            '<i class="fa fa-moon-o" style="font-size: 24px"></i>',
        );
    } else if (currentTheme === "dark") {
        // thay đổi css cho html để đổi màu theo theme
        $("html").attr("data-theme", "light");
        // update gtri cho theme trong localStorage
        localStorage.setItem("theme", "light");

        // đổi icon ở màn hình
        $("#themeId").empty();
        $("#themeId").append(
            '<i class="fa fa-sun-o" style="font-size: 24px"></i>',
        );
    }
});

// hiên thị danh sách account
function loadTable() {
    var filteredAccounts = [];
    // call API để lấy danh sách account
    // jqAjax
    $.ajax({
        type: "GET",
        url: "https://6b0047fe7fc363ccff09ff9b.mockapi.io/api/v1/account",
        // data: "data",// creat + update
        dataType: "JSON",
        success: function (response) {
            //response: dữ liệu trả ra(kết quả)
            filteredAccounts = response;
            $(".account-table-body").empty(); // xóa hết nội dung trong tbody
            for (var i = 0; i < filteredAccounts.length; i++) {
                $(".account-table-body").append(`<tr>
                            <td>${filteredAccounts[i].id}</td>
                            <td>${filteredAccounts[i].username}</td>
                            <td>${filteredAccounts[i].email}</td>
                            <td>${filteredAccounts[i].departmentName}</td>
                            <td>${filteredAccounts[i].positionName}</td>
                            <td>
                                <a class="btn btn-warning" data-toggle="modal" href="#modal-id" onclick="editAccount(${filteredAccounts[i].id})">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                </a>
                                <button type="button" class="btn btn-danger" onclick="deleteAccount(${filteredAccounts[i].id})">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </button>
                            </td>
                        </tr>`);
            }
        },
    });
}

function deleteAccount(id) {
    var confirm = window.confirm(
        "Bạn có chắc chắn muốn xóa account này không?",
    );
    if (confirm) {
        // call API để xóa dữ liệu
        $.ajax({
            type: "DELETE",
            url:
                "https://6b0047fe7fc363ccff09ff9b.mockapi.io/api/v1/account/" +
                id,
            dataType: "JSON",
            success: function (response) {
                loadTable(); // load lại danh sách account
                alert("Delete successfuly!");
            },
        });
    }
}

function create() {
    // - lấy dữ liệu từ các ô input
    var v_username = $("#inputUsername").val();
    var v_email = $("#inputEmail").val();
    var v_departmentName = $("#inputDepartmentName").val();
    var v_positionName = $("#inputPositionName").val();

    // - tạo ra 1 object  JS
    var newAccount = {
        username: v_username,
        email: v_email,
        departmentName: v_departmentName,
        positionName: v_positionName,
    };

    // - call api tạo account   JSON
    $.ajax({
        type: "POST",
        url: "https://6b0047fe7fc363ccff09ff9b.mockapi.io/api/v1/account",
        data: JSON.stringify(newAccount), // chuyên object to json
        contentType: "application/json",
        success: function (response) {
            // - reset lại các ô input
            resetModal();

            // - đóng modal đi
            $("#modal-id").modal("hide");

            // - gọi đến function loadTable để hiển thị lại ds
            loadTable();

            // thong báo tạo thành công
            alert("Create account successfully!");
        },
    });
}

function editAccount(id) {
    idUpdate = id; // lưu lại id cần update
    // call api để tìm ra account tương ứng
    $.ajax({
        type: "GET",
        url: "https://6b0047fe7fc363ccff09ff9b.mockapi.io/api/v1/account/" + id,
        dataType: "JSON",
        success: function (response) {
            // tồn tại
            $("#inputUsername").val(response.username);
            $("#inputEmail").val(response.email);
            $("#inputDepartmentName").val(response.departmentName);
            $("#inputPositionName").val(response.positionName);
        },
        error: function (err) {
            // ko tồn tại
            alert("Account not found!");
            $("#modal-id").modal("hide");
            loadTable();
        },
    });
}

function update() {
    // - lấy dữ liệu từ các ô input
    var v_username = $("#inputUsername").val();
    var v_email = $("#inputEmail").val();
    var v_departmentName = $("#inputDepartmentName").val();
    var v_positionName = $("#inputPositionName").val();

    var updateAccount = {
        username: v_username,
        email: v_email,
        departmentName: v_departmentName,
        positionName: v_positionName,
    };

    // call API update account
    $.ajax({
        type: "PUT",
        url:
            "https://6b0047fe7fc363ccff09ff9b.mockapi.io/api/v1/account/" +
            idUpdate,
        data: JSON.stringify(updateAccount),
        contentType: "application/json",
        success: function (response) {
            // - reset lại các ô input
            resetModal();

            // - đóng modal đi
            $("#modal-id").modal("hide");

            // thong báo tạo thành công
            alert("Update account successfully!");

            // - gọi đến function loadTable để hiển thị lại ds
            loadTable();
        },
    });
}

function resetModal() {
    $("#inputUsername").val("");
    $("#inputEmail").val("");
    $("#inputDepartmentName").val("");
    $("#inputPositionName").val("");
    idUpdate = -1; // reset lại idUpdate
}

// khi đóng modal thì reset lại các ô input
$("#modal-id").on("hidden.bs.modal", function () {
    resetModal();
});

// khi mở modal thì hien thị title tương ứng Create/Update account
$("#modal-id").on("show.bs.modal", function () {
    $(".modal-title").empty(); // xóa hết nội dung trong modal-title
    $(".modal-title").append(
        idUpdate === -1 ? "Create account" : "Update account",
    ); // nếu idUpdate = -1 thì hiển thị Create account, ngược lại hiển thị Update account
});

// create + update
function save() {
    if (idUpdate === -1) {
        create();
    } else {
        update();
    }
}
