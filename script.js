document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("add-device").addEventListener("click", function () {
        alert("Thêm thiết bị mới (Chức năng này sẽ được phát triển)");
    });
    
    document.getElementById("predict-btn").addEventListener("click", function () {
        alert("Dự đoán tiêu thụ điện (Chức năng này sẽ được phát triển)");
    });
});

function toggleDevice(deviceId, checkbox) {
    let status = checkbox.checked ? "Bật" : "Tắt";
    alert(`Thiết bị ${deviceId} đã ${status}`);
    // TODO: Gửi yêu cầu API đến backend để cập nhật trạng thái thiết bị
}

function deleteDevice(deviceId, button) {
    if (confirm("Bạn có chắc chắn muốn xóa thiết bị này?")) {
        let row = button.closest("tr");
        row.remove();
        alert(`Đã xóa thiết bị ${deviceId}`);
        // TODO: Gửi yêu cầu API đến backend để xóa thiết bị khỏi CSDL
    }
}
