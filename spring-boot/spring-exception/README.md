# JSP Standard Tag Library (JSTL)

# Có 5 loại

- Core Tags

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	+ Bộ lặp (iteration), các điều kiện logic, bắt ngoại lệ, url, chuyển tiếp (forward) hoặc chuyển hướng (redirect).
	
- Formatting and Localization Tags

	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	
	+ Định dạng số, ngày tháng và hỗ trợ i18n thông qua miền địa phương (locales) và bó tài nguyên (resource bundles).
	
- SQL Tags

	<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
	
	+ Tương tác với cơ sở dữ liệu.
	
- XML Tags

	<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
	
	+  Làm việc với các tài liệu XML như phân tích cú pháp XML, chuyển đổi dữ liệu XML và XPath đánh giá biểu thức.
	
- JSTL Functions Tags

	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
	+ Dùng để thực hiện các toán tử thông dùng, hầu hết trong số đó là cho String như nối chuỗi, phân tách chuỗi.
