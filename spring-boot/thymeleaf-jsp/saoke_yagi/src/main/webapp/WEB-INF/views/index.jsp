<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="UTF-8">
    <title>Sao Kê YaGi</title>
    <!-- JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.21.4/bootstrap-table.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-table@1.23.2/dist/extensions/page-jump-to/bootstrap-table-page-jump-to.min.js"></script>
    <!-- CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-table@1.23.2/dist/bootstrap-table.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-table@1.23.2/dist/extensions/page-jump-to/bootstrap-table-page-jump-to.min.css">
</head>

<style>
    body {
        /*background-color: #eee;*/
        font-family: "Poppins", sans-serif;
        font-weight: 300;
    }
    .height {
        height: 40vh;
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
    .table-responsive {
        overflow-x: auto;
        overflow-y: hidden;
    }
</style>

<div class="container">
    <h1 class="text-center">Datatable From URL</h1>
    <!-- Search -->
    <div class="row height d-flex justify-content-center align-items-center">
        <div class="col-md-8">
            <div class="search">
                <i class="fa fa-search"></i>
                <input id="input-search" type="text" class="form-control" placeholder="">
                <button id="btn-search" class="btn btn-primary">Search</button>
            </div>
        </div>
    </div>
    <!-- Loading Spinner -->
    <button class="btn btn-primary" disabled>
        <span class="spinner-grow spinner-grow-sm"></span>
        Loading..
    </button>
    <!-- Table -->
    <table id="myTable" class="table table-striped" data-show-jump-to="true">
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

<script>
    // Show loading spinner using jQuery
    function showLoading() {
        $("#loadingSpinner").show(); // Use jQuery to show the spinner
    }

    // Hide loading spinner using jQuery
    function hideLoading() {
        $("#loadingSpinner").hide(); // Use jQuery to hide the spinner
    }

    function createTable(data) {
        // console.log(data);
        $('#myTable').bootstrapTable({
            data: data,
            // page info
            pagination: true,
            pageSize: 10,
            // pageList: [9, 30, 45, 100],
            paginationPreText: "Previous",
            paginationNextText: "Next",
            showJumpTo: true,
            // Event to show loading before loading data
            onLoadSuccess: function() {
                hideLoading(); // Hide spinner after table is loaded
            },
            onLoadError: function() {
                hideLoading(); // Hide spinner if there is an error
            },
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
        // Init table
        $.getJSON('./transactions', (data) => {
            createTable(data);
        });

        // Search
        $('#btn-search').on('click', function() {
            let search = $('#input-search').val();
            if (search !== '' && search.length > 0) {
                $.ajax({
                    type: "GET",
                    url: './transactions?query=' + search,
                    success: function (data) {
                        $('#myTable').bootstrapTable('load', data);
                    },
                    error: function (e) {
                        console.log("ERROR : ", e);
                    }
                });
            }
            return false;
        });
    });
</script>
