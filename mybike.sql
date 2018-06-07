-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 03, 2018 lúc 06:05 CH
-- Phiên bản máy phục vụ: 10.1.21-MariaDB
-- Phiên bản PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `mybike`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `baoduong`
--

CREATE TABLE `baoduong` (
  `maxe` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `mabaoduong` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `baoduong`
--

INSERT INTO `baoduong` (`maxe`, `mabaoduong`) VALUES
('MX03', 'MBD03'),
('MX04', 'MBD01');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoidung`
--

CREATE TABLE `nguoidung` (
  `username` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nguoidung`
--

INSERT INTO `nguoidung` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacnho`
--

CREATE TABLE `nhacnho` (
  `maphutung` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `noidungnhacnho` varchar(1000) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacnho`
--

INSERT INTO `nhacnho` (`maphutung`, `noidungnhacnho`) VALUES
('PT01', 'Theo thời gian, dầu nhớt trong xe trở nên kém chất lượng làm giảm khả năng bôi trơn. Do vậy, bạn cần thay nhớt cho xe theo định kỳ khoảng 2.000-3.000 km một lần. Ngoài ra, cũng nên thay nhớt xe máy sau khi xe bị ngập nước.Để xe được thay nhớt với chất lượng tốt nhất, bạn nên thay dầu theo đúng tiêu chuẩn được hãng xe đề nghị. Hiện nay trên thị trường có 3 loại dầu nhớt chính là: dầu nhớt tổng hợp (100% Synthetic-oil), dầu bán tổng hợp ( Semi-synthetic oil) và dầu khoáng (Mineral oil). Tuỳ vào loại xe bạn đang sử dụng để lựa chọn dầu nhớt thích hợp.'),
('PT02', 'Bugi là bộ phận đánh lửa đốt cháy nhiên liệu và sinh công suất cho xe. Đầu bugi mòn sẽ gây hiện tượng đánh lửa không đều, động cơ hụt hơi, hao xăng. Mặc dù bugi là một bộ phận có độ bền cao nhưng bạn cũng nên kiểm tra và thay thế định kỳ 10.000 km/ lần để xe luôn vận hành tốt nhất.'),
('PT03', 'Nhiệm vụ của lọc gió là đưa luồng không khí sạch vào khoang nhiên liệu trước khi đốt cháy. Lọc gió bẩn sẽ khiến xe chạy yếu, không đốt cháy hết nhiên liệu, hao xăng. Tùy vào loại lọc gió của mỗi dòng xe mà kỹ thuật viên sẽ vệ sinh hoặc thay mới nhưng tốt hơn hết bạn nên kiểm tra lọc gió theo định kỳ 10.000 km.'),
('PT04', 'Hầu hết xe tay ga hiện nay đều sử dụng hệ thống làm mát bằng chất lỏng. Nếu xe mất quá nhiều nước mát sẽ khiến xe nóng máy, nghiêm trọng có thể vỡ lốc máy. Do đó bạn cũng nên kiểm tra nước làm mát cho xe, định kỳ khoảng 10.000 km/lần, đặc biệt sau những chuyến đi dài, đèo dốc.'),
('PT05', 'Để bảo vệ ắc quy lâu bền người dùng nên tắt hẳn chìa khóa xe về nút off khi không sử dụng. Với những bình ắc quy nước, nên kiểm tra châm nước thường xuyên và nạp lại bình mỗi tháng.Một yếu tố rất quan trọng ảnh hưởng tới tuổi thọ của ắc-quy (cả khô và nước) đó là nhiệt độ. Nhiệt độ cao khiến các phản ứng trong ắc-quy diễn ra nhanh hơn và vì thế nó cũng “chết” nhanh hơn. Vì vậy, hãy giữ ắc-quy sạch, mát và khô để nâng cao tuổi thọ của chúng. Các ắc-quy có thể sử dụng trong điều kiện nhiệt độ -15oC đến +45oC, xong tốt nhất nên sử dụng trong khoảng +5oc đến +30oc, ắc-quy sẽ cho tuổi thọ cao nhất.'),
('PT06', 'Má phanh là bộ phận chuyển động năng thành nhiệt năng, giúp xe giảm tốc, do đó sẽ mòn dần theo thời gian. Má phanh mòn là một trong những nguyên nhân gây vênh đĩa phanh. Về lâu dài nếu không thay má phanh mới, trường hợp nặng bạn sẽ phải thay luôn cả đĩa phanh.dầu phanh có thể bị nhiễm tạp chất sinh ra bọt khí trong quá trình hoạt động, làm giảm hiệu quả phanh hoặc làm phanh cứng, giật. Do đó, bạn nên kiểm tra, thay mới má phanh, thay dầu phanh sau mỗi 15.000 – 20.000 km.');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phutung`
--

CREATE TABLE `phutung` (
  `maphutung` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tenphutung` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `hanmuckm` int(11) NOT NULL,
  `hanmucngay` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phutung`
--

INSERT INTO `phutung` (`maphutung`, `tenphutung`, `hanmuckm`, `hanmucngay`) VALUES
('PT01', 'Dầu nhớt', 3000, 100),
('PT02', 'Bugi', 1000, 33),
('PT03', 'Lọc gió', 10000, 333),
('PT04', 'Nước làm mát', 10000, 333),
('PT05', 'Ác quy', 60000, 2000),
('PT06', 'Phanh', 50000, 1666);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phutungbd`
--

CREATE TABLE `phutungbd` (
  `mabaoduong` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `maphutung` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `cachthuc` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ngaybaoduong` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ghichu` varchar(1000) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phutungbd`
--

INSERT INTO `phutungbd` (`mabaoduong`, `maphutung`, `cachthuc`, `ngaybaoduong`, `ghichu`) VALUES
('MBD01', 'PT01', 'Bảo dưỡng', '23-5-2018', 'Thay dầu quán ô Sáu quận 1'),
('MBD01', 'PT02', 'Bảo dưỡng', '28-5-2018', 'Bugi không lên lửa, mòn đầu'),
('MBD01', 'PT04', 'Thay thế', '20-5-2018', 'Thay nước làm mát tại 23 Nguyễn Thị Riêng Quận 3'),
('MBD01', 'PT05', 'Bảo dưỡng', '1-6-2018', 'Bảo dưỡng tại nhà ông Bảy quận 1'),
('MBD02', 'PT01', 'Bảo dưỡng', '23-5-2018', 'Thay dầu chính hãng tại trung tâm mua xe'),
('MBD02', 'PT03', 'Bảo dưỡng', '5-2-2018', 'Giá 200000'),
('MBD02', 'PT06', 'Bảo dưỡng', '11-5-2018', 'Tra dầu vào phanh'),
('MBD03', 'PT05', 'Bảo dưỡng', '2-5-2011', 'Ác quy chập điện'),
('MBD03', 'PT06', 'Thay thế', '7-5-2014', 'Thay phanh sau'),
('MBD1191', 'PT01', 'Bảo dưỡng', '8-5-2018', 'do something'),
('MBD1191', 'PT04', 'Thay thế', '2-5-2018', 'thay nước làm mát tại Quận 1'),
('MBD1488', 'PT01', 'Thay thế', '15-5-2018', 'vxv'),
('MBD1488', 'PT04', 'Thay thế', '23-5-2018', 'vsg'),
('MBD152', 'PT01', 'Thay thế', '8-5-2018', 'bxhx'),
('MBD152', 'PT05', 'Thay thế', '15-5-2018', 'bxh'),
('MBD207', 'PT01', 'Thay thế', '1-5-2018', 'bdbd'),
('MBD207', 'PT04', 'Thay thế', '16-5-2018', 'vdvd'),
('MBD2373', 'PT01', 'Thay thế', '7-5-2018', 'vzv'),
('MBD2373', 'PT04', 'Thay thế', '22-5-2018', 'vzv'),
('MBD2400', 'PT01', 'Bảo dưỡng', '21-5-2018', 'vdg'),
('MBD2400', 'PT03', 'Thay thế', '22-5-2018', 'vzvss'),
('MBD2876', 'PT01', 'Bảo dưỡng', '9-5-2018', 'vzv'),
('MBD2876', 'PT04', 'Thay thế', '10-5-2018', 'vhdhd'),
('MBD3321', 'PT01', 'Bảo dưỡng', '1-6-2018', 'thay dầu'),
('MBD3321', 'PT04', 'Thay thế', '1-6-2018', 'thay nước làm mát'),
('MBD3321', 'PT06', 'Bảo dưỡng', '2-5-2018', 'tra dầu phanh xe'),
('MBD35', 'PT03', 'Bảo dưỡng', '3-5-2018', 'ghi chú'),
('MBD35', 'PT04', 'Thay thế', '5-5-2018', 'thay nước'),
('MBD3565', 'PT04', 'Thay thế', '9-5-2018', 'gdg'),
('MBD3565', 'PT05', 'Bảo dưỡng', '30-5-2018', 'vzv'),
('MBD4108', 'PT01', 'Bảo dưỡng', '1-5-2018', 'vxh'),
('MBD4108', 'PT03', 'Bảo dưỡng', '8-5-2018', 'vvx'),
('MBD4108', 'PT04', 'Bảo dưỡng', '9-5-2018', 'bdbd'),
('MBD4108', 'PT05', 'Bảo dưỡng', '7-5-2018', 'dv'),
('MBD4108', 'PT06', 'Thay thế', '9-5-2018', 'thay phanh'),
('MBD4134', 'PT05', 'Bảo dưỡng', '22-5-2018', 'ghi gì đó'),
('MBD417', 'PT01', 'Bảo dưỡng', '8-5-2018', 'gxhb'),
('MBD4387', 'PT02', 'Thay thế', '8-5-2018', 'thay mới bugi'),
('MBD4387', 'PT04', 'Thay thế', '17-5-2018', 'thay nước làm mát tại Quận 1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `xe`
--

CREATE TABLE `xe` (
  `maxe` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tenxe` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `hinhanh` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `xe`
--

INSERT INTO `xe` (`maxe`, `tenxe`, `hinhanh`) VALUES
('bdhhd', 'đức', 0x5b424033333937343365),
('bfhdhdnd', 'tên mới', 0x5b424061643837353934),
('gxgdbnd', 'google', 0x5b424065666439656262),
('gzgvtsg', 'vsggs', 0x5b424065343961623763),
('hdgd', 'tdtdt', 0x5b424036663834616162),
('hdhhd', 'xe phân khối', 0x5b424034356332646361),
('hdhhdhhdbd', 'hdhdhhd', 0x5b424032636336333637),
('hgdhd', 'ydhhd', 0x5b424032613335636566),
('hhdhdhds', 'bzhhs', 0x5b424035623232313639),
('hhdjdcds', 'gdhhd', 0x5b424038386231346661),
('mabfjjf', 'renew', 0x5b424063306236643439),
('mado', 'văn đức', 0x5b4240353433366532),
('maxeonu', 'tên xe mới', 0x5b424061343464393234),
('mhndc', 'duc demo', 0x5b424036343434643464),
('MX01', 'Honda Vision', ''),
('MX02', 'Honda Supra GTR 150', ''),
('MX03', 'Exciter', ''),
('MX04', 'Honda SH', '');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `baoduong`
--
ALTER TABLE `baoduong`
  ADD PRIMARY KEY (`maxe`,`mabaoduong`),
  ADD KEY `mabaoduong` (`mabaoduong`);

--
-- Chỉ mục cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`username`);

--
-- Chỉ mục cho bảng `nhacnho`
--
ALTER TABLE `nhacnho`
  ADD PRIMARY KEY (`maphutung`);

--
-- Chỉ mục cho bảng `phutung`
--
ALTER TABLE `phutung`
  ADD PRIMARY KEY (`maphutung`);

--
-- Chỉ mục cho bảng `phutungbd`
--
ALTER TABLE `phutungbd`
  ADD PRIMARY KEY (`mabaoduong`,`maphutung`),
  ADD KEY `maphutung` (`maphutung`);

--
-- Chỉ mục cho bảng `xe`
--
ALTER TABLE `xe`
  ADD PRIMARY KEY (`maxe`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `baoduong`
--
ALTER TABLE `baoduong`
  ADD CONSTRAINT `baoduong_ibfk_1` FOREIGN KEY (`maxe`) REFERENCES `xe` (`maxe`),
  ADD CONSTRAINT `baoduong_ibfk_2` FOREIGN KEY (`mabaoduong`) REFERENCES `phutungbd` (`mabaoduong`);

--
-- Các ràng buộc cho bảng `nhacnho`
--
ALTER TABLE `nhacnho`
  ADD CONSTRAINT `nhacnho_ibfk_1` FOREIGN KEY (`maphutung`) REFERENCES `phutung` (`maphutung`);

--
-- Các ràng buộc cho bảng `phutungbd`
--
ALTER TABLE `phutungbd`
  ADD CONSTRAINT `phutungbd_ibfk_1` FOREIGN KEY (`maphutung`) REFERENCES `phutung` (`maphutung`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
