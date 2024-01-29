<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
    <title>Ajax</title>
    <link rel="shortcut icon" href="#">
</head>

<div>
    <div class='container'>
        <h2>Ajax</h2>

        <hr/>
        <code><b>Data from Controller (Normal):</b> ${dataList}</code>
        <br>
        <code><b>Data from Controller (JSON):</b> ${dataListJSON}</code>

        <hr/>
        <form id=''>
            <div class="form-group">
                <label for="comment"><b>POST Form data to Controller</b>: Post và xem console trên server</label>
                <input class="form-control" rows="5" id="comment" value="Test gửi dữ liệu">
            </div>
            <button id='btn1' class="btn btn-primary" type="submit">Submit 1</button>
        </form>

        <hr/>
        <b>Get JSP data to Ajax:</b>
        <button id='btn2' class="btn btn-primary" type="submit">Submit 2</button>
        <p id='mess1'></p>
    </div>

    <script type="text/javascript">
        $(function () {
            // Button 1
            $('#btn1').click(function (e) {
                e.preventDefault();
                $.ajax({
                    type: 'POST',
                    url: './api/my-ajax?name=test2023',
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify({'id': '123456', 'comment': $('#comment').val()}),
                    success: function (data, status, xhr) {
                        // console.log(data);
                        // console.log(window.location.href);
                        $("#mess1").text(data);
                    },
                    error: function (jqXhr, textStatus, errorMessage) {
                        console.log(errorMessage);
                    }
                });
            });

            // Button 2
            $('#btn2').click(function (e) {
                e.preventDefault();

                // For List
                <%--let dataList = [--%>
                <%--	<c:forEach var="item" items="${dataList}" varStatus="loop">--%>
                <%--		'${item}' <c:if test="${!loop.last}">, </c:if>--%>
                <%--	</c:forEach>--%>
                <%--];--%>

                // For HashMap
                <%--let dataList = {};--%>
                <%--<c:forEach var="entry" items="${dataList}">--%>
                <%--	dataList['${entry.key}'] = '${entry.value}';--%>
                <%--</c:forEach>--%>

                // Now you can use 'dataList' in your JavaScript code

                let myData = JSON.stringify(JSON.parse(`${dataListJSON}`));
                console.log(myData);

                $.ajax({
                    type: 'POST',
                    url: './api/my-ajax2',
                    contentType: "application/json;charset=utf-8",
                    data: myData,
                    success: function (data, status, xhr) {
                        // console.log(data);
                        $("#mess1").text(data);
                    },
                    error: function (jqXhr, textStatus, errorMessage) {
                        console.log(errorMessage);
                    }
                });
            });
        });
    </script>
</div>
