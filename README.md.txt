Hệ thống Quản lý Quán Cafe (Coffee Shop Management System)

Mô tả: Dự án xây dựng hệ thống phần mềm hỗ trợ quản lý vận hành quán Cafe, thay thế quy trình quản lý thủ công (sổ sách, Excel) bằng giải pháp số hóa. Dự án tập trung vào việc áp dụng triệt để Phân tích thiết kế hướng đối tượng (OOAD), nguyên lý SOLID và các Design Patterns để đảm bảo kiến trúc phần mềm linh hoạt, dễ bảo trì và mở rộng.

 Tính năng chính (Key Features)
Hệ thống đáp ứng đầy đủ các nghiệp vụ cơ bản của một quán Cafe:


Quản lý Thực đơn (Menu): Thêm, sửa, xóa món; quản lý loại món (nước, bánh) và giá cả.
Quản lý Bàn (Table): Theo dõi trạng thái bàn (Trống, Đang phục vụ, Đã đặt).
Xử lý Đơn hàng (Order): Ghi nhận món theo bàn, thêm/bớt món, tính tổng tiền tự động.
Thanh toán (Payment): Hỗ trợ nhiều phương thức thanh toán linh hoạt (Tiền mặt, Thẻ, Ví điện tử).
Thông báo (Notification): Tự động thông báo cho Bếp và Nhân viên khi có đơn hàng mới hoặc thay đổi trạng thái.
Báo cáo & Thống kê: Xuất báo cáo doanh thu, món bán chạy theo ngày/tháng dưới nhiều định dạng (PDF, Excel, HTML).

Công nghệ & Kiến trúc (Tech Stack & Architecture)
Ngôn ngữ: Java (Core).
Kiến trúc: Layered Architecture (Phân lớp), tuân thủ 5 nguyên lý SOLID .
Công cụ: IntelliJ IDEA/Eclipse, Git.
Các Design Patterns đã áp dụng (Highlight)
Điểm nổi bật nhất của dự án là việc giải quyết các bài toán thiết kế bằng các mẫu thiết kế chuẩn (GoF Patterns):

1. State Pattern - Quản lý Trạng thái Đơn hàng
Vấn đề: Đơn hàng có vòng đời phức tạp (Mới tạo -> Đang xử lý -> Đã thanh toán -> Hủy). Sử dụng if-else sẽ làm code rối rắm .
Giải pháp: Tách mỗi trạng thái thành một class riêng (NewState, ProcessingState, PaidState). Giúp dễ dàng thêm trạng thái mới mà không sửa logic cũ .
2. Strategy Pattern - Phương thức Thanh toán
Vấn đề: Cần hỗ trợ nhiều kiểu thanh toán và có thể mở rộng trong tương lai (Momo, ZaloPay...).
Giải pháp: Định nghĩa interface PaymentStrategy. Các phương thức thanh toán cụ thể (CashPayment, CardPayment) sẽ implement interface này. Có thể đổi phương thức thanh toán ngay lúc chạy (runtime) .
3. Observer Pattern - Hệ thống Thông báo
Vấn đề: Khi đơn hàng thay đổi, nhiều bộ phận (Bếp, Thu ngân, Khách hàng) cần được thông báo tức thì.
Giải pháp: Order đóng vai trò là Subject, các bộ phận khác là Observer. Khi Order thay đổi, nó tự động notify() cho tất cả các bên liên quan mà không cần gọi trực tiếp, giúp giảm sự phụ thuộc (Decoupling) .
4. Builder Pattern - Khởi tạo Món (Dish)
Vấn đề: Một món uống có thể rất phức tạp với nhiều tùy chọn (Size, Topping, Đường, Đá...) dẫn đến Constructor quá dài .
Giải pháp: Sử dụng DishBuilder để khởi tạo đối tượng từng bước một cách rõ ràng và dễ đọc .
5. Factory Method - Xuất Báo cáo
Vấn đề: Cần xuất báo cáo ra nhiều định dạng khác nhau (PDF, Excel, HTML).
Giải pháp: Tách việc tạo báo cáo ra khỏi logic tính toán. ReportFactory sẽ quyết định tạo ra PDFExporter hay ExcelExporter tùy theo yêu cầu