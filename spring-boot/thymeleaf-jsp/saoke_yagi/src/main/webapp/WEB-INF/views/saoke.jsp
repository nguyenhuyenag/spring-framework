<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<head>
    <meta charset="UTF-8">
    <title>Sao Kê YaGi</title>
    <link rel="shortcut icon" href="#">
    <!-- JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.21.4/bootstrap-table.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-table@1.23.2/dist/extensions/page-jump-to/bootstrap-table-page-jump-to.min.js"></script>
    <!-- CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-table@1.23.2/dist/bootstrap-table.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-table@1.23.2/dist/extensions/page-jump-to/bootstrap-table-page-jump-to.min.css">
</head>

<style>
    body {
        font-family: "Poppins", sans-serif;
        font-weight: 300;
    }

    .search {
        position: relative;
        box-shadow: 0 0 40px rgba(51, 51, 51, .1);
    }

    #input-search {
        height: 60px;
        width: 500px;
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

    #btn-search {
        position: absolute;
        top: 5px;
        right: 5px;
        height: 50px;
        width: 110px;
        background: blue;
    }

    .btn-load-data {
        height: 58px;
        width: 110px;
        margin-left: 10px;
        background: blue;
    }

    .fixed-table-pagination > .pagination .page-jump-to input {
        width: 85px !important;
    }
</style>

<div class="container">
    <h1 class="text-center">Bootstrap Datatable</h1>
    <!-- Search -->
    <div class="row justify-content-center align-items-center">
        <div class="d-flex">
            <div class="d-inline-block search">
                <i class="fa fa-search"></i>
                <input id="input-search" type="search" class="form-control">
                <button id="btn-search" class="btn btn-primary">Search</button>
            </div>
            <div class="load-data d-inline-block">
                <button class="btn btn-primary btn-load-data">Load Data</button>
            </div>
        </div>
    </div>
    <!-- Loading Spinner -->
    <div class="text-center mt-4 mb-4">
        <div id="loading" class="spinner-border" role="status" style="display: none">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Table -->
    <table id="myTable" class="table table-striped mb-4" style="display: none">
        <thead>
        <tr class="text-center">
            <th>Ngày giao dịch</th>
            <th>Mã giao dịch</th>
            <th>Số tiền</th>
            <th>Nội dung giao dịch</th>
        </tr>
        </thead>
    </table>
</div>

<script>
    function isEmpty(str) {
        return !str || str.trim().length === 0;
    }

    function isNotEmpty(str) {
        return str && str.trim().length > 0;
    }

    function drawTable(data) {
        let table = $('#myTable');
        table.show();
        table.bootstrapTable('destroy'); // Destroy bootstrap table
        table.bootstrapTable({
            data: data,
            pageSize: 15,
            pagination: true,
            showJumpTo: true,
            paginationLoop: false,
            columns: [
                {
                    title: 'Ngày giao dịch',
                    field: 'date',
                    align: 'center',
                    sortable: true
                }, {
                    title: 'Mã giao dịch',
                    field: 'code',
                    align: 'center'
                }, {
                    title: 'Số tiền',
                    field: 'amount',
                    align: 'right',
                }, {
                    title: 'Nội dung giao dịch',
                    field: 'notes',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var keyword = searchValue().toLowerCase();
                        if (isEmpty(keyword)) {
                            return value;
                        }
                        return highlightText(value, keyword);
                    }
                }
            ]
        });
    }

    function highlightText(text, keyword) {
        return text.replace(new RegExp('(' + keyword + ')', 'gi'), '<strong class="text-warning">$1</strong>');
    }

    function search(keySearch = '') {
        $.ajax({
            type: 'GET',
            url: './transactions?query=' + encodeURIComponent(keySearch),
            beforeSend: function () {
                showLoading();
            },
            success: function (data) {
                drawTable(data);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            },
            complete: function () { // finally
                hideLoading();
                $('#btn-search, .btn-load-data').prop('disabled', false);
            }
        });
    }

    const $inputSearch = $('#input-search');

    function searchValue() {
        return $inputSearch.val().trim();
    }

    // Search
    $('#btn-search').on('click', function () {
        const $button = $(this);
        let keyword = searchValue();
        if (isNotEmpty(keyword)) {
            $button.prop('disabled', true);
            search(keyword);
        }
        return false;
    });

    $inputSearch.on('keyup', (event) => {
        const id = event.key || event.which || event.keyCode || 0;
        if ((id === 'Enter' || id === 13) && searchValue() !== '') {
            $('#btn-search').trigger('click');
        }
    });

    function showLoading() {
        $('#loading').show();
    }

    function hideLoading() {
        $('#loading').hide();
    }

    $('.btn-load-data').on('click', function () {
        $(this).prop('disabled', true);
        search(); // Search all
        return false;
    });
</script>
