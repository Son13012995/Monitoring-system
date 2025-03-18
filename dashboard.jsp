<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard Thiết Bị</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="dashboard-container">
        <h2>Dashboard Thiết Bị</h2>
        <button id="add-device"><i class="fas fa-plus"></i> Thêm thiết bị</button>
        
        <table>
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Thiết bị</th>
                    <th>Công suất tiêu thụ (W)</th>
                    <th>Trạng thái</th>
                </tr>
            </thead>
            <tbody id="device-list">
                <c:forEach var="device" items="${devices}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${device.name}</td>
                        <td>${device.powerRating} W</td>
                        <td>${device.status ? "Bật" : "Tắt"}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <button id="predict-btn"><i class="fas fa-chart-line"></i> Dự đoán tiêu thụ</button>
        <canvas id="power-chart"></canvas>
    </div>
</body>
</html>
