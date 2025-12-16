-- Tạo database cho SQL Server
CREATE DATABASE quan_an_db;
GO

USE quan_an_db;
GO

-- Bảng danh mục món ăn
CREATE TABLE danh_muc (
    id INT IDENTITY(1,1) PRIMARY KEY,
    ten_danh_muc NVARCHAR(100) NOT NULL,
    mo_ta NVARCHAR(MAX),
    ngay_tao DATETIME2 DEFAULT GETDATE()
);
GO

-- Bảng món ăn
CREATE TABLE mon_an (
    id INT IDENTITY(1,1) PRIMARY KEY,
    ten_mon NVARCHAR(200) NOT NULL,
    mo_ta NVARCHAR(MAX),
    gia DECIMAL(10,2) NOT NULL,
    hinh_anh NVARCHAR(255),
    id_danh_muc INT,
    trang_thai BIT DEFAULT 1,
    ngay_tao DATETIME2 DEFAULT GETDATE(),
    FOREIGN KEY (id_danh_muc) REFERENCES danh_muc(id) ON DELETE SET NULL
);
GO

-- Bảng bàn ăn
CREATE TABLE ban_an (
    id INT IDENTITY(1,1) PRIMARY KEY,
    so_ban NVARCHAR(10) NOT NULL UNIQUE,
    so_cho_ngoi INT NOT NULL,
    trang_thai NVARCHAR(20) DEFAULT 'TRONG'
);
GO

-- Bảng đơn hàng
CREATE TABLE don_hang (
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_ban INT,
    ten_khach_hang NVARCHAR(100),
    so_dien_thoai NVARCHAR(15),
    tong_tien DECIMAL(10,2) DEFAULT 0,
    trang_thai NVARCHAR(20) DEFAULT 'DANG_CHO',
    ghi_chu NVARCHAR(MAX),
    ngay_tao DATETIME2 DEFAULT GETDATE(),
    ngay_cap_nhat DATETIME2 DEFAULT GETDATE(),
    FOREIGN KEY (id_ban) REFERENCES ban_an(id) ON DELETE SET NULL
);
GO

-- Bảng chi tiết đơn hàng
CREATE TABLE chi_tiet_don_hang (
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_don_hang INT NOT NULL,
    id_mon_an INT NOT NULL,
    so_luong INT NOT NULL DEFAULT 1,
    gia DECIMAL(10,2) NOT NULL,
    thanh_tien DECIMAL(10,2) NOT NULL,
    ghi_chu NVARCHAR(MAX),
    FOREIGN KEY (id_don_hang) REFERENCES don_hang(id) ON DELETE CASCADE,
    FOREIGN KEY (id_mon_an) REFERENCES mon_an(id)
);
GO

-- Thêm dữ liệu mẫu
INSERT INTO danh_muc (ten_danh_muc, mo_ta) VALUES
(N'Cơm', N'Các món cơm phổ biến'),
(N'Phở', N'Phở và các món nước'),
(N'Bún', N'Các loại bún'),
(N'Đồ uống', N'Nước giải khát'),
(N'Món ăn vặt', N'Các món ăn vặt nhẹ');
GO

INSERT INTO mon_an (ten_mon, mo_ta, gia, hinh_anh, id_danh_muc, trang_thai) VALUES
(N'Cơm sườn', N'Cơm sườn nướng kèm rau', 35000, 'com-suon.jpg', 1, 1),
(N'Cơm gà', N'Cơm gà xối mỡ', 32000, 'com-ga.jpg', 1, 1),
(N'Cơm tấm', N'Cơm tấm sườn bì chả', 40000, 'com-tam.jpg', 1, 1),
(N'Phở bò', N'Phở bò tái nạm', 45000, 'pho-bo.jpg', 2, 1),
(N'Phở gà', N'Phở gà ta', 40000, 'pho-ga.jpg', 2, 1),
(N'Bún bò Huế', N'Bún bò Huế cay', 42000, 'bun-bo-hue.jpg', 3, 1),
(N'Bún chả', N'Bún chả Hà Nội', 38000, 'bun-cha.jpg', 3, 1),
(N'Nước ngọt', N'Coca, Pepsi, 7Up', 10000, 'nuoc-ngot.jpg', 4, 1),
(N'Trà đá', N'Trà đá miễn phí', 0, 'tra-da.jpg', 4, 1),
(N'Chả giò', N'Chả giò 2 cái', 15000, 'cha-gio.jpg', 5, 1);
GO

INSERT INTO ban_an (so_ban, so_cho_ngoi, trang_thai) VALUES
(N'B01', 4, N'TRONG'),
(N'B02', 4, N'TRONG'),
(N'B03', 2, N'TRONG'),
(N'B04', 6, N'TRONG'),
(N'B05', 4, N'TRONG'),
(N'B06', 2, N'TRONG'),
(N'B07', 8, N'TRONG'),
(N'B08', 4, N'TRONG');
GO

-- Đơn hàng mẫu
INSERT INTO don_hang (id_ban, ten_khach_hang, so_dien_thoai, tong_tien, trang_thai, ghi_chu) VALUES
(1, N'Nguyễn Văn A', '0901234567', 80000, N'DANG_NAU', N'Không hành'),
(2, N'Trần Thị B', '0912345678', 45000, N'HOAN_THANH', N'');
GO

INSERT INTO chi_tiet_don_hang (id_don_hang, id_mon_an, so_luong, gia, thanh_tien, ghi_chu) VALUES
(1, 1, 2, 35000, 70000, N'Ít cơm'),
(1, 8, 1, 10000, 10000, N''),
(2, 4, 1, 45000, 45000, N'Nhiều rau');
GO