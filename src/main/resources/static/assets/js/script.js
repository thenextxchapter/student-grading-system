$(document).ready(function () {
    $("#logoutLink").on("click", function (e) {
        e.preventDefault();
        document.logoutForm.submit();
    });
});

function showConfirmDeleteModal(link, entityName) {
    let entityId = link.attr("entityId");

    $("#yesButton").attr("href", link.attr("href"));
    $("#confirmText").text("Are you sure you want to delete this " + entityName + " ID " + entityId + "?");
    $("#confirmModal").modal("show")
}
