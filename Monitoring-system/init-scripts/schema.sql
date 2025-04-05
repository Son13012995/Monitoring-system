CREATE DATABASE IF NOT EXISTS energy_management;
USE energy_management;

-- =======================================
-- 🌟 Hệ thống Quản lý Năng lượng Tiêu thụ
-- =======================================

-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS energy_management;
USE energy_management;

-- =======================================
-- 🛃️ Tạo Bảng
-- =======================================

-- Bảng SmartOutlet: lưu thông tin thiết bị điện thông minh
CREATE TABLE SmartOutlet (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(255) NOT NULL
);

-- Bảng RawLog: lưu dữ liệu thô từ thiết bị
CREATE TABLE RawLog (
                        log_id INT AUTO_INCREMENT PRIMARY KEY,
                        outlet_id INT,
                        power FLOAT NOT NULL,
                        timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (outlet_id) REFERENCES SmartOutlet(id) ON DELETE CASCADE
);

-- Bảng AggregatedLog: lưu dữ liệu tổng hợp
CREATE TABLE AggregatedLog (
                               log_id INT AUTO_INCREMENT PRIMARY KEY,
                               outlet_id INT,
                               min_power FLOAT,
                               max_power FLOAT,
                               avg_power FLOAT,
                               timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (outlet_id) REFERENCES SmartOutlet(id) ON DELETE CASCADE
);

-- =======================================
-- ⚡ Tạo Chỉ mục
-- =======================================
CREATE INDEX idx_rawlog_outlet ON RawLog(outlet_id, timestamp);
CREATE INDEX idx_aggregatedlog_outlet ON AggregatedLog(outlet_id, timestamp);

-- =======================================
-- 🚀 Tạo Trigger
-- =======================================
INSERT INTO SmartOutlet (name) VALUES ('New Device');

-- Trigger nén dữ liệu từ RawLog sang AggregatedLog sau mỗi 60 bản ghi
DELIMITER //
CREATE TRIGGER compress_rawlog AFTER INSERT ON RawLog
    FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM RawLog) % 60 = 0 THEN
        INSERT INTO AggregatedLog (outlet_id, min_power, max_power, avg_power, timestamp)
    SELECT outlet_id, MIN(power), MAX(power), AVG(power), DATE_FORMAT(timestamp, '%Y-%m-%d %H:%i:00')
    FROM RawLog
    GROUP BY outlet_id, DATE_FORMAT(timestamp, '%Y-%m-%d %H:%i:00');
END IF;
END //
DELIMITER ;

-- =======================================
-- 🧪 Tạo Thủ tục kiểm tra (Test Procedure)
-- =======================================

-- Thủ tục thêm 60 bản ghi tự động vào RawLog
DELIMITER //
CREATE PROCEDURE insert_60_rawlog(IN outlet_id INT)
BEGIN
    DECLARE i INT DEFAULT 1;

    -- Nếu outlet_id là NULL, gán giá trị mặc định là 1
    IF outlet_id IS NULL THEN
        SET outlet_id = 1;
END IF;

    WHILE i <= 60 DO
        INSERT INTO RawLog (outlet_id, power)
        VALUES (outlet_id, FLOOR(RAND() * 100));
        SET i = i + 1;
END WHILE;
END //
DELIMITER ;


-- =======================================
-- 📝 Kiểm tra Trigger Nén Dữ liệu
-- =======================================
CALL insert_60_rawlog(1);
SELECT * FROM RawLog;

-- Kiểm tra dữ liệu nén trong bảng AggregatedLog
SELECT * FROM AggregatedLog;

-- =======================================
-- 📝 Kiểm tra Dữ liệu Tổng hợp
-- =======================================

-- Thêm thiết bị mới và kiểm tra
INSERT INTO SmartOutlet (name) VALUES ('Device A');
CALL insert_60_rawlog(2);

-- Kiểm tra kết quả tổng hợp
SELECT outlet_id, MIN(power), MAX(power), AVG(power)
FROM RawLog
WHERE outlet_id = 2
GROUP BY outlet_id;

SELECT min_power, max_power, avg_power
FROM AggregatedLog
WHERE outlet_id = 2;

-- =======================================
-- 📝 Kiểm tra Tổng hợp Nhiều Thiết bị
-- =======================================

-- Thêm nhiều thiết bị
INSERT INTO SmartOutlet (name) VALUES ('Device B'), ('Device C'), ('Device D');

-- Thêm dữ liệu và kiểm tra
CALL insert_60_rawlog(3);
CALL insert_60_rawlog(4);
CALL insert_60_rawlog(5);

-- Kiểm tra dữ liệu tổng hợp
SELECT outlet_id, COUNT(*) AS AggregatedLogs
FROM AggregatedLog
GROUP BY outlet_id;

-- =======================================
-- 🧹 Xóa Dữ liệu (Dọn Dẹp)
-- =======================================

-- Xóa tất cả dữ liệu trong bảng RawLog
DELETE FROM RawLog;
ALTER TABLE RawLog AUTO_INCREMENT = 1;

-- Xóa tất cả dữ liệu trong bảng AggregatedLog
DELETE FROM AggregatedLog;
ALTER TABLE AggregatedLog AUTO_INCREMENT = 1;

-- Xóa tất cả dữ liệu trong bảng SmartOutlet
DELETE FROM SmartOutlet;
ALTER TABLE SmartOutlet AUTO_INCREMENT = 1;

-- Hoàn tất!
SELECT '✔️ Quản lý năng lượng tiêu thụ - Thiết lập hoàn tất!' AS Status;



