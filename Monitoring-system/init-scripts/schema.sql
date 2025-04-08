-- =======================================
-- 🌟 Hệ thống Quản lý Năng lượng Tiêu thụ
-- =======================================

-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS energy_management;
USE energy_management;

-- =======================================
-- 🛃️ Tạo Bảng
-- =======================================

-- Bảng smart_outlet: lưu thông tin thiết bị điện thông minh
CREATE TABLE smart_outlet (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(255) NOT NULL
);

-- Bảng raw_log: lưu dữ liệu thô từ thiết bị
CREATE TABLE raw_log (
                         log_id INT AUTO_INCREMENT PRIMARY KEY,
                         outlet_id INT,
                         power FLOAT NOT NULL,
                         timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (outlet_id) REFERENCES smart_outlet(id) ON DELETE CASCADE
);

-- Bảng aggregated_log: lưu dữ liệu tổng hợp, có thêm outlet_id
CREATE TABLE aggregated_log (
                                log_id INT AUTO_INCREMENT PRIMARY KEY,
                                outlet_id INT,
                                avg_power FLOAT,
                                max_power FLOAT,
                                min_power FLOAT,
                                timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (outlet_id) REFERENCES smart_outlet(id) ON DELETE CASCADE
);

-- Bảng trung gian smart_outlet_aggregated_log cho quan hệ Many-to-Many
CREATE TABLE smart_outlet_aggregated_log (
                                             outlet_id INT NOT NULL,
                                             log_id INT NOT NULL,
                                             FOREIGN KEY (outlet_id) REFERENCES smart_outlet(id),
                                             FOREIGN KEY (log_id) REFERENCES aggregated_log(log_id)
);

-- =======================================
-- ⚡ Tạo Chỉ mục
-- =======================================
CREATE INDEX idx_rawlog_outlet ON raw_log(outlet_id, timestamp);
CREATE INDEX idx_aggregatedlog_timestamp ON aggregated_log(timestamp);
CREATE INDEX idx_smartoutlet_aggregated_log_outlet ON smart_outlet_aggregated_log(outlet_id);
CREATE INDEX idx_smartoutlet_aggregated_log_log ON smart_outlet_aggregated_log(log_id);

-- =======================================
-- 🚀 Seed dữ liệu test
-- =======================================
INSERT INTO smart_outlet (id, name) VALUES (1, 'Outlet A'), (2, 'Outlet B');

-- =======================================
-- 🔄 Trigger nén theo outlet_id mỗi 60 bản ghi
--      + Tự động insert vào bảng smart_outlet_aggregated_log
-- =======================================
DELIMITER //

CREATE TRIGGER compress_rawlog AFTER INSERT ON raw_log
    FOR EACH ROW
BEGIN
    DECLARE count_logs INT;

    SELECT COUNT(*) INTO count_logs
    FROM raw_log
    WHERE outlet_id = NEW.outlet_id;

    IF count_logs % 60 = 0 THEN
        -- Thêm dữ liệu nén vào aggregated_log
        INSERT INTO aggregated_log (outlet_id, min_power, max_power, avg_power, timestamp)
    SELECT
        outlet_id,
        MIN(power),
        MAX(power),
        AVG(power),
        DATE_FORMAT(MAX(timestamp), '%Y-%m-%d %H:%i:00')
    FROM raw_log
    WHERE outlet_id = NEW.outlet_id
    GROUP BY outlet_id;

    -- Liên kết outlet với log_id vừa được tạo
    INSERT INTO smart_outlet_aggregated_log (outlet_id, log_id)
    VALUES (
               NEW.outlet_id,
               (SELECT MAX(log_id) FROM aggregated_log WHERE outlet_id = NEW.outlet_id)
           );
END IF;
END;
//

DELIMITER ;

-- =======================================
-- 🧪 Thủ tục test chèn 60 bản ghi vào raw_log
-- =======================================

DELIMITER //

CREATE PROCEDURE insert_60_rawlog(IN outlet_id INT)
BEGIN
    DECLARE i INT DEFAULT 1;
    IF outlet_id IS NULL THEN
        SET outlet_id = 1;
END IF;

    WHILE i <= 60 DO
        INSERT INTO raw_log (outlet_id, power)
        VALUES (outlet_id, FLOOR(RAND() * 100));
        SET i = i + 1;
END WHILE;
END;
//

DELIMITER ;

-- =======================================
-- 🧪 Test Trigger và Nén + Liên kết tự động
-- =======================================
CALL insert_60_rawlog(1);
CALL insert_60_rawlog(2);

-- Xem dữ liệu raw
SELECT * FROM raw_log;

-- Xem kết quả nén
SELECT * FROM aggregated_log;

-- Xem bảng trung gian
SELECT * FROM smart_outlet_aggregated_log;
