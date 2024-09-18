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
        font-family: "Poppins", sans-serif;
        font-weight: 300;
    }
    /*.height {*/
    /*    height: 40vh;*/
    /*}*/
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
    .fixed-table-pagination > .pagination .page-jump-to input {
        width: 85px !important;
    }
</style>

<div class="container">
    <h1 class="text-center">Bootstrap Datatable</h1>
    <!-- Search -->
    <div class="row height d-flex justify-content-center align-items-center">
        <div class="col-md-8">
            <div class="search">
                <i class="fa fa-search"></i>
                <input id="input-search" type="search" class="form-control" placeholder="">
                <button id="btn-search" class="btn btn-primary">Search</button>
            </div>
        </div>
    </div>

    <!-- Loading Spinner -->
    <div class="text-center mt-4 mb-4">
        <div id="loading" class="spinner-border" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>

    <!-- Table -->
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

<script>
    function drawTable(data) {
        $('#myTable').bootstrapTable({
            data: data,
            pageSize: 15,
            pagination: true,
            showJumpTo: true,
            paginationLoop: false,
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
        search(true); // init table when reload page
    });

    // search(true, keySearch = '')
    function search(init, keySearch = '') {
        $.ajax({
            type: "GET",
            url: './transactions?query=' + keySearch,
            beforeSend: function() {
                showLoading();
            },
            success: function (data) {
                if (init) {
                    drawTable(data);
                } else {
                    $('#myTable').bootstrapTable('load', data);
                }
            },
            error: function (e) {
                console.log("ERROR : ", e);
            },
            complete: function () { // finally
                hideLoading();
                $('#btn-search').prop('disabled', false);
            }
        });
    }

    // Search
    $('#btn-search').on('click', function () {
        $(this).prop('disabled', true);
        // let keyword = $('#input-search').val().trim();
        search(false, $('#input-search').val().trim());
        return false;
    });

    function showLoading() {
        $('#loading').show();
    }

    function hideLoading() {
        $('#loading').hide();
    }

    $('#input-search').on("search", function () {
        $('#btn-search').click();
    });
</script>
