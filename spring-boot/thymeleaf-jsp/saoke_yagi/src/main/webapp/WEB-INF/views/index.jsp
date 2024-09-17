<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="UTF-8">
    <title>Bootstrap Table Examples</title>
    <script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.21.4/bootstrap-table.min.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.18.2/dist/bootstrap-table.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<style>
    body {
        background-color: #eee;
        font-family: "Poppins", sans-serif;
        font-weight: 300;
    }

    .height {
        height: 100vh;
    }

    .search {
        position: relative;
        box-shadow: 0 0 40px rgba(51, 51, 51, .1);
    }

    .search input {
        height: 60px;
        text-indent: 25px;
        border: 2px solid #d6d4d4;
    }

    .search input:focus {
        box-shadow: none;
        border: 2px solid blue;
    }

    .search .fa-search {
        position: absolute;
        top: 20px;
        left: 16px;
    }

    .search button {
        position: absolute;
        top: 5px;
        right: 5px;
        height: 50px;
        width: 110px;
        background: blue;
    }
</style>

<script>
    function createTable(data) {
        // console.log(data);
        $('#myTable').bootstrapTable({
            data: data,
            // page info
            pagination: true,
            // pageSize: 9,
            // pageList: [9, 30, 45, 100],
            // paginationPreText: "Previous",
            // paginationNextText: "Next",
            columns: [
                {
                    field: 'code',
                    title: 'Mã giao dịch',
                    align: 'center',
                    sortable: true
                }, {
                    field: 'amount',
                    align: 'center',
                    title: 'Số tiền'
                }, {
                    field: 'notes',
                    align: 'center',
                    title: 'Nội dung giao dịch'
                }, {
                    field: 'date',
                    align: 'center',
                    title: 'Ngày giao dịch'
                }
            ]
        });
    }

    $(function () {
        const url = './transactions';
        console.log(url);
        $.getJSON(url, (data) => {
            createTable(data);
            // console.log(data);
        });
    });
</script>

<div class="container">
    <h1 class="text-center">Datatable From URL</h1>

    <div class="row height d-flex justify-content-center align-items-center">
        <div class="col-md-8">
            <div class="search">
                <i class="fa fa-search"></i>
                <input type="text" class="form-control" placeholder="Have a question? Ask Now">
                <button class="btn btn-primary">Search</button>
            </div>
        </div>
    </div>

    <table id="myTable" class="table table-striped">
        <thead>
        <tr class="text-center">
            <th>Mã giao dịch</th>
            <th>Số tiền</th>
            <th>Nội dung giao dịch</th>
            <th>Ngày giao dịch</th>
        </tr>
        </thead>
    </table>
</div>
