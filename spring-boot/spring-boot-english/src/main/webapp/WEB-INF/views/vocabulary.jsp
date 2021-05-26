<style>
    .btn {
        width: 150px;
    }
</style>

<div class="container text-center">
    <h1 class="m-4">Vocabulary</h1>
    <div class="search">
        <form class="form-inline mt-2 mt-md-0 float-right" id="search-form">
            <input type="text" id="word" required class="form-control mr-sm-2" placeholder="Search">
            <button id="bth-search" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
    <table class="table table-bordered">
        <thead class="thead-light">
            <tr>
                <th style="width: 10%">No</th>
                <th style="width: 30%">Word</th>
                <th style="width: 30%">Pronounce</th>
                <th style="width: 30%">Translate</th>
            </tr>
        </thead>
        <tbody>
            <tr id="has-result">
                <td><span id="no"></span></td>
                <td><span id="_word"></span></td>
                <td><span id="pronounce"></span></td>
                <td><span id="translate"></span></td>
            </tr>
            <tr id="no-result">
                <td colspan="3"><span class="text-danger">No result</span></td>
            </tr>
        </tbody>
    </table>
    <div class="col">
        <button class="btn btn-primary m-1"><span aria-hidden="true">&larr;</span>&nbsp;Previous</button>
        <button onclick="random();" class="btn btn-primary m-1">Next&nbsp;<span aria-hidden="true">&rarr;</span></button>
        <button class="btn btn-primary">
            <span class="spinner-border spinner-border-sm"></span>Next..
        </button>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#no-result').hide();
        random();
        $("#search-form").submit(function (event) {
            // stop submit the form, we will post it manually
            event.preventDefault();
            search();
        });
    });
    function random() {
        $.ajax({
            type: "GET",
            url: "random-vocab",
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                // console.log("DATA : ", data);
                $('#no').text(data.id);
                $('#_word').text(data.word);
                $('#pronounce').text(data.pronounce);
                $('#translate').text(data.translate);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }
    function search() {
        $("#btn-search").prop("disabled", true);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "search?word=" + $("#word").val(),
            success: function (data) {
                // console.log("DATA : ", data);
                if (StringUtils.isEmpty(data)) {
                    $('#no-result').show();
                    $('#has-result').hide();
                } else {
                    $('#no-result').hide();
                    $('#has-result').show();
                }
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
        $("#btn-search").prop("disabled", false);
    }
</script>