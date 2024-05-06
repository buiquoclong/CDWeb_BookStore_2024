-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 09, 2023 lúc 02:37 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `bookstoredb`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `author`
--

CREATE TABLE `author` (
                          `authorID` int(11) NOT NULL,
                          `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
                          `author_code` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
                          `created_at` date DEFAULT NULL,
                          `updated_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `author`
--

INSERT INTO `author` (`authorID`, `name`, `author_code`, `created_at`, `updated_at`) VALUES
                                                                                         (16, 'Nguyễn Nhật Ánh', 'nguyen-nhat-anh', '2023-04-09', '2023-04-19'),
                                                                                         (18, 'Fujiko F. Fujio', 'fujiko-fujio', '2023-04-08', '2023-04-19'),
                                                                                         (19, 'Gosho Aoyama', 'gosho-aoyama', '2023-04-09', NULL),
                                                                                         (22, 'Antoine De Saint-Exupéry', 'antoine-de-saint', '2023-04-22', '2023-04-22'),
                                                                                         (24, 'Minette Walters', 'minette-walters', '2023-04-25', '2023-04-29'),
                                                                                         (25, 'Luis Sepulveda', 'luis-sepulveda', '2023-04-25', NULL),
                                                                                         (26, 'R. J. Palacio', 'palacio', '2023-04-25', NULL),
                                                                                         (30, 'Eran Katz', 'eran-katz', '2023-05-05', NULL),
                                                                                         (31, 'Luật sư Nguyễn Ngọc Bích', 'nguyen-ngoc-bich', '2023-05-05', NULL),
                                                                                         (32, ' Luật sư Trương Hữu Ngữ', 'truong-huu-ngu', '2023-05-05', NULL),
                                                                                         (33, 'Bộ Giáo Dục Và Đào Tạo', 'bo-giao-duc', '2023-05-05', NULL),
                                                                                         (34, 'Phạm Anh Đức', 'pham-anh-duc', '2023-05-05', NULL),
                                                                                         (35, 'Paul Kennedy', 'paul-kennedy', '2023-05-05', NULL),
                                                                                         (36, 'Nhiều tác giả', 'nhieu-tac-gia', '2023-05-05', NULL),
                                                                                         (37, 'Đồng Lạc', 'dong-lac', '2023-05-05', NULL),
                                                                                         (38, 'Rosie Nguyễn', 'rosie-nguyen', '2023-05-05', NULL),
                                                                                         (39, 'Hattie Hamilton', 'hattie-hamilton', '2023-05-05', NULL),
                                                                                         (40, ' Luis Sepulveda', ' luis-sepulveda', '2023-05-05', NULL),
                                                                                         (41, 'Gotouge Koyoharu', 'gotouge-koyoharu', '2023-05-23', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `book`
--

CREATE TABLE `book` (
                        `id` int(11) NOT NULL,
                        `title` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
                        `year_public` int(11) DEFAULT NULL,
                        `total_page` int(11) DEFAULT NULL,
                        `categoryID` int(11) NOT NULL,
                        `authorID` int(11) NOT NULL,
                        `publisher` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
                        `description` text COLLATE utf8_unicode_ci NOT NULL,
                        `price` int(11) NOT NULL,
                        `quantity_sold` int(11) NOT NULL,
                        `created_at` date DEFAULT NULL,
                        `updated_at` date DEFAULT NULL,
                        `active` bit(1) NOT NULL,
                        `hot` bit(1) NOT NULL,
                        `news` bit(1) NOT NULL,
                        `discount_percent` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `book`
--

INSERT INTO `book` (`id`, `title`, `year_public`, `total_page`, `categoryID`, `authorID`, `publisher`, `description`, `price`, `quantity_sold`, `created_at`, `updated_at`, `active`, `hot`, `news`, `discount_percent`) VALUES
                                                                                                                                                                                                                             (3, 'Conan Thám tử lừng danh - Khúc nhạc cầu siêu', 2000, 105, 1, 19, 'Kim Đồng', 'Theo chân cuộc hành trình của cậu bé thám tử trong cơ thể bị thu nhỏ - Conan', 25000, 50, NULL, NULL, b'1', b'1', b'1', 0),
                                                                                                                                                                                                                             (4, 'Truyện tranh Conan tuyển tập đặc biệt - Conan vs tổ chức áo đen', 2017, 200, 1, 19, 'Kim Đồng', '', 36000, 125, NULL, NULL, b'1', b'1', b'0', 20),
                                                                                                                                                                                                                             (6, 'Thám tử lừng danh Conan - Tập 80', 2007, 156, 1, 19, 'Kim Đồng', 'Mở đầu câu truyện, cậu học sinh trung học 17 tuổi Shinichi Kudo bị biến thành cậu bé Conan Edogawa. Shinichi trong phần đầu của Thám tử lừng danh Conan được miêu tả là một thám tử học đường xuất sắc. Trong một lần đi chơi công viên \"Miền Nhiệt đới\" với cô bạn từ thuở nhỏ Ran Mori, cậu tình cờ chứng kiến vụ một án giết người, Kishida - một hành khách trong trò chơi Chuyến tàu tốc hành đã bị giết một cách dã man. Cậu đã giúp cảnh sát làm sáng tỏ vụ án. Trên đường về nhà, cậu vô tình phát hiện một vụ làm ăn mờ ám của những người đàn ông mặc toàn đồ đen. Khi chúng phát hiện ra cậu, Shinichi đã bị đánh ngất đi. Sau đó những người đàn ông áo đen đó đã cho cậu uống một thứ thuốc độc chưa qua thử nghiệm là Apotoxin-4869 (APTX 4869) với mục đích thủ tiêu cậu. Tuy nhiên chất độc đã không giết chết Kudo. Khi tỉnh lại, cậu bàng hoàng nhận thấy mình đã bị teo nhỏ lại thành hình dạng của một cậu học sinh tiểu học.', 25000, 60, NULL, NULL, b'1', b'1', b'1', 10),
                                                                                                                                                                                                                             (20, 'Doraemon - Truyện ngắn - Kỉ niệm về bà', 0, 0, 1, 18, 'Kim Đồng', 'Doraemon - Truyện ngắn - Kỉ niệm về bà', 20000, 33, NULL, NULL, b'1', b'0', b'1', 0),
                                                                                                                                                                                                                             (21, 'Doraemon Truyện ngắn - Tập 44', 0, 0, 1, 18, 'Kim Đồng', 'Doraemon Truyện ngắn - Tập 44', 20000, 32, NULL, NULL, b'1', b'0', b'1', 0),
                                                                                                                                                                                                                             (22, 'Doraemon truyện ngắn - Tập 13', 1940, 0, 1, 18, 'Kim Đồng', 'Cùng phiêu lưu với chú mèo máy đến từ tương lai - Doraemon khám phá những câu chuyện thú vị.', 21000, 30, NULL, NULL, b'1', b'0', b'1', 10),
                                                                                                                                                                                                                             (24, 'Conan Thám tử lừng danh tập 1', 2007, 55, 1, 19, 'Kim Đồng', 'Conan Thám tử lừng danh tập 1', 20000, 101, NULL, NULL, b'1', b'1', b'1', 10),
                                                                                                                                                                                                                             (26, 'Những Người Hàng Xóm - Nguyễn Nhật Ánh', 0, 232, 10, 16, 'NXB Trẻ', 'Câu chuyện đi theo lời kể của một anh chàng mới lấy vợ, chuẩn bị đi làm và có ý thích viết văn. Anh chàng yêu vợ theo cách của mình, khen ngợi sùng bái người yêu cũng theo cách của mình, nhưng nhìn cuộc đời theo cách sống của những người hàng xóm. Sống trong tình yêu của vợ đầy mùi thơm và nhiều vị ngọt. Chứng kiến tình yêu của anh cảnh sát với cô bạn gái ngành y; mối tình thứ hai của người phụ nữ tốt bụng phát thanh viên ngôn ngữ ký hiệu. Và được chiêm nghiệm trong tình yêu đắm đuối mỗi ngày của ông họa sĩ già thương nhớ người vợ xinh đẹp-người mẫu, nàng thơ của ông.\r\n\r\nNhư một cuốn phim đầy màu sắc với âm điệu dịu dàng, êm ả. Cuộc sống bình yên của những người yêu thương nhau. Bài học về tình người đứng phía sau bài học về nghề viết, và cả trong câu chuyện về… một lối kinh doanh nhà cực kỳ đặc biệt! \r\n\r\nCâu chuyện mở ra sẽ là bất ngờ với bạn đọc “ruột” của Nguyễn Nhật Ánh, và kết thúc trong một sự dịu dàng nhẹ nhõm lòng, bởi nhà văn đã nhắc lại cho ta nhớ:  cuộc sống luôn thật là tươi đẹp biết bao. Khép sách lại, bạn sẽ nhận được niềm vui bình yên.\r\n\r\nVài đoạn trích trong tác phẩm:\r\n\r\n“…Nếu con biết cách mở cửa sổ, đời sống sẽ tràn vào trang viết của con. Đôi khi chúng ta vẫn nhìn đấy, nhưng chúng ta không thấy. Nếu trái tim con đập vì con người, thậm chí đập vì một con chim, con sẽ nhìn thấy rất nhiều thứ”…\r\n\r\n“…Ruben là người tử tế. Người tử tế cao hơn người đàng hoàng một bậc. Người đàng hoàng sống ngay ngắn, không làm hại ai. Còn người tử tế là người sẵn sàng chịu thiệt thòi vì người khác.\r\n\r\nTôi không rõ định nghĩa của ông Jakob chính xác đến mức nào nhưng tôi biết câu chuyện của ông đang làm tôi cảm động…”\r\n\r\nRất nhiều những câu chuyện cảm động nho nhỏ, có khi chỉ là tình tiết, hay một câu nói… trong khắp từng chương sách khiến bạn đọc vẫn có thể nhận ngay ra “chất văn” của nhà văn mình yêu quý - Nguyễn Nhật Ánh. Mặc dù cuốn sách này được ông viết theo một cách khác, hoàn toàn mới lạ, gây ngạc nhiên từ cảnh quan cho đến nhân vật.\r\n\r\nVà ắt nhiên - có một bài thơ dịu ngọt từ một tình yêu đáng ngưỡng mộ dài cả gần thế kỷ.\r\n\r\nNgoài ra, qua tác phẩm tác giả còn đề cập những đặc trưng của Việt Nam và Bỉ qua món ăn, qua danh lam thắng cảnh…', 110000, 321, NULL, NULL, b'1', b'1', b'1', 33),
                                                                                                                                                                                                                             (27, 'Mắt biếc - Nguyễn Nhật Ánh', 0, 300, 10, 16, 'NXB Trẻ', 'Mắt biếc là một tác phẩm được nhiều người bình chọn là hay nhất của nhà văn Nguyễn Nhật Ánh. Tác phẩm này cũng đã được dịch giả Kato Sakae dịch sang tiếng Nhật để giới thiệu với độc giả Nhật Bản.\r\n\r\n“Tôi gửi tình yêu cho mùa hè, nhưng mùa hè không giữ nổi. Mùa hè chỉ biết ra hoa, phượng đỏ sân trường và tiếng ve nỉ non trong lá. Mùa hè ngây ngô, giống như tôi vậy. Nó chẳng làm được những điều tôi ký thác. Nó để Hà Lan đốt tôi, đốt rụi. Trái tim tôi cháy thành tro, rơi vãi trên đường về.”\r\n\r\n… Bởi sự trong sáng của một tình cảm, bởi cái kết thúc buồn, rất buồn khi xuyên suốt câu chuyện vẫn là những điều vui, buồn lẫn lộn…', 110000, 150, NULL, NULL, b'1', b'0', b'1', 15),
                                                                                                                                                                                                                             (28, 'Ngày Xưa Có Một Chuyện Tình: Truyện Dài', 0, 344, 10, 16, 'NXB Trẻ', 'Ngày Xưa Có Một Chuyện Tình là tác phẩm mới tinh thứ 2 trong năm 2016 của nhà văn Nguyễn Nhật Ánh dài hơn 300 trang, được coi là tập tiếp theo của tập truyện Mắt biếc. Có một tình yêu dữ dội, với em,  của một người yêu em hơn chính bản thân mình - là anh.\r\n\r\nNgày xưa có một chuyện tình có phải là một câu chuyện cảm động  khi người ta yêu nhau, nỗi khát khao một hạnh phúc êm đềm ấm áp đến thế; hay đơn giản chỉ là chuyện ba người - anh, em, và người ấy…?\r\n\r\nBạn hãy mở sách ra, để chứng kiến làn gió tình yêu chảy qua như rải nắng trên khuôn mặt mùa đông của cô gái; nụ hôn đầu tiên ngọt mật, cái ôm đầu tiên, những giọt nước mắt và cái ôm xiết cuối cùng… rồi sẽ tìm thấy câu trả lời, cho riêng mình.', 120000, 100, NULL, NULL, b'1', b'1', b'1', 10),
                                                                                                                                                                                                                             (29, 'Kẻ nặn sáp', 2017, 352, 10, 24, 'IPM', 'Mẹ tôi luôn nói, không bao giờ được tin một người đàn ông có dái tai nằm thấp hơn khuôn miệng. Đó là dấu hiệu của bọn tội phạm. Nhìn ông ta xem.\r\n\r\nRất nhiều nhận định nghe đáng ngờ và đầy cảm tính như thế lại tồn tại sắt đá khắp câu chuyện này, giăng lên mê lộ khiến không những độc giả mà chính các nhân vật cũng phải bối rối và lạc đường trên hành trình đi tìm thật-giả.\r\n\r\nCách đây bốn năm, một cô gái trẻ bị kết án sát nhân, khi người ta tìm thấy thi thể mẹ và em cô giữa thảm máu lênh láng trên sàn bếp, được chặt thành từng mảnh rồi ráp lại, nhưng có lẽ do loạn trí, một số mảnh đã bị ráp nhầm từ người nọ sang người kia.\r\n\r\nDù thế nào, sự cố cũng đưa cô gái từ ngôi nhà từng bình yên đẹp đẽ đến thẳng trại giam. Và ở đó năm qua năm, cô dần biến thành một con quỷ cái đáng gờm với biệt danh Kẻ nặn sáp. Những mẩu nến, những vụn đất sét tựu hình người dưới các ngón tay cô như nghiền ngẫm cả hờn oán lẫn cầu nguyện, cho đến một ngày, thiên thần cứu rỗi quả đã xuất hiện trước cửa nhà giam  với mái tóc đỏ rực và niềm tin mong manh rằng có chuyện oan sai ở đây.\r\n\r\nBức tranh tàn sát lập thể bốn năm về trước, nay được tái hiện lần nữa, và các chi tiết gớm ghiếc chưa từng hé lộ giờ mới lỉa chỉa bung ra như những cọng rơm bục khỏi vỏ gối cũ.\r\n\r\nNhưng trong một ngàn sự thật, chỉ có một điều là thật sự. Thiên thần liệu có đủ sáng suốt và đức tin để trả mọi thứ về đúng chỗ?', 115000, 100, NULL, NULL, b'1', b'0', b'1', 10),
                                                                                                                                                                                                                             (30, 'Hoàng tử bé (Tái bản 2019)', 0, 102, 8, 22, 'Nhà Xuất Bản Hội Nhà Văn', 'Hoàng tử bé được viết ở New York trong những ngày tác giả sống lưu vong và được xuất bản lần đầu tiên tại New York vào năm 1943, rồi đến năm 1946 mới được xuất bản tại Pháp. Không nghi ngờ gì, đây là tác phẩm nổi tiếng nhất, được đọc nhiều nhất và cũng được yêu mến nhất của Saint-Exupéry. Cuốn sách được bình chọn là tác phẩm hay nhất thế kỉ 20 ở Pháp, đồng thời cũng là cuốn sách Pháp được dịch và được đọc nhiều nhất trên thế giới. Với 250 ngôn ngữ dịch khác nhau kể cả phương ngữ cùng hơn 200 triệu bản in trên toàn thế giới, Hoàng tử bé được coi là một trong những tác phẩm bán chạy nhất của nhân loại.\r\n\r\nỞ Việt Nam, Hoàng tử bé được dịch và xuất bản khá sớm. Từ năm 1966 đã có đồng thời hai bản dịch: Hoàng tử bé của Bùi Giáng do An Tiêm xuất bản và Cậu hoàng con của Trần Thiện Đạo do Khai Trí xuất bản. Từ đó đến nay đã có thêm nhiều bản dịch Hoàng tử bé mới của các dịch giả khác nhau. Bản dịch Hoàng tử bé lần này, xuất bản nhân dịp kỷ niệm 70 năm Hoàng tử bé ra đời, cũng là ấn bản đầu tiên được Gallimard chính thức chuyển nhượng bản quyền tại Việt Nam, hy vọng sẽ góp phần làm phong phú thêm việc dịch và tiếp nhận tác phẩm quan trọng này với các thế hệ độc giả.', 51000, 322, NULL, NULL, b'1', b'1', b'0', 0),
                                                                                                                                                                                                                             (31, 'Truyện đọc tiếng Anh - Story of a Seagull and the Cat', 0, 128, 4, 25, 'Harper Collins Publ. UK', 'Sau một vụ tràn dầu, một con hải âu đang hấp hối lên bờ để đẻ quả trứng cuối cùng và đáp xuống ban công, nơi nó gặp Zorba, một con mèo đen lớn từ cảng Hamburg. Con mèo hứa với con hải âu sẽ chăm sóc trứng, không ăn hải âu con một khi nó nở và - khó nhất trong tất cả - sẽ dạy con hải âu biết bay. Zorba và những người bạn mèo của mình sẽ thực hiện lời hứa và cho Lucky - con hải âu nhỏ được nhận nuôi, sức mạnh để khám phá bản chất thật của nó?\r\n\r\nMột câu chuyện cảm động, nâng cao ý thức về môi trường một cách mạnh mẽ!', 290000, 66, NULL, NULL, b'1', b'0', b'1', 10),
                                                                                                                                                                                                                             (32, 'Truyện đọc tiếng Anh - Wonder', 0, 121, 4, 26, 'Penguin Random House UK', '\"My name is August. I won\'t describe what I look like. Whatever you\'re thinking, it\'s probably worse.\" Auggie wants to be an ordinary ten-year-old. He does ordinary things - eating ice cream, playing on his Xbox. He feels ordinary - inside. But ordinary kids don\'t make other ordinary kids run away screaming in playgrounds. Ordinary kids aren\'t stared at wherever they go. Born with a terrible facial abnormality, Auggie has been home-schooled by his parents his whole life. Now, for the first time, he\'s being sent to a real school - and he\'s dreading it. All he wants is to be accepted - but can he convince his new classmates that he\'s just like them, underneath it all? Wonder is a funny, frank, astonishingly moving debut to read in one sitting, pass on to others, and remember long after the final page.', 288000, 12, NULL, NULL, b'1', b'0', b'1', 30),
(33, 'Truyện thiếu nhi tiếng Anh - Usborne 10 Ten-Minute Fairy Tales', 0, 256, 4, 26, 'USBORNE PUBLISHING', '10 Ten-Minute Fairy Tales A magical collection of fairy tales, featuring retellings of favourites such as Aladdin, Little Red Riding Hood and Snow White and the Seven Dwarfs. Each charming story in this hardback book is beautifully illustrated and takes just ten minutes to read – the perfect length for snuggling up and enjoying at bedtime.', 468000, 10, NULL, NULL, b'1', b'0', b'0', 6),
                                                                                                                                                                                                                              (37, 'Doraemon và những người bạn', 0, 75, 1, 18, 'Kim Đồng', 'Chú mèo máy đến từ tương lai với vô vàn các bảo bối', 21000, 66, NULL, NULL, b'1', b'0', b'1', 0),
                                                                                                                                                                                                                              (39, 'Trí tuệ Do Thái (Tái bản 2018)', 2018, 444, 17, 30, 'Nhà Xuất Bản Lao Động Xã Hội', 'Trí tuệ Do Thái là một cuốn sách tư duy đầy tham vọng trong việc nâng cao khả năng tự học tập, ghi nhớ và phân tích - những điều đã khiến người Do Thái vượt trội lên, chiếm lĩnh những vị trí quan trọng trong ngành truyền thông, ngân hàng và những giải thưởng sáng tạo trên thế giới.  \r\n\r\nTuy là một cuốn sách nhỏ nhưng Trí Tuệ Do Thái lại mang trong mình tri thức về một dân tộc có thể nhỏ về số lượng nhưng vĩ đại về trí tuệ và tài năng. Cuốn sách không chỉ lý giải lý do vì sao những người Do Thái trên thế giới lại thông minh và giàu có, mà còn đặc tả con đường thành công của một người Do Thái - Jerome cùng những triết lý được đúc kết đầy giá trị.\r\n\r\nTrí Tuệ Do Thái không dừng lại ở giới hạn của một cuốn sách triết lý hay kỹ năng. Thông qua Jerome, một kẻ lông bông thích la cà, tác giả đưa người đọc vào một chuyến khám phá về trí tuệ của người Do Thái, từ đó khơi ra những giới hạn để người đọc có thể tự khai phá trí tuệ bản thân với \"Năm nguyên tắc\" và \"Mười lăm gợi ý\". Đây sẽ là những bài học quý giá dành cho những ai muốn tồn tại và phát triển mạnh mẽ, không chỉ với con đường thành công của riêng mình.\r\n\r\nKhông được viết như một cuốn sách kỹ năng khô khan, Trí Tuệ Do Thái được dựng lên bằng một câu chuyện và rồi cũng khép lại với một cái kết mở, nơi những người Do Thái đang không ngừng đối mặt với cuộc sống và chinh phục nó.', 189000, 100, NULL, NULL, b'1', b'0', b'1', 30),
                                                                                                                                                                                                                              (40, 'Tư duy pháp lý của luật sư', 2015, 442, 2, 31, 'NXB Trẻ', 'Quyển sách này là phiên bản mới của quyển Tài ba của luật sư xuất bản năm 2010 và tái bản hai lần sau đó. Đó là kết quả của những góp ý từ độc giả, của kinh nghiệm tác giả thu thập được qua các lớp học được tổ chức tại Đoàn Luật sư TP. Hồ Chí Minh cũng như sự tìm tòi và học hỏi của tác giả trong suốt 5 năm qua. Cũng như sách trước, quyển sách này được viết cho các luật sư mới bước chân vào nghề.\r\n\r\nNội dung quyển sách được chia làm 4 phần:\r\n\r\nPhần một: Giới thiệu với bạn về tư duy pháp lý và đưa ra các điều kiện bạn phải có, hay phải thay đổi so với trước kia để có thể có tư duy pháp lý. Tốt nghiệp trường luật xong bạn chưa có khả năng tư duy pháp lý để làm luật sư; vì trường luật đào tạo bạn làm cán bộ pháp chế (tức là soạn luật để cho người khác áp dụng, và giám sát việc thực hiện luật). Bạn sẽ biết về điều này rõ hơn khi đọc Chương 2 của phần này.\r\n\r\nPhần hai: Trình bày cách tư duy pháp lý; gồm phương pháp thực hiện; các vụ án để bạn… luyện chưởng và biết tính chất của các câu hỏi pháp lý.\r\n\r\nPhần ba: Đưa ra một số vụ án để các bạn tập làm một mình hầu kiểm tra mức độ sử dụng tư duy pháp lý..\r\n\r\nPhần bốn: Một số bài đọc thêm để bạn mở rộng kiến thức.', 239000, 52, NULL, NULL, b'1', b'0', b'1', 15),
                                                                                                                                                                                                                              (41, 'Pháp Lý M&A Căn Bản (Tái Bản 2023)', 2023, 225, 2, 32, 'Nhà Xuất Bản Công Thương', 'Pháp lý M&A căn bản được ra đời với mong muốn rằng:\r\n\r\nCác bạn sinh viên luật có được hiểu biết về một lĩnh vực quan trọng trong mảng hành nghề luật sư tư vấn luật kinh doanh để bắt đầu chuẩn bị cho mình những kiến thức cần thiết ngay cả khi còn ngồi trên ghế nhà trường và cũng bớt bỡ ngỡ khi đi thực tập hoặc đi làm.\r\nCác bạn luật sư tập sự và luật sư chưa hoặc ít tham gia tư vấn cho các giao dịch M&A có thêm kiến thức cơ bản, mang tính hệ thống, thực tế về kỹ năng tư vấn M&A.\r\nChuyên viên pháp lý trong các doanh nghiệp hiểu rõ hơn về giao dịch đặc thù này để có thể sẵn sàng khi doanh nghiệp của mình tiến hành mua doanh nghiệp khác hoặc bị doanh nghiệp khác mua lại.\r\nDoanh nhân có cái nhìn tổng quát về giao dịch M&A, hiểu rõ hơn các điều khoản của hợp đồng phục vụ cho giao dịch M&A để có thể tiến hành giao dịch thuận lợi hơn, tự tin hơn, an toàn hơn, cũng như để biết thêm về vai trò của luật sư trong loại giao dịch khá phức tạp này.\r\nThẩm phán, viên chức nhà nước hiểu rõ hơn về giao dịch M&A để từ đó xét xử, xử lý thủ tục hành chính hay đề xuất, xây dựng, và soạn thảo các quy định pháp luật hợp lý hơn, chặt chẽ hơn và nhanh chóng hơn.', 120000, 33, NULL, NULL, b'1', b'0', b'1', 0),
                                                                                                                                                                                                                              (42, 'Giáo Trình Triết Học Mác – Lênin (Dành Cho Bậc Đại Học Hệ Không Chuyên Lý Luận Chính Trị) - Bộ mới năm 2021', 2021, 496, 3, 33, 'Nhà Xuất Bản Chính Trị Quốc Gia Sự Thật', 'Giáo trình do Ban biên soạn gồm các tác giả là nhà nghiên cứu, nhà giáo dục thuộc Viện Triết học - Học viện Chính trị quốc gia Hồ Chí Minh, các học viện, trường đại học, Viện Triết học - Viện Hàn lâm Khoa học xã hội Việt Nam, tổ chức biên soạn trên cơ sở kế thừa những kết quả nghiên cứu trước đây, đồng thời bổ sung nhiều nội dung, kiến thức, kết quả nghiên cứu mới, gắn với công cuộc đổi mới ở Việt Nam, nhất là những thành tựu trong 35 năm đổi mới đất nước.\r\n\r\nGiáo trình gồm 03 chương:\r\n\r\nChương 1 trình bày những nét khái quát nhất về triết học, triết học Mác - Lênin và vai trò của triết học Mác - Lênin trong đời sống xã hội.\r\nChương 2 trình bày những nội dung cơ bản của chủ nghĩa duy vật biện chứng, gồm: vấn đề vật chất và ý thức; phép biện chứng duy vật; lý luận nhận thức của chủ nghĩa duy vật biện chứng.\r\nChương 3 trình bày những nội dung cơ bản của chủ nghĩa duy vật lịch sử, gồm: vấn đề hình thái kinh tế - xã hội; giai cấp và dân tộc; nhà nước và cách mạng xã hội; ý thức xã hội; triết học về con người.\r\nGiáo trình được biên soạn theo yêu cầu đổi mới căn bản, toàn diện giáo dục và đào tạo, phù hợp với đối tượng người học, nhằm cung cấp những tri thức hiểu biết có tính nền tảng và hệ thống về triết học Mác - Lênin; xây dựng thế giới quan duy vật và phương pháp luận biện chứng duy vật làm nền tảng lý luận cho việc nhận thức các vấn đề, nội dung của các môn học khác; nhận thức được thực chất giá trị, bản chất khoa học, cách mạng của triết học Mác - Lênin. Đồng thời, giúp cho sinh viên vận dụng tri thức triết học Mác - Lênin, thế giới quan duy vật và phương pháp luận biện chứng duy vật để rèn luyện tư duy, giúp ích trong học tập và cuộc sống.', 94000, 110, NULL, NULL, b'1', b'1', b'0', 0),
                                                                                                                                                                                                                              (43, 'Giáo Trình Tư Tưởng Hồ Chí Minh (Dành Cho Bậc Đại Học Hệ Không Chuyên Lý Luận Chính Trị) - Bộ mới năm 2021', 2021, 272, 3, 33, 'Nhà Xuất Bản Chính Trị Quốc Gia Sự Thật', 'Giáo trình do tập thể tác giả là những nhà nghiên cứu, nhà giáo dục có nhiều kinh nghiệm trong nghiên cứu, giảng dạy về tư tưởng Hồ Chí Minh, PGS.TS. Mạch Quang Thắng làm chủ biên; được biên soạn theo quan điểm đổi mới căn bản, toàn diện giáo dục và đào tạo. Giáo trình thể hiện nhiều kết quả nghiên cứu mới về tư tưởng Hồ Chí Minh, gắn với các nội dung học tập và làm theo tư tưởng, đạo đức, phong cách Hồ Chí Minh.\r\n\r\nGiáo trình được kết cấu gồm 6 chương, trình bày những vấn đề cơ bản của tư tưởng Hồ Chí Minh. Chương 1 đề cập những vấn đề chung nhất của môn học (khái niệm, đối tượng, phương pháp nghiên cứu và ý nghĩa học tập môn Tư tưởng Hồ Chí Minh); Chương 2 trình bày cơ sở, quá trình hình thành và phát triển tư tưởng Hồ Chí Minh. Các chương còn lại gồm các nội dung: Tư tưởng Hồ Chí Minh về độc lập dân tộc và chủ nghĩa xã hội; Tư tưởng Hồ Chí Minh về Đảng Cộng sản Việt Nam và Nhà nước của nhân dân, do nhân dân, vì nhân dân; Tư tưởng Hồ Chí Minh về đại đoàn kết toàn dân tộc và đoàn kết quốc tế; Tư tưởng Hồ Chí Minh về văn hóa, con người.\r\n\r\nGiáo trình góp phần giúp người học hiểu sâu sắc, toàn diện và đầy đủ hơn về vai trò, vị trí, ý nghĩa của tư tưởng Hồ Chí Minh, các nội dung cơ bản trong tư tưởng Hồ Chí Minh, từ đó vận dụng, liên hệ với thực tiễn học tập, rèn luyện, xây dựng nhân cách để trở thành công dân tốt, đóng góp vào công cuộc xây dựng đất nước.', 90000, 150, NULL, NULL, b'1', b'1', b'0', 10),
                                                                                                                                                                                                                              (44, 'Giáo Trình Kinh Tế Đầu Tư ( Tặng Kèm Sổ Tay Xương Rồng )', 2017, 264, 3, 34, 'Nhà Xuất Bản Xây dựng', 'Môn học Kinh tế Đầu tư là một trong những môn học cung cấp kiến thức phân tích và đánh giá hiệu quả đầu tư cho các sinh viên các chuyên ngành: Ngành Kinh tế Xây dựng của trường Đại học Bách khoa, Đại học Đà Nẵng và các chuyên ngành gần. Đồng thời để có một tài liệu hoàn chỉnh, đáp ứng tốt nhất chuẩn đầu ra cho các chuyên ngành thuộc khối ngành Xây dựng, chúng tôi biên soạn cuốn giáo trình Kinh tế Đầu tư.\r\n\r\nGiáo trình là công trình tập thể do các giảng viên của bộ môn Quản lý Dự án Xây dựng biên soạn. Các giảng viên đảm nhận biên soạn giáo trình bao gồm:\r\n\r\n- PGS.TS. Phạm Anh Đức, chủ biên đảm nhận nội dung Chương 5, 6 và 7 của Phần 2 và Dự án điển hình Phần 3;\r\n\r\n- ThS. Nguyễn Thị Thảo Nguyên đảm nhận nội dung Chương 1, 3 của Phần 1. Chương 4, 8 của Phần 2 và Dự án điển hình Phần 3;\r\n\r\n- TS. Mai Anh Đức đảm nhận nội dung Chương 2 của Phần 1 và Dự án điển hình Phần 3.', 159000, 68, NULL, NULL, b'1', b'0', b'1', 11),
                                                                                                                                                                                                                              (45, 'Giáo Trình Chủ Nghĩa Xã Hội Khoa Học (Dành Cho Bậc Đại Học Hệ Không Chuyên Lý Luận Chính Trị) - Bộ mới năm 2021', 2021, 276, 3, 33, 'Nhà Xuất Bản Chính Trị Quốc Gia Sự Thật', 'Giáo trình do GS.TS. Hoàng Chí Bảo làm chủ biên, cùng tập thể tác giả là những nhà nghiên cứu, nhà giáo dục có nhiều kinh nghiệm trong nghiên cứu, giảng dạy về chủ nghĩa xã hội khoa học; được biên soạn theo quan điểm đổi mới căn bản, toàn diện giáo dục và đào tạo. Giáo trình thể hiện nhiều kết quả nghiên cứu mới về chủ nghĩa xã hội khoa học, gắn với thực tiễn xây dựng chủ nghĩa xã hội ở Việt Nam trong giai đoạn hiện nay.\r\n\r\nGiáo trình gồm 7 chương. Ngoài chương nhập môn trình bày sự ra đời, các giai đoạn phát triển của chủ nghĩa xã hội gắn liền với vai trò của C. Mác, Ph. Ăngghen và V.I. Lênin; đối tượng, phương pháp và ý nghĩa việc nghiên cứu, học tập môn Chủ nghĩa xã hội khoa học; các chương còn lại trình bày một cách hệ thống, toàn diện lý luận về chủ nghĩa xã hội và thời kỳ quá độ lên chủ nghĩa xã hội với những đặc trưng bản chất về: Sứ mệnh lịch sử của giai cấp công nhân; Chủ nghĩa xã hội và thời kỳ quá độ lên chủ nghĩa xã hội; Nền dân chủ và Nhà nước xã hội chủ nghĩa; Cơ cấu xã hội - giai cấp và liên minh giai cấp, tầng lớp trong thời kỳ quá độ lên chủ nghĩa xã hội; Vấn đề dân tộc và tôn giáo trong thời kỳ quá độ lên chủ nghĩa xã hội; Vấn đề gia đình trong thời kỳ quá độ lên chủ nghĩa xã hội.\r\n\r\nGiáo trình nhằm giúp sinh viên, học viên nắm được những tri thức cơ bản, cốt lõi nhất, mở rộng và chuyên sâu về Chủ nghĩa xã hội khoa học, một trong ba bộ phận cấu thành chủ nghĩa Mác - Lênin; từ đó nâng cao hiểu biết thực tiễn và khả năng vận dụng các tri thức nói trên vào việc xem xét, đánh giá những vấn đề chính trị - xã hội của đất nước liên quan đến chủ nghĩa xã hội và con đường đi lên chủ nghĩa xã hội ở nước ta; nắm chắc và vận dụng thành thạo những kiến thức đã học vào công việc giảng dạy của cá nhân sau khi ra trường.', 90000, 123, NULL, NULL, b'1', b'1', b'0', 10),
                                                                                                                                                                                                                              (46, 'Giáo Trình Kinh Tế Chính Trị Mác – Lênin (Dành Cho Bậc Đại Học Hệ Không Chuyên Lý Luận Chính Trị) - Bộ mới năm 2021', 2021, 292, 3, 33, 'Nhà Xuất Bản Chính Trị Quốc Gia Sự Thật', 'Giáo trình do PGS.TS. Ngô Tuấn Nghĩa chủ biên, cùng tập thể tác giả là những nhà nghiên cứu, nhà giáo dục có nhiều kinh nghiệm tổ chức biên soạn.\r\n\r\nGiáo trình gồm 6 chương:\r\n\r\nChương 1 cung cấp những tri thức cơ bản về sự ra đời và phát triển của môn học kinh tế chính trị Mác - Lênin; đối tượng nghiên cứu, phương pháp nghiên cứu và chức năng của khoa học kinh tế chính trị Mác - Lênin trong nhận thức cũng như trong thực tiễn.\r\n\r\nChương 2 cung cấp một cách có hệ thống về lý luận giá trị lao động của C. Mác thông qua các phạm trù cơ bản về hàng hóa, tiền tệ, giá cả, quy luật giá trị, tính hai mặt của lao động sản xuất hàng hóa, năng suất lao độ\r\n\r\nChương 3 trình bày ba nội dung: 1) Lý luận của C. Mác về giá trị thặng dư, đây cũng chính là nội dung cốt lõi học thuyết giá trị thặng dư của C. Mác, hòn đá tảng trong lý luận kinh tế chính trị của C. Mác; 2) Tích lũy tư bản (cách thức sử dụng giá trị thặng dư); 3) Phân phối giá trị thặng dư trong nền kinh tế thị trường tự do cạnh tranh tư bản chủ nghĩa.\r\n\r\nChương 4 cung cấp hệ thống tri thức lý luận của V.I. Lênin về độc quyền và độc quyền nhà nước trong nền kinh tế thị trường tư bản chủ nghĩa.\r\n\r\nChương 5 cung cấp tri thức lý luận cơ bản về nền kinh tế thị trường mang đặc thù phát triển của Việt Nam, vấn đề quan hệ lợi ích và bảo đảm hài hòa các quan hệ lợi ích trong phát triển ở Việt Nam.\r\n\r\nChương 6 cung cấp hệ thống tri thức về công nghiệp hóa, hiện đại hóa ở Việt Nam trong bối cảnh thích ứng với cuộc Cách mạng công nghiệp lần thứ tư; trong đó đề cập những nội dung cơ bản như: khái quát lịch sử các cuộc cách mạng công nghiệp; khái quát về công nghiệp hóa và các mô hình công nghiệp hóa tiêu biểu; tính tất yếu và nội dung cơ bản của công nghiệp hóa, hiện đại hóa ở Việt Nam. Đặc biệt, Chương 6 nhấn mạnh đến những quan điểm và giải pháp thực hiện công nghiệp hóa, hiện đại hóa ở Việt Nam trong bối cảnh cuộc Cách mạng công nghiệp lần thứ tư.', 85000, 98, NULL, NULL, b'1', b'1', b'0', 20),
                                                                                                                                                                                                                              (47, 'Sự Trỗi Dậy Và Suy Tàn Của Các Cường Quốc ( Tái bản)', 2022, 944, 6, 35, 'Nhã Xuất Bản Thế Giới', 'Sự trỗi dậy và suy tàn của các cường quốc được ra mắt lần đầu năm 1987, bàn về tình hình sức mạnh quốc gia và quốc tế trong thời kỳ “hiện đại”, hay hậu Phục hưng; giải thích quá trình trỗi dậy và suy tàn của các Cường quốc khác nhau trong suốt năm thế kỷ kể từ khi hình thành “nền quân chủ mới” ở Tây Âu; đưa ra dự báo về vị thế của một số quốc gia vào thời điểm cuối thế kỷ 20 (mà thực tiễn cho đến nay đã xác nhận tính đúng đắn hoặc thiếu chính xác của chúng).\r\n\r\nLuận điểm bao trùm trong tác phẩm bao gồm hai ý: một là, sức mạnh của một Cường quốc chỉ có thể được đong đếm trong tương quan với các nước khác; hai là, uy thế về lâu về dài hoặc trong một xung đột cụ thể của một Cường quốc có mối tương liên chặt chẽ với các nguồn lực sẵn có và tính bền vững của nền kinh tế quốc gia. Ý thứ hai nói theo cách khác là nếu một nước nuôi dưỡng tham vọng và/hoặc có yêu cầu về an ninh ở mức cao hơn nền tảng tài nguyên của mình, thì nước đó sẽ phải đối mặt với nguy cơ bị quá sức về quân sự và bị suy thoái tương đối đồng thời.', 567000, 150, NULL, NULL, b'1', b'1', b'1', 32),
                                                                                                                                                                                                                              (48, 'Đại Việt Sử Ký Toàn Thư Trọn Bộ (Tái Bản 2020)', 2020, 1280, 6, 36, 'Nhà Xuất Bản Hồng Đức', 'Trong các sách lịch sử cũ của ta , thì \" Đại Việt sử ký toàn thư \" là một bộ sử lớn chép từ Hồng Bàng đến Ất Mão ( 1675) đời vua Gia Tôn nhà Lê. \" Đại Việt sử ký toàn thư \" là bộ sách lịch sử quý báu trong tủ sách sử cũ của nước Việt Nam , rất cần thiết cho những người nghiên cứu lịch sử dân tộc .', 205000, 607, NULL, NULL, b'1', b'1', b'0', 0),
                                                                                                                                                                                                                              (49, 'Trần Triều Nhàn Thoại', 2022, 268, 6, 37, 'Nhà Xuất Bản Hội Nhà Văn', 'Là một trong những tác giả đam mê lịch sử và bắt đầu viết truyện dã sử từ rất sớm, Đồng Lạc đã cho ra mắt cuốn sách Trần Triều nhàn thoại, thể hiện tình yêu và tâm huyết đối với sử Việt. Cuốn sách là tập hợp của những câu chuyện ngắn viết về một thời Trần từ khi bắt đầu vào những năm cuối của nhà Lý, những biến cố lịch sử đã diễn ra trong giai đoạn đó và ảnh hưởng đến cả những thế hệ sau này. Trong cuốn sách cũng có cả những chi tiết hư ảo, những suy tưởng của những con người trong thế hệ ấy, về thời đại, con người và đất nước. Một dòng lịch sử lại ảnh hưởng đến cả vạn câu chuyện đời, không chỉ là chuyện đời của đế vương, của những vương tôn quý tộc mà là cuộc đời của muôn vàn bách tích. Bởi đất nước và lịch sử là sự đóng góp của cả những người dân thường vẫn luôn chung lòng vì đất nước.', 109500, 66, NULL, NULL, b'1', b'0', b'1', 0),
                                                                                                                                                                                                                              (50, 'Tuổi trẻ đáng giá bao nhiêu (tặng kèm bookmark thiết kế)', 2016, 285, 5, 38, 'Nhà Xuất Bản Hội Nhà Văn', 'Tuổi Trẻ Đáng Giá Bao Nhiêu\r\n\"Bạn hối tiếc vì không nắm bắt lấy một cơ hội nào đó, chẳng có ai phải mất ngủ.\r\nSuy cho cùng, quyết định là ở bạn. Muốn có điều gì hay không là tùy bạn.\r\nVì sau tất cả, chẳng ai quan tâm.\"\r\nNhận định\r\n\r\n\"Tôi đã đọc quyển sách này một cách thích thú. Có nhiều kiến thức và kinh nghiệm hữu ích, những điều mới mẻ ngay cả với người gần trung niên như tôi.\r\n\r\nTuổi trẻ đáng giá bao nhiêu? được tác giả chia làm 3 phần: HỌC, LÀM, ĐI.\r\nNhưng tôi thấy cuốn sách còn thể hiện một phần thứ tư nữa, đó là ĐỌC.\r\n\r\nHãy đọc sách, nếu bạn đọc sách một cách bền bỉ, sẽ đến lúc bạn bị thôi thúc không ngừng bởi ý muốn viết nên cuốn sách của riêng mình.\r\n\r\nNếu tôi còn ở tuổi đôi mươi, hẳn là tôi sẽ đọc Tuổi trẻ đáng giá bao nhiêu? nhiều hơn một lần.\"\r\n(Đặng Nguyễn Đông Vy, tác giả, nhà báo)', 75000, 458, NULL, NULL, b'1', b'1', b'0', 10),
                                                                                                                                                                                                                              (51, 'Cẩm Nang Sinh Tồn Cho Bạn Trẻ - Vượt Qua Khủng Hoảng Tuổi Đôi Mươi', 2021, 144, 5, 39, 'Nhà Xuất Bản Thanh Hóa', '“Em ơi có bao nhiêu\r\n60 năm cuộc đời\r\n20 năm đầu\r\nSung sướng không bao lâu…”\r\n\r\nQuả đúng như câu hát trên, khi mà năm 20 tuổi, tôi bước vào đời với ngọn lửa nhiệt huyết cháy bỏng, đặt mục tiêu đời mình là trở thành CEO hay KOL trên mạng xã hội, sở hữu một quán cà phê với doanh thu ổn định và đi du lịch khắp năm châu bốn bể.\r\nAi mà ngờ, chỉ năm năm sau, dòng đời xô đẩy và vả tôi “sấp mặt”, đến mức tôi chỉ còn mong mình xoay xở đủ tiền thuê nhà tháng này, hình đăng lên Instagram được thêm vài lượt “like” và có thời gian về thăm bố mẹ mỗi tháng một lần.\r\n\r\nNếu bạn cũng như tôi thì xin chúc mừng, bạn không cô đơn! Và đây chính là cuốn sách – hay là chiếc phao cứu sinh – dành cho bạn. Quyển “bí kíp” này sẽ giúp bạn nhận ra bạn nào phải là “kẻ thất bại” như bạn nghĩ, và hành trình trở thành một người-lớn-trưởng-thành của bạn vẫn diễn ra ổn thỏa chứ chẳng hề trì trệ. VƯỢT QUA KHỦNG HOẢNG TUỔI ĐÔI MƯƠI cũng sẽ hướng dẫn bạn cách trở nên vững vàng nơi đầu sóng ngọn gió trong cơn khủng hoảng tuổi đôi mươi.', 90000, 176, NULL, NULL, b'1', b'1', b'1', 15),
                                                                                                                                                                                                                              (52, 'Phát triển trí tuệ (140 câu đố) - Bộ sách Rèn luyện Trí thông minh - Dành cho trẻ 5-6 tuổi', 2022, 90, 8, 36, 'Nhà Xuất Bản Hà Nội', 'Đứng trước sự phát triển của công nghệ và phổ cập internet như hiện nay, việc khó khăn của các bậc phụ huynh có con cái trong độ tuổi từ 5 - 6 chính là tìm cách làm sao để con mình tách ra khỏi sự đam mê của các trò chơi trên các thiết bị điện tử như smartphone, iPằm có được sự phát triển đầy đủ nhất của não bộ, kích thích tài năng của các bé. Bộ sách này hoàn toàn đáp ứng được mong muốn đó của các bậc làm cha mẹ trong việc đào tạo, hướng dẫn con nhỏ làm quen, khai thác sự phát triển trí não của bé.\r\nBộ Sách \"Phát triển trí tuệ - Mẹ hỏi bé trả lời\" được biên soạn nhằm giúp các bậc phụ huynh có thể tận dụng tốt khoảng thời gian quý báu này. Nội dung là những câu trắc nghiệm theo tiêu điểm, những câu hỏi kích thích tính tư duy logic, sáng tạo, những bài tập giúp trẻ xây dựng kỹ năng, sự khéo léo, tính chính xác và rèn luyện tính kiên trìực hành tốt tất cả bài tập này, sẽ kích thích sự phát triển toàn diện não bộ của trẻ, giúp trẻ trở nên thông minh hơn.', 50000, 653, NULL, NULL, b'1', b'1', b'0', 30),
                                                                                                                                                                                                                              (53, 'Chuyện Con Chó Tên Là Trung Thành', 2021, 95, 8, 40, 'Nhà Xuất Bản Hội Nhà Văn', 'Giữa rừng già Nam Mỹ, một chú chó được xua đi săn đuổi một thổ dân da đỏ. Trên đường lần theo dấu kẻ trốn chạy, chú chó dần nhận ra mùi của những thứ mình đã đánh mất: mùi củi khô, mùi bột mì, mùi mật ong,… và rồi mùi người anh em của mình. Chú chó nhớ lại tất cả những gì những Con người của Đất từng dạy cho nó: cách tôn trọng thiên nhiên, biết ơn mẹ đất, sống hòa hợp với vạn vật và đặc biệt cái tên của nó, Afmau - theo tiếng thổ dân nghĩa là Trung thành.\r\n\r\nVới tài năng kể chuyện vô song, Luis Sepúlveda biết cách tôn vinh những tình cảm cổ xưa, cao quý một cách sống động, để lại những ấn tượng khó quên về thế giới của người Mapuche, về mối gắn kết của họ với thiên nhiên vĩ đại.', 45000, 740, NULL, NULL, b'1', b'1', b'0', 10),
                                                                                                                                                                                                                              (54, 'Thanh gươm diệt quỷ', 2000, 95, 1, 41, 'Kim Đồng', 'Kimetsu no Yaiba – Tanjirou là con cả của gia đình vừa mất cha. Một ngày nọ, Tanjirou đến thăm thị trấn khác để bán than, khi đêm về cậu ở nghỉ tại nhà người khác thay vì về nhà vì lời đồn thổi về ác quỷ luôn rình mò gần núi vào buổi tối. Khi cậu về nhà vào ngày hôm sau, bị kịch đang đợi chờ cậu…', 20000, 321, NULL, NULL, b'1', b'0', b'1', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bookimage`
--

CREATE TABLE `bookimage` (
                             `imageID` int(11) NOT NULL,
                             `path` text COLLATE utf8_unicode_ci NOT NULL,
                             `bookID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bookimage`
--

INSERT INTO `bookimage` (`imageID`, `path`, `bookID`) VALUES
                                                          (9, 'admin/img/bookupload/doremon-13.jpg', 22),
                                                          (10, 'admin/img/bookupload/conan-khuc-nhac-cau-sieu.jpg', 3),
                                                          (11, 'admin/img/bookupload/conan-special-1.jpg', 4),
                                                          (12, 'admin/img/bookupload/doremon-ki-niem-ve-ba.jpg', 20),
                                                          (13, 'admin/img/bookupload/conan-80.jpg', 6),
                                                          (14, 'admin/img/bookupload/doremon-44.jpg', 21),
                                                          (16, 'admin/img/bookupload/conan-1.jpg', 24),
                                                          (22, 'admin/img/bookupload/conan-1a.jpg', 24),
                                                          (23, 'admin/img/bookupload/nhung-nguoi-hang-xom.jpg', 26),
                                                          (24, 'admin/img/bookupload/mat-biec.jpg', 27),
                                                          (25, 'admin/img/bookupload/ngay-xua-co-mot-chuyen-tinh.jpg', 28),
                                                          (26, 'admin/img/bookupload/ke-nan-sap.jpg', 29),
                                                          (27, 'admin/img/bookupload/hoang-tu-be.jpg', 30),
                                                          (28, 'admin/img/bookupload/the-story-of-a-seagull-and-the-cat.jpg', 31),
                                                          (29, 'admin/img/bookupload/wonder.jpg', 32),
                                                          (30, 'admin/img/bookupload/fairy-tales.jpg', 33),
                                                          (35, 'admin/img/bookupload/doremon.jpg', 37),
                                                          (42, 'admin/img/bookupload/tri-tue-do-thai.jpg', 39),
                                                          (43, 'admin/img/bookupload/tu-duy-phap-ly.jpg', 40),
                                                          (44, 'admin/img/bookupload/phap-ly-can-ban.jpg', 41),
                                                          (45, 'admin/img/bookupload/triet.jpg', 42),
                                                          (46, 'admin/img/bookupload/tu-tuong.jpg', 43),
                                                          (47, 'admin/img/bookupload/kinh-te-dau-tu.jpg', 44),
                                                          (48, 'admin/img/bookupload/cnxh.jpg', 45),
                                                          (49, 'admin/img/bookupload/ktct.jpg', 46),
                                                          (50, 'admin/img/bookupload/su-troi-day.jpg', 47),
                                                          (51, 'admin/img/bookupload/dai-viet-su-ky-toan-thu.jpg', 48),
                                                          (52, 'admin/img/bookupload/tran-trieu-nhan-thoai.jpg', 49),
                                                          (53, 'admin/img/bookupload/tuoi-tre-dang-gia.jpg', 50),
                                                          (54, 'admin/img/bookupload/cam-nang-sinh-ton.jpg', 51),
                                                          (55, 'admin/img/bookupload/phat-trien-tri-tue.jpg', 52),
                                                          (56, 'admin/img/bookupload/chuyen-con-cho-ten-la-trung-thanh.jpg', 53),
                                                          (57, 'admin/img/bookupload/thanh-guom-diet-quy.jpg', 54),
                                                          (58, 'admin/img/bookupload/hinh-anh-doremon.jpg', 22);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
                        `cartID` int(11) NOT NULL,
                        `quantity` int(11) DEFAULT NULL,
                        `userID` int(11) DEFAULT NULL,
                        `bookID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
                            `categoryID` int(11) NOT NULL,
                            `code` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
                            `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
                            `created_at` date DEFAULT NULL,
                            `updated_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`categoryID`, `code`, `name`, `created_at`, `updated_at`) VALUES
                                                                                      (1, 'truyen-tranh', 'Truyện tranh', '2023-02-17', NULL),
                                                                                      (2, 'chinh-tri', 'Chính trị - Pháp lý', '2023-03-10', '2023-05-03'),
                                                                                      (3, 'giao-khoa', 'Giáo khoa - Giáo trình', NULL, NULL),
                                                                                      (4, 'tieng-anh', 'Tiếng Anh', NULL, NULL),
                                                                                      (5, 'tam-ly', 'Tâm lý - Giới tính', NULL, '2023-05-03'),
                                                                                      (6, 'lich-su', 'Lịch sử', NULL, NULL),
                                                                                      (7, 'khoa-hoc', 'Khoa học - Kỹ thuật', NULL, NULL),
                                                                                      (8, 'thieu-nhi', 'Thiếu nhi', '2023-03-02', '2023-05-05'),
                                                                                      (9, 'kinh-doanh', 'Kinh doanh', NULL, NULL),
                                                                                      (10, 'van-hoc', 'Văn học', NULL, NULL),
                                                                                      (12, 'tieng-viet', 'Tiếng Việt', NULL, NULL),
                                                                                      (17, 'ky-nang', 'Kỹ năng sống', '2023-05-05', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderline`
--

CREATE TABLE `orderline` (
                             `id` int(11) NOT NULL,
                             `quantity` int(11) DEFAULT NULL,
                             `total_price` int(11) DEFAULT NULL,
                             `bookID` int(11) DEFAULT NULL,
                             `orderID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orderline`
--

INSERT INTO `orderline` (`id`, `quantity`, `total_price`, `bookID`, `orderID`) VALUES
                                                                                   (13, 1, 20000, 21, 11),
                                                                                   (14, 1, 18000, 24, 11),
                                                                                   (15, 1, 205000, 48, 12),
                                                                                   (16, 2, 162000, 45, 12),
                                                                                   (17, 1, 35000, 52, 13),
                                                                                   (18, 1, 76500, 51, 13),
                                                                                   (19, 1, 40500, 53, 13),
                                                                                   (20, 1, 68000, 46, 14),
                                                                                   (21, 1, 51000, 30, 15),
                                                                                   (22, 1, 120000, 41, 15),
                                                                                   (23, 1, 20000, 21, 16),
                                                                                   (24, 3, 60000, 20, 16);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
                          `orderID` int(11) NOT NULL,
                          `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                          `created_at` date DEFAULT NULL,
                          `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                          `note` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                          `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                          `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
                          `total_price` int(11) DEFAULT NULL,
                          `updated_at` date DEFAULT NULL,
                          `userID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`orderID`, `address`, `created_at`, `name`, `note`, `phone`, `status`, `total_price`, `updated_at`, `userID`) VALUES
                                                                                                                                        (11, '36 Lê Hồng Phong', '2023-04-24', NULL, '', NULL, 'Đang xử lý..', 53000, NULL, 1),
                                                                                                                                        (12, '141/2 Đinh Tiên Hoàng, Q1, TP Hồ Chí Minh', '2023-05-06', NULL, 'Bọc hàng cẩn thận, giao giờ hành chính', NULL, 'Đang xử lý..', 382000, NULL, 4),
                                                                                                                                        (13, '81 Nguyễn Gia', '2023-05-06', NULL, '', NULL, 'Đang xử lý..', 167000, NULL, 4),
                                                                                                                                        (14, 'TP Hồ Chí Minh', '2023-05-06', NULL, '', NULL, 'Đang giao hàng', 83000, NULL, 2),
                                                                                                                                        (15, '36 Lê Hồng Phong', '2023-05-11', NULL, '', NULL, 'Đang xử lý..', 186000, NULL, 2),
                                                                                                                                        (16, 'HCM', '2023-06-08', NULL, 'abc', NULL, 'Đang xử lý..', 95000, NULL, 7);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
                        `roleID` int(11) NOT NULL,
                        `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`roleID`, `name`) VALUES
                                          (1, 'ROLE_ADMIN'),
                                          (2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roleuser`
--

CREATE TABLE `roleuser` (
                            `userID` int(11) NOT NULL,
                            `roleID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `roleuser`
--

INSERT INTO `roleuser` (`userID`, `roleID`) VALUES
                                                (3, 2),
                                                (1, 1),
                                                (1, 2),
                                                (2, 1),
                                                (2, 2),
                                                (4, 2),
                                                (6, 2),
                                                (7, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
                         `userID` int(11) NOT NULL,
                         `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
                         `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
                         `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
                         `birthdate` date DEFAULT NULL,
                         `gender` bit(1) DEFAULT NULL,
                         `phone` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
                         `password` varchar(70) COLLATE utf8_unicode_ci DEFAULT NULL,
                         `status` bit(1) DEFAULT NULL,
                         `confirm_token` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
                         `is_enable` bit(1) DEFAULT NULL,
                         `provider` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
                         `created_at` date DEFAULT NULL,
                         `updated_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`userID`, `email`, `fullname`, `username`, `birthdate`, `gender`, `phone`, `password`, `status`, `confirm_token`, `is_enable`, `provider`, `created_at`, `updated_at`) VALUES
                                                                                                                                                                                                (1, 'camhong41201@gmail.com', 'Nguyen Van A', 'cmhg', '2023-04-15', b'1', '0783456781', '$2a$10$nj5rhD4uv7foG1ixoNByUe3gUohnRkMp/rI5d9bCse/AoM3p0JKbe', b'1', '349251', b'1', NULL, NULL, '2023-05-04'),
                                                                                                                                                                                                (2, 'camhongdev@gmail.com', 'Nguyễn Tí Tò', 'tito-nguyen', '2004-06-05', b'0', '0312312319', '$2a$10$MI3cVSVzotI9Q1hSSGRcwuDHseBwmNNgOFkCnlW2bwx2pri9REpOW', b'1', '337625', b'1', NULL, NULL, '2023-05-11'),
                                                                                                                                                                                                (3, '19130063@st.hcmuaf.edu.vn', NULL, 'hyuny', NULL, b'0', NULL, '$2a$12$Dvd8rJCJcLjq1hLmTmlD7uCa.3gEnPxEe0FhvyR8GMcXrLQZqsYeu', b'1', '794103', b'1', NULL, NULL, NULL),
                                                                                                                                                                                                (4, 'hacao412@gmail.com', 'Nguyễn Tí', 'chuotcon', '2003-03-12', b'0', '0312312319', '$2a$10$dPuMfetDE2v3xHt1d47F6.FLUAVff0ZsCvfzM.YTWH6Cvzbi.hXeS', b'1', '319007', b'1', NULL, '2023-05-06', '2023-05-06'),
                                                                                                                                                                                                (6, 'camhong412@gmail.com', NULL, 'gogg', NULL, b'0', NULL, '$2a$10$qMx0XZmXiaTLIUwXIZUbKO0sTXRW0xPUla9uG0k9QXga4zGgjCc0.', b'1', '917964', b'0', NULL, '2023-05-06', NULL),
                                                                                                                                                                                                (7, '19130079@st.hcmuaf.edu.vn', NULL, '19130079@st.hcmuaf.edu.vn', NULL, b'0', NULL, NULL, b'1', NULL, b'1', NULL, '2023-06-07', '2023-06-08');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `author`
--
ALTER TABLE `author`
    ADD PRIMARY KEY (`authorID`);

--
-- Chỉ mục cho bảng `book`
--
ALTER TABLE `book`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_book_cat` (`categoryID`),
  ADD KEY `FKhcj0e0ky3ftaweqnllqfwbn99` (`authorID`);

--
-- Chỉ mục cho bảng `bookimage`
--
ALTER TABLE `bookimage`
    ADD PRIMARY KEY (`imageID`),
  ADD KEY `bookID` (`bookID`);

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
    ADD PRIMARY KEY (`cartID`),
  ADD KEY `fk_cart_user` (`userID`),
  ADD KEY `fk_cart_book` (`bookID`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
    ADD PRIMARY KEY (`categoryID`);

--
-- Chỉ mục cho bảng `orderline`
--
ALTER TABLE `orderline`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKcyl1xgylw2sa0k3oqighqgd56` (`bookID`),
  ADD KEY `fk_order` (`orderID`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
    ADD PRIMARY KEY (`orderID`),
  ADD KEY `FKpnm1eeupqm4tykds7k3okqegv` (`userID`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
    ADD PRIMARY KEY (`roleID`);

--
-- Chỉ mục cho bảng `roleuser`
--
ALTER TABLE `roleuser`
    ADD KEY `fk_user` (`userID`),
  ADD KEY `fk_role` (`roleID`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `author`
--
ALTER TABLE `author`
    MODIFY `authorID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT cho bảng `book`
--
ALTER TABLE `book`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT cho bảng `bookimage`
--
ALTER TABLE `bookimage`
    MODIFY `imageID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT cho bảng `cart`
--
ALTER TABLE `cart`
    MODIFY `cartID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
    MODIFY `categoryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `orderline`
--
ALTER TABLE `orderline`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
    MODIFY `orderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
    MODIFY `roleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
    MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `book`
--
ALTER TABLE `book`
    ADD CONSTRAINT `FKhcj0e0ky3ftaweqnllqfwbn99` FOREIGN KEY (`authorID`) REFERENCES `author` (`authorID`),
  ADD CONSTRAINT `fk_book_cat` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`);

--
-- Các ràng buộc cho bảng `bookimage`
--
ALTER TABLE `bookimage`
    ADD CONSTRAINT `bookimage_ibfk_1` FOREIGN KEY (`bookID`) REFERENCES `book` (`id`);

--
-- Các ràng buộc cho bảng `cart`
--
ALTER TABLE `cart`
    ADD CONSTRAINT `fk_cart_book` FOREIGN KEY (`bookID`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `fk_cart_user` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`);

--
-- Các ràng buộc cho bảng `orderline`
--
ALTER TABLE `orderline`
    ADD CONSTRAINT `FKcyl1xgylw2sa0k3oqighqgd56` FOREIGN KEY (`bookid`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `FKdouy9nmjxe36by2xqq0t5pqxf` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`),
  ADD CONSTRAINT `fk_order` FOREIGN KEY (`orderID`) REFERENCES `orders` (`orderid`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
    ADD CONSTRAINT `FKpnm1eeupqm4tykds7k3okqegv` FOREIGN KEY (`userid`) REFERENCES `users` (`userID`);

--
-- Các ràng buộc cho bảng `roleuser`
--
ALTER TABLE `roleuser`
    ADD CONSTRAINT `fk_role` FOREIGN KEY (`roleID`) REFERENCES `role` (`roleID`),
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
